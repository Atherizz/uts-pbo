import java.util.List;
import java.util.Queue;

public class Koki extends Karyawan {
    
    public Koki() { 
        super("Koki", 1); 
    }

    @Override
    public void bekerja(Restoran restoran) {
        Pelanggan pelanggan = pilihPesanan(restoran);
        
        if (pelanggan == null) {
            System.out.println("Tidak ada pesanan di antrian Koki.");
            restoran.MajukanWaktu(1);
            return; 
        }

        int durasiMasak = this.hitungBiayaAksi(pelanggan); 

        int waktuSelesai = restoran.getWaktuSimulasi() + durasiMasak;
        pelanggan.setWaktuSelesaiMasak(waktuSelesai);
        
        restoran.getDaftarMemasak().add(pelanggan);
        
        System.out.println("Koki mulai memasak untuk " + pelanggan.getNama() + "...");
        System.out.println("Pesanan akan selesai pada tick: " + waktuSelesai);

        restoran.MajukanWaktu(2); 
    }

    private Pelanggan pilihPesanan(Restoran restoran) {
        System.out.println("--- 2. Stasiun Koki ---");
        Queue<Pelanggan> antrian = restoran.getAntrianMasak();

        Pelanggan pelangganPertama = antrian.peek();

        if (pelangganPertama == null) {
            System.out.println("Antrian masak kosong / menu telah siap");
            return null;
        }

        boolean stokCukup = true;
        List<Makanan> pesananPelanggan = pelangganPertama.getPesanan();

        for (Makanan m : pesananPelanggan) {
            if (m.getDurasiMasak() > 0) {
                List<String> resep = m.getResep();
                for (String namaBahan : resep) {
                    if (restoran.cekStok(namaBahan) <= 0) {
                        System.out.println("[STOK HABIS] Tidak bisa memasak pesanan "
                                           + pelangganPertama.getNama() + " (" + m.getNama() + ").");
                        System.out.println("Bahan '" + namaBahan + "' habis! Harap restock bahan terlebih dahulu.");
                        stokCukup = false;
                        break;
                    }
                }
            }
            if (!stokCukup) {
                break;
            }
        }

        if (!stokCukup) {
            return null;
        }

        System.out.println("Stok cukup. Mengambil pesanan otomatis: " + pelangganPertama.getNama());
        return antrian.poll();
    }

    protected int hitungBiayaAksi(Pelanggan pelanggan) {
        int durasiNormal = pelanggan.getTotalDurasiMasak(); 
        
        int durasiFinal = durasiNormal - this.levelKeahlian; 
        
        return Math.max(1, durasiFinal); 
    }
}