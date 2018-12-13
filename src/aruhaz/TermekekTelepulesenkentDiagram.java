package aruhaz;

import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class TermekekTelepulesenkentDiagram extends JDialog {

    private Modell modell;


    public TermekekTelepulesenkentDiagram(JFrame parent, Modell modell) {
        super(parent, true);
         initComponents();
        this.modell = modell;
        init(parent);
    }
    
    private void init(JFrame parent){
        setTitle("Grafikon");
        setContentPane(panelElkeszitese());
        setSize(this.getPreferredSize());
        setLocationRelativeTo(parent);
    }

    private JPanel panelElkeszitese() {
        JFreeChart diagramm = oszlopdiagramElkeszitese();
        ////TICKEK BEÁLLíTÁSAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA

        
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
        ArrayList<String> telepulesek = new ArrayList<>();
        ArrayList<Integer> termekDB = new ArrayList<>();
        
        for (Termek termek : modell.getTermekek()) {
            if(telepulesek.contains(termek.getTelepules())){
                int index =  telepulesek.indexOf(termek.getTelepules());
                termekDB.set(index, termekDB.get(index)+1);
            }
            else{
                telepulesek.add(termek.getTelepules());
                termekDB.add(1);
            }
        }
        
        final DefaultCategoryDataset adatok = new DefaultCategoryDataset();
        for (int i = 0; i < telepulesek.size(); i++) {
            adatok.addValue(termekDB.get(i), "darab", telepulesek.get(i));
        }

        return adatok;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pGrafikon = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout pGrafikonLayout = new javax.swing.GroupLayout(pGrafikon);
        pGrafikon.setLayout(pGrafikonLayout);
        pGrafikonLayout.setHorizontalGroup(
            pGrafikonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 926, Short.MAX_VALUE)
        );
        pGrafikonLayout.setVerticalGroup(
            pGrafikonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 528, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 938, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pGrafikon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pGrafikon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pGrafikon;
    // End of variables declaration//GEN-END:variables
}
