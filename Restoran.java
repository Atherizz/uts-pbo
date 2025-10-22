import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class Restoran {
    private int keuangan;
    private int reputasi;
    private int waktuSimulasi;
    private List<Makanan> daftarMenu;
    private List<ItemStok> daftarStok;

    private List<Pelanggan> daftarMemasak;
    private Queue<Pelanggan> antrianKasir; 
    private Queue<Pelanggan> antrianMasak;

    private Random random;

        public Restoran() {
        this.keuangan = 100000;
        this.reputasi = 50;
        this.waktuSimulasi = 100;
        this.random = new Random();

        this.daftarMenu = new ArrayList<>();
        this.daftarStok = new ArrayList<>();
        this.daftarMemasak = new ArrayList<>();
        this.antrianKasir = new LinkedList<>();
        this.antrianMasak = new LinkedList<>();

        inisialisasiMenu();
        inisialisasiStok();
    }

        private void inisialisasiMenu() {
        daftarMenu.add(new Makanan("Burger Keju", 35000, 15, 8, List.of("Roti", "Daging", "Keju", "Selada", "Roti")));
        daftarMenu.add(new Makanan("Kentang Goreng", 15000, 8, 0, List.of()));
        daftarMenu.add(new Makanan("Soda Stroberi", 12000, 0, 5, List.of("Gelas", "Es Batu", "Sirup Stroberi", "Soda")));
        }   

        private void inisialisasiStok() {
        daftarStok.add(new ItemStok("Daging", 50, 8000));
        daftarStok.add(new ItemStok("Roti", 100, 2500));
        daftarStok.add(new ItemStok("Keju", 30, 4000));
        daftarStok.add(new ItemStok("Selada", 30, 1500));
        daftarStok.add(new ItemStok("Kentang", 100, 3000));
        daftarStok.add(new ItemStok("Gelas", 50, 1000));
        daftarStok.add(new ItemStok("Es Batu", 200, 200));
        daftarStok.add(new ItemStok("Sirup Stroberi", 100, 3500));
        daftarStok.add(new ItemStok("Soda", 100, 2000));
        daftarStok.add(new ItemStok("Garam", 100, 100));
        }

        public void MajukanWaktu(int biayaAksi) {
        for (int i = 0; i < biayaAksi; i++) {
            this.waktuSimulasi++;
            
            SimulasiKedatanganPelanggan();
            CekKesabaranPelanggan();
            CekProsesMasakSelesai();
        }
    }

    private void SimulasiKedatanganPelanggan() {
        if (random.nextInt(100) < 10) { 
            Pelanggan baru = new Pelanggan(this.waktuSimulasi, this.daftarMenu);
            this.antrianKasir.add(baru);
            System.out.println("\n[SISTEM] Pelanggan baru (" + baru.getNama() + ") datang! Sabar s/d tick " + baru.getBatasAkhirSabar());
        }
    }

    private void CekKesabaranPelanggan() {
        Pelanggan p = antrianKasir.peek();
        if (p != null && p.isSabarHabis(this.waktuSimulasi)) {
            antrianKasir.poll();
            this.reputasi -= 10;
            System.out.println("\n[MARAH] " + p.getNama() + " marah dan pergi! Reputasi -10. Sisa Reputasi: " + this.reputasi);
        }
        
        if (this.reputasi <= 0) {
        }
    }
    
    private void CekProsesMasakSelesai() {
        for (int i = 0; i < daftarMemasak.size(); i++) {
            Pelanggan p = daftarMemasak.get(i);
            
            if (this.waktuSimulasi >= p.getWaktuSelesaiMasak()) {
                daftarMemasak.remove(i);
            
                SelesaikanPelanggan(p, 100);
                
                i--;
            }
        }
    }
    
    public void SelesaikanPelanggan(Pelanggan p, int skorRakitan) {
        int skorAkhir = p.getSkorKepuasan();
        if (skorAkhir == 0) {
            skorAkhir = skorRakitan;
        }
        
        int totalHarga = 0;
        for (Makanan m : p.getPesanan()) { totalHarga += m.getHarga(); }
        
        int tip = hitungTip(skorAkhir, totalHarga);
        
        this.keuangan += tip;
        this.reputasi += 5;

        System.out.println("\n[SUKSES] Pesanan " + p.getNama() + " Selesai.");
        System.out.println("Skor Kepuasan: " + skorAkhir + "/100. Tip: Rp" + tip);
    }

    private int hitungTip(int skorKepuasan, int totalHarga) {
        if (skorKepuasan >= 90) return (int)(totalHarga * 0.15);
        if (skorKepuasan >= 70) return (int)(totalHarga * 0.10);
        return 0;
    }

    public ItemStok getStok(String namaBahan) {
        for (ItemStok item : this.daftarStok) {
            if (item.getNama().equalsIgnoreCase(namaBahan)) {
                return item;
            }
        }
        return null;
    }

    public void kurangiStok(String namaBahan, int jumlah) {
        ItemStok item = getStok(namaBahan);
        if (item != null) {
            item.kurangiStok(jumlah);
        }
    }
    
    public int cekStok(String namaBahan) {
        ItemStok item = getStok(namaBahan);
        return (item != null) ? item.getJumlah() : 0;
    }

    public int getWaktuSimulasi() { return this.waktuSimulasi; }
    public int getKeuangan() { return this.keuangan; }
    public int getReputasi() { return this.reputasi; }
    public Queue<Pelanggan> getAntrianKasir() { return this.antrianKasir; }
    public List<Pelanggan> getDaftarMemasak() { return this.daftarMemasak; }
    public List<Makanan> getDaftarMenu() { return this.daftarMenu; }
    public List<ItemStok> getDaftarStok() { return this.daftarStok; }
    public Queue<Pelanggan> getAntrianMasak() { return this.antrianMasak; }
    
    public void tambahKeuangan(int jumlah) { this.keuangan += jumlah; }

    public void TampilkanStatus() {
        System.out.println("\n=============================================");
        System.out.println("| WAKTU: " + this.waktuSimulasi + " tick | KEUANGAN: Rp" + this.keuangan + " | REPUTASI: " + this.reputasi + "/100 |");
        System.out.println("=============================================");
        
        System.out.print("Antrian Kasir: ");
        if (antrianKasir.isEmpty()) {
            System.out.println("Kosong.");
        } else {
            antrianKasir.forEach(p -> System.out.print(p.getNama() + " (s/d " + p.getBatasAkhirSabar() + ") | "));
            System.out.println();
        }
        
        System.out.print("Proses Masak: ");
        if (daftarMemasak.isEmpty()) {
            System.out.println("Kosong.");
        } else {
            daftarMemasak.forEach(p -> System.out.print(p.getNama() + " (Matang: " + p.getWaktuSelesaiMasak() + ") | "));
            System.out.println();
        }
    }
}
