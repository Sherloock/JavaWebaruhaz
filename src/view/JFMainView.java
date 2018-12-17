package view;

import aruhaz.Modell;
import kimutatas.Pdf;
import aruhaz.Termek;
import com.sun.xml.internal.messaging.saaj.soap.ImageDataContentHandler;
import diagramok.JDDiagram;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageObserver;
import java.io.File;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.TreeSet;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public final class JFMainView extends JFrame {

    private ArrayList<String> termekekStrings = new ArrayList<>();
    private final Modell modell;
    private JPOsszesTermek pTermekek;
    private JPKategoriaStatisztika pKategorizal;
    private JPKategoriaNezet pCsoportositas;

    public JFMainView(Modell m) {
        initComponents();
        modell = m;
        elemekMagyaritasa();
        init();
        adatokFrissitese();
    }

    private void init() {
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();

        setSize(d.width, d.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(this);

        pTermekek = new JPOsszesTermek(this, modell);
        tpUrlap.add(pTermekek, "Összes termék");

        pKategorizal = new JPKategoriaStatisztika(this, modell);
        tpUrlap.add(pKategorizal, "Kategória statisztika");

        pCsoportositas = new JPKategoriaNezet(this, modell);
        pCsoportositas.setLayout(new BorderLayout());
        tpUrlap.add(pCsoportositas, "Kategória nézet");
    }

    private void elemekMagyaritasa() {
        UIManager.put("OptionPane.noButtonText", "Nem");
        UIManager.put("OptionPane.yesButtonText", "Igen");
        UIManager.put("OptionPane.cancelButtonText", "Mégse");
        UIManager.put("OptionPane.okButtonText", "Rendben");

        //http://jhead.hu/index.php/home/blog/java/14-filechooser-parbeszedablak-magyaritasa
        UIManager.put("FileChooser.updateButtonMnemonic", new Integer('f'));
        UIManager.put("FileChooser.directoryOpenButtonMnemonic", new Integer('o'));
        UIManager.put("FileChooser.openButtonMnemonic", new Integer('o'));
        UIManager.put("FileChooser.cancelButtonMnemonic", new Integer('q'));
        UIManager.put("FileChooser.helpButtonMnemonic", new Integer('s'));
        UIManager.put("FileChooser.saveButtonMnemonic", new Integer('s'));
        UIManager.put("FileChooser.filesOfTypeLabelText", "Adott típusú fájlok:");
        UIManager.put("FileChooser.fileDescriptionText", "Általános fájl");
        UIManager.put("FileChooser.fileNameLabelText", "Fájl neve:");
        UIManager.put("FileChooser.cancelButtonToolTipText", "Ablak bezárása");
        UIManager.put("FileChooser.helpButtonToolTipText", "Fájlválasztó segítség");
        UIManager.put("FileChooser.upFolderAccessibleName", "Fel");
        UIManager.put("FileChooser.updateButtonText", "Frissít");
        UIManager.put("FileChooser.newFolderErrorText", "Hiba a mappa létrehozása közben");
        UIManager.put("FileChooser.lookInLabelText", "Keresés itt:");
        UIManager.put("FileChooser.homeFolderAccessibleName", "Kezdőmappa");
        UIManager.put("FileChooser.homeFolderToolTipText", "Kezdőmappa");
        UIManager.put("FileChooser.saveButtonToolTipText", "Kiválasztott fájl mentése");
        UIManager.put("FileChooser.openButtonToolTipText", "Kiválasztott fájlok megnyitása");
        UIManager.put("FileChooser.directoryOpenButtonToolTipText", "Kiválasztott mappa megnyitása");
        UIManager.put("FileChooser.listViewButtonAccessibleName", "Lista");
        UIManager.put("FileChooser.listViewButtonToolTipText", "Lista");
        UIManager.put("FileChooser.directoryDescriptionText", "Mappa");
        UIManager.put("FileChooser.updateButtonToolTipText", "Mappalista frissítése");
        UIManager.put("FileChooser.directoryOpenButtonText", "Megnyitás");
        UIManager.put("FileChooser.openButtonText", "Megnyitás");
        UIManager.put("FileChooser.openDialogTitleText", "Megnyitás");
        UIManager.put("FileChooser.cancelButtonText", "Mégsem");
        UIManager.put("FileChooser.saveButtonText", "Mentés");
        UIManager.put("FileChooser.saveDialogTitleText", "Mentés");
        UIManager.put("FileChooser.saveInLabelText", "Mentés ide:");
        UIManager.put("FileChooser.acceptAllFileFilterText", "Összes fájl");
        UIManager.put("FileChooser.detailsViewButtonAccessibleName", "Részletek");
        UIManager.put("FileChooser.detailsViewButtonToolTipText", "Részletek");
        UIManager.put("FileChooser.helpButtonText", "Segítség");
        UIManager.put("FileChooser.upFolderToolTipText", "Ugrás egy szinttel feljebb");
        UIManager.put("FileChooser.newFolderAccessibleName", "Új mappa");
        UIManager.put("FileChooser.newFolderToolTipText", "Új mappa");
        UIManager.put("FileChooser.other.newFolder", "Új mappa");
        UIManager.put("FileChooser.win32.newFolder", "Új mappa");
        UIManager.put("FileChooser.win32.newFolder.subsequent", "Új mappa ({0})");
        UIManager.put("FileChooser.other.newFolder.subsequent", "Új mappa.{0}");
    }

    //frissíti az aktuális tábla tartalmát
    public void adatokFrissitese() {
        termekekStringsFeltolt();
        pTermekek.frissit();
        pKategorizal.frissit();
        pCsoportositas.frissit();
    }

    private void termekekStringsFeltolt() {
        Collection<String> collection = new TreeSet<>(Collator.getInstance());

        for (int i = 0; i < modell.getTermekek().size(); i++) {
            Termek t = modell.getTermekek().get(i);
            collection.add(t.getNev() + ";" + t.ID);
        }
        termekekStrings = new ArrayList<>(collection);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        spMain = new javax.swing.JSplitPane();
        tpUrlap = new javax.swing.JTabbedPane();
        pGombok = new javax.swing.JPanel();
        btnTermekTorol = new javax.swing.JButton();
        btnKilepes = new javax.swing.JButton();
        btnDiagramok = new javax.swing.JButton();
        btnArakModositas = new javax.swing.JButton();
        btnTermekHozzaad = new javax.swing.JButton();
        btnPDF = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Webáruház 3.0");

        spMain.setDividerLocation(200);
        spMain.setDividerSize(0);
        spMain.setToolTipText("");

        tpUrlap.setMaximumSize(new java.awt.Dimension(2000000, 200000));
        tpUrlap.setMinimumSize(new java.awt.Dimension(600, 400));
        tpUrlap.setPreferredSize(new java.awt.Dimension(1024, 768));
        spMain.setRightComponent(tpUrlap);

        pGombok.setMaximumSize(new java.awt.Dimension(200, 32767));
        pGombok.setMinimumSize(new java.awt.Dimension(200, 0));
        pGombok.setPreferredSize(new java.awt.Dimension(200, 801));

        btnTermekTorol.setText("Termék törlése...");
        btnTermekTorol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTermekTorolActionPerformed(evt);
            }
        });

        btnKilepes.setText("Kilépés");
        btnKilepes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKilepesActionPerformed(evt);
            }
        });

        btnDiagramok.setText("Diagramok...");
        btnDiagramok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDiagramokActionPerformed(evt);
            }
        });

        btnArakModositas.setText("Árak módosítása...");
        btnArakModositas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArakModositasActionPerformed(evt);
            }
        });

        btnTermekHozzaad.setText("Termék hozzáadása...");
        btnTermekHozzaad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTermekHozzaadActionPerformed(evt);
            }
        });

        btnPDF.setText("PDF kimutatás...");
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pGombokLayout = new javax.swing.GroupLayout(pGombok);
        pGombok.setLayout(pGombokLayout);
        pGombokLayout.setHorizontalGroup(
            pGombokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pGombokLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pGombokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKilepes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTermekHozzaad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTermekTorol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnArakModositas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnDiagramok, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPDF, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pGombokLayout.setVerticalGroup(
            pGombokLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pGombokLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnTermekHozzaad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTermekTorol)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnArakModositas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDiagramok)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPDF)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 573, Short.MAX_VALUE)
                .addComponent(btnKilepes)
                .addContainerGap())
        );

        btnTermekTorol.getAccessibleContext().setAccessibleDescription("");

        spMain.setLeftComponent(pGombok);

        getContentPane().add(spMain, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnTermekTorolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTermekTorolActionPerformed
        new JDTorles(this, termekekStrings).setVisible(true);
    }//GEN-LAST:event_btnTermekTorolActionPerformed

    private void btnTermekHozzaadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTermekHozzaadActionPerformed
        new JDHozzaadas(this).setVisible(true);
    }//GEN-LAST:event_btnTermekHozzaadActionPerformed

    private void btnKilepesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKilepesActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Biztosan ki akar lépni?", "Kilépés", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnKilepesActionPerformed

    private void btnArakModositasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArakModositasActionPerformed
        new JDArmodositas(this, termekekStrings).setVisible(true);
    }//GEN-LAST:event_btnArakModositasActionPerformed

    private void btnDiagramokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagramokActionPerformed
        new JDDiagram(this, modell.getStatisztika()).setVisible(true);
    }//GEN-LAST:event_btnDiagramokActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        boolean sikeres = false;

        while (!sikeres) {

            JFileChooser chooser = new JFileChooser(".\\kimutatasok");

            FileFilter pdfFilter = new FileNameExtensionFilter("PDF Document", "pdf");
            chooser.setFileFilter(pdfFilter);

            String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(Calendar.getInstance().getTime());
            chooser.setSelectedFile(new File("WebáruházKimutatás" + timeStamp));

            if (chooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
                File fajlNev = chooser.getSelectedFile();
                sikeres = Pdf.kimutatas(modell, fajlNev.getAbsolutePath());
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnPDFActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArakModositas;
    private javax.swing.JButton btnDiagramok;
    private javax.swing.JButton btnKilepes;
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnTermekHozzaad;
    private javax.swing.JButton btnTermekTorol;
    private javax.swing.JPanel pGombok;
    private javax.swing.JSplitPane spMain;
    private javax.swing.JTabbedPane tpUrlap;
    // End of variables declaration//GEN-END:variables

    // kép átméretezése default
    public static Image kepAtmeretezese(Image image) {
        return kepAtmeretezese(image, 800, 600);
    }

    // ha nagyobb mint a megadott paraméterek
    public static Image kepAtmeretezese(Image image, int maxWidth, int maxHeight) {
        ImageObserver observer = new ImageDataContentHandler();
        int imageWidth = image.getWidth(observer);
        int imageHeight = image.getHeight(observer);

        if (maxWidth < imageWidth || maxHeight < imageHeight) {

            double ratioWidth = maxWidth / (double) imageWidth;
            double ratioHeight = maxHeight / (double) imageHeight;
            double ratio = Math.min(ratioHeight, ratioWidth);

            Image newImage = image.getScaledInstance((int) (imageWidth * ratio), (int) (imageHeight * ratio), Image.SCALE_DEFAULT);
            return newImage;
        }
        return image;
    }

    public Modell getModell() {
        return modell;
    }



}
