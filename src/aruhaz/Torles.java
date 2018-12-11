package aruhaz;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class Torles extends JDialog {
     private Modell modell;
    
    public Torles(JFrame parent, Modell modell) {
        super(parent, true);
        initComponents();
        setTitle("Termék törlése");      
        setLocationRelativeTo(parent);
        
        this.modell = modell;
        kategoriakFeltolt();
    }
    private void kategoriakFeltolt() {//kategóriák lenyíló menü feltöltése
        Collection<String> collection = new TreeSet<String>(Collator.getInstance());
        
        for (int i = 0; i < modell.getTermekek().size(); i++) {
            Termek t = modell.getTermekek().get(i);
            collection.add(t.getNev() + " - " + t.ID);
        }
        
        ArrayList<String> list = new ArrayList<String>(collection);
        
        for (int i = 0; i < list.size(); i++) {   
            cbTermekek.addItem(list.get(i));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEltavolit = new javax.swing.JButton();
        btnMegse = new javax.swing.JButton();
        cbTermekek = new javax.swing.JComboBox<>();

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
       modell.termekTorlese(cbTermekek.getSelectedItem().toString().split("\\ - ")[1]);
        this.dispose();
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
