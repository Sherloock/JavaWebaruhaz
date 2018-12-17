package view;


import aruhaz.Modell;
import aruhaz.Tablazat;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

public class JPKategoriaNezet extends javax.swing.JPanel {
    private final Modell modell;
    private final JFMainView view;
  
    public JPKategoriaNezet(JFMainView view, Modell modell) {
        this.view = view;
        this.modell = modell;
        
        setLayout(new BorderLayout());
        frissit();
    }


      public void frissit() {
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
        JTree tree = new JTree(root);

        add(new JScrollPane(tree));

        tree.setShowsRootHandles(true);
        tree.setRootVisible(true);

        tree.getSelectionModel().addTreeSelectionListener((TreeSelectionEvent e) -> {
            DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
        });
        revalidate();
    }
     
      //fa rendezese abc sorrendbe
    public static void rendezTree(DefaultMutableTreeNode root) {
        Enumeration e = root.depthFirstEnumeration();
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
            if (!node.isLeaf()) {
                rendezNode(node);
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

    public static void rendezNode(DefaultMutableTreeNode parent) {
        int n = parent.getChildCount();
        ArrayList<DefaultMutableTreeNode> children = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            children.add((DefaultMutableTreeNode) parent.getChildAt(i));
        }
        Collections.sort(children, tnc);
        parent.removeAllChildren();
        children.forEach(parent::add);
    }
}
