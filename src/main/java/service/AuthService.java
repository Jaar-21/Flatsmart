package service;

import dao.PenggunaDAO;
import java.sql.SQLException;
import model.Pengguna;
import util.Sesi;

public class AuthService {

    private final PenggunaDAO dao = new PenggunaDAO();


    public Pengguna login(String username, String password) throws SQLException {
        if (username == null || username.isBlank() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Username dan password wajib diisi.");
        }

        Pengguna pengguna = dao.autentikasi(username.trim(), password);
        if (pengguna == null) {
            throw new IllegalArgumentException("Username atau password salah.");
        }
        if (!pengguna.isKasir() && !pengguna.isManajer()) {
            throw new IllegalArgumentException(
                    "Role akun tidak dikenali. Hubungi penanggung jawab toko.");
        }

        Sesi.masuk(pengguna);
        return pengguna;
    }

    public void logout() {
        Sesi.keluar();
    }
}
