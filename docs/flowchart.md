# Flowchart — FlatSmart

Seluruh diagram di bawah mengikuti alur kode nyata: `view` → `controller` → `service` → `dao` → MySQL.
Simpan sebagai `.mmd` atau render lewat VS Code / mermaid.live.

## 1. Alur utama aplikasi (login dan pemisahan role)

```mermaid
flowchart TD
  A([Mulai: Main.main]) --> B[DatabaseConnection.periksaKoneksi]
  B -->|gagal| Z[Tampil pesan koneksi<br/>aplikasi berhenti]
  B -->|berhasil| C[Tampil LoginFrame]
  C --> D{Username dan<br/>password diisi?}
  D -->|tidak| C
  D -->|ya| E[AuthController.login]
  E --> F[AuthService -> PenggunaDAO.autentikasi<br/>SELECT dari pengguna]
  F --> G{Baris ditemukan?}
  G -->|tidak| H[Pesan: username atau password salah] --> C
  G -->|ya| I{Kolom role}
  I -->|kasir| J[Sesi.masuk -> KasirFrame]
  I -->|manajer| K[Sesi.masuk -> ManajerFrame]
  I -->|lainnya| L[Pesan: role tidak dikenali] --> C
  J --> M[Pilih menu: penjualan / tagihan / riwayat]
  K --> N[Pilih menu: produk / promosi / analisis]
  M --> O{Klik Keluar?}
  N --> O
  O -->|ya| P[AuthController.logout -> Sesi.keluar] --> C
  O -->|tidak| M
```

## 2. UC-03 Transaksi penjualan produk (kasir)

```mermaid
flowchart TD
  A([Kasir membuka menu Penjualan]) --> B[Input barcode atau pilih saran produk]
  B --> C[PenjualanController.cariItem barcode jumlah]
  C --> D[StokDAO.cariBatchTerkeluar<br/>batch dengan tanggal_exp terdekat]
  D --> E{Batch tersedia dan<br/>jumlah cukup?}
  E -->|tidak| F[Peringatan: stok habis / kurang] --> B
  E -->|ya| G[PromosiDAO.cariAktifByStok]
  G --> H{Promo aktif?}
  H -->|ya| I[ItemKeranjang memakai diskon persen]
  H -->|tidak| J[ItemKeranjang harga normal]
  I --> K[Tambah ke tabel keranjang]
  J --> K
  K --> L{Selesai input?}
  L -->|belum| B
  L -->|ya| M[Pilih metode bayar dan uang diterima]
  M --> N[PenjualanController.proses]
  N --> O[Uang cukup?]
  O -->|tidak| P[Peringatan: uang kurang] --> M
  O -->|ya| Q[DatabaseConnection.transaction]
  Q --> R[INSERT transaksi header]
  R --> S[INSERT transaksi_produk]
  S --> T[INSERT detail_transaksi per item]
  T --> U[UPDATE stok kurangi jumlah]
  U --> V{Semua berhasil?}
  V -->|tidak| W[ROLLBACK semua perubahan] --> X[Pesan gagal database]
  V -->|ya| Y[COMMIT]
  Y --> AA[TransaksiController.buatStruk]
  AA --> AB[PembayaranBerhasilDialog]
  AB --> AC{Cetak struk?}
  AC -->|ya| AD[txtStruk.print]
  AC -->|tidak| AE[Kembali ke layar penjualan]
```

## 3. UC-04 Pembayaran tagihan (kasir)

```mermaid
flowchart TD
  A([Menu Tagihan]) --> B[Isi layanan, provider, nomor dan nama pelanggan, nominal]
  B --> C[Total = nominal + TagihanController.BIAYA_ADMIN]
  C --> D{Validasi form lolos?}
  D -->|tidak| E[Tandai field bermasalah] --> B
  D -->|ya| F[TagihanController.proses]
  F --> G[DatabaseConnection.transaction]
  G --> H[INSERT transaksi header]
  H --> I[INSERT transaksi_tagihan]
  I --> J{Berhasil?}
  J -->|tidak| K[ROLLBACK] --> L[Pesan gagal database]
  J -->|ya| M[COMMIT]
  M --> N[TransaksiController.buatStruk] --> O[PembayaranBerhasilDialog + cetak]
```

## 4. UC-07 CRUD produk (manajer)

