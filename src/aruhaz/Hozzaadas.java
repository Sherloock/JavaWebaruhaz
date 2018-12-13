package aruhaz;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Hozzaadas extends JDialog {

    private Modell modell;
    

    public Hozzaadas(JFrame parent, Modell modell) {
        super(parent, true);

        initComponents();
        
        this.modell = modell;
        kategoriakFeltolt();
        telepulesekFeltolt();
        
        init(parent);
    }
    
    private void init(JFrame parent) {
        setTitle("Termék hozzáadása");
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

    private void kategoriakFeltolt() {//kategóriák lenyíló menü feltöltése     
        ArrayList<String> kategoriaStrings = new ArrayList<String>(modell.getKategoriak());
        Collections.sort(kategoriaStrings);

        for (int i = 0; i < kategoriaStrings.size(); i++) {
            cbKategoria.addItem(kategoriaStrings.get(i).split("\\;")[0]);
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
        lb30200 = new javax.swing.JLabel();
        l200 = new javax.swing.JLabel();
        lbKarakterSzam = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnTalloz.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnTalloz.setText("Tallózás");
        btnTalloz.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTallozActionPerformed(evt);
            }
        });

        lbNev.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNev.setText("Név:");

        lbTelep.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbTelep.setText("Település:");

        lbKat.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbKat.setText("Kategória:");

        lbLeir.setText("Leírás:");

        lbKep.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbKep.setText("Kép:");

        taLeiras.setColumns(20);
        taLeiras.setLineWrap(true);
        taLeiras.setRows(15);
        taLeiras.setTabSize(25);
        taLeiras.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                taLeirasKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                taLeirasKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                taLeirasKeyTyped(evt);
            }
        });
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

        lbNev1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbNev1.setText("Ár:");

        lb30200.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lb30200.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb30200.setText("30-200 karakter");

        l200.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        l200.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        l200.setText("/ 200");

        lbKarakterSzam.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lbKarakterSzam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbKarakterSzam.setText("0");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbLeir)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(lbKat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbTelep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbNev, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbNev1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbKep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnFelvisz, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnKilepesF, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lb30200)
                        .addGap(141, 141, 141)
                        .addComponent(lbKarakterSzam, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(l200)
                        .addGap(0, 9, Short.MAX_VALUE))
                    .addComponent(spLeiras)
                    .addComponent(cbKategoria, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbTelepules, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfAr, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfNev)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(tfKepPath, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTalloz, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfNev, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNev))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfAr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbNev1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbTelep)
                    .addComponent(cbTelepules, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbKat)
                    .addComponent(cbKategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfKepPath, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTalloz, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbKep))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbLeir)
                        .addGap(110, 110, 110))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spLeiras, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(l200)
                        .addComponent(lbKarakterSzam))
                    .addComponent(lb30200))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnKilepesF)
                    .addComponent(btnFelvisz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTallozActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTallozActionPerformed
        try {
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(".\\images"));

            //kép filter
            FileFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
            chooser.setFileFilter(imageFilter);

            chooser.showOpenDialog(null);
            File file = chooser.getSelectedFile();

            File directory = new File("./");
            String konyvtar = directory.getAbsolutePath();
            file.getAbsolutePath().replace(konyvtar.substring(0, konyvtar.length()-1), "");
            
            tfKepPath.setText(file.getAbsolutePath().replace(konyvtar.substring(0, konyvtar.length()-1), ""));
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

    private boolean ellenorizFile() {
        File f = new File(tfKepPath.getText());
        String mimetype= new MimetypesFileTypeMap().getContentType(f);
        String type = mimetype.split("/")[0];
        
        return !tfKepPath.getText().isEmpty() && f.exists() && !f.isDirectory() && type.equals("image");
    }
    

    private boolean ellenorzes() {
        String hiba = "";
        if (!ellenorizNev()) {
            hiba += "A névnek minimum 1 maximum 30 karakter hosszúnak kell lennie!\n";
        }
        if (!ellenorizAr()) {
            hiba += "Az árnak pozitív számnak kell lennie!\n";
        }
        if (!ellenorizFile()) {
            hiba += "Kötelező képet feltölteni!\n";
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
        this.dispose();
    }//GEN-LAST:event_btnKilepesFActionPerformed

    private void taLeirasKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taLeirasKeyTyped
        lbKarakterSzam.setText(taLeiras.getText().length() + "");
        lbKarakterSzam.setForeground(ellenorizLeiras() ? Color.black : Color.red);
    }//GEN-LAST:event_taLeirasKeyTyped

    private void taLeirasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taLeirasKeyPressed
        lbKarakterSzam.setText(taLeiras.getText().length() + "");
        lbKarakterSzam.setForeground(ellenorizLeiras() ? Color.black : Color.red);
    }//GEN-LAST:event_taLeirasKeyPressed

    private void taLeirasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_taLeirasKeyReleased
        lbKarakterSzam.setText(taLeiras.getText().length() + "");
        lbKarakterSzam.setForeground(ellenorizLeiras() ? Color.black : Color.red);
    }//GEN-LAST:event_taLeirasKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFelvisz;
    private javax.swing.JButton btnKilepesF;
    private javax.swing.JButton btnTalloz;
    private javax.swing.JComboBox<String> cbKategoria;
    private javax.swing.JComboBox<String> cbTelepules;
    private javax.swing.JLabel l200;
    private javax.swing.JLabel lb30200;
    private javax.swing.JLabel lbKarakterSzam;
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
