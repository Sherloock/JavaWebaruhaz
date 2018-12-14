package diagramok;

import aruhaz.Modell;
import aruhaz.Statisztika;
import aruhaz.Tablazat;
import aruhaz.Termek;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class TermekekAratlagKategoriankent {

    private Statisztika statisztika;
    
    public TermekekAratlagKategoriankent(Statisztika statisztika) {
        this.statisztika = statisztika;
    }

    public JPanel build() {
       JFreeChart chart = kordiagramElkeszitese(adatokElokeszitese());
        return new ChartPanel(chart);
    }



    private PieDataset adatokElokeszitese() {
        Tablazat tablazat = statisztika.termekAtlagosAraKategoriankent();
        
        
        DefaultPieDataset adatok = new DefaultPieDataset();
        for (int i = 0; i < tablazat.getAdatok().length; i++) {
            adatok.setValue(tablazat.getAdatok()[i][0] + " (" + tablazat.getAdatok()[i][1] + "Ft)",
                    (Integer)tablazat.getAdatok()[i][1]);
            
//            int atlag = db[i] == 0 ? 0 : sum[i] / db[i];
//            if (atlag != 0) {
//                adatok.setValue(kategoriak.get(i) + " (" + atlag + "Ft)", atlag);
//            }
        }
        return adatok;
    }

    private JFreeChart kordiagramElkeszitese(PieDataset adatok) {
        JFreeChart chart = ChartFactory.createPieChart(
                "a termékek átlagos ára kategóriánként", // chart title 
                adatok, // data    
                true, // include legend   
                true,
                false);

        return chart;
    }
}
