package view;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class MyJTable extends JTable {

    public MyJTable(TableModel dm) {
        super(dm);
        init();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int colIndex) {
        return false;
    }

    @Override
    public Class getColumnClass(int column) {
        return getValueAt(0, column).getClass();
    }

    @Override
    public Component prepareRenderer(
            TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);

        //  szinez
        if (!isRowSelected(row)) {
            c.setBackground(row % 2 == 0 ? getBackground() : Color.LIGHT_GRAY);
        }
        return c;
    }

    private void init() {
        setAutoCreateRowSorter(true);
        setFillsViewportHeight(true);
        
        //jobbra rendez
        DefaultTableCellRenderer jobbraRendez = new DefaultTableCellRenderer();
        jobbraRendez.setHorizontalAlignment(JLabel.RIGHT);

        for (int i = 0; i < getColumnCount(); i++) {
            if(getColumnClass(i).equals(Integer.class)){
                getColumnModel().getColumn(i).setCellRenderer(jobbraRendez);
            }
        }
    }

}
