package controller;

import java.sql.SQLException;
import model.Pengguna;
import service.AuthService;

public class AuthController extends BasisController {

    private final AuthService service = new AuthService();

    public AuthController() {
        super("Autentikasi");
    }

    public Pengguna login(String username, String password) throws SQLException {
        return service.login(username, password);
    }

    public void logout() {
        service.logout();
    }
}
