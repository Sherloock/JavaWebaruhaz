package diagramok;

import aruhaz.Tablazat;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class Kordiagram {

    public static JPanel build(Tablazat tablazat) {
        tablazat.rendezes(1);
        JFreeChart chart = kordiagramElkeszitese(adatokElokeszitese(tablazat));
        return new ChartPanel(chart);
    }

    private static PieDataset adatokElokeszitese(Tablazat tablazat) {
        DefaultPieDataset adatok = new DefaultPieDataset();
        for (Object[] adat : tablazat.getAdatok()) {
            adatok.setValue(adat[0] + " (" + adat[1] + "Ft)", (Integer) adat[1]);
        }
        return adatok;
    }

    private static JFreeChart kordiagramElkeszitese(PieDataset adatok) {
        JFreeChart chart = ChartFactory.createPieChart(
                "", // chart title 
                adatok, // data    
                false, // include legend   
                true,
                true);

        return chart;
    }
}
