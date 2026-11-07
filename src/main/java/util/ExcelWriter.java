package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


public class ExcelWriter {

    public static final int DEFAULT = 0;
    public static final int JUDUL = 1;
    public static final int SUBJUDUL = 2;
    public static final int KEPALA = 3;
    public static final int TEKS = 4;
    public static final int ANGKA = 5;
    public static final int RUPIAH = 6;
    public static final int LABEL = 7;
    public static final int NILAI = 8;
    public static final int TANGGAL = 9;
    public static final int HALUS = 10;

    private static final LocalDate EPOK_EXCEL = LocalDate.of(1899, 12, 30);

    private final List<Lembar> lembar = new ArrayList<>();

    // buat lembar baru
    public Lembar lembarBaru(String nama) {
        Lembar l = new Lembar(nama, lembar.size());
        lembar.add(l);
        return l;
    }

    // simpan berkas excel
    public void simpan(File tujuan) throws IOException {
        try (OutputStream os = new FileOutputStream(tujuan);
             ZipOutputStream zip = new ZipOutputStream(os, StandardCharsets.UTF_8)) {
            tulis(zip, "[Content_Types].xml", contentTypes());
            tulis(zip, "_rels/.rels", rels());
            tulis(zip, "xl/workbook.xml", workbook());
            tulis(zip, "xl/_rels/workbook.xml.rels", workbookRels());
            tulis(zip, "xl/styles.xml", styles());
            for (Lembar l : lembar) {
                tulis(zip, "xl/worksheets/sheet" + (l.index + 1) + ".xml", l.xml());
            }
        }
    }

    // tulis entri zip
    private static void tulis(ZipOutputStream zip, String nama, String isi) throws IOException {
        zip.putNextEntry(new ZipEntry(nama));
        zip.write(isi.getBytes(StandardCharsets.UTF_8));
        zip.closeEntry();
    }

