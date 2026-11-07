package controller;

import java.sql.SQLException;
import java.util.List;

public interface CrudController<T> extends BacaController<T> {

    List<T> semua() throws SQLException;

    void tambah(T entity) throws SQLException;

    void ubah(T entity) throws SQLException;

    void hapus(String id) throws SQLException;
}
