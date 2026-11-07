package util;

import model.Pengguna;

public class Sesi {
    private static Pengguna pengguna;

    public static void masuk(Pengguna p) { pengguna = p; }
    public static void keluar()          { pengguna = null; }
    public static Pengguna get()         { return pengguna; }
    public static String username()      { return pengguna == null ? "" : pengguna.getUsername(); }
}
