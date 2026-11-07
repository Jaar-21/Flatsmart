package controller;

import java.sql.SQLException;
import model.Pengguna;
import service.AuthService;

/**
 * Controller autentikasi. Password diperiksa di database lewat DAO, hasilnya
 * disimpan di sesi aplikasi.
 */
public class AuthController extends BasisController {

    private final AuthService service = new AuthService();

    // konstruktor autentikasi
    public AuthController() {
        super("Autentikasi");
    }

    // login pengguna
    public Pengguna login(String username, String password) throws SQLException {
        return service.login(username, password);
    }

    // logout pengguna
    public void logout() {
        service.logout();
    }
}
