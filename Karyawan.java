

public abstract class Karyawan {
    
    protected String namaPeran;
    protected int levelKeahlian;  

    public Karyawan(String nama, int level) {
        this.namaPeran = nama;
        this.levelKeahlian = level;
    }
    
    public String getNamaPeran() {
        return this.namaPeran;
    }
    
    public int getLevel() {
        return this.levelKeahlian;
    }
    
 
    public abstract void bekerja(Restoran restoran); 

    protected abstract int hitungBiayaAksi(Pelanggan pelanggan);

}