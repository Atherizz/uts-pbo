
public abstract class Karyawan implements IBekerja {
    
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
    
    @Override
    public abstract void bekerja(Restoran restoran); 
}