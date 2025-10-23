import java.util.ArrayList;
import java.util.List;

public class Makanan {
    private String nama;
    private int harga;
    private int durasiMasak;
    private List<String> resep; 

    public Makanan(String nama, int harga, int durasiMasak, List<String> resep) {
        this.nama = nama;
        this.harga = harga;
        this.durasiMasak = durasiMasak;
        this.resep = new ArrayList<>(resep); 
    }
    
    public String getNama() { return this.nama; }
    public int getHarga() { return this.harga; }
    public int getDurasiMasak() { return this.durasiMasak; }
    public List<String> getResep() { return this.resep; }
}

