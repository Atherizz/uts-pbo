# Simulasi Restoran Cepat Saji (Text-Based)

Game ini adalah simulasi berbasis teks di mana Anda berperan sebagai manajer (dan satu-satunya karyawan) yang harus menjalankan restoran cepat saji.

## Tujuan Permainan 🎯

Tujuan utama Anda adalah **mengelola restoran secara efisien** untuk melayani pelanggan, mendapatkan keuntungan, dan menjaga reputasi restoran tetap tinggi. Anda harus:
* Melayani pesanan pelanggan dengan cepat sebelum kesabaran mereka habis.
* Memasak makanan sesuai pesanan.
* Mengelola stok bahan baku agar tidak kehabisan.
* **Menghindari kebangkrutan** (reputasi mencapai 0).

## Cara Memainkan 🕹️

### 1. Kompilasi dan Menjalankan (Setup)
* Pastikan Anda memiliki Java Development Kit (JDK) terinstal.
* Buka terminal atau command prompt.
* Navigasi ke direktori tempat Anda menyimpan semua file `.java`.
* Kompilasi semua file: `javac *.java`
* Jalankan game: `java Game`

### 2. Gameplay Loop (Alur Permainan)
Game ini berjalan secara **turn-based (berbasis giliran)**. Setiap giliran, Anda akan melihat status restoran saat ini, lalu memilih aksi yang ingin dilakukan.

**Tampilan Status Utama:**
Setiap awal giliran, Anda akan melihat informasi seperti:
* **Waktu Simulasi (Tick):** Jam internal game.
* **Keuangan:** Uang restoran saat ini.
* **Reputasi:** Skor reputasi (0-100).
* **Antrian Kasir:** Daftar pelanggan yang menunggu dilayani beserta batas waktu sabar mereka.
* **Proses Masak:** Daftar pesanan yang sedang dimasak Koki dan kapan akan matang.
* **Antrian Masak:** Daftar pesanan yang menunggu giliran dimasak Koki.

### 3. Pilihan Aksi 🧑‍🍳
Setelah melihat status, Anda akan diminta memilih aksi:

* **1. Layani Pelanggan (Kasir):**
    * Mengambil pelanggan pertama dari **Antrian Kasir**.
    * Menerima pembayaran (menambah Keuangan).
    * Memindahkan pesanan ke **Antrian Masak** (jika perlu dimasak) atau langsung ke **Antrian Rakit** (jika tidak perlu dimasak - *jika fitur rakit diaktifkan*). Jika tidak ada rakit, langsung selesai jika tidak perlu masak.
    * Aksi ini memakan **biaya waktu (tick)** tertentu, yang dipengaruhi level Kasir.

* **2. Masak Pesanan (Koki):**
    * Mengambil pesanan pertama dari **Antrian Masak**.
    * Mengecek ketersediaan **stok bahan baku**. Jika tidak cukup, aksi gagal.
    * Jika stok cukup, **mengurangi stok** dan memulai proses masak. Pesanan dipindahkan ke **Proses Masak** dengan *timer* kapan akan matang.
    * Aksi *memulai* masak ini memakan **biaya waktu (tick)** kecil (misal: 2 tick). Durasi masaknya sendiri berjalan pasif.

* **3. Restock Bahan (Logistik):**
    * Menampilkan **daftar stok** saat ini beserta harga belinya.
    * Meminta Anda memasukkan **nama bahan** dan **jumlah** yang ingin dibeli.
    * Mengecek apakah **Keuangan** cukup.
    * Jika cukup, mengurangi Keuangan dan **menambah stok**.
    * Aksi ini memakan **biaya waktu (tick)** tertentu (misal: 10 tick, simulasi pengiriman).

* **9. Tunggu / Majukan Waktu:**
    * Berguna jika tidak ada aksi mendesak yang bisa dilakukan (misal: semua antrian kosong).
    * Meminta Anda memasukkan **jumlah tick** yang ingin dilewati.
    * Selama waktu dimajukan, simulasi tetap berjalan: pelanggan baru bisa datang, masakan bisa matang, dan kesabaran pelanggan yang menunggu bisa habis.

* **0. Keluar:**
    * Mengakhiri permainan.

### 4. Sistem Waktu (Tick) ⏳
Waktu dalam game diukur dalam **tick**. Waktu **tidak berjalan otomatis**. Ia hanya maju ketika Anda **melakukan sebuah aksi**. Setiap aksi memiliki "biaya" *tick* yang berbeda. Semakin banyak *tick* berlalu, semakin dekat pelanggan pada batas kesabarannya.

