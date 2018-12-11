package aruhaz;

import java.awt.BorderLayout;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.TreeSet;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;

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
        pTablazat.removeAll();
        String[] fejlec = {"Id", "Település", "Név", "Kategória", "Leírás", "Ár", "Kép"};
        String[][] adatok = modell.GetTermekMatrix();
        JTable jtTermekek = new JTable(adatok, fejlec) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };

        JScrollPane gorgetoSav = new JScrollPane(jtTermekek);
        jtTermekek.setFillsViewportHeight(true);

        pTablazat.setLayout(new BorderLayout());
        pTablazat.add(jtTermekek.getTableHeader(), BorderLayout.PAGE_START);
        pTablazat.add(gorgetoSav, BorderLayout.CENTER);
        pTablazat.revalidate();

        termekekStringsFeltolt();
    }

    private void termekekStringsFeltolt() {
        Collection<String> collection = new TreeSet<String>(Collator.getInstance());

        for (int i = 0; i < modell.getTermekek().size(); i++) {
            Termek t = modell.getTermekek().get(i);
            collection.add(t.getNev() + ";" + t.ID);
        }

        termekekStrings = new ArrayList<String>(collection);

        cbTermekek.addItem("Összes termék");
        for (int i = 0; i < termekekStrings.size(); i++) {
            cbTermekek.addItem(termekekStrings.get(i).split("\\;")[0]);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tpUrlap = new javax.swing.JTabbedPane();
        pCsoport = new javax.swing.JPanel();
        spTree = new javax.swing.JScrollPane();
        tTermekKat = new javax.swing.JTree();
        pTablazat = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        pValtozas = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        sliderArValt = new javax.swing.JSlider();
        cbTermekek = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        btnArValtoztatas = new javax.swing.JButton();
        labelArValt = new javax.swing.JLabel();
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

        pCsoport.setEnabled(false);

        spTree.setViewportView(tTermekKat);

        javax.swing.GroupLayout pCsoportLayout = new javax.swing.GroupLayout(pCsoport);
        pCsoport.setLayout(pCsoportLayout);
        pCsoportLayout.setHorizontalGroup(
            pCsoportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCsoportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTree, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(466, Short.MAX_VALUE))
        );
        pCsoportLayout.setVerticalGroup(
            pCsoportLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCsoportLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spTree, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
                .addContainerGap())
        );

        tpUrlap.addTab("Csoportosítás", pCsoport);

        pTablazat.setPreferredSize(new java.awt.Dimension(786, 522));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setPreferredSize(new java.awt.Dimension(800, 600));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout pTablazatLayout = new javax.swing.GroupLayout(pTablazat);
        pTablazat.setLayout(pTablazatLayout);
        pTablazatLayout.setHorizontalGroup(
            pTablazatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTablazatLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 642, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pTablazatLayout.setVerticalGroup(
            pTablazatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pTablazatLayout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 536, Short.MAX_VALUE))
        );

        tpUrlap.addTab("Táblázat", pTablazat);

        pValtozas.setMaximumSize(new java.awt.Dimension(1000, 800));
        pValtozas.setMinimumSize(new java.awt.Dimension(600, 400));
        pValtozas.setName(""); // NOI18N
        pValtozas.setPreferredSize(new java.awt.Dimension(790, 500));

        jLabel1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel1.setText("Ár megváltoztatása (adott százalékkal)");

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

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setText("Termék kiválasztása");

        btnArValtoztatas.setText("Ár megváltoztatása");
        btnArValtoztatas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArValtoztatasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pValtozasLayout = new javax.swing.GroupLayout(pValtozas);
        pValtozas.setLayout(pValtozasLayout);
        pValtozasLayout.setHorizontalGroup(
            pValtozasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pValtozasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pValtozasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(sliderArValt, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pValtozasLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(78, 78, 78)
                        .addComponent(cbTermekek, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pValtozasLayout.createSequentialGroup()
                        .addComponent(btnArValtoztatas, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(labelArValt, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pValtozasLayout.setVerticalGroup(
            pValtozasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pValtozasLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pValtozasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbTermekek, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(sliderArValt, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addGroup(pValtozasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnArValtoztatas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(labelArValt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(319, Short.MAX_VALUE))
        );

        tpUrlap.addTab("Árak módosítása", pValtozas);

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnTermekHozzaad)
                    .addComponent(btnGrafikon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnKilepes, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnTermekTorol, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnPdfKimutatas, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        if (JOptionPane.showConfirmDialog(this, "Biztos kiakar lépni?", "Kilépés", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }//GEN-LAST:event_btnKilepesActionPerformed

    private void btnPdfKimutatasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPdfKimutatasActionPerformed
        // TODO add your handling code here:
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
                labelArValt.setText("Az összes termék ára megváltozott " + sliderArValt.getValue() + "%-al!");
            } 
            
            //egy termék
            else {
                index--;
                String idStr = termekekStrings.get(index).split(";")[1];
                int id = Integer.parseInt(idStr);
                modell.arvaltoztatas(id, valtoztatasMerteke);
                labelArValt.setText(modell.getTermekById(id).getNev() + " termék ára megváltozott " + sliderArValt.getValue() + "%-al!");
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelArValt;
    private javax.swing.JPanel pCsoport;
    private javax.swing.JPanel pTablazat;
    private javax.swing.JPanel pValtozas;
    private javax.swing.JSlider sliderArValt;
    private javax.swing.JScrollPane spTree;
    private javax.swing.JTree tTermekKat;
    private javax.swing.JTabbedPane tpUrlap;
    // End of variables declaration//GEN-END:variables

}
