package diagramok;

import aruhaz.Statisztika;
import aruhaz.Tablazat;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class TermekekTelepulesenkent {

    public JPanel build(Statisztika statisztika) {
        JFreeChart diagramm = oszlopdiagramElkeszitese(statisztika);
        return new ChartPanel(diagramm);
    }

    private JFreeChart oszlopdiagramElkeszitese(Statisztika statisztika) {
        JFreeChart oszlopdiagramm = ChartFactory.createBarChart(
                "",
                "Település",
                "Termékek száma",
                adatokElokeszitese(statisztika),
                PlotOrientation.HORIZONTAL,
                true, true, false);

        return oszlopdiagramm;
    }

    private CategoryDataset adatokElokeszitese(Statisztika statisztika) {
        Tablazat tablazat = statisztika.termekekSzamaTelepulesenkent();

        final DefaultCategoryDataset adatok = new DefaultCategoryDataset();
        for (Object[] adat : tablazat.getAdatok()) {
            adatok.addValue((Integer) adat[1], "darab", (String) adat[0]);
        }

        return adatok;
    }
}
