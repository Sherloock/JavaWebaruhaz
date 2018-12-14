package aruhaz;

public class Tablazat {
    private Object fejlec[];
    private Object adatok[][];

    public Tablazat(Object[] fejlec, Object[][] adatok) {
        this.fejlec = fejlec;
        this.adatok = adatok;
    }

    public Object[] getFejlec() {
        return fejlec;
    }

    public Object[][] getAdatok() {
        return adatok;
    }
}
