public class itemStock {
    String namaBahan;
    int jumlahStok;

    public itemStock(String string, int i) {
        //TODO Auto-generated constructor stub
    }

    public void ItemStok(String nama, int jumlah) {
        this.namaBahan = nama;
        this.jumlahStok = jumlah;
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
}