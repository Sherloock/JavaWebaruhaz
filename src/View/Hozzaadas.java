package View;

import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Hozzaadas extends JDialog {

    private final AruhazMainView view;

    public Hozzaadas(AruhazMainView view) {
        super(view, true);

        initComponents();
        this.view = view;
        kategoriakFeltolt();
        telepulesekFeltolt();
    }

    private void kategoriakFeltolt() {//kategóriák lenyíló menü feltöltése     
        ArrayList<String> kategoriaStrings = new ArrayList<>(view.getModell().getKategoriak());
        Collections.sort(kategoriaStrings);

        for (int i = 0; i < kategoriaStrings.size(); i++) {
            cbKategoria.addItem(kategoriaStrings.get(i).split("\\;")[0]);
        }
    }

    private void telepulesekFeltolt() { //kategóriák lenyíló menü feltöltése
        view.getModell().getTelepulesek().forEach((kategoria) -> {
            cbTelepules.addItem(kategoria);
        });
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
        btnHozzaad = new javax.swing.JButton();
        btnKilepesF = new javax.swing.JButton();
        cbTelepules = new javax.swing.JComboBox<>();
        cbKategoria = new javax.swing.JComboBox<>();
        lbAr = new javax.swing.JLabel();
        lb30200 = new javax.swing.JLabel();
        l200 = new javax.swing.JLabel();
        lbKarakterSzam = new javax.swing.JLabel();
        btnKepMegnyitasa = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Termék hozzáadása");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        btnTalloz.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnTalloz.setText("Tallózás...");
        btnTalloz.setMaximumSize(new java.awt.Dimension(92, 20));
        btnTalloz.setMinimumSize(new java.awt.Dimension(92, 20));
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
        lbKep.setText("Kép  elérési címe:");

        taLeiras.setColumns(20);
        taLeiras.setLineWrap(true);
        taLeiras.setRows(5);
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

        btnHozzaad.setText("Hozzáad");
        btnHozzaad.setMaximumSize(new java.awt.Dimension(68, 32));
        btnHozzaad.setMinimumSize(new java.awt.Dimension(68, 32));
        btnHozzaad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHozzaadActionPerformed(evt);
            }
        });

        btnKilepesF.setText("Mégse");
        btnKilepesF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKilepesFActionPerformed(evt);
            }
        });

        lbAr.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbAr.setText("Ár:");

        lb30200.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lb30200.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lb30200.setText("30-200 karakter");

        l200.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        l200.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        l200.setText("/ 200");

        lbKarakterSzam.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        lbKarakterSzam.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbKarakterSzam.setText("0");

        btnKepMegnyitasa.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        btnKepMegnyitasa.setText("Megnyit...");
        btnKepMegnyitasa.setMaximumSize(new java.awt.Dimension(97, 20));
        btnKepMegnyitasa.setMinimumSize(new java.awt.Dimension(97, 20));
        btnKepMegnyitasa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKepMegnyitasaActionPerformed(evt);
            }
        });

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
                        .addComponent(lbAr, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbKep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(lb30200)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbKarakterSzam, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(l200)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnHozzaad, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnKilepesF, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spLeiras, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(cbKategoria, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbTelepules, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tfAr)
                    .addComponent(tfNev, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(tfKepPath, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnTalloz, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnKepMegnyitasa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
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
                    .addComponent(lbAr))
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
                    .addComponent(lbKep)
                    .addComponent(btnKepMegnyitasa, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTalloz, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, Short.MAX_VALUE)
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
                    .addComponent(btnHozzaad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                File file = chooser.getSelectedFile();

                File directory = new File("./");
                String konyvtar = directory.getAbsolutePath();
                String s = file.getAbsolutePath().replace(konyvtar.substring(0, konyvtar.length() - 1), "");

                tfKepPath.setText("." + s.replace("\\", "/"));
            }

        } catch (HeadlessException e) {
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
        if (tfKepPath.getText().isEmpty()) {
            return false;
        }
        File f = new File(tfKepPath.getText().substring(1));
        String mimetype = new MimetypesFileTypeMap().getContentType(f);
        String type = mimetype.split("/")[0];

        return f.exists() && !f.isDirectory() && type.equals("image");
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

    private void btnHozzaadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHozzaadActionPerformed
        if (ellenorzes()) {
            view.getModell().termekHozzadasa(
                cbTelepules.getSelectedItem().toString(),
                tfNev.getText(),
                cbKategoria.getSelectedItem().toString(),
                taLeiras.getText(),
                tfAr.getText(),
                tfKepPath.getText()
            );
            ((AruhazMainView)this.getParent()).adatokFrissitese();
            this.dispose();
        }
    }//GEN-LAST:event_btnHozzaadActionPerformed

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

    private void btnKepMegnyitasaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKepMegnyitasaActionPerformed
        try {
            File file = new File(tfKepPath.getText().substring(1));
            Image image = view.kepAtmeretezese(ImageIO.read(file));
            JLabel picLabel = new JLabel(new ImageIcon(image));
            JOptionPane.showMessageDialog(null, picLabel, file.toString(), JOptionPane.PLAIN_MESSAGE, null);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "A képet nem sikerült megnyitni!", "Hiba!", JOptionPane.ERROR_MESSAGE);
        } catch (IndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "Nincs kép megadva!", "Hiba!", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnKepMegnyitasaActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        setLocationRelativeTo(view);
    }//GEN-LAST:event_formWindowOpened

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHozzaad;
    private javax.swing.JButton btnKepMegnyitasa;
    private javax.swing.JButton btnKilepesF;
    private javax.swing.JButton btnTalloz;
    private javax.swing.JComboBox<String> cbKategoria;
    private javax.swing.JComboBox<String> cbTelepules;
    private javax.swing.JLabel l200;
    private javax.swing.JLabel lb30200;
    private javax.swing.JLabel lbAr;
    private javax.swing.JLabel lbKarakterSzam;
    private javax.swing.JLabel lbKat;
    private javax.swing.JLabel lbKep;
    private javax.swing.JLabel lbLeir;
    private javax.swing.JLabel lbNev;
    private javax.swing.JLabel lbTelep;
    private javax.swing.JScrollPane spLeiras;
    private javax.swing.JTextArea taLeiras;
    private javax.swing.JTextField tfAr;
    private javax.swing.JTextField tfKepPath;
    private javax.swing.JTextField tfNev;
    // End of variables declaration//GEN-END:variables
}
