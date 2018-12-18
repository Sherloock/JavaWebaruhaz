package aruhaz;

import java.util.ArrayList;

public class Modell {

    private final Fajl fajlKezeles;
    private final ArrayList<Termek> TERMEKEK ;
    private final ArrayList<String> KATEGORIAK;
    private final ArrayList<String> TELEPULESEK ;

    public Modell(Fajl fajlKezeles) {
        this.fajlKezeles = fajlKezeles;
        TERMEKEK = fajlKezeles.termekekBetoltese();
        KATEGORIAK = fajlKezeles.kategoriakBetoltese();
        TELEPULESEK = fajlKezeles.telepulesekBetoltese();
    }
    
    // termek hozzadasa a modellhez és a fájlhoz
    public void termekHozzadasa(String telepules, String nev, String kategoria, String leiras, String ar, String kep) {
        //autoincrement
        Termek ujTermek = new Termek(telepules, nev, leiras, kategoria, ar, kep);
        
        TERMEKEK.add(ujTermek);
        fajlKezeles.termekHozzadasa(ujTermek);
    }

    //adott id-vel rendelkező elem törlése
    public void termekTorlese(int id) {
        TERMEKEK.remove(getTermekById(id));
        fajlKezeles.termekTorlese(id + "");
    }

    //egy termék árának megváltoztatása
    public void arvaltoztatas(int id, double szazalek) {
        Termek termek = getTermekById(id);
        if(termek != null){
            termek.szazalekosArvaltoztatas(szazalek);
            int ujAr = termek.getAr();
            fajlKezeles.arvaltoztatas(id,ujAr);
        }
    }

    //összes ár megváltoztatása
    public void arvaltoztatas(double szazalek) {
        TERMEKEK.forEach((termek) -> { arvaltoztatas(termek.ID, szazalek); });
    }

    //termék visszaadása az ID-je alalpján
    public Termek getTermekById(int id) {
        for (Termek termek : TERMEKEK) {
            if (termek.ID == id) {
                return termek;
            }
        }
        return null;
    }

    // GETTERS
    public ArrayList<String> getKategoriak() {
        return KATEGORIAK;
    }

    public ArrayList<Termek> getTermekek() {
        return TERMEKEK;
    }

    public ArrayList<String> getTelepulesek() {
        return TELEPULESEK;
    }
    
    public Statisztika getStatisztika(){
        return new Statisztika(this);
    }     
}
