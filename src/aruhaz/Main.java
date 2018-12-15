package aruhaz;

import View.AruhazMainView;

public class Main {

    public static void main(String[] args) {
        Modell m = new Modell("./files/termek.xml", "./files/kategoria.xml");
        AruhazMainView v = new AruhazMainView(m);
        v.setVisible(true);
    }
}
