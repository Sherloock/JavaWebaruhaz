package main;

import view.JFMainView;
import aruhaz.Modell;

public class Main {
    public static void main(String[] args) {
        Modell m = new Modell("./files/termek.xml", "./files/kategoria.xml");
        JFMainView v = new JFMainView(m);
        v.setVisible(true);
    }
}
