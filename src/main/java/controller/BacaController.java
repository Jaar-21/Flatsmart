package controller;

import java.sql.SQLException;

/**
 * Kontrak baca untuk seluruh controller MVC. Setiap modul punya nama tampilan
 * dan mampu menampilkan satu data lewat id utamanya.
 *
 * @param <T> tipe entity yang dikendalikan controller
 */
public interface BacaController<T> {

    // nama modul
    String modul();

    // cari data lewat id
    T cari(String id) throws SQLException;
}
