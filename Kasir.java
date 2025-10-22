import java.util.Queue;


public class Kasir extends Karyawan {
    
    public Kasir() {
        super("Kasir", 1); 
    }

    @Override
    public void bekerja(Restoran restoran) {
        Pelanggan pelanggan = pilihPelanggan(restoran);

        if (pelanggan == null) {
            System.out.println("Tidak ada pelanggan yang dilayani.");
            restoran.MajukanWaktu(1); 
            return; 
        }
        
        int totalPesanan = hitungTotalPesanan(pelanggan);
        
        terimaPembayaran(restoran, pelanggan, totalPesanan);
        
        pindahkanKeStasiunBerikutnya(restoran, pelanggan);

        int biayaAksi = hitungBiayaAksi(pelanggan);
        restoran.MajukanWaktu(biayaAksi);
    }

    private Pelanggan pilihPelanggan(Restoran restoran) {
        System.out.println("--- 1. Stasiun Kasir ---");
        Queue<Pelanggan> antrian = restoran.getAntrianKasir();

        Pelanggan pelangganPertama = antrian.peek(); 

        if (pelangganPertama == null) {
            System.out.println("Antrian kasir kosong.");
            return null; 
        }
        
        System.out.println("Pelanggan berikutnya: " + pelangganPertama.getNama());
        System.out.println("Pesanan: " + pelangganPertama.getPesanan().get(0).getNama()); 

        return antrian.poll(); 

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
    
    private void pindahkanKeStasiunBerikutnya(Restoran restoran, Pelanggan pelanggan) {
        int totalDurasiMasak = pelanggan.getTotalDurasiMasak(); 

        if (totalDurasiMasak > 0) {
            restoran.getAntrianMasak().add(pelanggan);
            System.out.println("Pesanan " + pelanggan.getNama() + " dikirim ke Dapur (Koki).");
        }
    }

    @Override
    protected int hitungBiayaAksi(Pelanggan pelanggan) {
    int waktuAksiNormal = 5; 
    int waktuAksiFinal = waktuAksiNormal - this.levelKeahlian; 

    return Math.max(1, waktuAksiFinal); 
    }
    }