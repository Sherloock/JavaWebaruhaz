package diagramok;

import aruhaz.Tablazat;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class Oszlopdiagram {

    public static JPanel build(Tablazat tablazat) {
        JFreeChart diagramm = oszlopdiagramElkeszitese(tablazat);
        return new ChartPanel(diagramm);
    }

    private static JFreeChart oszlopdiagramElkeszitese(Tablazat tablazat) {
        JFreeChart oszlopdiagramm = ChartFactory.createBarChart(
                "",
                tablazat.getFejlec()[0].toString(),
                tablazat.getFejlec()[1].toString(),
                adatokElokeszitese(tablazat),
                PlotOrientation.HORIZONTAL,
                true, true, false);

        return oszlopdiagramm;
    }

    private static CategoryDataset adatokElokeszitese(Tablazat tablazat) {
        final DefaultCategoryDataset adatok = new DefaultCategoryDataset();
        for (Object[] adat : tablazat.getAdatok()) {
            adatok.addValue((Integer) adat[1], "darab", (String) adat[0]);
        }

        return adatok;
    }
}
