import java.util.List;

public class Logistik extends Karyawan implements IStorable {

    public Logistik() {
        super("Logistik", 1);
    }

    public void bekerja(Restoran restoran, String namaBahan, int jumlahBeli) {

        if (namaBahan == null || namaBahan.isEmpty() || jumlahBeli <= 0) {
            System.out.println("Input tidak valid untuk restock.");
            restoran.MajukanWaktu(1);
            return;
        }

        ItemStok item = restoran.getStok(namaBahan);
        if (item == null) {
            System.out.println("Bahan '" + namaBahan + "' tidak ditemukan.");
            restoran.MajukanWaktu(1);
            return;
        }

        int hargaSatuan = item.getHargaBeli();
        int totalBiaya = hargaSatuan * jumlahBeli;

        System.out.println("Harga satuan " + namaBahan + ": Rp" + hargaSatuan); // Tampilkan harga satuan
        System.out.println("Total biaya untuk " + jumlahBeli + " " + namaBahan + ": Rp" + totalBiaya);

        if (restoran.getKeuangan() >= totalBiaya) {
            restoran.tambahKeuangan(-totalBiaya);
            item.tambahStok(jumlahBeli);

            System.out.println("Pembelian sukses! Stok " + namaBahan + " sekarang: " + item.getJumlah());

            int biayaAksi = this.hitungBiayaAksi(null);
            System.out.println("Proses pengiriman memakan " + biayaAksi + " tick.");
            restoran.MajukanWaktu(biayaAksi);

        } else {
            System.out.println("Pembelian GAGAL! Uang restoran tidak cukup.");
            restoran.MajukanWaktu(1);
        }
    }

    @Override
    public void bekerja(Restoran restoran) {
       System.out.println("[INFO] Aksi Logistik memerlukan input nama bahan dan jumlah dari Main.");
    }

    public void tampilkanStok(Restoran restoran) {
        System.out.println("Daftar Stok Saat Ini:");
        List<ItemStok> daftarStok = restoran.getDaftarStok();

        for (ItemStok item : daftarStok) {
            System.out.println("- " + item.getNama() + ": " + item.getJumlah() + " (Rp" + item.getHargaBeli() + "/item)");
        }
    }

    @Override
    protected int hitungBiayaAksi(Pelanggan pelanggan) {
        return 6;
    }
}