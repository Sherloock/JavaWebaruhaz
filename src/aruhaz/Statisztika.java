package aruhaz;

import static aruhaz.Tablazat.*;
import java.util.ArrayList;

public class Statisztika {

    private final Modell modell;

    public Statisztika(Modell modell) {
        this.modell = modell;
    }

    public Tablazat osszesAdat() {
        Object[] fejlec = {ID, TELEP, T_NEV, KAT, LEIRAS, AR, KEP};
        Object[][] adatok = new Object[modell.getTermekek().size()][fejlec.length];
        
        for (int i = 0; i < modell.getTermekek().size(); i++) {
            adatok[i][0] = modell.getTermekek().get(i).ID;
            adatok[i][1] = modell.getTermekek().get(i).getTelepules();
            adatok[i][2] = modell.getTermekek().get(i).getNev();
            adatok[i][3] = modell.getTermekek().get(i).getKategoria();
            adatok[i][4] = modell.getTermekek().get(i).getLeiras();
            adatok[i][5] = modell.getTermekek().get(i).getAr();
            adatok[i][6] = modell.getTermekek().get(i).getKep();
        }
        return new Tablazat(fejlec, adatok);
    }

    public Tablazat termekSzamaKategoriankent() {
        ArrayList<String>kategoriak = modell.getKategoriak();
        
        Object[] fejlec = {KAT, T_SZAMA};
        Object[][] adatok = new Object[kategoriak.size()][fejlec.length];

        //kategóriák feltölt
        for (int i = 0; i < kategoriak.size(); i++) {
            adatok[i][0] = kategoriak.get(i);

            int db = 0;
            for (int j = 0; j < modell.getTermekek().size(); j++) {
                if (kategoriak.get(i).equals(modell.getTermekek().get(j).getKategoria())) {
                    db++;
                }
            }
            adatok[i][1] = db;
        }
        return new Tablazat(fejlec, adatok);
    }

    public Tablazat termekAtlagosAraKategoriankent() {
        ArrayList<String>kategoriak = modell.getKategoriak();
        Object[] fejlec = {KAT, AR_ATL};
        int n = kategoriak.size();

        int sum[] = new int[n];
        int db[] = new int[n];

        modell.getTermekek().forEach((termek) -> {
            for (int i = 0; i < kategoriak.size(); i++) {
                if (kategoriak.get(i).equals(termek.getKategoria())) {
                    db[i]++;
                    sum[i] += termek.getAr();
                }
            }
        });
        
        Object[][] adatok = new Object[n][fejlec.length];
        
        for (int i = 0; i < n; i++) {
            adatok[i][0] = kategoriak.get(i);
            adatok[i][1] = db[i] == 0 ? 0 : sum[i] / db[i];
        }
      
        return new Tablazat(fejlec, adatok);
    }

    public Tablazat kategoriaDbMinMaxAr() {
        ArrayList<String>kategoriak = modell.getKategoriak();
        Object[] fejlec = {KAT, T_SZAMA, T_MIN, T_MAX};
        Object[][] adatok = new Object[kategoriak.size()][fejlec.length];

        //kategóriák feltölt
        for (int i = 0; i < kategoriak.size(); i++) {
            adatok[i][0] = kategoriak.get(i);

            int szamlalo = 0, min = Integer.MAX_VALUE, max = 0;
            for (int j = 0; j < modell.getTermekek().size(); j++) {
                if (kategoriak.get(i).equals(modell.getTermekek().get(j).getKategoria())) {
                    szamlalo++;
                    min = Math.min(min, modell.getTermekek().get(j).getAr());
                    max = Math.max(max, modell.getTermekek().get(j).getAr());
                }
            }
            adatok[i][1] = szamlalo;
            adatok[i][2] = szamlalo == 0 ? 0 : min;
            adatok[i][3] = szamlalo == 0 ? 0 : max;
        }
        return new Tablazat(fejlec, adatok);
    }

    public Tablazat termekekSzamaTelepulesenkent() {
        Object[] fejlec = {TELEP, T_SZAMA};

        ArrayList<String> telepulesek = new ArrayList<>();
        ArrayList<Integer> termekDB = new ArrayList<>();

        modell.getTermekek().forEach((termek) -> {
            if (telepulesek.contains(termek.getTelepules())) {
                int index = telepulesek.indexOf(termek.getTelepules());
                termekDB.set(index, termekDB.get(index) + 1);
            } else {
                telepulesek.add(termek.getTelepules());
                termekDB.add(1);
            }
        });

        Object[][] adatok = new Object[telepulesek.size()][fejlec.length];
        for (int i = 0; i < telepulesek.size(); i++) {
            adatok[i][0] = telepulesek.get(i);
            adatok[i][1] = termekDB.get(i);
        }
        return new Tablazat(fejlec, adatok);
    }

}
