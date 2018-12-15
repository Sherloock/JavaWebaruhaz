package diagramok;

import aruhaz.Statisztika;
import aruhaz.Tablazat;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class TermekekAratlagKategoriankent {

    public JPanel build(Statisztika statisztika) {
        JFreeChart chart = kordiagramElkeszitese(adatokElokeszitese(statisztika));
        return new ChartPanel(chart);
    }

    private PieDataset adatokElokeszitese(Statisztika statisztika) {
        Tablazat tablazat = statisztika.termekAtlagosAraKategoriankent();

        DefaultPieDataset adatok = new DefaultPieDataset();
        for (Object[] adat : tablazat.getAdatok()) {
            adatok.setValue(adat[0] + " (" + adat[1] + "Ft)", (Integer) adat[1]);
        }
        return adatok;
    }

    private JFreeChart kordiagramElkeszitese(PieDataset adatok) {
        JFreeChart chart = ChartFactory.createPieChart(
                "", // chart title 
                adatok, // data    
                true, // include legend   
                true,
                false);

        return chart;
    }
}
