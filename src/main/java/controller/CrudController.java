package controller;

import java.sql.SQLException;
import java.util.List;

public interface CrudController<T> extends BacaController<T> {

    // ambil semua data
    List<T> semua() throws SQLException;

    // tambah data
    void tambah(T entity) throws SQLException;

    // ubah data
    void ubah(T entity) throws SQLException;

    // hapus data
    void hapus(String id) throws SQLException;
}
