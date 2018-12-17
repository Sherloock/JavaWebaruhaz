package view;

import aruhaz.Modell;
import aruhaz.Tablazat;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class JPKategoriaStatisztika extends javax.swing.JPanel {
    
    private final Modell modell;
  
    public JPKategoriaStatisztika(JFMainView view, Modell modell) {
        this.modell = modell;
    }

     public void frissit() {
        removeAll();
        
        Tablazat tablazat = modell.getStatisztika().kategoriaDbMinMaxAr();
        
        DefaultTableModel tablaModell = new DefaultTableModel(tablazat.getAdatok(), tablazat.getFejlec()) {
            @Override
            public Class getColumnClass(int column) {
                return (column >= 1 && column <= 3) ? Integer.class : String.class;
            }
        };

        JTable tabla = new MyJTable(tablaModell);

        JScrollPane gorgetoSav = new JScrollPane(tabla);
        setLayout(new BorderLayout(WIDTH, HEIGHT));
        
        add(tabla.getTableHeader(), BorderLayout.PAGE_START);
        add(gorgetoSav, BorderLayout.CENTER);

         
        //jobbra rendez
        DefaultTableCellRenderer jobbraRendez = new DefaultTableCellRenderer();
        jobbraRendez.setHorizontalAlignment(JLabel.RIGHT);

        tabla.getColumnModel().getColumn(1).setCellRenderer(jobbraRendez);
        tabla.getColumnModel().getColumn(2).setCellRenderer(jobbraRendez);
        tabla.getColumnModel().getColumn(3).setCellRenderer(jobbraRendez);

        tabla.revalidate();
    }
}
