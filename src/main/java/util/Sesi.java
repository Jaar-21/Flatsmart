package util;

import model.Pengguna;

public class Sesi {
    private static Pengguna pengguna;

    // simpan sesi login
    public static void masuk(Pengguna p) { pengguna = p; }
    // hapus sesi login
    public static void keluar()          { pengguna = null; }
    // ambil pengguna aktif
    public static Pengguna get()         { return pengguna; }
    // ambil username aktif
    public static String username()      { return pengguna == null ? "" : pengguna.getUsername(); }
}