    // susun xml content types
    private String contentTypes() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
          .append("<Types xmlns=\"http://schemas.openxmlformats.org/package/2006/content-types\">")
          .append("<Default Extension=\"rels\" ContentType=\"application/vnd.openxmlformats-package.relationships+xml\"/>")
          .append("<Default Extension=\"xml\" ContentType=\"application/xml\"/>")
          .append("<Override PartName=\"/xl/workbook.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet.main+xml\"/>")
          .append("<Override PartName=\"/xl/styles.xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.styles+xml\"/>");
        for (Lembar l : lembar) {
            sb.append("<Override PartName=\"/xl/worksheets/sheet").append(l.index + 1)
              .append(".xml\" ContentType=\"application/vnd.openxmlformats-officedocument.spreadsheetml.worksheet+xml\"/>");
        }
        return sb.append("</Types>").toString();
    }

    // susun xml rels
    private String rels() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
             + "<Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\">"
             + "<Relationship Id=\"rId1\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/officeDocument\" Target=\"xl/workbook.xml\"/>"
             + "</Relationships>";
    }

    // susun xml workbook
    private String workbook() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
          .append("<workbook xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\" ")
          .append("xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"><sheets>");
        for (Lembar l : lembar) {
            sb.append("<sheet name=\"").append(kabahkan(l.nama))
              .append("\" sheetId=\"").append(l.index + 1)
              .append("\" r:id=\"rId").append(l.index + 1).append("\"/>");
        }
        return sb.append("</sheets></workbook>").toString();
    }

    // susun xml rels workbook
    private String workbookRels() {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
          .append("<Relationships xmlns=\"http://schemas.openxmlformats.org/package/2006/relationships\">");
        for (Lembar l : lembar) {
            sb.append("<Relationship Id=\"rId").append(l.index + 1)
              .append("\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/worksheet\" Target=\"worksheets/sheet")
              .append(l.index + 1).append(".xml\"/>");
        }
        int stylesId = lembar.size() + 1;
        sb.append("<Relationship Id=\"rId").append(stylesId)
          .append("\" Type=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships/styles\" Target=\"styles.xml\"/>");
        return sb.append("</Relationships>").toString();
    }

    // susun xml styles
    private static String styles() {
        return "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"
             + "<styleSheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\">"
             + "<numFmts count=\"2\">"
             + "<numFmt numFmtId=\"164\" formatCode=\"&quot;Rp&quot; #,##0\"/>"
             + "<numFmt numFmtId=\"165\" formatCode=\"dd/mm/yyyy\"/>"
             + "</numFmts>"
             + "<fonts count=\"7\">"
             + "<font><sz val=\"11\"/><name val=\"Calibri\"/></font>"                                  
             + "<font><b/><sz val=\"16\"/><color rgb=\"FF0F1E3C\"/><name val=\"Calibri\"/></font>"      
             + "<font><b/><sz val=\"12\"/><color rgb=\"FF0F1E3C\"/><name val=\"Calibri\"/></font>"      
             + "<font><b/><sz val=\"11\"/><color rgb=\"FFFFFFFF\"/><name val=\"Calibri\"/></font>"      
             + "<font><sz val=\"11\"/><color rgb=\"FF1E293B\"/><name val=\"Calibri\"/></font>"          
             + "<font><sz val=\"10\"/><color rgb=\"FF64748B\"/><name val=\"Calibri\"/></font>"          
             + "<font><b/><sz val=\"13\"/><color rgb=\"FF10284F\"/><name val=\"Calibri\"/></font>"      
             + "</fonts>"
             + "<fills count=\"3\">"
             + "<fill><patternFill patternType=\"none\"/></fill>"
             + "<fill><patternFill patternType=\"gray125\"/></fill>"
             + "<fill><patternFill patternType=\"solid\"><fgColor rgb=\"FF10284F\"/><bgColor indexed=\"64\"/></patternFill></fill>"
             + "</fills>"
             + "<borders count=\"2\">"
             + "<border><left/><right/><top/><bottom/><diagonal/></border>"
             + "<border><left style=\"thin\"><color rgb=\"FFCBD5E1\"/></left><right style=\"thin\"><color rgb=\"FFCBD5E1\"/></right>"
             + "<top style=\"thin\"><color rgb=\"FFCBD5E1\"/></top><bottom style=\"thin\"><color rgb=\"FFCBD5E1\"/></bottom><diagonal/></border>"
             + "</borders>"
             + "<cellStyleXfs count=\"1\"><xf numFmtId=\"0\" fontId=\"0\" fillId=\"0\" borderId=\"0\"/></cellStyleXfs>"
             + "<cellXfs count=\"11\">"
             // urutan gaya: 0 baku, 1 judul, 2 subjek, 3 kepala tabel, 4 teks, 5 angka,
             // 6 rupiah, 7 label, 8 nilai, 9 tanggal, 10 tulisan halus
             + "<xf numFmtId=\"0\" fontId=\"0\" fillId=\"0\" borderId=\"0\" xfId=\"0\"/>"
             + "<xf numFmtId=\"0\" fontId=\"1\" fillId=\"0\" borderId=\"0\" xfId=\"0\" applyFont=\"1\"><alignment vertical=\"center\"/></xf>"
             + "<xf numFmtId=\"0\" fontId=\"2\" fillId=\"0\" borderId=\"0\" xfId=\"0\" applyFont=\"1\"/>"
             + "<xf numFmtId=\"0\" fontId=\"3\" fillId=\"2\" borderId=\"1\" xfId=\"0\" applyFont=\"1\" applyFill=\"1\" applyBorder=\"1\">"
             + "<alignment horizontal=\"center\" vertical=\"center\" wrapText=\"1\"/></xf>"
             + "<xf numFmtId=\"0\" fontId=\"4\" fillId=\"0\" borderId=\"1\" xfId=\"0\" applyFont=\"1\" applyBorder=\"1\">"
             + "<alignment vertical=\"center\"/></xf>"
             + "<xf numFmtId=\"3\" fontId=\"4\" fillId=\"0\" borderId=\"1\" xfId=\"0\" applyNumberFormat=\"1\" applyFont=\"1\" applyBorder=\"1\">"
             + "<alignment horizontal=\"right\" vertical=\"center\"/></xf>"
             + "<xf numFmtId=\"164\" fontId=\"4\" fillId=\"0\" borderId=\"1\" xfId=\"0\" applyNumberFormat=\"1\" applyFont=\"1\" applyBorder=\"1\">"
             + "<alignment horizontal=\"right\" vertical=\"center\"/></xf>"
             + "<xf numFmtId=\"0\" fontId=\"2\" fillId=\"0\" borderId=\"0\" xfId=\"0\" applyFont=\"1\"/>"
             + "<xf numFmtId=\"0\" fontId=\"6\" fillId=\"0\" borderId=\"0\" xfId=\"0\" applyFont=\"1\"/>"
             + "<xf numFmtId=\"165\" fontId=\"4\" fillId=\"0\" borderId=\"1\" xfId=\"0\" applyNumberFormat=\"1\" applyFont=\"1\" applyBorder=\"1\">"
             + "<alignment horizontal=\"center\" vertical=\"center\"/></xf>"
             + "<xf numFmtId=\"0\" fontId=\"5\" fillId=\"0\" borderId=\"0\" xfId=\"0\" applyFont=\"1\"/>"
             + "</cellXfs>"
             + "<cellStyles count=\"1\"><cellStyle name=\"Normal\" xfId=\"0\" builtinId=\"0\"/></cellStyles>"
             + "</styleSheet>";
    }

    public static final class Lembar {
        private final String nama;
        private final int index;
        private final List<Baris> baris = new ArrayList<>();
        private final int[] lebarKolom = new int[26];
        private final List<String> gabungan = new ArrayList<>();

        // konstruktor lembar
        private Lembar(String nama, int index) {
            this.nama = nama;
            this.index = index;
        }

        // jumlah baris
        public int barisJumlah() {
            return baris.size();
        }

        // tambah baris baru
        public Baris baris() {
            Baris b = new Baris(baris.size());
            baris.add(b);
            return b;
        }

        // tambah baris kosong
        public void barisKosong() {
            baris.add(new Baris(baris.size()));
        }

        // atur lebar kolom
        public void lebar(int kolom, int px) {
            if (kolom >= 0 && kolom < lebarKolom.length) {
                lebarKolom[kolom] = px;
            }
        }

        /** Gabungkan rentang sel, koordinat 0-based (baris/kolom). */
        public void gabung(int br1, int bc1, int br2, int bc2) {
            gabungan.add(ref(br1, bc1) + ":" + ref(br2, bc2));
        }

        // susun xml lembar
        private String xml() {
            StringBuilder sb = new StringBuilder();
            sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
              .append("<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\">");
            sb.append("<cols>");
            for (int i = 0; i < lebarKolom.length; i++) {
                if (lebarKolom[i] > 0) {
                    sb.append("<col min=\"").append(i + 1).append("\" max=\"").append(i + 1)
                      .append("\" width=\"").append(lebarKolom[i]).append("\" customWidth=\"1\"/>");
                }
            }
            sb.append("</cols><sheetData>");
            for (Baris b : baris) {
                sb.append(b.xml());
            }
            sb.append("</sheetData>");
            if (!gabungan.isEmpty()) {
                sb.append("<mergeCells count=\"").append(gabungan.size()).append("\">");
                for (String g : gabungan) {
                    sb.append("<mergeCell ref=\"").append(g).append("\"/>");
                }
                sb.append("</mergeCells>");
            }
            return sb.append("</worksheet>").toString();
        }
    }

    public static final class Baris {
        private final int nomorBaris;
        private final List<Sel> sel = new ArrayList<>();
        private int kolom = 0;

        // konstruktor baris
        private Baris(int nomorBaris) {
            this.nomorBaris = nomorBaris;
        }

        // isi sel teks
        public Baris teks(String nilai, int gaya) {
            sel.add(Sel.str(kolom++, nomorBaris, nilai, gaya));
            return this;
        }

        // isi sel angka
        public Baris angka(long nilai, int gaya) {
            sel.add(Sel.num(kolom++, nomorBaris, nilai, gaya));
            return this;
        }

        // isi sel tanggal
        public Baris tanggal(LocalDate nilai, int gaya) {
            sel.add(Sel.num(kolom++, nomorBaris, ChronoUnit.DAYS.between(EPOK_EXCEL, nilai), gaya));
            return this;
        }

        // isi sel kosong
        public Baris kosong(int gaya) {
            sel.add(Sel.str(kolom++, nomorBaris, "", gaya));
            return this;
        }

        // lewati satu kolom
        public Baris lewati() {
            kolom++;
            return this;
        }

        // susun xml baris
        String xml() {
            StringBuilder sb = new StringBuilder();
            sb.append("<row r=\"").append(nomorBaris + 1).append("\">");
            for (Sel s : sel) {
                sb.append(s.xml());
            }
            return sb.append("</row>").toString();
        }
    }

    // isi satu sel spreadsheet
    private record Sel(int kolom, int baris, String nilai, Long angka, boolean numerik, int gaya) {
        // buat sel teks
        static Sel str(int k, int b, String v, int g) { return new Sel(k, b, v, null, false, g); }
        // buat sel angka
        static Sel num(int k, int b, long v, int g)   { return new Sel(k, b, null, v, true, g); }

        // susun xml sel
        String xml() {
            String r = ref(baris, kolom);
            if (numerik) {
                return "<c r=\"" + r + "\" s=\"" + gaya + "\"><v>" + angka + "</v></c>";
            }
            if (nilai == null || nilai.isEmpty()) {
                return "<c r=\"" + r + "\" s=\"" + gaya + "\"/>";
            }
            return "<c r=\"" + r + "\" s=\"" + gaya + "\" t=\"inlineStr\"><is><t xml:space=\"preserve\">"
                    + kabahkan(nilai) + "</t></is></c>";
        }
    }

    // referensi koordinat sel
    private static String ref(int baris, int kolom) {
        return huruf(kolom) + (baris + 1);
    }

    // huruf nama kolom
    private static String huruf(int kolom) {
        StringBuilder sb = new StringBuilder();
        int k = kolom;
        do {
            sb.insert(0, (char) ('A' + k % 26));
            k = k / 26 - 1;
        } while (k >= 0);
        return sb.toString();
    }

    // escape karakter xml
    private static String kabahkan(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;");
    }
}
