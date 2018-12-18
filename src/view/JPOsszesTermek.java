package view;

import static aruhaz.Tablazat.*;
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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class JPOsszesTermek extends javax.swing.JPanel {

    private final Modell modell;
    private final JFMainView view;
    
    public JPOsszesTermek(JFMainView view, Modell modell) {
        this.modell = modell;
        this.view = view;
    }

    public void frissit() {
        removeAll();
        
        float[] termekekTablaOszlopszelesseg = {2.0f, 8.0f, 14.0f, 9.0f, 43.0f, 6.0f, 18.0f};

        Tablazat tablazat = modell.getStatisztika().osszesAdat();
        tablazat.rendezes(0);
        
        DefaultTableModel tablaModell = new DefaultTableModel(tablazat.getAdatok(), tablazat.getFejlec()) {
            @Override
            public Class getColumnClass(int column) {
                String oszlopNeve = getColumnName(column);
                if (oszlopNeve.equals(AR) || oszlopNeve.equals(ID)) {
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

        tabla.setSize(getSize());
        tablaOszlopainakAtmeretezese(tabla, termekekTablaOszlopszelesseg);

        //képek megnyitása kattintásra
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int row = tabla.rowAtPoint(evt.getPoint());
                int col = tabla.columnAtPoint(evt.getPoint());

               if (tabla.getColumnName(col).equals(KEP)) { 
                    int id = (int) tabla.getValueAt(row, 0); //id 0.oszlop

                    String path = modell.getTermekById(id).getKep();
                    try {
                        File file = new File(path.substring(1));
                        Image image = JFMainView.kepAtmeretezese(ImageIO.read(file));
                        JLabel picLabel = new JLabel(new ImageIcon(image));
                        JOptionPane.showMessageDialog(view, picLabel, modell.getTermekById(id).getNev(), JOptionPane.CLOSED_OPTION, null);
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
                tablaOszlopainakAtmeretezese(tabla, termekekTablaOszlopszelesseg);
            }
        });

        revalidate();
    }

    //TODO: oszlopok átrendezésnél rosszul müködik
    public void tablaOszlopainakAtmeretezese(JTable tabla, float[] oszlopSzelessegek) {
        if (tabla != null) {
            //tabla.setPreferredSize(tabla.getPreferredSize());
            int tablaSzelesseg = tabla.getWidth();
            TableColumnModel jTableOszlopModell = tabla.getColumnModel();
            for (int i = 0; i < jTableOszlopModell.getColumnCount(); i++) {
                int pWidth = Math.round(oszlopSzelessegek[i] * tablaSzelesseg / 100);
                jTableOszlopModell.getColumn(i).setPreferredWidth(pWidth);
            }
        }
    }
}
