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
  private Statisztika statisztika;


    public TermekekTelepulesenkent(Statisztika statisztika) {

        this.statisztika = statisztika;

    }
    public JPanel build(){
         JFreeChart diagramm = oszlopdiagramElkeszitese();
         return new ChartPanel(diagramm);
    }

    private JFreeChart oszlopdiagramElkeszitese() {
        JFreeChart oszlopdiagramm = ChartFactory.createBarChart(
                "a Termékek darabszáma telelpülésenként",
                "Település",
                "Termékek száma",
                adatokElokeszitese(),
                PlotOrientation.HORIZONTAL,
                true, true, false);

        return oszlopdiagramm;
    }

    private CategoryDataset adatokElokeszitese() {

        
        Tablazat tablazat = statisztika.termekekSzamaTelepulesenkent();

        final DefaultCategoryDataset adatok = new DefaultCategoryDataset();
        for (int i = 0; i < tablazat.getAdatok().length; i++) {
            adatok.addValue((Integer)tablazat.getAdatok()[i][1], "darab", (String)tablazat.getAdatok()[i][0]);
        }

        return adatok;
    }

}
