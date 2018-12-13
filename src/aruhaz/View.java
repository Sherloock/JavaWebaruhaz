package aruhaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.TreeSet;
import javax.swing.JComponent;
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
import javax.swing.table.TableCellRenderer;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.MutableTreeNode;

public class View extends JFrame {

    private ArrayList<String> termekekStrings = new ArrayList<>();
    private Modell modell;
    private JFrame frame;

    public View(Modell m) {
        initComponents();
        
        modell = m;
        adatokFrissitese();
        
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setTitle("Webáruház 3.0");
        setLocationRelativeTo(this);
        setLayout(new BorderLayout());
        
        UIManager.put("OptionPane.cancelButtonText", "Nem");
        UIManager.put("OptionPane.okButtonText", "Igen");
    }

    //frissíti az aktuális tábla tartalmát
    public void adatokFrissitese() {
        termekekTablaFeltolt();
        kategoriaTablaFeltolt();
        termekekStringsFeltolt();
        kategoriaTreeFeltolt();
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
            
                
            int szamlalo = 0, min = Integer.MAX_VALUE, max = 0;
            for (int j = 0; j < termekLista.size(); j++) {
                if (kategoriak.get(i).equals(termekLista.get(j).getKategoria())) {
                    szamlalo++;
                    min = Math.min(min, termekLista.get(j).getAr());
                    max = Math.max(max, termekLista.get(j).getAr());
                }
            }
            adatok[i][1] = szamlalo;
            adatok[i][2] = szamlalo == 0 ? 0 : min;
            adatok[i][3] = szamlalo == 0 ? 0 : max;
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
        
        tableKategorizal.getColumnModel().getColumn(1).setPreferredWidth(10);
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
                JComponent jc = (JComponent) c;

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

        tableTermekek = tablaElkeszitese(model);


        JScrollPane gorgetoSav = new JScrollPane(tableTermekek);
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

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpUrlap = new javax.swing.JTabbedPane();
        pTermekek = new javax.swing.JPanel();
        spTermekek = new javax.swing.JScrollPane();
        tableTermekek = new javax.swing.JTable();
        pKategorizal = new javax.swing.JPanel();
        spKategorizal = new javax.swing.JScrollPane();
        tableKategorizal = new javax.swing.JTable();
        pCsoportositas = new javax.swing.JPanel();
        btnTermekHozzaad = new javax.swing.JButton();
        btnTermekTorol = new javax.swing.JButton();
        btnTermekekSzamaTelepulesenkent = new javax.swing.JButton();
        btnPdfKimutatas = new javax.swing.JButton();
        btnKilepes = new javax.swing.JButton();
        btnArakModositas = new javax.swing.JButton();
        btnAtlagar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Adatok felvitele");
        setPreferredSize(new java.awt.Dimension(850, 600));

        tpUrlap.setMaximumSize(new java.awt.Dimension(1000, 800));
        tpUrlap.setMinimumSize(new java.awt.Dimension(600, 400));
        tpUrlap.setPreferredSize(new java.awt.Dimension(700, 600));

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
                .addGap(0, 460, Short.MAX_VALUE))
        );

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

        btnTermekekSzamaTelepulesenkent.setText("Település statisztika");
        btnTermekekSzamaTelepulesenkent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTermekekSzamaTelepulesenkentActionPerformed(evt);
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

        btnArakModositas.setText("Ár módosítás...");
        btnArakModositas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArakModositasActionPerformed(evt);
            }
        });

        btnAtlagar.setText("Átlagár statisztika...");
        btnAtlagar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtlagarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tpUrlap, javax.swing.GroupLayout.PREFERRED_SIZE, 652, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnKilepes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPdfKimutatas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTermekHozzaad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTermekTorol, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTermekekSzamaTelepulesenkent, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(btnArakModositas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(btnAtlagar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnTermekHozzaad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTermekTorol)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnArakModositas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAtlagar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTermekekSzamaTelepulesenkent)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPdfKimutatas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnKilepes)
                .addContainerGap())
            .addComponent(tpUrlap, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
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

    private void btnTermekekSzamaTelepulesenkentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTermekekSzamaTelepulesenkentActionPerformed
         new TermekekTelepulesenkentDiagram(this.frame, modell).setVisible(true);
    }//GEN-LAST:event_btnTermekekSzamaTelepulesenkentActionPerformed

    private void btnKilepesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKilepesActionPerformed
        if (JOptionPane.showConfirmDialog(this, "Biztos ki akar lépni?", "Kilépés", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnKilepesActionPerformed

    private void btnPdfKimutatasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPdfKimutatasActionPerformed
         new PdfKimutatas(this.frame, modell).setVisible(true);
    }//GEN-LAST:event_btnPdfKimutatasActionPerformed

    private void cbTermekArValtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbTermekArValtActionPerformed


    }//GEN-LAST:event_cbTermekArValtActionPerformed

    private void btnArakModositasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArakModositasActionPerformed
        new Armodositas(this.frame, modell, termekekStrings, this).setVisible(true);
        adatokFrissitese();
    }//GEN-LAST:event_btnArakModositasActionPerformed

    private void btnAtlagarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtlagarActionPerformed
        new AtlagarKategoriankentDiagram(this.frame, modell).setVisible(true);
    }//GEN-LAST:event_btnAtlagarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnArakModositas;
    private javax.swing.JButton btnAtlagar;
    private javax.swing.JButton btnKilepes;
    private javax.swing.JButton btnPdfKimutatas;
    private javax.swing.JButton btnTermekHozzaad;
    private javax.swing.JButton btnTermekTorol;
    private javax.swing.JButton btnTermekekSzamaTelepulesenkent;
    private javax.swing.JPanel pCsoportositas;
    private javax.swing.JPanel pKategorizal;
    private javax.swing.JPanel pTermekek;
    private javax.swing.JScrollPane spKategorizal;
    private javax.swing.JScrollPane spTermekek;
    private javax.swing.JTable tableKategorizal;
    private javax.swing.JTable tableTermekek;
    private javax.swing.JTabbedPane tpUrlap;
    // End of variables declaration//GEN-END:variables

//sptabla nincs sehol és nem lehet eltüntetni :(
}
