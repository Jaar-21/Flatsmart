package controller;

import java.sql.SQLException;

public interface BacaController<T> {

    String modul();

    T cari(String id) throws SQLException;
}
