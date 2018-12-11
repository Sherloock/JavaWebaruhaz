package aruhaz;

import java.io.File;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.UIManager;


public class Felvitel extends JDialog {

    Modell modell;

    public Felvitel(JFrame parent, Modell modell) {
        super(parent, true);     
        
        initComponents();
        setTitle("Termék felvitele");      
        setLocationRelativeTo(parent);

        //Java elemek magyarítása
        UIManager.put("FileChooser.openDialogTitleText", "Forrásfájl megnyitása");
        UIManager.put("FileChooser.lookInLabelText", "Aktuális mappa:");
        UIManager.put("FileChooser.openButtonText", "Megnyitás");
        UIManager.put("FileChooser.cancelButtonText", "Mégse");
        UIManager.put("FileChooser.fileNameLabelText", "Fájl neve:");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Fájl típusa:");
        UIManager.put("OptionPane.noButtonText", "Nem");
        UIManager.put("OptionPane.yesButtonText", "Igen");
        
        this.modell = modell;
        
        kategoriakFeltolt();
        telepulesekFeltolt();
    }

    private void kategoriakFeltolt() {//kategóriák lenyíló menü feltöltése
        for (String kategoria : modell.getKategoriak()) {
            cbKategoria.addItem(kategoria);
        }
    }
    
    private void telepulesekFeltolt() {//kategóriák lenyíló menü feltöltése
        for (String kategoria : modell.getTelepulesek()) {
            cbTelepules.addItem(kategoria);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfAr = new javax.swing.JTextField();
        btnTalloz = new javax.swing.JButton();
        lbNev = new javax.swing.JLabel();
        tfKepPath = new javax.swing.JTextField();
        lbTelep = new javax.swing.JLabel();
        lbKat = new javax.swing.JLabel();
        lbLeir = new javax.swing.JLabel();
        lbKep = new javax.swing.JLabel();
        spLeiras = new javax.swing.JScrollPane();
        taLeiras = new javax.swing.JTextArea();
        tfNev = new javax.swing.JTextField();
        btnFelvisz = new javax.swing.JButton();
        btnKilepesF = new javax.swing.JButton();
        cbTelepules = new javax.swing.JComboBox<>();
        cbKategoria = new javax.swing.JComboBox<>();
        lbNev1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tfAr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfArActionPerformed(evt);
            }
        });

        btnTalloz.setText("jButton1");
        btnTalloz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTallozActionPerformed(evt);
            }
        });

        lbNev.setText("Név:");

        lbTelep.setText("Település:");

        lbKat.setText("Kategória:");

        lbLeir.setText("Leírás:");

        lbKep.setText("Kép:");

        taLeiras.setColumns(20);
        taLeiras.setRows(15);
        taLeiras.setTabSize(25);
        spLeiras.setViewportView(taLeiras);

        tfNev.setToolTipText("Minta János");
        tfNev.setName(""); // NOI18N

        btnFelvisz.setText("Felvitel");
        btnFelvisz.setMaximumSize(new java.awt.Dimension(68, 32));
        btnFelvisz.setMinimumSize(new java.awt.Dimension(68, 32));
        btnFelvisz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFelviszActionPerformed(evt);
            }
        });

        btnKilepesF.setText("Mégse");
        btnKilepesF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKilepesFActionPerformed(evt);
            }
        });

        cbKategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKategoriaActionPerformed(evt);
            }
        });

        lbNev1.setText("Ár:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbNev)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbKat)
                                    .addComponent(lbTelep)
                                    .addComponent(lbKep)
                                    .addComponent(lbNev1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(tfKepPath)
                                    .addComponent(cbKategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cbTelepules, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(tfAr)
                                    .addComponent(tfNev, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnTalloz, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lbLeir)
                                    .addComponent(spLeiras, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnFelvisz, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnKilepesF, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNev))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNev1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelep)
                    .addComponent(cbTelepules, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbKat)
                    .addComponent(cbKategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfKepPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTalloz, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbKep))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbLeir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(spLeiras, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnFelvisz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnKilepesF))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tfArActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfArActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfArActionPerformed



    private void btnTallozActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTallozActionPerformed
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.showOpenDialog(null);
            File f = chooser.getSelectedFile();
            String filename = f.getAbsolutePath();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Érvénytelen tallózási útvonal!", "Hiba!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTallozActionPerformed


    private boolean ellenorizNev() {
        return (tfNev.getText().length() > 0 && tfNev.getText().length() <= 30);
    }
    
    private boolean ellenorizLeiras() {
        return (taLeiras.getText().length() >= 30 && taLeiras.getText().length() <= 200);
    }

    private boolean ellenorizAr() {
        try {
            return Integer.parseInt(tfAr.getText()) > 0;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private boolean ellenorizKep() {
        return !tfKepPath.getText().isEmpty();
    }

    private boolean ellenorzes() {
        String hiba = "";
        if (!ellenorizNev()) {
            hiba += "A névnek minimum 1 maximum 30 karakter hosszúnak kell lennie!\n";
        }

        if (!ellenorizAr()) {
            hiba += "Az árnak pozitív számnak kell lennie!\n";
        }
        if (!ellenorizKep()) {
            tfKepPath.setText("Hiányzó adat");
        }
        if (!ellenorizLeiras()) {
            hiba += "A leírásnak minimum 30 maximum 200 karakter hosszúnak kell lennie!\n";
        }
        if (hiba.isEmpty()) {
            return true;
        } else {
            JOptionPane.showMessageDialog(this, hiba, "Hiba!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    private void btnFelviszActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFelviszActionPerformed
        if (ellenorzes()) {
            modell.termekHozzadasa(
                cbTelepules.getSelectedItem().toString(), 
                tfNev.getText(), 
                cbKategoria.getSelectedItem().toString(), 
                taLeiras.getText(), 
                tfAr.getText(), 
                tfKepPath.getText()
            );
            this.dispose();
        }
    }//GEN-LAST:event_btnFelviszActionPerformed

    private void btnKilepesFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKilepesFActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_btnKilepesFActionPerformed

    private void cbKategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbKategoriaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFelvisz;
    private javax.swing.JButton btnKilepesF;
    private javax.swing.JButton btnTalloz;
    private javax.swing.JComboBox<String> cbKategoria;
    private javax.swing.JComboBox<String> cbTelepules;
    private javax.swing.JLabel lbKat;
    private javax.swing.JLabel lbKep;
    private javax.swing.JLabel lbLeir;
    private javax.swing.JLabel lbNev;
    private javax.swing.JLabel lbNev1;
    private javax.swing.JLabel lbTelep;
    private javax.swing.JScrollPane spLeiras;
    private javax.swing.JTextArea taLeiras;
    private javax.swing.JTextField tfAr;
    private javax.swing.JTextField tfKepPath;
    private javax.swing.JTextField tfNev;
    // End of variables declaration//GEN-END:variables
}
