package view;

import aruhaz.Modell;
import aruhaz.Tablazat;
import static aruhaz.Tablazat.*;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
                String oszlopNeve = getColumnName(column);
                if (oszlopNeve.equals(T_SZAMA) || oszlopNeve.equals(T_MIN) || oszlopNeve.equals(T_MAX)) {
                    return Integer.class;
                } else {
                    return String.class;
                }
            }
        };

        JTable tabla = new MyJTable(tablaModell);

        JScrollPane gorgetoSav = new JScrollPane(tabla);
        setLayout(new BorderLayout(WIDTH, HEIGHT));
        
        add(tabla.getTableHeader(), BorderLayout.PAGE_START);
        add(gorgetoSav, BorderLayout.CENTER);

        tabla.revalidate();
    }
}
