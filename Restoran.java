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
    private List<itemStock> daftarStok;

    private Queue<Pelanggan> antrianKasir; 
    private List<Pelanggan> daftarMemasak;
    private Queue<Pelanggan> antrianRakit;

    private Random random;

        public Restoran() {
        this.keuangan = 100000;
        this.reputasi = 50;
        this.waktuSimulasi = 100;
        this.random = new Random();

        this.daftarMenu = new ArrayList<>();
        this.daftarStok = new ArrayList<>();
        this.antrianKasir = new LinkedList<>();
        this.daftarMemasak = new ArrayList<>();
        this.antrianRakit = new LinkedList<>();

        inisialisasiMenu();
        inisialisasiStok();
    }

    private void inisialisasiMenu() {
        daftarMenu.add(new Makanan("Burger Keju", 25000, 15, 8, List.of("Roti Bawah", "Daging", "Keju", "Selada", "Roti Atas")));
        daftarMenu.add(new Makanan("Kentang Goreng", 10000, 8, 0, List.of()));
        daftarMenu.add(new Makanan("Soda Stroberi", 8000, 0, 5, List.of("Gelas", "Es Batu", "Sirup Stroberi", "Soda")));
    }

    private void inisialisasiStok() {
        daftarStok.add(new itemStock("Daging", 50));
        daftarStok.add(new itemStock("Roti Bawah", 50));
        daftarStok.add(new itemStock("Roti Atas", 50));
        daftarStok.add(new itemStock("Keju", 30));
        daftarStok.add(new itemStock("Selada", 30));
        daftarStok.add(new itemStock("Kentang", 100));
        daftarStok.add(new itemStock("Gelas", 50));
        daftarStok.add(new itemStock("Es Batu", 200));
        daftarStok.add(new itemStock("Sirup Stroberi", 100));
        daftarStok.add(new itemStock("Soda", 100));
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
                
                if (perluDirakit(p.getPesanan())) {
                    this.antrianRakit.add(p);
                    System.out.println("\n[SISTEM] Pesanan " + p.getNama() + " Matang & Siap Dirakit!");
                } else {
                    SelesaikanPelanggan(p, 100);
                }
                i--;
            }
        }
    }
    
    private boolean perluDirakit(List<Makanan> pesanan) {
        for (Makanan m : pesanan) {
            if (m.getResep() != null && !m.getResep().isEmpty()) {
                return true;
            }
        }
        return false;
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

    public itemStock getStok(String namaBahan) {
        for (itemStock item : this.daftarStok) {
            if (item.getNama().equalsIgnoreCase(namaBahan)) {
                return item;
            }
        }
        return null;
    }

    public void kurangiStok(String namaBahan, int jumlah) {
        itemStock item = getStok(namaBahan);
        if (item != null) {
            item.kurangiStok(jumlah);
        }
    }
    
    public int cekStok(String namaBahan) {
        itemStock item = getStok(namaBahan);
        return (item != null) ? item.getJumlah() : 0;
    }

    public int getWaktuSimulasi() { return this.waktuSimulasi; }
    public int getKeuangan() { return this.keuangan; }
    public int getReputasi() { return this.reputasi; }
    public Queue<Pelanggan> getAntrianKasir() { return this.antrianKasir; }
    public Queue<Pelanggan> getAntrianRakit() { return this.antrianRakit; }
    public List<Pelanggan> getDaftarMemasak() { return this.daftarMemasak; }
    public List<Makanan> getDaftarMenu() { return this.daftarMenu; }
    
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
        
        System.out.print("Antrian Rakit: ");
        if (antrianRakit.isEmpty()) {
            System.out.println("Kosong.");
        } else {
            antrianRakit.forEach(p -> System.out.print(p.getNama() + " | "));
            System.out.println();
        }
    }
}
