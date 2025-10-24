import java.util.Scanner;

public class Game {
    private Restoran restoran;
    private Kasir stasiunKasir;
    private Koki stasiunKoki;
    private Logistik stasiunLogistik;
    private Scanner scanner;
    private boolean isGameRunning;

    public Game() {
        this.restoran = new Restoran();
        this.stasiunKasir = new Kasir();
        this.stasiunKoki = new Koki();
        this.stasiunLogistik = new Logistik();
        this.scanner = new Scanner(System.in);
        this.isGameRunning = true;
    }

    public void run() {

        System.out.println("Selamat Datang di Restoran Cepat Saji!");

        restoran.inisialisasiPelanggan();

        while (isGameRunning) {
            restoran.TampilkanStatus();
            tampilkanMenuAksi();
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    stasiunKasir.bekerja(restoran);
                    break;
                case "2":
                    stasiunKoki.bekerja(restoran);
                    break;
                case "3":
                    aksiLogistik();
                    break;
                case "4":
                    stasiunLogistik.tampilkanStok(restoran);
                    break;
                case "9":
                    aksiMajukanWaktu();
                    break;
                case "0":
                    isGameRunning = false;
                    System.out.println("Terima kasih sudah bermain!");
                    break;
                default:
                    System.out.println("Pilihan tidak valid.");
                    break;
            }

            if (restoran.getReputasi() <= 0) {
                System.out.println("\n================ GAME OVER ================");
                System.out.println("Reputasi restoran Anda hancur! Anda Bangkrut.");
                System.out.println("==========================================");
                isGameRunning = false;
            }
        }
        scanner.close();
    }

    private void tampilkanMenuAksi() {
        System.out.println("\n--- PILIH AKSI ---");
        System.out.println("1. Layani Pelanggan (Kasir)");
        System.out.println("2. Masak Pesanan (Koki)");
        System.out.println("3. Restock Bahan (Logistik)");
        System.out.println("4. Melihat Stok Bahan");
        System.out.println("9. Tunggu / Majukan Waktu");
        System.out.println("0. Keluar");
        System.out.print("Pilihan Anda: ");
    }

    private void aksiLogistik() {
        stasiunLogistik.tampilkanStok(restoran);
        System.out.print("Masukkan nama bahan yang ingin dibeli (atau 'batal'): ");
        String namaBahan = scanner.nextLine();

        if (namaBahan.equalsIgnoreCase("batal")) {
            System.out.println("Restock dibatalkan.");
            restoran.MajukanWaktu(1);
            return;
        }

        int jumlahBeli = 0;
        try {
            System.out.print("Masukkan jumlah yang ingin dibeli: ");
            jumlahBeli = Integer.parseInt(scanner.nextLine());
            if (jumlahBeli <= 0) {
                 System.out.println("Jumlah harus positif.");
                 restoran.MajukanWaktu(1);
                 return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Input jumlah tidak valid.");
            restoran.MajukanWaktu(1);
            return;
        }

        stasiunLogistik.bekerja(restoran, namaBahan, jumlahBeli);
    }

    private void aksiMajukanWaktu() {
        System.out.print("Majukan waktu berapa tick? (misal: 10): ");
        try {
            int tick = Integer.parseInt(scanner.nextLine());
            if (tick > 0) {
                restoran.MajukanWaktu(tick);
            } else {
                 System.out.println("Jumlah tick harus positif.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Input tick tidak valid.");
        }
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.run();
    }
}