```mermaid
flowchart TD
  A([Menu Kelola Produk]) --> B[ProdukController.cari keyword filter]
  B --> C[Tabel produk: barcode, nama, harga, stok, aksi]
  C --> D{Aksi pengguna}
  D -->|Tambah atau Ubah| E[FormProdukDialog]
  E --> F[Pemeriksa: barcode, nama, harga beli, harga jual, tanggal exp]
  F --> G{Validasi input lolos?}
  G -->|tidak| H[Pesan inline di lblStatus] --> E
  G -->|ya| I[Bangun objek model Produk]
  I --> J{Mode}
  J -->|baru| K[ProdukController.tambah]
  K --> L[DatabaseConnection.transaction]
  L --> M[INSERT produk]
  M --> N[INSERT stok batch pertama]
  N --> O{Berhasil?}
  O -->|tidak| P[ROLLBACK + barcode ganda ditolak] --> E
  O -->|ya| Q[COMMIT + pesan sukses]
  J -->|ubah| R[ProdukController.ubah] --> S[UPDATE produk] --> Q
  D -->|Hapus| T[Pesan.konfirmasi]
  T -->|batal| C
  T -->|ya| U[ProdukController.hapus]
  U --> V{Pernah ditransaksikan?}
  V -->|ya| W[Ditolak: produk pernah jadi transaksi] --> C
  V -->|tidak| X[transaction: DELETE stok lalu DELETE produk] --> Y[COMMIT + muat ulang tabel]
  D -->|Batch| Z[ProdukController.cariBatch] --> AA[Dialog daftar stok per batch]
  Q --> C
  Y --> C
```

## 5. UC-08 CRUD promosi (manajer)

```mermaid
flowchart TD
  A([Menu Kelola Promosi]) --> B[PromosiController.cari keyword tanggal aktif]
  B --> C[Tabel promosi: kode, nama, diskon, periode, status]
  C --> D{Aksi}
  D -->|Tambah atau Ubah| E[FormPromosiDialog]
  E --> F[PromosiController.buatKode -> prm-xxx]
  F --> G[Cari produk, lalu PromosiController.cariBatch barcode]
  G --> H{Batch dipilih?}
  H -->|tidak| I[Peringatan pilih batch] --> E
  H -->|ya| J[Validasi nama, diskon 1-100, rentang tanggal]
  J --> K{Lolos?}
  K -->|tidak| L[Pesan peringatan] --> E
  K -->|ya| M[Bangun objek model Promosi]
  M --> N{Mode}
  N -->|baru| O[PromosiController.tambah]
  O --> P[transaction: INSERT promosi + UPDATE stok set promosi_kode]
  N -->|ubah| Q[PromosiController.ubah]
  Q --> R[transaction: UPDATE promosi + lepas batch lama + pasang batch baru]
  P --> S[COMMIT + pesan sukses]
  R --> S
  D -->|Hapus| T[Pesan.konfirmasi]
  T --> U[PromosiController.hapus]
  U --> V[transaction: lepas semua batch lalu DELETE promosi]
  V --> W[COMMIT + muat ulang tabel]
  S --> C
  W --> C
```

## 6. UC-10 dan UC-11 Analisis penjualan serta export

```mermaid
flowchart TD
  A([Menu Analisis Penjualan]) --> B[Pilih tanggal awal dan akhir via date picker]
  B --> C[Klik Tampilkan]
  C --> D[AnalisisController.omzet, unitTerjual, jumlahTransaksi, totalDiskon]
  D --> E[AnalisisController.trenOmzetHarian -> panel grafik bar]
  E --> F[AnalisisController.rincianPerProduk -> tabel rincian]
  F --> G[AnalisisController.prioritasBatch -> kartu prioritas persediaan]
  G --> H{Aksi lanjutan}
  H -->|Klik ganda baris rincian| I[AnalisisController.riwayatProduk]
  I --> J[DetailPenjualanProdukDialog: daftar transaksi produk tsb]
  H -->|Klik Tinjau diskon| K[Lompat ke Kelola Promosi dengan batch terisi]
  H -->|Export Excel| L[Pilih folder dan nama berkas]
  L --> M[LaporanController.ekspor awal akhir file penyusun]
  M --> N[LaporanExportService menyusun 4 sheet]
  N --> O{Penulisan berkas sukses?}
  O -->|tidak| P[Pesan gagal menulis Excel]
  O -->|ya| Q[Pesan sukses: tampilkan path berkas .xlsx]
```
