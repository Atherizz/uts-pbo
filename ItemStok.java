public class ItemStok {
    private String namaBahan;
    private int jumlahStok;
    private int hargaBeli; 

    public ItemStok(String nama, int jumlah, int hargaBeli) {
        this.namaBahan = nama;
        this.jumlahStok = jumlah;
        this.hargaBeli = hargaBeli;
    }

    public void kurangiStok(int jumlah) {
        if (this.jumlahStok >= jumlah) {
            this.jumlahStok -= jumlah;
        } else {
            System.out.println("Stok " + this.namaBahan + " tidak mencukupi!");
        }
    }

    public void tambahStok(int jumlah) {
        this.jumlahStok += jumlah;
    }

    public String getNama() {
        return this.namaBahan;
    }

    public int getJumlah() {
        return this.jumlahStok;
    }

    public int getHargaBeli() {
        return this.hargaBeli;
    }
}