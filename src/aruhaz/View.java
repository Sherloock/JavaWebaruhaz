package aruhaz;

import java.awt.BorderLayout;
import java.awt.List;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.TreeSet;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class View extends JFrame {

    private ArrayList<String> termekekStrings = new ArrayList<>();
    private Modell modell;
    private JFrame frame;

    public View(Modell m) {
        initComponents();
        ini();

        UIManager.put("OptionPane.cancelButtonText", "Nem");//java beépített objektumok honosítása
        UIManager.put("OptionPane.okButtonText", "Igen");

        modell = m;
        adatokFrissitese();
    }

    private void ini() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Webáruház 3.0");
        setLocationRelativeTo(this);
        setLayout(new BorderLayout());
    }

    //frissíti az aktuális tábla tartalmát
    public void adatokFrissitese() {
        termekekTablaFeltolt();
        kategoriaTablaFeltolt();
        termekekStringsFeltolt();
        kategoriaTreeFeltolt();
    }

    public static void rendezTree(DefaultMutableTreeNode root) {
        Enumeration e = root.depthFirstEnumeration();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
            if (!node.isLeaf()) {
                rendez(node); 
            }
        }
    }

    public static Comparator<DefaultMutableTreeNode> tnc = new Comparator<DefaultMutableTreeNode>() {
        @Override
        public int compare(DefaultMutableTreeNode a, DefaultMutableTreeNode b) {
            //Sort the parent and child nodes separately:
            if (a.isLeaf() && !b.isLeaf()) {
                return 1;
            } else if (!a.isLeaf() && b.isLeaf()) {
                return -1;
            } else {
                String sa = a.getUserObject().toString();
                String sb = b.getUserObject().toString();
                return sa.compareToIgnoreCase(sb);
            }
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
        for (MutableTreeNode node : children) {
            parent.add(node);
        }
    }

    private void kategoriaTreeFeltolt() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

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

        pCsoportositas.add(new JScrollPane(treeCsoportositas));

        treeCsoportositas.setShowsRootHandles(false);
        treeCsoportositas.setRootVisible(true);

        treeCsoportositas.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) treeCsoportositas.getLastSelectedPathComponent();
            }
        });
    }

    private void kategoriaTablaFeltolt() {
        pKategorizal.removeAll();
        Object[] fejlec = {"Kategória", "Termékek száma", "Legolcsóbb", "Legdrágább"};

        ArrayList<Termek> termekLista = modell.getTermekek();
        ArrayList<String> kategoriak = modell.getKategoriak();

        Object[][] adatok = new Object[kategoriak.size()][4];
        //kategóriák feltölt
        for (int i = 0; i < kategoriak.size(); i++) {
            adatok[i][0] = kategoriak.get(i);
            int szamlalo = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
            for (int j = 0; j < termekLista.size(); j++) {
                if (kategoriak.get(i).equals(termekLista.get(j).getKategoria())) {
                    szamlalo++;
                    if (min > termekLista.get(j).getAr()) {
                        min = (int) termekLista.get(j).getAr();
                    }
                    if (max < termekLista.get(j).getAr()) {
                        max = (int) termekLista.get(j).getAr();

                    }
                }
            }
            adatok[i][1] = szamlalo;
            adatok[i][2] = min;
            adatok[i][3] = max;
        }

        DefaultTableModel tablaModell = new DefaultTableModel(adatok, fejlec) {
            @Override
            public Class getColumnClass(int column) {
                if (column >= 1 && column <= 3) {
                    return Integer.class;
                } else {
                    return String.class;
                }
            }
        };

        tableKategorizal = new JTable(tablaModell) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        JScrollPane gorgetoSav = new JScrollPane(tableKategorizal);
        tableKategorizal.setAutoCreateRowSorter(true);
        tableKategorizal.setFillsViewportHeight(true);
        pKategorizal.setLayout(new BorderLayout());
        pKategorizal.add(tableKategorizal.getTableHeader(), BorderLayout.PAGE_START);
        pKategorizal.add(gorgetoSav, BorderLayout.CENTER);

        //jobbra rendez
        DefaultTableCellRenderer jobbraRendez = new DefaultTableCellRenderer();
        jobbraRendez.setHorizontalAlignment(JLabel.RIGHT);

        tableKategorizal.getColumnModel().getColumn(1).setCellRenderer(jobbraRendez);
        tableKategorizal.getColumnModel().getColumn(2).setCellRenderer(jobbraRendez);
        tableKategorizal.getColumnModel().getColumn(3).setCellRenderer(jobbraRendez);

        tableKategorizal.getColumnModel().getColumn(1).setPreferredWidth(10);

        pKategorizal.revalidate();
    }

    private void termekekTablaFeltolt() {
        pTermekek.removeAll();
        Object[] fejlec = {"Id", "Település", "Név", "Kategória", "Leírás", "Ár", "Kép"};

        Object[][] adatok = new Object[modell.getTermekek().size()][fejlec.length];
        for (int i = 0; i < modell.getTermekek().size(); i++) {
            adatok[i][0] = modell.getTermekek().get(i).ID;
            adatok[i][1] = modell.getTermekek().get(i).getTelepules();
            adatok[i][2] = modell.getTermekek().get(i).getNev();
            adatok[i][3] = modell.getTermekek().get(i).getKategoria();
            adatok[i][4] = modell.getTermekek().get(i).getLeiras();
            adatok[i][5] = modell.getTermekek().get(i).getAr();
            adatok[i][6] = modell.getTermekek().get(i).getKep();
        }

        DefaultTableModel model = new DefaultTableModel(adatok, fejlec) {
            @Override
            public Class getColumnClass(int column) {
                if (column == 0 || column == 5) {
                    return Integer.class;
                } else {
                    return String.class;
                }
            }
        };

        tableTermekek = new JTable(model) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        JScrollPane gorgetoSav = new JScrollPane(tableTermekek);
        tableTermekek.setAutoCreateRowSorter(true);
        tableTermekek.setFillsViewportHeight(true);

        pTermekek.setLayout(new BorderLayout());
        pTermekek.add(tableTermekek.getTableHeader(), BorderLayout.PAGE_START);
        pTermekek.add(gorgetoSav, BorderLayout.CENTER);

        DefaultTableCellRenderer jobbraRendez = new DefaultTableCellRenderer();
        jobbraRendez.setHorizontalAlignment(JLabel.RIGHT);

        tableTermekek.getColumnModel().getColumn(0).setCellRenderer(jobbraRendez);
        tableTermekek.getColumnModel().getColumn(5).setCellRenderer(jobbraRendez);

        tableTermekek.getColumnModel().getColumn(0).setPreferredWidth(10);
        pTermekek.revalidate();
    }

    private void termekekStringsFeltolt() {
        Collection<String> collection = new TreeSet<String>(Collator.getInstance());

        for (int i = 0; i < modell.getTermekek().size(); i++) {
            Termek t = modell.getTermekek().get(i);
            collection.add(t.getNev() + ";" + t.ID);
        }

        termekekStrings = new ArrayList<String>(collection);

        cbTermekek.removeAll();
        cbTermekek.addItem("Összes termék");
        for (int i = 0; i < termekekStrings.size(); i++) {
            cbTermekek.addItem(termekekStrings.get(i).split("\\;")[0]);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpUrlap = new javax.swing.JTabbedPane();
        pCsoportositas = new javax.swing.JPanel();
        pTermekek = new javax.swing.JPanel();
        spTermekek = new javax.swing.JScrollPane();
        tableTermekek = new javax.swing.JTable();
        pKategorizal = new javax.swing.JPanel();
        spKategorizal = new javax.swing.JScrollPane();
        tableKategorizal = new javax.swing.JTable();
        pArvaltozas = new javax.swing.JPanel();
        labelArValt = new javax.swing.JLabel();
        sliderArValt = new javax.swing.JSlider();
        cbTermekek = new javax.swing.JComboBox<>();
        labelTermekKiv = new javax.swing.JLabel();
        btnArValtoztatas = new javax.swing.JButton();
        labelArValtUzenet = new javax.swing.JLabel();
        btnTermekHozzaad = new javax.swing.JButton();
        btnTermekTorol = new javax.swing.JButton();
        btnGrafikon = new javax.swing.JButton();
        btnPdfKimutatas = new javax.swing.JButton();
        btnKilepes = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Adatok felvitele");
        setPreferredSize(new java.awt.Dimension(850, 600));

        tpUrlap.setMaximumSize(new java.awt.Dimension(1000, 800));
        tpUrlap.setMinimumSize(new java.awt.Dimension(600, 400));
        tpUrlap.setPreferredSize(new java.awt.Dimension(700, 600));

        pCsoportositas.setEnabled(false);
        pCsoportositas.setLayout(new java.awt.BorderLayout());
        tpUrlap.addTab("Csoportosítás", pCsoportositas);

        pTermekek.setPreferredSize(new java.awt.Dimension(786, 522));

        tableTermekek.setAutoCreateColumnsFromModel(false);
        tableTermekek.setAutoCreateRowSorter(true);
        tableTermekek.setPreferredSize(new java.awt.Dimension(800, 600));
        spTermekek.setViewportView(tableTermekek);

        javax.swing.GroupLayout pTermekekLayout = new javax.swing.GroupLayout(pTermekek);
        pTermekek.setLayout(pTermekekLayout);
        pTermekekLayout.setHorizontalGroup(
            pTermekekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTermekekLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTermekek, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pTermekekLayout.setVerticalGroup(
            pTermekekLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTermekekLayout.createSequentialGroup()
                .addComponent(spTermekek, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 538, Short.MAX_VALUE))
        );

        tpUrlap.addTab("Termékek", pTermekek);

        tableKategorizal.setAutoCreateRowSorter(true);
        tableKategorizal.setCellSelectionEnabled(true);
        tableKategorizal.setPreferredSize(new java.awt.Dimension(800, 600));
        spKategorizal.setViewportView(tableKategorizal);

        javax.swing.GroupLayout pKategorizalLayout = new javax.swing.GroupLayout(pKategorizal);
        pKategorizal.setLayout(pKategorizalLayout);
        pKategorizalLayout.setHorizontalGroup(
            pKategorizalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pKategorizalLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(spKategorizal, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pKategorizalLayout.setVerticalGroup(
            pKategorizalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pKategorizalLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(spKategorizal, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tpUrlap.addTab("Kategóriák", pKategorizal);

        pArvaltozas.setMaximumSize(new java.awt.Dimension(1000, 800));
        pArvaltozas.setMinimumSize(new java.awt.Dimension(600, 400));
        pArvaltozas.setName(""); // NOI18N
        pArvaltozas.setPreferredSize(new java.awt.Dimension(790, 500));

        labelArValt.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
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

        javax.swing.GroupLayout pArvaltozasLayout = new javax.swing.GroupLayout(pArvaltozas);
        pArvaltozas.setLayout(pArvaltozasLayout);
        pArvaltozasLayout.setHorizontalGroup(
            pArvaltozasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pArvaltozasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pArvaltozasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelArValt)
                    .addComponent(sliderArValt, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pArvaltozasLayout.createSequentialGroup()
                        .addComponent(labelTermekKiv)
                        .addGap(78, 78, 78)
                        .addComponent(cbTermekek, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pArvaltozasLayout.createSequentialGroup()
                        .addComponent(btnArValtoztatas, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(labelArValtUzenet, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pArvaltozasLayout.setVerticalGroup(
            pArvaltozasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pArvaltozasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pArvaltozasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTermekKiv, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTermekek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(labelArValt, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(sliderArValt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(pArvaltozasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnArValtoztatas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelArValtUzenet, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(321, Short.MAX_VALUE))
        );

        tpUrlap.addTab("Árak módosítása", pArvaltozas);

        btnTermekHozzaad.setText("Termék hozzáadása...");
        btnTermekHozzaad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTermekHozzaadActionPerformed(evt);
            }
        });

        btnTermekTorol.setText("Termék törlése...");
        btnTermekTorol.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTermekTorolActionPerformed(evt);
            }
        });

        btnGrafikon.setText("Grafikon készítése...");
        btnGrafikon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGrafikonActionPerformed(evt);
            }
        });

        btnPdfKimutatas.setText("PDF kimutatás...");
        btnPdfKimutatas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPdfKimutatasActionPerformed(evt);
            }
        });

        btnKilepes.setText("Kilépés");
        btnKilepes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKilepesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpUrlap, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGrafikon, javax.swing.GroupLayout.DEFAULT_SIZE, 185, Short.MAX_VALUE)
                    .addComponent(btnKilepes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTermekTorol, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPdfKimutatas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTermekHozzaad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tpUrlap, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .addComponent(btnTermekHozzaad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTermekTorol)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGrafikon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPdfKimutatas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 346, Short.MAX_VALUE)
                .addComponent(btnKilepes)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnTermekTorol.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents


    private void btnTermekTorolActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTermekTorolActionPerformed
        new Torles(this.frame, modell).setVisible(true);
        adatokFrissitese();

    }//GEN-LAST:event_btnTermekTorolActionPerformed

    private void btnTermekHozzaadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTermekHozzaadActionPerformed
        new Hozzaadas(this.frame, modell).setVisible(true);
        adatokFrissitese();
    }//GEN-LAST:event_btnTermekHozzaadActionPerformed

    private void btnGrafikonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGrafikonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnGrafikonActionPerformed

    private void btnKilepesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKilepesActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Biztos ki akar lépni?", "Kilépés", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnKilepesActionPerformed

    private void btnPdfKimutatasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPdfKimutatasActionPerformed

    }//GEN-LAST:event_btnPdfKimutatasActionPerformed

    private void cbTermekArValtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTermekArValtActionPerformed


    }//GEN-LAST:event_cbTermekArValtActionPerformed

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
                modell.arvaltoztatas(id, valtoztatasMerteke);
                labelArValtUzenet.setText(modell.getTermekById(id).getNev() + " termék ára megváltozott " + sliderArValt.getValue() + "%-al!");
            }
            adatokFrissitese();
        }

    }//GEN-LAST:event_btnArValtoztatasActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArValtoztatas;
    private javax.swing.JButton btnGrafikon;
    private javax.swing.JButton btnKilepes;
    private javax.swing.JButton btnPdfKimutatas;
    private javax.swing.JButton btnTermekHozzaad;
    private javax.swing.JButton btnTermekTorol;
    private javax.swing.JComboBox<String> cbTermekek;
    private javax.swing.JLabel labelArValt;
    private javax.swing.JLabel labelArValtUzenet;
    private javax.swing.JLabel labelTermekKiv;
    private javax.swing.JPanel pArvaltozas;
    private javax.swing.JPanel pCsoportositas;
    private javax.swing.JPanel pKategorizal;
    private javax.swing.JPanel pTermekek;
    private javax.swing.JSlider sliderArValt;
    private javax.swing.JScrollPane spKategorizal;
    private javax.swing.JScrollPane spTabla1;
    private javax.swing.JScrollPane spTermekek;
    private javax.swing.JTable tableKategorizal;
    private javax.swing.JTable tableTermekek;
    private javax.swing.JTabbedPane tpUrlap;
    // End of variables declaration//GEN-END:variables
}
