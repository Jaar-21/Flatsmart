package model;

import util.Format;

public class Pengguna {
    private final String username;
    private final String nama;
    private final String role;      
    // buat objek pengguna
    public Pengguna(String username, String nama, String role) {
        this.username = username;
        this.nama = nama;
        this.role = role;
    }

    // username pengguna
    public String getUsername()   { return username; }
    // nama pengguna
    public String getNama()       { return nama; }
    // nama tampil pengguna
    public String getNamaTampil() { return Format.judul(nama); }

    // cek role kasir
    public boolean isKasir()   { return "kasir".equalsIgnoreCase(role); }
    // cek role manajer
    public boolean isManajer() { return "manajer".equalsIgnoreCase(role); }
    // nama role tampil
    public String getNamaRole() { return isManajer() ? "Manajer" : "Kasir"; }
}