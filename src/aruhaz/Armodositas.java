package aruhaz;

import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Armodositas extends JDialog {

    private final Modell modell;
    private final ArrayList<String> termekekStrings;
    private final WebaruhazMainView view;

    public Armodositas(JFrame parent, Modell modell, ArrayList<String> termekekStrings, WebaruhazMainView view) {
        super(parent, true);
        initComponents();

        this.termekekStrings = termekekStrings;
        this.view = view;
        this.modell = modell;
        termeklistaBellitasaComboBoxra();
        
        init(parent);
    }

    private void init(JFrame parent) {
        setTitle("Ár módosítás");
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
    }

    private void termeklistaBellitasaComboBoxra() {
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
        btnMegse = new javax.swing.JButton();

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

        labelTermekKiv.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        labelTermekKiv.setText("Termék kiválasztása");

        btnArValtoztatas.setText("Ár megváltoztatása");
        btnArValtoztatas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArValtoztatasActionPerformed(evt);
            }
        });

        labelArValtUzenet.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelArValt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(labelTermekKiv)
                                .addGap(18, 18, 18)
                                .addComponent(cbTermekek, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(36, 36, 36))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(sliderArValt, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnArValtoztatas, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMegse, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelArValtUzenet, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
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
                .addGap(4, 4, 4)
                .addComponent(labelArValtUzenet, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnArValtoztatas)
                    .addComponent(btnMegse))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMegseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMegseActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnMegseActionPerformed

    private void btnArValtoztatasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArValtoztatasActionPerformed
        double valtoztatasMerteke = sliderArValt.getValue() / 100.0;
        if (valtoztatasMerteke != 0.0) {

            int indexAbc = cbTermekek.getSelectedIndex();

            //Összes    egy termék
            int id = (indexAbc == 0) ? -1 : Integer.parseInt(termekekStrings.get(indexAbc - 1).split(";")[1]);

            if (JOptionPane.showConfirmDialog(this, "Biztos meg akarja változtatni a kiválaszott termék árát? ("
                    + (id == -1 ? "Összes termék" : modell.getTermekById(id).getNev())
                    + ")", "Termék törlése", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {

                //összes
                if (id == -1) {
                    modell.arvaltoztatas(valtoztatasMerteke);
                    labelArValtUzenet.setText("Az összes termék ára megváltozott " + sliderArValt.getValue() + "%-al!");
                } //egy termék
                else {
                    int elozoAr = modell.getTermekById(id).getAr();
                    modell.arvaltoztatas(id, valtoztatasMerteke);

                    labelArValtUzenet.setText(modell.getTermekById(id).getNev() + " termék ára megváltozott " + sliderArValt.getValue() + "%-al! ("
                            + elozoAr + " --> " + modell.getTermekById(id).getAr() + ")");

                }
                view.adatokFrissitese();
            }
        }
    }//GEN-LAST:event_btnArValtoztatasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArValtoztatas;
    private javax.swing.JButton btnMegse;
    private javax.swing.JComboBox<String> cbTermekek;
    private javax.swing.JLabel labelArValt;
    private javax.swing.JLabel labelArValtUzenet;
    private javax.swing.JLabel labelTermekKiv;
    private javax.swing.JSlider sliderArValt;
    // End of variables declaration//GEN-END:variables
}
