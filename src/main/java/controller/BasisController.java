package controller;

/**
 * Induk seluruh controller: menyimpan nama modul (encapsulation) dan
 * menyediakan pemeriksaan id yang dipakai ulang oleh tiap subclass (inheritance).
 */
public abstract class BasisController {

    private final String modul;

    // konstruktor basis
    protected BasisController(String modul) {
        this.modul = modul;
    }

    // ambil nama modul
    public final String modul() {
        return modul;
    }

    // validasi id wajib
    protected String idWajib(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(modul + ": data yang dipilih tidak valid.");
        }
        return id.trim();
    }
}
