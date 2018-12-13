package aruhaz;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class PdfKimutatas extends JDialog {

    private Modell modell;
    private ArrayList<String> termekekStrings = new ArrayList<>();

    public PdfKimutatas(JFrame parent, Modell modell) {
        super(parent, true);
        initComponents();
        setTitle("Termék törlése");
        setLocationRelativeTo(parent);

        this.modell = modell;
        
    }

  

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 88, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
