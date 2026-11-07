package config;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayDeque;
import java.util.Deque;


public final class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/db_flatsmart";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static final int MAKS_KONEKSI = 5;
    private static final int DETIK_VALID = 2;
    private static final long BATAS_TUNGGU_MS = 5000;

    private static final DatabaseConnection POOL = new DatabaseConnection();
    private static final ThreadLocal<Connection> TRANSAKSI = new ThreadLocal<>();

    private final Deque<Connection> siapPakai = new ArrayDeque<>();
    private int koneksiHidup;

    private DatabaseConnection() {
    }

    public static DatabaseConnection instance() {
        return POOL;
    }

    public static void periksaKoneksi() throws SQLException {
        query("SELECT 1", ps -> {
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        });
    }

    public static <T> T transaction(SqlWork<T> work) throws SQLException {
        if (TRANSAKSI.get() != null) {
            return work.run();
        }
        try (Connection c = instance().buka()) {
            c.setAutoCommit(false);
            TRANSAKSI.set(c);
            try {
                T hasil = work.run();
                c.commit();
                return hasil;
            } catch (SQLException | RuntimeException e) {
                c.rollback();
                throw e;
            } finally {
                TRANSAKSI.remove();
                c.setAutoCommit(true);
            }
        }
    }

    public static <T> T query(String sql, PsWork<T> work) throws SQLException {
        if (TRANSAKSI.get() != null) {
            try (PreparedStatement ps = TRANSAKSI.get().prepareStatement(sql)) {
                return work.run(ps);
            }
        }
        try (Connection c = instance().buka();
             PreparedStatement ps = c.prepareStatement(sql)) {
            return work.run(ps);
        }
    }

    public static int update(String sql, Binder bind) throws SQLException {
        return query(sql, ps -> {
            bind.bind(ps);
            return ps.executeUpdate();
        });
    }

    public static int insertAmbilId(String sql, Binder bind) throws SQLException {
        if (TRANSAKSI.get() != null) {
            return sisip(TRANSAKSI.get(), sql, bind);
        }
        try (Connection c = instance().buka()) {
            return sisip(c, sql, bind);
        }
    }

    private static int sisip(Connection c, String sql, Binder bind) throws SQLException {
        try (PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            bind.bind(ps);
            if (ps.executeUpdate() == 0) {
                throw new SQLException("Baris baru tidak berhasil disimpan.");
            }
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (!keys.next()) {
                    throw new SQLException("Database tidak mengembalikan id baru.");
                }
                return keys.getInt(1);
            }
        }
    }


    private synchronized Connection buka() throws SQLException {
        long batasTunggu = System.currentTimeMillis() + BATAS_TUNGGU_MS;
        while (true) {
            while (!siapPakai.isEmpty()) {
                Connection calon = siapPakai.pollFirst();
                if (masihHidup(calon)) {
                    return bungkus(calon);
                }
                koneksiHidup--;
                tutupSenyap(calon);
            }
            if (koneksiHidup < MAKS_KONEKSI) {
                koneksiHidup++;
                try {
                    return bungkus(DriverManager.getConnection(URL, USER, PASSWORD));
                } catch (SQLException e) {
                    koneksiHidup--;
                    throw e;
                }
            }
            long sisa = batasTunggu - System.currentTimeMillis();
            if (sisa <= 0) {
                return DriverManager.getConnection(URL, USER, PASSWORD);
            }
            try {
                wait(sisa);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return DriverManager.getConnection(URL, USER, PASSWORD);
            }
        }
    }

    private synchronized void kembalikan(Connection asli) {
        if (masihHidup(asli)) {
            siapPakai.addLast(asli);
        } else {
            koneksiHidup--;
            tutupSenyap(asli);
        }
        notifyAll();
    }

    private static boolean masihHidup(Connection c) {
        try {
            return !c.isClosed() && c.isValid(DETIK_VALID);
        } catch (SQLException e) {
            return false;
        }
    }

    private static void tutupSenyap(Connection c) {
        try {
            c.close();
        } catch (SQLException diabaikan) {
           
        }
    }


    private Connection bungkus(Connection asli) {
        return (Connection) Proxy.newProxyInstance(DatabaseConnection.class.getClassLoader(),
                new Class<?>[]{Connection.class}, new Peminjam(asli));
    }

    private final class Peminjam implements InvocationHandler {

        private final Connection asli;

        private Peminjam(Connection asli) {
            this.asli = asli;
        }

        @Override
        public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args)
                throws Throwable {
            if ("close".equals(method.getName()) && method.getParameterCount() == 0) {
                kembalikan(asli);
                return null;
            }
            try {
                return method.invoke(asli, args);
            } catch (InvocationTargetException e) {
                throw e.getCause();
            }
        }
    }

    @FunctionalInterface
    public interface SqlWork<T> {
        T run() throws SQLException;
    }

    @FunctionalInterface
    public interface PsWork<T> {
        T run(PreparedStatement ps) throws SQLException;
    }

    @FunctionalInterface
    public interface Binder {
        void bind(PreparedStatement ps) throws SQLException;
    }
}
