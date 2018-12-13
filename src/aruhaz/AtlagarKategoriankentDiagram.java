package aruhaz;

import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class AtlagarKategoriankentDiagram extends JDialog {

    private Modell modell;
    

    public AtlagarKategoriankentDiagram(JFrame parent, Modell modell) {
        super(parent, true);
        initComponents();
        
        this.modell = modell;
        
        init(parent);
    }

    private void init(JFrame parent) {
        setTitle("Grafikon");
        setContentPane(panelElkeszitese());
        setSize(this.getPreferredSize());
        setLocationRelativeTo(parent);
    }

    private JPanel panelElkeszitese() {
        JFreeChart chart = kordiagramElkeszitese(adatokElokeszitese());
        return new ChartPanel(chart);
    }

    private PieDataset adatokElokeszitese() {
        int n = modell.getKategoriak().size();

        ArrayList<String> kategoriak = modell.getKategoriak();
        int sum[] = new int[n];
        int db[] = new int[n];

        for (Termek termek : modell.getTermekek()) {
            for (int i = 0; i < kategoriak.size(); i++) {
                if (kategoriak.get(i).equals(termek.getKategoria())) {
                    db[i]++;
                    sum[i] += termek.getAr();
                }
            }
        }

        DefaultPieDataset adatok = new DefaultPieDataset();
        for (int i = 0; i < n; i++) {
            int atlag = db[i] == 0 ? 0 : sum[i] / db[i];
            if (atlag != 0) {
                adatok.setValue(kategoriak.get(i) + " (" + atlag + "Ft)", atlag);
            }
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
