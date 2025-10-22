import java.util.Queue;
import java.util.Scanner;

public class Kasir extends Karyawan {
    
    public Kasir() {

        super("Kasir", 1); 
    }

    @Override
    public void bekerja(Restoran restoran) {

    }

    private Pelanggan pilihPelanggan(Restoran restoran) {
        return null; // Pemain memilih 'n' atau batal
    }

    private int hitungTotalPesanan(Pelanggan pelanggan) {
        int total = 0;
        for (Makanan item : pelanggan.getPesanan()) {
            total += item.getHarga();
        }
        return total;
    }

    private void terimaPembayaran(Restoran restoran, Pelanggan pelanggan, int totalHarga) {
        restoran.tambahKeuangan(totalHarga); 
        System.out.println("Pembayaran diterima dari " + pelanggan.getNama() + " sebesar Rp" + totalHarga);
        System.out.println("Keuangan restoran saat ini: Rp" + restoran.getKeuangan());
    }
    
    private void pindahkanKeDapur(Restoran restoran, Pelanggan pelanggan) {
        restoran.getAntrianMasak().add(pelanggan); // Asumsi ada getter
        System.out.println("Pesanan " + pelanggan.getNama() + " dikirim ke Dapur (Koki).");
    }

}