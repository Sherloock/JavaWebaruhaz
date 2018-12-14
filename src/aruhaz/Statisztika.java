package aruhaz;

import java.util.ArrayList;

public class Statisztika {

    private final Modell modell;
    ArrayList<Termek> termekLista;
    ArrayList<String> kategoriak;

    public Statisztika(Modell modell) {
        this.modell = modell;
        termekLista = modell.getTermekek();
        kategoriak = modell.getKategoriak();
    }

    public Tablazat osszesAdat() {
        Object[] fejlec = {"Id", "Település", "Név", "Kategória", "Leírás", "Ár", "Kép"};

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

        Object[] fejlec = {"Kategória", "Termékek Száma"};
        Object[][] adatok = new Object[kategoriak.size()][fejlec.length];

        //kategóriák feltölt
        for (int i = 0; i < kategoriak.size(); i++) {
            adatok[i][0] = kategoriak.get(i);

            int db = 0;
            for (int j = 0; j < termekLista.size(); j++) {
                if (kategoriak.get(i).equals(termekLista.get(j).getKategoria())) {
                    db++;
                }
            }
            adatok[i][1] = db;
        }
        return new Tablazat(fejlec, adatok);
    }

    public Tablazat termekAtlagosAraKategoriankent() {

        Object[] fejlec = {"Kategória", "Átlagos ár"};
        int n = kategoriak.size();

        int sum[] = new int[n];
        int db[] = new int[n];

        for (Termek termek : modell.getTermekek()) {
            for (int i = 0; i < kategoriak.size(); i++) {
                if (kategoriak.get(i).equals(termek.getKategoria())) {
                    db[i]++;
                    sum[i] += termek.getAr();
                }
            }
        }
        
        Object[][] adatok = new Object[n][fejlec.length];
        
        for (int i = 0; i < n; i++) {
            adatok[i][0] = kategoriak.get(i);
            adatok[i][1] = db[i] == 0 ? 0 : sum[i] / db[i];
        }
      
        return new Tablazat(fejlec, adatok);
    }

    public Tablazat kategoriaDbMinMaxAr() {
        Object[] fejlec = {"Kategória", "Termékek száma", "Legolcsóbb", "Legdrágább"};
        Object[][] adatok = new Object[kategoriak.size()][fejlec.length];

        //kategóriák feltölt
        for (int i = 0; i < kategoriak.size(); i++) {
            adatok[i][0] = kategoriak.get(i);

            int szamlalo = 0, min = Integer.MAX_VALUE, max = 0;
            for (int j = 0; j < termekLista.size(); j++) {
                if (kategoriak.get(i).equals(termekLista.get(j).getKategoria())) {
                    szamlalo++;
                    min = Math.min(min, termekLista.get(j).getAr());
                    max = Math.max(max, termekLista.get(j).getAr());
                }
            }
            adatok[i][1] = szamlalo;
            adatok[i][2] = szamlalo == 0 ? 0 : min;
            adatok[i][3] = szamlalo == 0 ? 0 : max;
        }
        return new Tablazat(fejlec, adatok);
    }

    public Tablazat termekekSzamaTelepulesenkent() {
        Object[] fejlec = {"Település", "Termékek száma"};

        ArrayList<String> telepulesek = new ArrayList<>();
        ArrayList<Integer> termekDB = new ArrayList<>();

        for (Termek termek : modell.getTermekek()) {
            if (telepulesek.contains(termek.getTelepules())) {
                int index = telepulesek.indexOf(termek.getTelepules());
                termekDB.set(index, termekDB.get(index) + 1);
            } else {
                telepulesek.add(termek.getTelepules());
                termekDB.add(1);
            }
        }

        Object[][] adatok = new Object[telepulesek.size()][fejlec.length];
        for (int i = 0; i < telepulesek.size(); i++) {
            adatok[i][0] = telepulesek.get(i);
            adatok[i][1] = termekDB.get(i);
        }
        return new Tablazat(fejlec, adatok);
    }

}
