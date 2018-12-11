package aruhaz;

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
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnEltavolit = new javax.swing.JButton();
        btnMegse1 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();

        btnEltavolit.setText("Eltávolítás");
        btnEltavolit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEltavolitActionPerformed(evt);
            }
        });

        btnMegse1.setText("Mégse");
        btnMegse1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMegse1ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEltavolit, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnMegse1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMegse1)
                    .addComponent(btnEltavolit))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEltavolitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEltavolitActionPerformed
      // modell.eltavolit(jComboBox1.getSelectedItem().toString());
        this.dispose();
    }//GEN-LAST:event_btnEltavolitActionPerformed

    private void btnMegse1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMegse1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnMegse1ActionPerformed
                               


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEltavolit;
    private javax.swing.JButton btnMegse1;
    private javax.swing.JComboBox<String> jComboBox1;
    // End of variables declaration//GEN-END:variables
}
