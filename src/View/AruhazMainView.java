package View;

import aruhaz.Modell;
import aruhaz.Pdf;
import aruhaz.Statisztika;
import aruhaz.Tablazat;
import aruhaz.Termek;
import diagramok.Diagram;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import java.text.Collator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.TreeSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;

public final class AruhazMainView extends JFrame {

    private static float[] termekekTablaOszlopszelesseg = {2.0f, 8.0f, 14.0f, 9.0f, 43.0f, 6.0f, 18.0f};

    private ArrayList<String> termekekStrings = new ArrayList<>();
    private final Modell modell;
    private Statisztika statisztika;

    public AruhazMainView(Modell m) {
        initComponents();

        modell = m;

        init();
        elemekMagyaritasa();
        adatokFrissitese();
    }

    private void init() {

        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension d = t.getScreenSize();
        setSize(d.width, d.height);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(this);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                tablaOszlopainakAtmeretezese(tableTermekek, termekekTablaOszlopszelesseg);
            }
        });

    }

    private void elemekMagyaritasa() {
        UIManager.put("OptionPane.noButtonText", "Nem");
        UIManager.put("OptionPane.yesButtonText", "Ok");
        UIManager.put("OptionPane.cancelButtonText", "Mégse");

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
        statisztika = new Statisztika(modell);
        termekekStringsFeltolt();
        termekekTablaFeltolt();
        kategoriaTablaFeltolt();
        kategoriaTreeFeltolt();
    }

    private void tablaOszlopainakAtmeretezese(JTable tabla, float[] oszlopSzelessegek) {
        if (tabla != null) {
            tabla.setPreferredSize(tabla.getPreferredSize());
            int tablaSzelesseg = tabla.getWidth();

            TableColumnModel jTableOszlopModell = tabla.getColumnModel();
            for (int i = 0; i < jTableOszlopModell.getColumnCount(); i++) {
                int pWidth = Math.round(oszlopSzelessegek[i] * tablaSzelesseg / 100);
                jTableOszlopModell.getColumn(i).setPreferredWidth(pWidth);
            }
        }
    }

    //fa rendezese abc sorrendbe
    public static void rendezTree(DefaultMutableTreeNode root) {
        Enumeration e = root.depthFirstEnumeration();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
            if (!node.isLeaf()) {
                rendez(node);
            }
        }
    }

    public static Comparator<DefaultMutableTreeNode> tnc = (DefaultMutableTreeNode a, DefaultMutableTreeNode b) -> {
        if (a.isLeaf() && !b.isLeaf()) {
            return 1;
        } else if (!a.isLeaf() && b.isLeaf()) {
            return -1;
        } else {
            String sa = a.getUserObject().toString();
            String sb = b.getUserObject().toString();
            return sa.compareToIgnoreCase(sb);
        }
    };

    public static void rendez(DefaultMutableTreeNode parent) {
        int n = parent.getChildCount();
        ArrayList<DefaultMutableTreeNode> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add((DefaultMutableTreeNode) parent.getChildAt(i));
        }
        Collections.sort(children, tnc); //iterative merge sort
        parent.removeAllChildren();
        children.forEach(parent::add);
    }

    private void kategoriaTreeFeltolt() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Kategóriák");

        ArrayList<String> termekek = new ArrayList<>();

        for (int i = 0; i < modell.getTermekek().size(); i++) {
            termekek.add(modell.getTermekek().get(i).getNev());
        }
        Collections.sort(termekek);

        for (int j = 0; j < modell.getKategoriak().size(); j++) {

            //kategoriak hozzáad csomopontonként           
            DefaultMutableTreeNode kategoriakTreeNode = new DefaultMutableTreeNode(modell.getKategoriak().get(j));

            for (int i = 0; i < modell.getTermekek().size(); i++) {
                if (modell.getKategoriak().get(j).equals(modell.getTermekek().get(i).getKategoria())) {
                    kategoriakTreeNode.add(new DefaultMutableTreeNode(modell.getTermekek().get(i).getNev()));
                }
            }
            root.add(kategoriakTreeNode);
        }

        rendezTree(root);
        JTree treeCsoportositas = new JTree(root);

        pCsoportositas.removeAll();
        pCsoportositas.add(new JScrollPane(treeCsoportositas));

        treeCsoportositas.setShowsRootHandles(false);
        treeCsoportositas.setRootVisible(true);

        treeCsoportositas.getSelectionModel().addTreeSelectionListener((TreeSelectionEvent e) -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeCsoportositas.getLastSelectedPathComponent();
        });
    }

    private void kategoriaTablaFeltolt() {
        pKategorizal.removeAll();
        Tablazat tablazat = statisztika.kategoriaDbMinMaxAr();
        DefaultTableModel tablaModell = new DefaultTableModel(tablazat.getAdatok(), tablazat.getFejlec()) {
            @Override
            public Class getColumnClass(int column) {
                return (column >= 1 && column <= 3) ? Integer.class : String.class;
            }
        };

        tableKategorizal = tablaElkeszitese(tablaModell);

        JScrollPane gorgetoSav = new JScrollPane(tableKategorizal);
        pKategorizal.setLayout(new BorderLayout());
        pKategorizal.add(tableKategorizal.getTableHeader(), BorderLayout.PAGE_START);
        pKategorizal.add(gorgetoSav, BorderLayout.CENTER);

        //jobbra rendez
        DefaultTableCellRenderer jobbraRendez = new DefaultTableCellRenderer();
        jobbraRendez.setHorizontalAlignment(JLabel.RIGHT);

        tableKategorizal.getColumnModel().getColumn(1).setCellRenderer(jobbraRendez);
        tableKategorizal.getColumnModel().getColumn(2).setCellRenderer(jobbraRendez);
        tableKategorizal.getColumnModel().getColumn(3).setCellRenderer(jobbraRendez);

        tableKategorizal.getColumnModel().getColumn(1).setPreferredWidth(1);
        tableKategorizal.revalidate();
    }

    private JTable tablaElkeszitese(DefaultTableModel modell) {
        JTable tabla = new JTable(modell) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }

            public Class getColumnClass(int column) {
                return getValueAt(0, column).getClass();
            }

            public Component prepareRenderer(
                    TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);

                //  szinez
                if (!isRowSelected(row)) {
                    c.setBackground(row % 2 == 0 ? getBackground() : Color.LIGHT_GRAY);
                }
                return c;
            }
        };
        tabla.setAutoCreateRowSorter(true);
        tabla.setFillsViewportHeight(true);
        return tabla;
    }

    private void termekekTablaFeltolt() {
        pTermekek.removeAll();
        Tablazat tablazat = statisztika.osszesAdat();
        DefaultTableModel tablaModell = new DefaultTableModel(tablazat.getAdatok(), tablazat.getFejlec()) {
            @Override
            public Class getColumnClass(int column) {
                return (column == 0 || column == 5) ? Integer.class : String.class;
            }
        };
        tableTermekek = tablaElkeszitese(tablaModell);

        DefaultTableCellRenderer jobbraRendez = new DefaultTableCellRenderer();
        jobbraRendez.setHorizontalAlignment(JLabel.RIGHT);

        tableTermekek.getColumnModel().getColumn(0).setCellRenderer(jobbraRendez);
        tableTermekek.getColumnModel().getColumn(5).setCellRenderer(jobbraRendez);

        JScrollPane gorgetoSav = new JScrollPane(tableTermekek);
        pTermekek.setLayout(new BorderLayout());
        pTermekek.add(tableTermekek.getTableHeader(), BorderLayout.PAGE_START);
        pTermekek.add(gorgetoSav, BorderLayout.CENTER);

        tableTermekek.setSize(pTermekek.getSize());
        tablaOszlopainakAtmeretezese(tableTermekek, termekekTablaOszlopszelesseg);

        tableTermekek.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int picCol = 6;
                int idCol = 0;
                int row = tableTermekek.rowAtPoint(evt.getPoint());
                int col = tableTermekek.columnAtPoint(evt.getPoint());
                if ( tableTermekek.getColumnName(col).equals(tablazat.getFejlec()[picCol])) {
                    int id = (int) tableTermekek.getValueAt(row, idCol);
                    
                    String path  = modell.getTermekById(id).getKep();
                    try {
                        File file = new File(path.substring(1));
                        Image image = kepAtmeretezese(ImageIO.read(file));
                        JLabel picLabel = new JLabel(new ImageIcon(image));
                        JOptionPane.showMessageDialog(null, picLabel, path, JOptionPane.PLAIN_MESSAGE, null);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "A képet nem sikerült megnyitni!", "Hiba!", JOptionPane.ERROR_MESSAGE);
                    } catch (IndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "Nincs kép megadva!", "Hiba!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        pTermekek.revalidate();
    }
    public Image kepAtmeretezese(Image image){
        int maxWidth = 800, maxHeight = 600; 
        int imageWidth = image.getWidth(rootPane);
        int imageHeight = image.getHeight(rootPane);
                
        if(maxWidth < imageWidth || maxHeight < imageHeight){
        
            double ratioWidth = maxWidth/(double)imageWidth;
            double ratioHeight = maxHeight/(double)imageHeight;
            double ratio = Math.min(ratioHeight, ratioWidth);

             Image newImage = image.getScaledInstance((int)(imageWidth*ratio), (int)(imageHeight*ratio), Image.SCALE_DEFAULT);
             return newImage;
        }
        return image;
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
        pTermekek = new javax.swing.JPanel();
        pKategorizal = new javax.swing.JPanel();
        spKategorizal = new javax.swing.JScrollPane();
        tableKategorizal = new javax.swing.JTable();
        pCsoportositas = new javax.swing.JPanel();
        pGombok = new javax.swing.JPanel();
        btnTermekTorol = new javax.swing.JButton();
        btnKilepes = new javax.swing.JButton();
        btnDiagramok = new javax.swing.JButton();
        btnArakModositas = new javax.swing.JButton();
        btnTermekHozzaad = new javax.swing.JButton();
        btnPDF = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Webáruház 3.0");
        setPreferredSize(new java.awt.Dimension(1024, 576));

        spMain.setDividerLocation(200);
        spMain.setDividerSize(0);
        spMain.setToolTipText("");

        tpUrlap.setMaximumSize(new java.awt.Dimension(2000000, 200000));
        tpUrlap.setMinimumSize(new java.awt.Dimension(600, 400));
        tpUrlap.setPreferredSize(new java.awt.Dimension(1024, 768));

        pTermekek.setPreferredSize(new java.awt.Dimension(786, 522));
        pTermekek.setLayout(new java.awt.BorderLayout());
        tpUrlap.addTab("Összes termék", pTermekek);

        tableKategorizal.setAutoCreateRowSorter(true);
        tableKategorizal.setCellSelectionEnabled(true);
        tableKategorizal.setPreferredSize(new java.awt.Dimension(800, 600));
        spKategorizal.setViewportView(tableKategorizal);

        javax.swing.GroupLayout pKategorizalLayout = new javax.swing.GroupLayout(pKategorizal);
        pKategorizal.setLayout(pKategorizalLayout);
        pKategorizalLayout.setHorizontalGroup(
            pKategorizalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pKategorizalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(spKategorizal, javax.swing.GroupLayout.PREFERRED_SIZE, 638, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pKategorizalLayout.setVerticalGroup(
            pKategorizalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pKategorizalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(spKategorizal, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(98, 98, 98))
        );

        tpUrlap.addTab("Kategória statisztika", pKategorizal);

        pCsoportositas.setEnabled(false);
        pCsoportositas.setLayout(new java.awt.BorderLayout());
        tpUrlap.addTab("Kategória nézet", pCsoportositas);

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

        btnArakModositas.setText("Árak módosítás...");
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
        new Torles(this, modell).setVisible(true);
        adatokFrissitese();
    }//GEN-LAST:event_btnTermekTorolActionPerformed

    private void btnTermekHozzaadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTermekHozzaadActionPerformed
        new Hozzaadas(this, modell).setVisible(true);
        adatokFrissitese();
    }//GEN-LAST:event_btnTermekHozzaadActionPerformed

    private void btnKilepesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKilepesActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Biztos ki akar lépni?", "Kilépés", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnKilepesActionPerformed

    private void btnArakModositasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArakModositasActionPerformed
        new Armodositas(this, modell, termekekStrings).setVisible(true);
        adatokFrissitese();

    }//GEN-LAST:event_btnArakModositasActionPerformed

    private void btnDiagramokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDiagramokActionPerformed
        new Diagram(this, statisztika).setVisible(true);
    }//GEN-LAST:event_btnDiagramokActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        boolean sikeres = false;

        while (!sikeres) {

            JFileChooser chooser = new JFileChooser();

            chooser.setCurrentDirectory(new File(".\\kimutatasok"));

            FileFilter pdfFilter = new FileNameExtensionFilter("PDF Document", "pdf");
            chooser.setFileFilter(pdfFilter);

            String timeStamp = new SimpleDateFormat("yyyy.MM.dd_HH.mm.ss").format(Calendar.getInstance().getTime());
            chooser.setSelectedFile(new File(timeStamp));

            if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                File fajlNev = chooser.getSelectedFile();
                sikeres = Pdf.kimutatas(modell, statisztika, fajlNev.getAbsolutePath());
            } else {
                return;
            }
        }
    }//GEN-LAST:event_btnPDFActionPerformed

    private JTable tableTermekek;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArakModositas;
    private javax.swing.JButton btnDiagramok;
    private javax.swing.JButton btnKilepes;
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnTermekHozzaad;
    private javax.swing.JButton btnTermekTorol;
    private javax.swing.JPanel pCsoportositas;
    private javax.swing.JPanel pGombok;
    private javax.swing.JPanel pKategorizal;
    private javax.swing.JPanel pTermekek;
    private javax.swing.JScrollPane spKategorizal;
    private javax.swing.JSplitPane spMain;
    private javax.swing.JTable tableKategorizal;
    private javax.swing.JTabbedPane tpUrlap;
    // End of variables declaration//GEN-END:variables

//sptabla nincs sehol és nem lehet eltüntetni :(
}
