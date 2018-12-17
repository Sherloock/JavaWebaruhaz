package View;

import aruhaz.Termek;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

public class Torles extends JDialog {

    private final AruhazMainView view;
    private ArrayList<String> termekekStrings = new ArrayList<>();

    public Torles(AruhazMainView view) {
        super(view, true);

        initComponents();
        this.view = view;
        termekekStringsFeltolt();
    }

    private void termekekStringsFeltolt() {
        Collection<String> collection = new TreeSet<>(Collator.getInstance());

        for (int i = 0; i < view.getModell().getTermekek().size(); i++) {
            Termek t = view.getModell().getTermekek().get(i);
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

        btnTorol = new javax.swing.JButton();
        btnMegse = new javax.swing.JButton();
        cbTermekek = new javax.swing.JComboBox<>();

        setTitle("Termék törlése");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnTorol.setText("Töröl");
        btnTorol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTorolActionPerformed(evt);
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
                .addComponent(btnTorol, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(btnTorol))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTorolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTorolActionPerformed
        int index = cbTermekek.getSelectedIndex();
        String nev = termekekStrings.get(index).split(";")[0];
        String id = termekekStrings.get(index).split(";")[1];

        if (JOptionPane.showConfirmDialog(this, "Biztos törölni akarja \""+ nev + "\" nevű terméket? (Azonosító:" + id + ")", "Termék törlése", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {

            view.getModell().termekTorlese(id);
            ((AruhazMainView)this.getParent()).adatokFrissitese();
            this.dispose();
        }
    }//GEN-LAST:event_btnTorolActionPerformed

    private void btnMegseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMegseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnMegseActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setLocationRelativeTo(this.getParent());
    }//GEN-LAST:event_formWindowOpened


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnMegse;
    private javax.swing.JButton btnTorol;
    private javax.swing.JComboBox<String> cbTermekek;
    // End of variables declaration//GEN-END:variables
}
