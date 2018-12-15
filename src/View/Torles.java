package View;

import aruhaz.Modell;
import aruhaz.Termek;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Torles extends JDialog {

    private final Modell modell;
    private ArrayList<String> termekekStrings = new ArrayList<>();
    

    public Torles(JFrame parent, Modell modell) {
        super(parent, true);
        initComponents();
       
        this.modell = modell;
        termekekStringsFeltolt();
        
         setLocationRelativeTo(parent);
    }
    
    private void termekekStringsFeltolt() {
        Collection<String> collection = new TreeSet<>(Collator.getInstance());

        for (int i = 0; i < modell.getTermekek().size(); i++) {
            Termek t = modell.getTermekek().get(i);
            collection.add(t.getNev() + ";" + t.ID);
        }

        termekekStrings = new ArrayList<>(collection);

        for (int i = 0; i < termekekStrings.size(); i++) {
            cbTermekek.addItem(termekekStrings.get(i).split("\\;")[0]);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEltavolit = new javax.swing.JButton();
        btnMegse = new javax.swing.JButton();
        cbTermekek = new javax.swing.JComboBox<>();

        setTitle("Termék törlése");

        btnEltavolit.setText("Eltávolítás");
        btnEltavolit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEltavolitActionPerformed(evt);
            }
        });

        btnMegse.setText("Mégse");
        btnMegse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMegseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEltavolit, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnMegse, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbTermekek, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cbTermekek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMegse)
                    .addComponent(btnEltavolit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEltavolitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEltavolitActionPerformed
        int index = cbTermekek.getSelectedIndex();
        String nev = termekekStrings.get(index).split(";")[0];
        String id = termekekStrings.get(index).split(";")[1];
        
        
        if (JOptionPane.showConfirmDialog(this, "Biztos törölni akarja \"" 
                + nev + "\" nevű terméket? (Azonosító:" + id+")", "Termék törlése", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
           
            
            modell.termekTorlese(id);
            this.dispose();
        }
    }//GEN-LAST:event_btnEltavolitActionPerformed

    private void btnMegseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMegseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnMegseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEltavolit;
    private javax.swing.JButton btnMegse;
    private javax.swing.JComboBox<String> cbTermekek;
    // End of variables declaration//GEN-END:variables
}
