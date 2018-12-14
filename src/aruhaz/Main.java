package aruhaz;

public class Main {

    public static void main(String[] args) {
           Modell m = new Modell("./files/termek.xml", "./files/kategoria.xml");          
           WebaruhazMainView v = new WebaruhazMainView(m);
           
           v.setVisible(true);
       }
}
