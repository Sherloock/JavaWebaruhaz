package aruhaz;

import java.util.Arrays;
import java.util.Comparator;

public class Tablazat {
    public final int X;
    public final int Y;
    
    private final Object fejlec[];
    private final Object adatok[][];

    public Tablazat(Object[] fejlec, Object[][] adatok) {
        this.fejlec = fejlec;
        this.adatok = adatok;
        X = adatok[0].length;
        Y = adatok.length;
        rendezes(1);
    }

    public Object[] getFejlec() {
        return fejlec;
    }

    public Object[][] getAdatok() {
        return adatok;
    }
    
    public void rendezes(int oszlop){
        if(oszlop >= 0 && oszlop < fejlec.length){
            if(adatok[0][oszlop].getClass().equals(Integer.class)){ //ha számok csökkenő
                Arrays.sort(adatok, Comparator.comparing((Object[] entry) -> (Integer)entry[oszlop]).reversed());
            }
            
            else if(adatok[0][oszlop].getClass().equals(String.class)){ //ha szöveg abc
                Arrays.sort(adatok, Comparator.comparing((Object[] entry) -> (String)entry[oszlop]));
            }
        }
    }
}
