import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pelanggan {
     private String nama;
    private List<Makanan> pesanan;
    private int kesabaran; // Batas waktu tunggu acak (misal 40-60 tick)
    private int waktuKedatangan;
    private int batasAkhirSabar; 
    private int waktuSelesaiMasak;
    private int skorKepuasan; 

    private static int counter = 1;

    public Pelanggan(int waktuDatang, List<Makanan> daftarMenu) {
        this.nama = "Pelanggan #" + counter++;
        this.waktuKedatangan = waktuDatang;
        this.pesanan = buatPesananRandom(daftarMenu);
        
        Random rand = new Random();
        this.kesabaran = rand.nextInt(21) + 40; 
        this.batasAkhirSabar = this.waktuKedatangan + this.kesabaran;
    }

    private List<Makanan> buatPesananRandom(List<Makanan> daftarMenu) {
        Random rand = new Random();
        List<Makanan> pesananAcak = new ArrayList<>();
        
        int jumlahItem = rand.nextInt(2) + 1; 
        
        for (int i = 0; i < jumlahItem; i++) {

            Makanan itemRandom = daftarMenu.get(rand.nextInt(daftarMenu.size()));
            pesananAcak.add(itemRandom);
        }
        return pesananAcak;
    }
    

    public boolean isSabarHabis(int waktuSekarang) {
        return waktuSekarang > this.batasAkhirSabar;
    }
    
    public int getTotalDurasiMasak() {
        int total = 0;
        for (Makanan m : this.pesanan) {
            total += m.getDurasiMasak();
        }
        return total;
    }
    

    public String getNama() { return this.nama; }
    public List<Makanan> getPesanan() { return this.pesanan; }
    public int getBatasAkhirSabar() { return this.batasAkhirSabar; }
    public int getWaktuKedatangan() { return this.waktuKedatangan; }
    public int getWaktuSelesaiMasak() { return this.waktuSelesaiMasak; }
    public void setWaktuSelesaiMasak(int tick) { this.waktuSelesaiMasak = tick; }
    public int getSkorKepuasan() { return this.skorKepuasan; }
    public void setSkorKepuasan(int skor) { this.skorKepuasan = skor; }

    public int getWaktuTungguTotal(int waktuSekarang) {
        return waktuSekarang - this.waktuKedatangan;
    }
}
