package controller;

public abstract class BasisController {

    private final String modul;

    protected BasisController(String modul) {
        this.modul = modul;
    }

    public final String modul() {
        return modul;
    }

    protected String idWajib(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(modul + ": data yang dipilih tidak valid.");
        }
        return id.trim();
    }
}
