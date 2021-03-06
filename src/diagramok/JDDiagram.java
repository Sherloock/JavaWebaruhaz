package diagramok;

import aruhaz.Statisztika;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class JDDiagram extends JDialog {
    

    public JDDiagram(JFrame parent, Statisztika statisztika) {
        super(parent, true);
        initComponents();
        init(parent, statisztika);
    }

    private void init(JFrame parent, Statisztika statisztika) {      
        tpDiagramok.add(JPOszlopdiagram.build(statisztika.termekekSzamaTelepulesenkent()), "Termékek száma településenként");
        tpDiagramok.add(JPKordiagram.build(statisztika.termekAtlagosAraKategoriankent()), "Termékek átlagos ára kategóriánként");
        setLocationRelativeTo(parent);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpDiagramok = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Diagramok");
        setMinimumSize(new java.awt.Dimension(200, 200));
        setPreferredSize(new java.awt.Dimension(800, 600));

        tpDiagramok.setPreferredSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 938, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tpDiagramok, javax.swing.GroupLayout.DEFAULT_SIZE, 938, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(tpDiagramok, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tpDiagramok;
    // End of variables declaration//GEN-END:variables
}
