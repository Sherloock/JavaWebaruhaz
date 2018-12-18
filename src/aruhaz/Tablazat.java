package aruhaz;

import java.util.Arrays;
import java.util.Comparator;

public class Tablazat {

    public static final String ID = "Id";
    public static final String TELEP = "Települes";
    public static final String T_NEV = "Termék neve";
    public static final String KAT = "Kategória";
    public static final String LEIRAS = "Leírás";
    public static final String AR = "Ár";
    public static final String KEP = "Kép elérési címe";
    public static final String T_MIN = "Legolcsóbb termék";
    public static final String T_MAX = "Legdrágabb termék";
    public static final String T_SZAMA = "Termékek száma";
    public static final String AR_ATL = "Átlagos ár";

    public final int X;
    public final int Y;

    private final Object fejlec[];
    private final Object adatok[][];

    public Tablazat(Object[] fejlec, Object[][] adatok) {
        this.fejlec = fejlec;
        this.adatok = adatok;
        X = adatok[0].length;
        Y = adatok.length;
        rendezes(1, false); 
    }

    public Object[] getFejlec() {
        return fejlec;
    }

    public Object[][] getAdatok() {
        return adatok;
    }

    //alapból növekvő sorrend
    public void rendezes(int oszlop) {
        rendezes(oszlop, true);
    }

    public void rendezes(int oszlop, boolean novekvoSorrend) {
        if (oszlop >= 0 && oszlop < fejlec.length) {

            if (adatok[0][oszlop].getClass().equals(Integer.class)) {
                if (novekvoSorrend) {
                    Arrays.sort(adatok, Comparator.comparing((Object[] entry) -> (Integer) entry[oszlop]));
                } else {
                    Arrays.sort(adatok, Comparator.comparing((Object[] entry) -> (Integer) entry[oszlop]).reversed());
                }
            }
            
            else if (adatok[0][oszlop].getClass().equals(String.class)) {
                if (novekvoSorrend) {
                    Arrays.sort(adatok, Comparator.comparing((Object[] entry) -> (String) entry[oszlop]));
                } else {
                    Arrays.sort(adatok, Comparator.comparing((Object[] entry) -> (String) entry[oszlop]).reversed());
                }
            }
        }
    }
}
