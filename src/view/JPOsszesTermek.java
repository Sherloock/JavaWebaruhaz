package view;

import aruhaz.Modell;
import aruhaz.Tablazat;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class JPOsszesTermek extends javax.swing.JPanel {

    private final Modell modell;
    private final JFMainView view;

    public JPOsszesTermek(JFMainView view, Modell modell) {
        this.view = view;
        this.modell = modell;

        frissit();
    }

    public void frissit() {
        removeAll();
        float[] termekekTablaOszlopszelesseg = {2.0f, 8.0f, 14.0f, 9.0f, 43.0f, 6.0f, 18.0f};

        Tablazat tablazat = modell.getStatisztika().osszesAdat();
        DefaultTableModel tablaModell = new DefaultTableModel(tablazat.getAdatok(), tablazat.getFejlec()) {
            @Override
            public Class getColumnClass(int column) {
                return (column == 0 || column == 5) ? Integer.class : String.class;
            }
        };
        JTable tableTermekek = new MyJTable(tablaModell);
                //view.tablaElkeszitese(tablaModell);

        DefaultTableCellRenderer jobbraRendez = new DefaultTableCellRenderer();
        jobbraRendez.setHorizontalAlignment(JLabel.RIGHT);

        tableTermekek.getColumnModel().getColumn(0).setCellRenderer(jobbraRendez);
        tableTermekek.getColumnModel().getColumn(5).setCellRenderer(jobbraRendez);

        JScrollPane gorgetoSav = new JScrollPane(tableTermekek);
        setLayout(new BorderLayout(WIDTH, HEIGHT));

        add(tableTermekek.getTableHeader(), BorderLayout.PAGE_START);
        add(gorgetoSav, BorderLayout.CENTER);

        tableTermekek.setSize(getSize());
        tablaOszlopainakAtmeretezese(tableTermekek, termekekTablaOszlopszelesseg);

        //képek megnyitása kattintásra
        tableTermekek.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tableTermekek.rowAtPoint(evt.getPoint());
                int col = tableTermekek.columnAtPoint(evt.getPoint());

                if (tableTermekek.getColumnName(col).equals(tablazat.getFejlec()[6])) { //képek 6. oszlop
                    int id = (int) tableTermekek.getValueAt(row, 0); //id 0.oszlop

                    String path = modell.getTermekById(id).getKep();
                    try {
                        File file = new File(path.substring(1));
                        Image image = view.kepAtmeretezese(ImageIO.read(file));
                        JLabel picLabel = new JLabel(new ImageIcon(image));
                        JOptionPane.showMessageDialog(null, picLabel, modell.getTermekById(id).getNev(), JOptionPane.CLOSED_OPTION, null);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "A képet nem sikerült megnyitni!", "Hiba!", JOptionPane.ERROR_MESSAGE);
                    } catch (IndexOutOfBoundsException ex) {
                        JOptionPane.showMessageDialog(new JFrame(), "Nincs kép megadva!", "Hiba!", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                tablaOszlopainakAtmeretezese(tableTermekek, termekekTablaOszlopszelesseg);
            }
        });

        revalidate();
    }

    //TODO: oszlopok átrendezésnél rosszul müködik
    public void tablaOszlopainakAtmeretezese(JTable tabla, float[] oszlopSzelessegek) {
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
}
