package diagramok;


import aruhaz.Statisztika;
import javax.swing.JDialog;
import javax.swing.JFrame;


public class Digrammok extends JDialog {


    private Statisztika statisztika;

    public Digrammok(JFrame parent, Statisztika statisztika) {
        super(parent, true);
        initComponents();
        
        this.statisztika = statisztika;
        
        init(parent);
    }

    private void init(JFrame parent) {
        setTitle("Diagrammok");
        
        tpDiagramok.add(new TermekekTelepulesenkent(statisztika).build(), "Termékek száma településenként");
        tpDiagramok.add(new TermekekAratlagKategoriankent(statisztika).build(), "Termékek átlagos ára kategóriánként");
        



        //setSize(this.getPreferredSize());
        setSize(800, 600);
        setLocationRelativeTo(parent);
    }



    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpDiagramok = new javax.swing.JTabbedPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(200, 200));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 938, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(tpDiagramok)
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 540, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(tpDiagramok)
                    .addContainerGap()))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane tpDiagramok;
    // End of variables declaration//GEN-END:variables
}
