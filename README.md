<div align="center">


  ```text
                ███████╗██╗      █████╗ ████████╗███████╗███╗   ███╗ █████╗ ██████╗ ████████╗
                ██╔════╝██║     ██╔══██╗╚══██╔══╝██╔════╝████╗ ████║██╔══██╗██╔══██╗╚══██╔══╝
                █████╗  ██║     ███████║   ██║   ███████╗██╔████╔██║███████║██████╔╝   ██║   
                ██╔══╝  ██║     ██╔══██║   ██║   ╚════██║██║╚██╔╝██║██╔══██║██╔══██╗   ██║   
                ██║     ███████╗██║  ██║   ██║   ███████║██║ ╚═╝ ██║██║  ██║██║  ██║   ██║   
                ╚═╝     ╚══════╝╚═╝  ╚═╝   ╚═╝   ╚══════╝╚═╝     ╚═╝╚═╝  ╚═╝╚═╝  ╚═╝   ╚═╝   
  ```

[![Java](https://img.shields.io/badge/Java-17%20LTS-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Swing](https://img.shields.io/badge/GUI-Java%20Swing-5382a1?style=for-the-badge&logo=java&logoColor=white)](https://docs.oracle.com/javase/8/docs/technotes/guides/swing/)
[![NetBeans](https://img.shields.io/badge/IDE-Apache%20NetBeans-1B6AC6?style=for-the-badge&logo=apache&logoColor=white)](https://netbeans.apache.org/)
[![MySQL](https://img.shields.io/badge/Database-MySQL%208.x-4479A1?style=for-the-badge&logo=mysql&logoColor=white)](https://www.mysql.com/)
[![Figma](https://img.shields.io/badge/Design-Figma-F24E1E?style=for-the-badge&logo=figma&logoColor=white)](https://www.figma.com/)
[![GitHub](https://img.shields.io/badge/VCS-GitHub-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/)
[![Maven](https://img.shields.io/badge/Build-Apache%20Maven-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

<br/>

**Aplikasi desktop operasional minimarket berbasis Java Swing & MySQL yang mengolah data transaksi kasir menjadi tren penjualan harian, rekomendasi promosi batch (FIFO), dan analisis efektivitas omzet untuk mendukung keputusan manajer secara presisi.**

<br/>

[🏷️ Nama Program](#nama-program) •
[📝 Deskripsi Singkat](#deskripsi-singkat) •
[🧩 Fitur Utama](#fitur-utama) •
[🧱 Tech Stack](#tech-stack) •
[📐 Perancangan Sistem](#perancangan-sistem) •
[📁 Struktur Package](#struktur-package) •
[🎯 4 Pilar OOP](#penerapan-oop) •
[🚀 Cara Penggunaan](#cara-penggunaan) •
[👥 Kontributor](#kontributor)

---

### 🖥️ Antarmuka Ruang Kerja Sistem

| 🧑‍💼 Ruang Kerja Kasir | 👨‍💼 Ruang Kerja Manajer |
|:---:|:---:|
| <img src="docs/gambar/fitur/home-kasir.png" alt="Ruang Kasir" width="480"/> | <img src="docs/gambar/fitur/home-manajer.png" alt="Ruang Manajer" width="480"/> |
| *Pencarian cepat barcode, Smart FIFO, auto-promo, dan struk thermal* | *Kartu KPI, grafik tren omzet, prioritas batch expired, dan export Excel* |

</div>

---

<a id="nama-program"></a>
## 🏷️ Nama Program

<div align="center">

# 🛒 FlatSmart
### *Sistem Informasi Operasional Minimarket & Decision Support System*

**Solusi Perangkat Lunak Desktop Terintegrasi Berbasis Java SE 17, Java Swing, dan MySQL**  
*Dikembangkan oleh Mahasiswa Program Studi Sistem Informasi • Fakultas Teknik • Universitas Mulawarman (2026)*

</div>

---

<a id="deskripsi-singkat"></a>
## 📝 Deskripsi Singkat

### 💡 Latar Belakang Masalah
Pada operasional minimarket konvensional, transaksi penjualan harian umumnya hanya berhenti sebagai arsip kertas struk atau baris data mentah yang tidak diolah. Akibatnya:
1. **Manajer kesulitan memantau tren**: Tidak diketahui pasti produk mana yang paling mendongkrak omzet vs produk yang mengalami perlambatan (*dead stock*).
2. **Promosi tidak efektif**: Program diskon kerap dipasang acak tanpa melihat usia simpan (*shelf life*) produk.
3. **Kerugian barang kedaluwarsa**: Produk lama tertimbun di rak belakang hingga melewati masa kedaluwarsa karena tidak menerapkan pengeluaran berbasis FIFO (*First In, First Out*).

### ✨ Solusi yang Dihadirkan FlatSmart
**FlatSmart** mentransformasikan seluruh transaksi kasir menjadi **dasbor visual tren penjualan**, **penilaian efektivitas promosi**, serta **rekomendasi cerdas pemotongan diskon batch**. Sistem ini menghubungkan aktivitas operasional kasir di meja depan (*Front Office*) dengan pengambilan keputusan manajerial (*Back Office*) secara terintegrasi.

```text
Masalah Minimarket Konvensional              Solusi Cerdas FlatSmart
┌──────────────────────────────────────┐     ┌──────────────────────────────────────┐
│ • Transaksi menumpuk tanpa analisis │     │ • Visualisasi Tren Omzet Harian      │
│ • Barang expired lolos pengawasan    │ ──▶ │ • Smart FIFO Batch & Auto-Promo      │
│ • Diskon acak & tidak terukur        │     │ • Evaluasi Efektivitas Promo         │
│ • Potensi data korup manual          │     │ • Transaksi SQL Atomik (Commit/Roll) │
└──────────────────────────────────────┘     └──────────────────────────────────────┘
```

### 🌟 6 Manfaat Utama
1. **Keputusan Berbasis Data Riil**: Manajer dapat membaca grafik tren omzet harian dan matriks kinerja produk (`Terlaris`, `Normal`, `Lambat`) tanpa mengira-ngira.
2. **Promosi Tepat Sasaran**: Sistem otomatis menyarankan produk lambat laku dan mendekati masa kedaluwarsa untuk segera diberi diskon cuci gudang.
3. **Efektivitas Promosi Terukur**: Membandingkan langsung nilai omzet, unit terjual, dan total potongan promosi untuk menentukan diskon mana yang menguntungkan.
4. **Meningkatkan Peluang Omzet**: Ketersediaan produk terlaris selalu terpantau lewat indikator stok menipis, mencegah hilangnya potensi transaksi.
5. **Mitigasi Kerugian Produk Expired**: Algoritma Smart FIFO memastikan barang dengan kedaluwarsa terdekat otomatis terpilih di kasir.
6. **Integritas Transaksi Tinggi**: Header transaksi, detail item belanja, dan pengurangan stok dieksekusi dalam **satu transaksi SQL atomik** (Anti-gagal sebagian).

### 🎯 Keterkaitan SDGs
Proyek perancangan FlatSmart mengacu pada 3 pilar SDGs:
* **SDGs 8 (Decent Work and Economic Growth)**: Mendorong pertumbuhan ekonomi inklusif dan efisiensi operasional minimarket melalui pengolahan data penjualan riil untuk penentuan strategi omzet dan efisiensi kerja staf.
* **SDGs 9 (Industry, Innovation, and Infrastructure)**: Membangun infrastruktur digital dan standardisasi sistem informasi minimarket yang terintegrasi, andal, dan siap mendukung inovasi ritel.
* **SDGs 12 (Responsible Consumption and Production)**: Meminimalkan limbah barang terbuang (*product waste*) melalui deteksi dini produk lambat laku dan mendekati kedaluwarsa via algoritma Smart FIFO dan promosi dini.

---

<a id="fitur-utama"></a>
## 🧩 Fitur Utama Program

Aplikasi memisahkan hak akses secara ketat (*Role-Based Access Control*) berdasarkan akun login di database:

```text
                            ┌────────────────────────┐
                            │  🔐 Login Multi-Role   │
                            └───────────┬────────────┘
                                        │
                 ┌──────────────────────┴──────────────────────┐
                 ▼                                             ▼
     ┌───────────────────────┐                     ┌───────────────────────┐
     │   🧑‍💼 RUANG KASIR      │                     │   👨‍💼 RUANG MANAJER    │
     ├───────────────────────┤                     ├───────────────────────┤
     │ 1. Transaksi Penjualan│                     │ 1. CRUD Produk & Batch│
     │ 2. Saran Barcode Auto │                     │ 2. CRUD Diskon Promo  │
     │ 3. Smart FIFO Promo   │                     │ 3. Monitoring Expired │
     │ 4. Cetak Struk 40-Col │                     │ 4. Dasbor KPI & Tren  │
     │ 5. Bayar Tagihan PPOB │                     │ 5. Analisis Produk    │
     │ 6. Riwayat Transaksi  │                     │ 6. Jembatan Promo     │
     │                       │                     │ 7. Ekspor Excel .xlsx │
     └───────────────────────┘                     └───────────────────────┘
```

<br/>

### 🛒 Fitur Ruang Kasir

<details open>
<summary><b>Fitur Ruang Kasir</b></summary>
<br/>

#### 1. Autentikasi dan Pemisahan Role Login
Memverifikasi username dan password pengguna ke tabel `pengguna`. Sesi login tersimpan di `util/Sesi` dan sistem otomatis mengarahkan ke `KasirFrame` atau `ManajerFrame`.

<p align="center">
  <img src="docs/gambar/penggunaan/home-kasir.png" alt="Layar Kasir" width="750"/>
  <br/><em>Ruang kerja kasir siap transaksi setelah berhasil login</em>
</p>

#### 2. Transaksi Cepat dan Keranjang Belanja Otomatis
Kasir dapat mengetik sebagian barcode atau nama produk. Sistem otomatis mencari produk yang stoknya masih tersedia tanpa perlu menghafal kode barcode.

<p align="center">
  <img src="docs/gambar/penggunaan/kasir-keranjang.png" alt="Keranjang Belanja" width="750"/>
  <br/><em>Daftar item belanjaan masuk ke tabel keranjang beserta kalkulasi harga</em>
</p>

#### 3. Smart FIFO Batch dan Potongan Diskon Otomatis
Setiap barang yang ditambahkan ke keranjang otomatis mengambil batch stok dengan tanggal kedaluwarsa terdekat yang masih valid. Jika batch tersebut memiliki promo aktif, sistem langsung memotong harga secara otomatis.

#### 4. Pembayaran, Kembalian, dan Konfirmasi Transaksi
Mendukung pembayaran Tunai, QRIS, dan Kartu Debit. Mengkalkulasi subtotal, potongan diskon, total akhir, uang diterima, dan kembalian secara otomatis. Seluruh proses disimpan dalam 1 Transaksi SQL Atomik (header + detail + pengurangan stok).

<p align="center">
  <img src="docs/gambar/penggunaan/pembayaran.png" alt="Dialog Pembayaran" width="450"/>
  <br/><em>Dialog input pembayaran dan kalkulasi kembalian otomatis</em>
</p>

#### 5. Cetak Struk Kasir 40 Kolom
Menyajikan pratinjau struk berformat monospaced 40 karakter yang kompatibel langsung dengan printer thermal kasir 58mm.

<p align="center">
  <img src="docs/gambar/penggunaan/struk.png" alt="Pratinjau Struk" width="450"/>
  <br/><em>Pratinjau format cetak struk thermal 40 kolom</em>
</p>

#### 6. Pembayaran Tagihan PPOB
Mencatat transaksi pembayaran tagihan eksternal, validasi format ID pelanggan, dan menambahkan biaya admin otomatis Rp2.500 ke dalam transaksi.

<p align="center">
  <img src="docs/gambar/penggunaan/pembayaran-tagihan.png" alt="Pembayaran Tagihan" width="750"/>
  <br/><em>Form pembayaran tagihan pelanggan dengan biaya admin otomatis</em>
</p>

#### 7. Riwayat Transaksi dan Cetak Ulang Struk
Memungkinkan kasir meninjau kembali transaksi, memfilter rentang tanggal atau kata kunci, serta mencetak ulang struk lama jika diminta pembeli.

<p align="center">
  <img src="docs/gambar/penggunaan/lihat-transaksi.png" alt="Riwayat Transaksi" width="750"/>
  <br/><em>Daftar riwayat transaksi kasir dengan filter pencarian dan opsi buka struk</em>
</p>

</details>

---

### 📊 Fitur Ruang Manajer

<details open>
<summary><b>Fitur Ruang Manajer</b></summary>
<br/>

#### 8. Pengelolaan Produk dan Batch Stok
Manajer dapat menambah, mengedit, dan mencari produk. Penambahan produk baru otomatis membuat batch stok perdana beserta tanggal kedaluwarsanya. Dilengkapi proteksi: produk yang pernah ditransaksikan tidak dapat dihapus demi integritas data keuangan.

<p align="center">
  <img src="docs/gambar/penggunaan/home-manajer.png" alt="Kelola Produk" width="750"/>
  <br/><em>Dasbor utama manajer: kelola master produk dan pemantauan stok menipis</em>
</p>

<p align="center">
  <img src="docs/gambar/penggunaan/tambah-produk.png" alt="Tambah Produk" width="450"/>
  <br/><em>Form penambahan produk baru beserta batch stok awal dan tanggal expired</em>
</p>

#### 9. Pengelolaan Promosi Bertarget Batch
Manajer dapat membuat kode promosi (misal: `prm-001`), diskon 1% - 100%, dan mengaitkannya ke batch stok spesifik. Status promosi (`Akan datang`, `Aktif`, `Berakhir`) dihitung dinamis dari tanggal sistem.

<p align="center">
  <img src="docs/gambar/penggunaan/tambah-promosi.png" alt="Tambah Promosi" width="450"/>
  <br/><em>Form pembuatan promosi diskon yang ditargetkan pada batch produk tertentu</em>
</p>

#### 10. Pemantauan Stok Menipis dan Kedaluwarsa
Mengklik baris produk akan membuka rincian nomor batch, kuantitas unit, dan tanggal kedaluwarsa di bawah tabel.

<p align="center">
  <img src="docs/gambar/penggunaan/batch-produk.png" alt="Rincian Batch" width="650"/>
  <br/><em>Panel inspeksi batch stok produk terpilih</em>
</p>

#### 11. Dasbor Analisis KPI dan Grafik Tren Omzet Harian
Menampilkan ringkasan total omzet, unit terjual, jumlah transaksi, dan diskon. Dilengkapi Grafik Batang Tren Omzet Harian yang digambar langsung di panel Swing (`Graphics2D`) dengan garis rata-rata omzet.

<p align="center">
  <img src="docs/gambar/penggunaan/analisis-penjualan.png" alt="Analisis Penjualan" width="750"/>
  <br/><em>Dasbor visual omzet: kartu metrik KPI dan grafik batang tren harian</em>
</p>

#### 12. Rekomendasi Prioritas Promosi
Kartu Prioritas Persediaan mendeteksi batch yang mendekati expired. Klik tombol Tinjau diskon batch di Kelola Promosi untuk langsung berpindah ke menu promo dengan ID batch yang telah terpilih otomatis.

<p align="center">
  <img src="docs/gambar/penggunaan/tinjauan-diskon.png" alt="Tinjauan Diskon" width="450"/>
  <br/><em>Kartu rekomendasi penanganan batch yang mendekati masa kedaluwarsa</em>
</p>

#### 13. Riwayat Detail Transaksi Produk
Klik-ganda pada baris produk di tabel analisis untuk membuka riwayat transaksi lengkap barang tersebut selama rentang periode yang sedang dianalisis.

<p align="center">
  <img src="docs/gambar/penggunaan/detail-produk.png" alt="Detail Transaksi Produk" width="750"/>
  <br/><em>Dialog riwayat detail penjualan per transaksi untuk produk terpilih</em>
</p>

#### 14. Ekspor Laporan Analisis ke Excel 4-Sheet
Menyimpan rekapitulasi data analisis ke berkas Excel resmi (`.xlsx`) yang terbagi dalam 4 Sheet: Ringkasan, Rincian Produk, Tren Harian, dan Detail Transaksi. Generator Excel dibangun murni (`util/ExcelWriter.java`) menggunakan standar Open Packaging Conventions via `java.util.zip` tanpa memerlukan library eksternal berukuran besar seperti Apache POI.

<p align="center">
  <img src="docs/gambar/penggunaan/export-excel.png" alt="Export Excel" width="600"/>
  <br/><em>Notifikasi konfirmasi laporan berhasil diekspor ke berkas Excel resmi</em>
</p>

</details>

---

<a id="tech-stack"></a>
## 🧱 Tech Stack

Sistem FlatSmart dibangun menggunakan tumpukan teknologi modern, efisien, dan andal yang dikelompokkan sesuai ketentuan proyek:

<div align="center">

```text
┌────────────────────────────────────────────────────────────────────────┐
│                     DESAIN, IDE & VERSION CONTROL                      │
│        🎨 Figma (UI/UX)  •  💻 Apache NetBeans  •  🐙 GitHub (VCS)     │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │
┌───────────────────────────────────▼────────────────────────────────────┐
│                       BAHASA & GUI FRAMEWORK                           │
│          ☕ Java 17 LTS   •   🖼️ Java Swing (NetBeans Matisse)          │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │
┌───────────────────────────────────▼────────────────────────────────────┐
│                    DATABASE, POOLING & DRIVERS                         │
│  🗄️ MySQL / Laragon / XAMPP  •  🔌 Connector/J 9.4.0  •  🏊 Pool (5)   │
└───────────────────────────────────┬────────────────────────────────────┘
                                    │
┌───────────────────────────────────▼────────────────────────────────────┐
│                     CUSTOM LIBRARIES & ENGINES                         │
│  📊 Native Graphics2D Chart • 📑 OOXML ExcelWriter • 📅 DatePicker Util│
└────────────────────────────────────────────────────────────────────────┘
```

</div>

<br/>

| Kategori | Teknologi | Versi | Peran dalam FlatSmart | Alasan Pemilihan Teknis |
|---|---|:---:|---|---|
| **Bahasa Pemrograman** | **Java** | 17 LTS | Bahasa utama seluruh lapisan sistem (Model, View, Controller, Service, DAO, Utility). | Mendukung 4 pilar OOP murni, type safety ketat, performa tinggi, dan kompatibilitas jangka panjang. |
| **Integrated Dev Environment** | **Apache NetBeans** | 18+ / 21+ | Lingkungan pengembangan utama (IDE), manajemen proyek Maven, runner `nbactions.xml`, dan debugging visual. | IDE standar resmi kurikulum PBO dengan dukungan native GUI Builder Matisse untuk mendesain berkas `.form` Swing secara visual. |
| **Database** | **MySQL / MariaDB** | 8.x / 10.4 (Laragon / XAMPP) | Basis data relasional `db_flatsmart` (8 tabel ACID, Foreign Key, Cascade). | Standar industri yang andal untuk transaksi ACID (*Atomicity, Consistency, Isolation, Durability*) dan mudah dikelola lewat Laragon / XAMPP. |
| **GUI Framework** | **Java Swing (JFC)** | Bawaan JDK | Kerangka antarmuka pengguna desktop: `JFrame`, `JDialog`, `JTable`, `JTextArea`. | Standar desktop kurikulum PBO, tidak memerlukan instalasi dependensi runtime tambahan, dan responsif. |
| **GUI Builder** | **NetBeans Matisse** | Bawaan NetBeans | Perancang antarmuka visual dua arah (menghasilkan berkas `.form` dan blok Java yang sinkron). | Mempermudah penataan tata letak visual komponen secara cepat, presisi, dan terintegrasi langsung di NetBeans. |
| **Layout Manager** | **AbsoluteLayout** | Snapshot | Pengatur posisi pixel-perfect pada form kartu, breadcrumb, dan dasbor. | Menjaga konsistensi posisi komponen UI agar tidak bergeser saat ukuran panel berubah. |
| **Database Driver** | **MySQL Connector/J** | 9.4.0 | Jembatan komunikasi JDBC antara aplikasi Java dan MySQL server via `DriverManager`. | Driver JDBC resmi dari Oracle/MySQL dengan dukungan protokol koneksi terbaru. |
| **Desain Antarmuka** | **Figma** | Cloud | Perancangan prototipe UI/UX, penentuan palet warna, hirarki visual, dan tipografi. | Memastikan antarmuka kasir dan dasbor manajer memiliki pengalaman pengguna (UX) yang intuitif sebelum dikodekan. |
| **Version Control** | **GitHub / Git** | 2.x | Pelacakan riwayat kode, kolaborasi antar anggota tim, issue tracking, dan backup kode. | Memudahkan kolaborasi pengembangan tim, kontrol versi berkas, dan dokumentasi repositori proyek. |
| **Build Tool** | **Apache Maven** | 4.0.0 | Manajemen dependensi tunggal (`pom.xml`), kompilasi kode, dan runner konfigurasi. | Standar otomatisasi build Java modern; memastikan dependensi terunduh seragam di setiap komputer tim. |
| **Reporting Engine** | **`ExcelWriter` (Custom)** | Internal | Library generator berkas `.xlsx` 4-sheet berbasis OOXML murni memakai `java.util.zip`. | Menghasilkan berkas Excel resmi tanpa membebani ukuran aplikasi dengan library besar seperti Apache POI. |
| **Connection Pooling**| **Custom Pool Engine** | Internal | Mengelola kolam daur ulang 5 koneksi fisik database (`DatabaseConnection.java`). | Mengeliminasi latensi *handshake* koneksi berulang pada kueri intensif kasir. |

---

<a id="perancangan-sistem"></a>
## 📐 Perancangan Sistem

Perancangan sistem FlatSmart mengacu pada dokumen resmi **Laporan Project Akhir Praktikum Sistem Basis Data — Program Studi Sistem Informasi Universitas Mulawarman**.

---

### 1. 📊 Flowchart Sistem

<details open>
<summary><b>Flowchart Sistem</b></summary>
<br/>

Berikut adalah 5 diagram alur kerja sistem FlatSmart:

#### A. Flowchart Menu Login
Menggambarkan proses autentikasi pengguna saat mengakses FlatSmart. Dimulai dengan memeriksa input username dan password. Jika tidak valid, sistem menampilkan pesan bahwa data tidak ditemukan. Jika valid, sistem memeriksa role: akun manajer melakukan sinkronisasi database akun manajer dan menampilkan menu manajer (`ManajerFrame`), sedangkan jika akun adalah kasir, sistem melakukan sinkronisasi database akun kasir dan menampilkan menu kasir (`KasirFrame`).

<p align="center">
  <img src="docs/gambar/perancangan/flatsmart-menu-login.png" alt="Flowchart Menu Login" width="850"/>
  <br/><em>Alur autentikasi dan validasi role akun pengguna</em>
</p>

---

#### B. Flowchart Menu Kasir
Menggambarkan fitur yang dapat digunakan pada bagian kasir:
* **Pembayaran Produk**: Menampilkan transaksi, kasir memasukkan barcode produk, sistem memvalidasi produk dan stok, kasir memasukkan jumlah barang. Sistem menghitung total pembayaran beserta promo yang berlaku. Kasir memilih metode pembayaran dan memasukkan uang diterima. Jika uang cukup, sistem menghitung kembalian, menampilkan struk belanja, dan menyimpan riwayat transaksi.
* **Pembayaran Tagihan**: Kasir memasukkan jenis tagihan, sistem memvalidasi, kasir memasukkan nomor pelanggan, nominal, dan nama pelanggan. Sistem menghitung total tagihan beserta biaya admin Rp2.500, memproses pembayaran, menampilkan struk, dan menyimpan riwayat transaksi.
* **Riwayat Transaksi**: Kasir dapat melihat daftar riwayat transaksi yang telah dilakukan.
* **Keluar**: Mengarahkan kasir kembali ke menu login.

<p align="center">
  <img src="docs/gambar/perancangan/flatsmart-menu-kasir.png" alt="Flowchart Menu Kasir" width="850"/>
  <br/><em>Alur operasional kasir untuk penjualan produk dan pembayaran tagihan</em>
</p>

---

#### C. Flowchart Menu Manajer
Menggambarkan fitur yang dapat digunakan oleh manajer setelah berhasil login:
* **Kelola Produk dan Kelola Promosi**: Mengarahkan ke menu pengelolaan sesuai pilihan.
* **Analisis Penjualan**: Manajer diminta memasukkan tanggal awal dan tanggal akhir untuk menentukan periode analisis. Sistem memvalidasi tanggal. Jika valid, sistem menghitung total omzet, jumlah produk terjual, dan melakukan klasifikasi produk (`Terlaris`, `Normal`, `Lambat`), lalu menampilkan rincian hasil analisis penjualan.
* **Keluar**: Sistem meminta konfirmasi sebelum kembali ke menu login.

<p align="center">
  <img src="docs/gambar/perancangan/flatsmart-menu-manajer.png" alt="Flowchart Menu Manajer" width="850"/>
  <br/><em>Alur navigasi manajer dan proses analisis penjualan</em>
</p>

---

#### D. Flowchart Menu Kelola Produk
Menggambarkan proses pemilihan fitur pengelolaan data produk dengan lima pilihan:
* **Menampilkan Produk**: Menampilkan daftar produk yang tersedia.
* **Menambah Produk**: Manajer memasukkan barcode, nama produk, harga beli, harga jual, stok, dan tanggal kedaluwarsa. Sistem menyimpan ke database dan menampilkan informasi berhasil.
* **Memperbarui Produk**: Sistem meminta barcode produk, memvalidasi, menampilkan detail, lalu manajer memasukkan harga beli dan harga jual yang baru.
* **Menghapus Produk**: Sistem meminta barcode, memvalidasi, menampilkan detail, dan meminta konfirmasi sebelum menghapus data (otomatis ditolak bila produk pernah ditransaksikan).
* **Keluar**: Kembali ke menu manajer.

<p align="center">
  <img src="docs/gambar/perancangan/flatsmart-menu-kelola-produk.png" alt="Flowchart Kelola Produk" width="850"/>
  <br/><em>Alur pengelolaan master produk dan batch persediaan</em>
</p>

---

#### E. Flowchart Menu Kelola Promosi
Menggambarkan pilihan menu pengelolaan promosi produk:
* **Menampilkan Promosi**: Menampilkan daftar promosi yang tersedia.
* **Menambah Promosi**: Manajer memasukkan nama promosi dan barcode produk. Sistem memeriksa produk. Jika valid, manajer memasukkan persentase diskon serta tanggal mulai dan tanggal selesai promosi. Sistem membuat kode promosi otomatis (`prm-xxx`) lalu menyimpan ke database.
* **Menghapus Promosi**: Menampilkan daftar promosi, meminta kode promosi yang akan dihapus, validasi kode, konfirmasi, lalu menghapus data promosi dari database.
* **Keluar**: Kembali ke menu manajer.

<p align="center">
  <img src="docs/gambar/perancangan/flatsmart-menu-kelola-promosi.png" alt="Flowchart Kelola Promosi" width="850"/>
  <br/><em>Alur pembuatan dan penghapusan promosi diskon bertarget</em>
</p>

</details>

---

### 2. 👤 Use Case Diagram

<details open>
<summary><b>Use Case Diagram</b></summary>
<br/>

Menggambarkan peran antara kasir dan manajer dalam menggunakan sistem FlatSmart:

<p align="center">
  <img src="docs/gambar/perancangan/use-case.png" alt="Use Case Diagram" width="850"/>
  <br/><em>Diagram alur use case interaksi aktor Kasir dan Manajer</em>
</p>

#### Penjelasan Peran Aktor Use Case
* **Kasir**:
  * Dapat login untuk masuk ke dalam sistem lalu melihat menu kasir yang dapat melakukan pembayaran produk dan pembayaran tagihan.
  * Pada pembayaran produk, kasir mengelola belanja dengan menambah, mengubah, atau menghapus produk yang akan dibeli di keranjang.
  * Kasir memproses pembelian dengan menghitung total bayar, melakukan proses pembayaran berdasarkan metode yang dipilih, menghitung kembalian, menyimpan riwayat transaksi/pembayaran, serta menampilkan dan mencetak struk sebagai bukti transaksi.
  * Pada pembayaran tagihan, kasir memproses pembayaran tagihan setiap pelanggan sesuai dengan layanan yang tersedia: listrik, air, pulsa, dan BPJS.
  * Kasir dapat melihat dan menyimpan riwayat transaksi yang telah dilakukan.
* **Manajer**:
  * Dapat login untuk masuk ke dalam sistem dan mengakses menu manajer.
  * Mengelola produk dengan melihat, menambah, mengedit, dan menghapus produk.
  * Mengelola promosi dengan melihat promosi yang tersedia, membuat promosi baru sesuai kebutuhan minimarket, dan menghapus promosi.
  * Melakukan analisis penjualan untuk mendapatkan informasi pendukung keputusan: menghitung jumlah produk terjual, menghitung total omzet, melihat rincian produk, dan mengklasifikasikan produk berdasarkan hasil penjualan.

</details>

---

### 3. 🗄️ Entity Relationship Diagram

<details open>
<summary><b>Entity Relationship Diagram</b></summary>
<br/>

Perancangan basis data `db_flatsmart` dijelaskan ke dalam 2 model terpisah: **ERD Logical** dan **ERD Relational**.

#### A. ERD Logical
ERD Logical berfokus pada struktur konseptual data bisnis minimarket, atribut penting tiap entitas, dan hubungan antar entitas sebelum diimplementasikan ke dalam tabel fisik database:
* **Entitas Pengguna**: Menyimpan informasi pengguna yang mengakses sistem (`username`, `password`, `nama_pengguna`, `role`).
* **Entitas Produk**: Menyimpan data barang dagangan (`barcode`, `nama`, `harga_beli`, `harga_jual`, `stok`).
* **Entitas Stok**: Menyimpan data kuantitas persediaan dan tanggal kedaluwarsa (`id_stok`, `stok`, `tanggal_exp`).
* **Entitas Promosi**: Menyimpan data aturan diskon dan periode masa aktif promosi (`kode_promosi`, `nama`, `diskon`, `tanggal_mulai`, `tanggal_selesai`).
* **Entitas Transaksi**: Entitas *supertype* yang mencatat transaksi utama (`id_transaksi`, `metode_pembayaran`, `total_bayar`, `uang_diterima`, `kembalian`, `tanggal_transaksi`).
* **Entitas Transaksi Produk**: Entitas *subtype* khusus transaksi pembelian produk minimarket (`id_transaksi`, `jumlah_item`).
* **Entitas Transaksi Tagihan**: Entitas *subtype* khusus transaksi pembayaran tagihan (`id_transaksi`, `jenis_tagihan`, `no_pelanggan`, `nama_pelanggan`, `nominal_tagihan`, `provider`).
* **Entitas Detail Transaksi**: Menyimpan baris rincian produk yang dibeli pada setiap transaksi (`id_detail_transaksi`, `jumlah`, `harga_satuan`, `subtotal`).

<p align="center">
  <img src="docs/gambar/perancangan/logical.png" alt="ERD Logical" width="850"/>
  <br/><em>Model konseptual basis data FlatSmart (ERD Logical)</em>
</p>

---

#### B. ERD Relational
ERD Relational memetakan tabel fisik pada MySQL lengkap dengan kunci utama (*Primary Key*), kunci tamu (*Foreign Key*), kardinalitas relasi, dan tipe data:

<p align="center">
  <img src="docs/gambar/perancangan/relational.png" alt="ERD Relational" width="850"/>
  <br/><em>Model fisik relasional basis data db_flatsmart (ERD Relational)</em>
</p>

##### Tabel Pembagian Relasi dan Kardinalitas
| No | Pasangan Relasi Entitas | Kardinalitas | Penjelasan Relasi |
|:---:|---|:---:|---|
| 1 | **Pengguna & Transaksi** | **One to many (1:N)** | Satu pengguna dapat menangani banyak transaksi, dan setiap transaksi dilakukan oleh satu pengguna saja (kasir yang menangani). |
| 2 | **Pengguna & Produk** | **One to many (1:N)** | Satu pengguna dapat menangani banyak produk, dan setiap produk dihubungkan dengan pengguna yang mencatatnya. |
| 3 | **Pengguna & Promosi** | **One to many (1:N)** | Satu pengguna (manajer) dapat mengelola banyak promosi, dan setiap promosi dikaitkan dengan satu pengguna. |
| 4 | **Transaksi & Transaksi Produk** | **One to one (1:1)** | Hubungan superclass-subclass: satu data transaksi memiliki satu data transaksi produk. |
| 5 | **Transaksi & Transaksi Tagihan** | **One to one (1:1)** | Hubungan superclass-subclass: satu data transaksi memiliki satu data transaksi tagihan. |
| 6 | **Transaksi Produk & Detail Transaksi** | **One to many (1:N)** | Satu transaksi produk memiliki banyak rincian item belanja. |
| 7 | **Produk & Stok** | **One to many (1:N)** | Satu produk memiliki banyak persediaan batch stok dengan tanggal kedaluwarsa masing-masing. |
| 8 | **Promosi & Stok** | **One to many (1:N)** | Satu promosi dapat diterapkan pada banyak batch stok barang. |
| 9 | **Stok & Detail Transaksi** | **One to many (1:N)** | Satu batch stok dapat dirujuk pada banyak rincian transaksi penjualan. |

##### 9 Aturan ERDish Resmi
1. **ERDish Pengguna dengan Transaksi**:  
   * Setiap pengguna dapat melayani satu atau banyak transaksi.  
   * Setiap transaksi harus dilayani oleh satu dan hanya satu pengguna.
2. **ERDish Pengguna dengan Produk**:  
   * Setiap pengguna dapat mengelola satu atau banyak produk.  
   * Setiap produk harus dikelola satu dan hanya satu pengguna.
3. **ERDish Pengguna dengan Promosi**:  
   * Setiap pengguna dapat membuat satu atau banyak promosi.  
   * Setiap promosi harus dibuat oleh satu dan hanya satu pengguna.
4. **ERDish Transaksi dengan Transaksi Produk**:  
   * Setiap transaksi dapat memiliki satu dan hanya satu transaksi produk.  
   * Setiap transaksi produk harus berasal dari satu dan hanya satu transaksi.
5. **ERDish Transaksi dengan Transaksi Tagihan**:  
   * Setiap transaksi dapat memiliki satu dan hanya satu transaksi tagihan.  
   * Setiap transaksi tagihan harus berasal dari satu dan hanya satu transaksi.
6. **ERDish Transaksi Produk dengan Detail Transaksi**:  
   * Setiap transaksi produk harus memiliki satu atau banyak detail transaksi.  
   * Setiap detail transaksi harus dimiliki oleh satu dan hanya satu transaksi produk.
7. **ERDish Produk dengan Stok**:  
   * Setiap produk dapat memiliki satu atau banyak stok.  
   * Setiap stok dapat dimiliki satu dan hanya satu produk.
8. **ERDish Promosi dengan Stok**:  
   * Setiap promosi dapat menerapkan satu atau banyak stok.  
   * Setiap stok harus diterapkan satu dan hanya satu promosi.
9. **ERDish Stok dengan Detail Transaksi**:  
   * Setiap stok harus memiliki satu atau banyak detail transaksi.  
   * Setiap detail transaksi harus dimiliki oleh satu dan hanya satu stok.

</details>

---

<a id="struktur-package"></a>
## 📁 Struktur Folder dan Package

```text
flatsmart/
├── pom.xml                         # Konfigurasi Maven (Java 17, Driver MySQL 9.4.0)
├── nbactions.xml                   # Runner Script NetBeans (exec-maven-plugin)
├── db_flatsmart.sql                # Skema DDL & Data Dummy Lengkap (8 Tabel)
├── lib/                            # Pustaka lokal (AbsoluteLayout SNAPSHOT)
├── docs/                           # Dokumentasi Arsitektur, Flowchart, Use-Case & Gambar
│   ├── arsitektur.md
│   ├── flowchart.md
│   ├── use-case.md
│   └── gambar/                     # Aset Gambar Dokumentasi & Tangkapan Layar
└── src/main/
    ├── java/
    │   ├── com/mycompany/flatsmart/
    │   │   └── Flatsmart.java      # Main Entry Point (Pemeriksaan DB & Inisialisasi GUI)
    │   ├── view/                   # [VIEW] 14 Layar Swing Form (.java & .form)
    │   ├── controller/             # [CONTROLLER] 11 Controller & Kontrak Interface
    │   ├── service/                # [SERVICE] 8 Business Logic Services & Atomic Handler
    │   ├── dao/                    # [DAO] 6 Data Access Objects (Native SQL Builder)
    │   ├── model/                  # [MODEL] 10 POJO / Entity DTO
    │   ├── config/                 # [CONFIG] DatabaseConnection (Singleton Pool Engine)
    │   └── util/                   # [UTIL] ExcelWriter, Format, Regex, DatePicker
    └── resources/
        └── icons/                  # 24 Aset Ikon Antarmuka High-Resolution (.png)
```

<div align="center">

| Package | Jumlah Kelas | Tanggung Jawab Utama | Prinsip Pemisahan Tugas |
|---|:---:|---|---|
| `view` | 14 (+14 `.form`) | Menampilkan komponen visual, menangkap interaksi pengguna, merender tabel dan grafik. | **Bebas dari sintaks SQL dan JDBC.** |
| `controller` | 11 | Menjadi jembatan antara View dan Service; memvalidasi parameter dasar. | Menegakkan kontrak antarmuka CRUD seragam. |
| `service` | 8 | Mengatur logika bisnis minimarket: FIFO, diskon bertarget, dan pembungkusan transaksi. | Menjamin atomisitas commit/rollback multi-tabel. |
| `dao` | 6 | Menulis klausa kueri SQL dan memetakan `ResultSet` JDBC menjadi objek Model. | **Satu-satunya pemilik kalimat SQL.** |
| `model` | 10 | Representasi entitas data dengan atribut `private` dan *getter/setter*. | Mengamankan integritas nilai data (*State Isolation*). |
| `config` | 1 | Mengelola kolam koneksi (*Connection Pool*), siklus hidup koneksi, dan thread safety. | Menerapkan Singleton Pattern anti-kebocoran koneksi. |
| `util` | 8 | Kelas bantu: generator Excel `.xlsx`, pemformat Rupiah, validator regex, kalender popup. | Modular dan dapat digunakan ulang di seluruh layar. |

</div>

---

<a id="penerapan-oop"></a>
## 🎯 Penjelasan Penerapan 4 Pilar OOP

Proyek FlatSmart dibangun dengan menerapkan **4 Pilar Utama Pemrograman Berorientasi Objek (OOP)** secara menyeluruh untuk menjamin keterbacaan, keamanan data, dan kemudahan pemeliharaan:

<br/>

### 1. 🛡️ Encapsulation — Enkapsulasi
Semua atribut dalam kelas model dan konfigurasi diatur dengan hak akses `private`. Akses dan manipulasi nilai hanya dapat dilakukan melalui metode *getter* dan *setter*. Logika perhitungan internal dibungkus rapat di dalam kelasnya tanpa memaparkan data mentah.

Contoh implementasi enkapsulasi pada kelas Model:
```java
public class Promosi {
    private String kodePromosi;
    private String namaPromosi;
    private LocalDate tglMulai;
    private LocalDate tglSelesai;

    public String getStatus() {
        LocalDate now = LocalDate.now();
        if (now.isBefore(tglMulai))  return "Akan datang";
        if (now.isAfter(tglSelesai)) return "Berakhir";
        return "Aktif";
    }
}
```
* **Penjelasan & Kegunaan**: Status promosi tidak disimpan sebagai nilai string statis di database, melainkan dihitung secara dinamis melalui metode `getStatus()`. Atribut `tglMulai` dan `tglSelesai` dienkapsulasi rapat sehingga objek View tidak dapat mengubah tanggal secara langsung. Kredensial JDBC pada kelas `DatabaseConnection` juga dienkapsulasi dengan modifier `private` agar alamat host, username, dan password database tidak bocor keluar kelas.

---

### 2. 🧬 Inheritance — Pewarisan
Mewariskan atribut dan metode umum dari superclass ke subclass untuk mencegah duplikasi kode (*Don't Repeat Yourself*).

Contoh implementasi pewarisan pada Controller:
```java
public abstract class BasisController {
    private final String modul;

    protected BasisController(String modul) { this.modul = modul; }
    public final String modul() { return modul; }

    protected String idWajib(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException(modul + ": data yang dipilih tidak valid.");
        }
        return id.trim();
    }
}

public class ProdukController extends BasisController implements CrudController<Produk> {
    private final ProdukService service = new ProdukService();

    public ProdukController() {
        super("Produk");
    }

    @Override
    public void hapus(String barcode) throws SQLException {
        service.hapus(idWajib(barcode));
    }
}
```
* **Penjelasan & Kegunaan**: Kelas `ProdukController` mewarisi `BasisController`. Dengan memanggil `super("Produk")`, subclass otomatis mewarisi identitas nama modul serta metode validasi `idWajib()`. Jika format validasi ID perlu diubah di kemudian hari, pengembang cukup memperbarui logika di kelas induk `BasisController` dan seluruh controller turunan otomatis terbarui.

---

### 3. 🎭 Polymorphism — Polimorfisme
Kemampuan suatu objek atau metode untuk memiliki banyak bentuk melalui **Interface Polymorphism**, **Method Overriding**, dan **Functional Interface (Lambdas)**.

Contoh implementasi polimorfisme interface dan functional callback:
```java
private final CrudController<Produk> controller = new ProdukController();

@FunctionalInterface
public interface PsWork<T> { T run(PreparedStatement ps) throws SQLException; }

return DatabaseConnection.query(SQL_CARI_BARCODE, ps -> {
    ps.setString(1, barcode);
    try (ResultSet rs = ps.executeQuery()) {
        return rs.next() ? peta(rs) : null;
    }
});
```
* **Penjelasan & Kegunaan**: Form antarmuka memanggil kontrak interface `CrudController<Produk>`, bukan kelas konkretnya secara langsung. Selain itu, metode `DatabaseConnection.query()` memanfaatkan functional interface `PsWork<T>` sehingga berbagai query SQL dari berbagai DAO dapat dieksekusi dengan bentuk pemanggilan seragam menggunakan ekspresi lambda.

---

### 4. 🧩 Abstraction — Abstraksi
Menyembunyikan detail implementasi teknis yang rumit dan hanya memperlihatkan antarmuka esensial kepada pengguna kelas.

Contoh kontrak abstraksi dan transaksi atomik:
```java
public interface CrudController<T> extends BacaController<T> {
    List<T> semua() throws SQLException;
    void tambah(T entity) throws SQLException;
    void ubah(T entity) throws SQLException;
    void hapus(String id) throws SQLException;
}

@FunctionalInterface public interface SqlWork<T> { T run() throws SQLException; }

public static <T> T transaction(SqlWork<T> work) throws SQLException {
    Connection c = ambilKoneksiTransaksi();
    boolean komit = false;
    try {
        T res = work.run();
        c.commit();
        komit = true;
        return res;
    } finally {
        if (!komit) c.rollback();
    }
}
```
* **Penjelasan & Kegunaan**: Lapisan View sama sekali tidak mengetahui sintaks query SQL, driver JDBC, maupun proses buka-tutup koneksi. View cukup memanggil antarmuka tingkat tinggi seperti `PenjualanController.proses()`. Seluruh kerumitan `setAutoCommit(false)`, `commit()`, dan `rollback()` disembunyikan di dalam lapisan abstraksi `DatabaseConnection.transaction()`.

---

<a id="cara-penggunaan"></a>
## 🚀 Cara Penggunaan Program

### 1. Persiapan Lingkungan dan Instalasi

| Langkah | Instruksi Instalasi | Tampilan / Screenshot |
|:---:|---|:---:|
| **1** | Nyalakan service **MySQL & Apache** melalui **Laragon** atau XAMPP pada port default `3306`. | <img src="docs/gambar/penggunaan/laragon.png" alt="Laragon Server" width="280"/> |
| **2** | Buat database `db_flatsmart` dan import berkas `db_flatsmart.sql` lewat phpMyAdmin atau terminal. | <img src="docs/gambar/penggunaan/import-database.png" alt="Import Database" width="280"/> |
| **3** | Buka project FlatSmart di **Apache NetBeans** (`File > Open Project`), tunggu Maven memuat dependensi otomatis. | <img src="docs/gambar/penggunaan/open-project.png" alt="Buka Project NetBeans" width="280"/> |
| **4** | Klik kanan pada project di NetBeans ➡️ pilih **Run** (atau tekan **F6**). Konfigurasi `nbactions.xml` akan mengompilasi dan membuka `LoginFrame`. | <img src="docs/gambar/penggunaan/run.png" alt="Run NetBeans" width="280"/> |
| **5** | Jika koneksi database belum menyala, sistem akan menampilkan dialog peringatan koneksi sebelum keluar. | <img src="docs/gambar/penggunaan/login-error.png" alt="Error Koneksi" width="280"/> |

---

### 2. Akun Demo Bawaan Database

Sistem telah menyediakan akun demo siap pakai yang tersimpan di tabel `pengguna`:

| Hak Akses | Username | Password | Nama Pengguna | Akses Menu |
|:---:|:---:|:---:|---|---|
| **Kasir** | `ksr01` | *tercatat di SQL* | Andi Pratama | Penjualan, Tagihan PPOB, Riwayat Transaksi |
| **Kasir** | `ksr02` | *tercatat di SQL* | Dina Marlina | Penjualan, Tagihan PPOB, Riwayat Transaksi |
| **Kasir** | `ksr03` | *tercatat di SQL* | Fajar Nugroho | Penjualan, Tagihan PPOB, Riwayat Transaksi |
| **Manajer** | `mgr01` | *tercatat di SQL* | Rina Wulandari | Kelola Produk, Promosi Diskon, Dasbor Analisis, Export Excel |

---

### 3. Panduan Alur Kerja Kasir

| No | Tahapan Transaksi | Penjelasan Alur | Screenshot Antarmuka |
|:---:|---|---|:---:|
| **1** | **Login Akun Kasir** | Masukkan username `ksr01` dan password. Sistem membuka ruang kerja kasir. | <img src="docs/gambar/penggunaan/home-kasir.png" alt="Home Kasir" width="280"/> |
| **2** | **Input Keranjang Belanja** | Scan barcode atau ketik nama produk. Batch kedaluwarsa terdekat & promo otomatis terpasang. | <img src="docs/gambar/penggunaan/kasir-keranjang.png" alt="Keranjang" width="280"/> |
| **3** | **Proses Pembayaran & Konfirmasi Transaksi** | Pilih metode bayar (Tunai/QRIS/Debit), input uang diterima, dan konfirmasi. Header, detail belanja, dan pengurangan stok tersimpan atomik. Struk 40-kolom siap dicetak. | <img src="docs/gambar/penggunaan/pembayaran.png" alt="Pembayaran" width="240"/><br/><img src="docs/gambar/penggunaan/struk.png" alt="Struk Kasir" width="240"/><br/><img src="docs/gambar/penggunaan/cetak-struk.png" alt="Cetak Struk" width="240"/> |
| **4** | **Pembayaran Tagihan PPOB** | Buka menu Tagihan, input ID pelanggan dan nominal. Biaya admin Rp2.500 otomatis ditambahkan. | <img src="docs/gambar/penggunaan/pembayaran-tagihan.png" alt="Tagihan" width="280"/> |
| **5** | **Pencarian Riwayat Transaksi** | Buka menu Riwayat untuk melihat transaksi kasir lampau atau mencetak ulang struk lama. | <img src="docs/gambar/penggunaan/lihat-transaksi.png" alt="Riwayat" width="280"/> |

---

### 4. Panduan Alur Kerja Manajer

| No | Tahapan Manajerial | Penjelasan Alur | Screenshot Antarmuka |
|:---:|---|---|:---:|
| **1** | **Login Akun Manajer** | Masukkan username `mgr01`. Sistem membuka `ManajerFrame` pada panel Kelola Produk. | <img src="docs/gambar/penggunaan/home-manajer.png" alt="Produk" width="280"/> |
| **2** | **Tambah Produk Baru** | Isi barcode, nama, harga beli, harga jual, stok awal, dan tanggal expired batch pertama. | <img src="docs/gambar/penggunaan/tambah-produk.png" alt="Form Tambah Produk" width="280"/> |
| **3** | **Perbarui Data Produk** | Ubah data master harga beli dan harga jual produk yang sudah tersimpan di database. | <img src="docs/gambar/penggunaan/update-produk.png" alt="Update Produk" width="280"/> |
| **4** | **Hapus Produk** | Penghapusan data master produk. Sistem otomatis menolak bila produk pernah ditransaksikan. | <img src="docs/gambar/penggunaan/hapus-produk.png" alt="Hapus Produk" width="280"/> |
| **5** | **Inspeksi Batch Stok** | Klik baris produk untuk menampilkan rincian ID batch, kuantitas, dan tanggal expired di bawah tabel. | <img src="docs/gambar/penggunaan/batch-produk.png" alt="Batch" width="280"/> |
| **6** | **Kelola Promosi Diskon** | Masukkan nama promo, tentukan diskon persen, periode tanggal, dan kaitkan ke batch target. | <img src="docs/gambar/penggunaan/tambah-promosi.png" alt="Promosi" width="280"/> |
| **7** | **Analisis Penjualan & KPI** | Tentukan rentang tanggal analisis, klik Tampilkan untuk melihat KPI, omzet, dan grafik batang. | <img src="docs/gambar/penggunaan/analisis-penjualan.png" alt="Analisis" width="280"/> |
| **8** | **Tinjau Rekomendasi Diskon**| Klik tombol tinjau pada batch yang mendekati expired untuk langsung meluncurkan promo. | <img src="docs/gambar/penggunaan/tinjauan-diskon.png" alt="Tinjau Diskon" width="280"/> |
| **9** | **Drill-Down Detail Barang** | Klik-ganda baris produk di tabel analisis untuk membuka riwayat transaksi lengkap barang itu. | <img src="docs/gambar/penggunaan/detail-produk.png" alt="Detail Produk" width="280"/> |
| **10** | **Ekspor Laporan Excel** | Klik tombol Export Excel untuk menyimpan laporan analisis 4-sheet dalam format `.xlsx`. | <img src="docs/gambar/penggunaan/export-excel.png" alt="Export Excel" width="280"/> |

---

<a id="kontributor"></a>
## 👥 Kontributor

Proyek ini dikembangkan oleh tim mahasiswa **Program Studi Sistem Informasi, Fakultas Teknik, Universitas Mulawarman (2026)**:

<div align="center">

| Foto / Avatar | Nama Lengkap | NIM | Tugas yang Dikerjakan |
|:---:|---|:---:|---|
| <img src="https://api.dicebear.com/7.x/bottts/svg?seed=Fajar" width="65"/> | **Ahmad Fajar Novia** | `2509116041` | • Membuat konsep program dan alur sistem (Flowchart).<br/>• Membuat seluruh program aplikasi beserta tampilannya.<br/>• Membuat struktur awal database (perintah create tabel dan insert data awal).<br/>• Menyusun dokumentasi README aplikasi. |
| <img src="https://api.dicebear.com/7.x/bottts/svg?seed=Farah" width="65"/> | **Farah Hikmatul Maula** | `2509116099` | • Menyusun Laporan.<br/>• Membuat PPT (slide presentasi).<br/>• Membuat ERD (Entity Relationship Diagram).<br/>• Membuat query database: DDL, DML, relasi JOIN, dan select tingkat lanjut 1. |
| <img src="https://api.dicebear.com/7.x/bottts/svg?seed=Marza" width="65"/> | **Marza Hanaya Melodya Goga** | `2409116103` | • Menyusun Laporan.<br/>• Membuat PPT (slide presentasi).<br/>• Membuat ERD (Entity Relationship Diagram).<br/>• Membuat query database: select tingkat lanjut 2. |
| <img src="https://api.dicebear.com/7.x/bottts/svg?seed=Harits" width="65"/> | **Harits** | `2509116048` | • Membantu sedikit pembuatan program aplikasi.<br/>• Menyusun Laporan.<br/>• Membuat PPT (slide presentasi).<br/>• Membuat Use Case Diagram. |

</div>

<br/>

> 🎓 **Afiliasi**: Program Studi Sistem Informasi • Fakultas Teknik • Universitas Mulawarman (2026)

---

## 📎 Dokumen Referensi & Lampiran

- 📐 **Dokumen Arsitektur Lengkap**: [`docs/arsitektur.md`](docs/arsitektur.md)
- 📊 **Source Flowchart (Mermaid Source)**: [`docs/flowchart.md`](docs/flowchart.md)
- 👤 **Dokumentasi Skenario Use Case**: [`docs/use-case.md`](docs/use-case.md)
- 🗄️ **Skema Basis Data DDL & DML**: [`db_flatsmart.sql`](db_flatsmart.sql)
- 🖼️ **Daftar Lengkap Aset Gambar README**: [`docs/gambar/README.md`](docs/gambar/README.md)

<div align="center">
  <br/>
  <b>FlatSmart v1.0 — Capstone Pemrograman Berorientasi Objek & Praktikum Sistem Basis Data</b><br/>
  <i>Developed with Java 17, Apache NetBeans, Swing, MySQL, Figma, and GitHub</i>
</div>
