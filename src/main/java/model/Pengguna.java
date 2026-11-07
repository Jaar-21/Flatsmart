package model;

import util.Format;

public class Pengguna {
    private final String username;
    private final String nama;
    private final String role;      
    public Pengguna(String username, String nama, String role) {
        this.username = username;
        this.nama = nama;
        this.role = role;
    }

    public String getUsername()   { return username; }
    public String getNama()       { return nama; }
    public String getNamaTampil() { return Format.judul(nama); }

    public boolean isKasir()   { return "kasir".equalsIgnoreCase(role); }
    public boolean isManajer() { return "manajer".equalsIgnoreCase(role); }
    public String getNamaRole() { return isManajer() ? "Manajer" : "Kasir"; }
}
