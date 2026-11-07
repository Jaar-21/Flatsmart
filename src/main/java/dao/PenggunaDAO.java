package dao;

import config.DatabaseConnection;
import model.Pengguna;

import java.sql.ResultSet;
import java.sql.SQLException;


public class PenggunaDAO {

    private static final String SQL_AUTENTIKASI =
            "SELECT username, nama_pengguna, role FROM pengguna "
          + "WHERE username = ? AND password = CAST(? AS BINARY)";


    public Pengguna autentikasi(String username, String password) throws SQLException {
        return DatabaseConnection.query(SQL_AUTENTIKASI, ps -> {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? peta(rs) : null;
            }
        });
    }

    private Pengguna peta(ResultSet rs) throws SQLException {
        return new Pengguna(rs.getString("username"), rs.getString("nama_pengguna"),
                rs.getString("role"));
    }
}
