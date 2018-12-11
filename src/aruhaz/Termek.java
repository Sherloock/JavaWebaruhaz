package aruhaz;

public class Termek {
    
    public final int ID;
    private String telepules;
    private String nev;
    private String kategoria;
    private String leiras;
    private int ar;
    private String kep;
    
    Termek(String ID, String telepules, String nev, String leiras, String kategoria, String ar, String kep) {
        this(Integer.parseInt(ID), telepules, nev, leiras, kategoria, Integer.parseInt(ar), kep);
    }
    
    Termek(int ID, String telepules, String nev, String leiras, String kategoria, int ar, String kep) {
        this.ID =ID;
        this.telepules = telepules;
        this.nev = nev;
        this.kategoria = kategoria;
        this.leiras = leiras;
        this.ar = ar;
    }

    public void szazalekosArvaltoztatas(int szazalek){
        ar *= (1+szazalek);
    }

    @Override
    public String toString() {
        return "Termek{" + "ID=" + ID + ", telepules=" + telepules + ", nev=" + nev + ", "
                + "kategoria=" + kategoria + ", leiras=" + leiras + ", ar=" + ar + ", kep=" + kep + '}';
    }
    
    
    // getters and setters
    
    public String getTelepules() {
        return telepules;
    }

    public String getNev() {
        return nev;
    }

    public String getKategoria() {
        return kategoria;
    }

    public String getLeiras() {
        return leiras;
    }

    public int getAr() {
        return ar;
    }

    public String getKep() {
        return kep;
    }

    public void setTelepules(String telepules) {
        this.telepules = telepules;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public void setKategoria(String kategoria) {
        this.kategoria = kategoria;
    }

    public void setLeiras(String leiras) {
        this.leiras = leiras;
    }

    public void setAr(int ar) {
        this.ar = ar;
    }

    public void setKep(String kep) {
        this.kep = kep;
    }
}
