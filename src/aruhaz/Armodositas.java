package aruhaz;

import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

public class Armodositas extends JDialog {

    private Modell modell;
     private ArrayList<String> termekekStrings;

    public Armodositas(JFrame parent, Modell modell, ArrayList<String> termekekStrings) {
        super(parent, true);     
        this.termekekStrings = termekekStrings;
        
        initComponents();
        setTitle("Árak módosítása");      
        setLocationRelativeTo(parent);

        //Java elemek magyarítása
        UIManager.put("FileChooser.openDialogTitleText", "Forrásfájl megnyitása");
        UIManager.put("FileChooser.lookInLabelText", "Aktuális mappa:");
        UIManager.put("FileChooser.openButtonText", "Megnyitás");
        UIManager.put("FileChooser.cancelButtonText", "Mégse");
        UIManager.put("FileChooser.fileNameLabelText", "Fájl neve:");
        UIManager.put("FileChooser.filesOfTypeLabelText", "Fájl típusa:");
        UIManager.put("OptionPane.noButtonText", "Nem");
        UIManager.put("OptionPane.yesButtonText", "Ok");
        
        this.modell = modell;
        
        cbTermekek.removeAllItems();
        cbTermekek.addItem("Összes termék");
        for (int i = 0; i < termekekStrings.size(); i++) {
            cbTermekek.addItem(termekekStrings.get(i).split("\\;")[0]);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelArValt = new javax.swing.JLabel();
        sliderArValt = new javax.swing.JSlider();
        cbTermekek = new javax.swing.JComboBox<>();
        labelTermekKiv = new javax.swing.JLabel();
        btnArValtoztatas = new javax.swing.JButton();
        labelArValtUzenet = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelArValt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelArValt.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelArValt.setText("Ár megváltoztatása (adott százalékkal)");

        sliderArValt.setMajorTickSpacing(5);
        sliderArValt.setMaximum(30);
        sliderArValt.setMinimum(-30);
        sliderArValt.setMinorTickSpacing(5);
        sliderArValt.setPaintLabels(true);
        sliderArValt.setPaintTicks(true);
        sliderArValt.setSnapToTicks(true);
        sliderArValt.setValue(0);
        sliderArValt.setAutoscrolls(true);
        sliderArValt.setPreferredSize(new java.awt.Dimension(200, 30));
        sliderArValt.setValueIsAdjusting(true);

        cbTermekek.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbTermekekActionPerformed(evt);
            }
        });

        labelTermekKiv.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelTermekKiv.setText("Termék kiválasztása");

        btnArValtoztatas.setText("Ár megváltoztatása");
        btnArValtoztatas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArValtoztatasActionPerformed(evt);
            }
        });

        labelArValtUzenet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelArValtUzenet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sliderArValt, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(labelTermekKiv)
                                        .addGap(18, 18, 18)
                                        .addComponent(cbTermekek, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(30, 30, 30)))
                                .addContainerGap())
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnArValtoztatas, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(190, 190, 190))))
                    .addComponent(labelArValt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbTermekek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTermekKiv, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(labelArValt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sliderArValt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnArValtoztatas)
                .addGap(18, 18, 18)
                .addComponent(labelArValtUzenet, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cbTermekekActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTermekekActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbTermekekActionPerformed

    private void btnArValtoztatasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArValtoztatasActionPerformed
        double valtoztatasMerteke = sliderArValt.getValue() / 100.0;

        if (valtoztatasMerteke != 0.0) {
            int index = cbTermekek.getSelectedIndex();

            //összes
            if (index == 0) {
                modell.arvaltoztatas(valtoztatasMerteke);
                labelArValtUzenet.setText("Az összes termék ára megváltozott " + sliderArValt.getValue() + "%-al!");
            } //egy termék
            else {
                index--;
                String idStr = termekekStrings.get(index).split(";")[1];
                int id = Integer.parseInt(idStr);

                int elozoAr = modell.getTermekById(id).getAr();
                modell.arvaltoztatas(id, valtoztatasMerteke);

                labelArValtUzenet.setText(modell.getTermekById(id).getNev() + " termék ára megváltozott " + sliderArValt.getValue() + "%-al! (" +
                    elozoAr +" --> " + modell.getTermekById(id).getAr() + ")");
            }
        }
    }//GEN-LAST:event_btnArValtoztatasActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArValtoztatas;
    private javax.swing.JComboBox<String> cbTermekek;
    private javax.swing.JLabel labelArValt;
    private javax.swing.JLabel labelArValtUzenet;
    private javax.swing.JLabel labelTermekKiv;
    private javax.swing.JSlider sliderArValt;
    // End of variables declaration//GEN-END:variables
}
