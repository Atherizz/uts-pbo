import java.util.ArrayList;
import java.util.List;

public class Makanan {
     private String nama;
    private int harga;
    private int durasiMasak;
    private int durasiRakit; 
    private List<String> resep; 

    public Makanan(String nama, int harga, int durasiMasak, int durasiRakit, List<String> resep) {
        this.nama = nama;
        this.harga = harga;
        this.durasiMasak = durasiMasak;
        this.durasiRakit = durasiRakit;

        this.resep = new ArrayList<>(resep); 
    }
    
    public String getNama() { return this.nama; }
    public int getHarga() { return this.harga; }
    public int getDurasiMasak() { return this.durasiMasak; }
    public int getDurasiRakit() { return this.durasiRakit; }
    public List<String> getResep() { return this.resep; }
}

