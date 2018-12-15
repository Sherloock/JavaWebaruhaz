package aruhaz;

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
    }

    public Object[] getFejlec() {
        return fejlec;
    }

    public Object[][] getAdatok() {
        return adatok;
    }
}
