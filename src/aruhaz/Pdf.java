package aruhaz;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Pdf {

    public static boolean kimutatas(Modell modell, String absPath) {
        Font FONT_NAGY = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Font FONT_SIMA = FontFactory.getFont(FontFactory.COURIER, 12, BaseColor.BLACK);

        Statisztika statisztika = modell.getStatisztika();
        File file = new File(absPath + ".pdf");
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
            document.open();

            //fejlec
            String line = "Webáruház statisztika\n\n";
            document.add(new Paragraph(line, FONT_NAGY));

            //termékkategóriák száma
            line = "Termékkategóriák száma: " + modell.getKategoriak().size() + "\n";
            document.add(new Paragraph(line, FONT_SIMA));

            //termékek száma, 
            line = "Termékek száma: " + modell.getTermekek().size() + "\n\n";
            document.add(new Paragraph(line, FONT_SIMA));

            //termékek átlagos ára termékkategóriánként, 
            Paragraph p = new Paragraph("Termékek átlagos ára kategóriánként\n\n", FONT_SIMA);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(tablazatElkeszitese(statisztika.termekAtlagosAraKategoriankent()));

            //termékek száma termékkategóriánként, 
            p = new Paragraph("\nTermékek száma kategóriánként\n\n", FONT_SIMA);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(tablazatElkeszitese(statisztika.termekSzamaKategoriankent()));

            //táblázatosan a termékkategóriák neve, min. és max. ára, mennyisége,
            p = new Paragraph("\nKategóriák neve, mennyisége legolcsóbb és legdrágább a kategóriában\n\n", FONT_SIMA);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(tablazatElkeszitese(statisztika.kategoriaDbMinMaxAr()));

            //a generált fájl elérési útvonala, 
            line = "\nA generált fájl elérési útvonala: " + file + "\n";
            document.add(new Paragraph(line, FONT_SIMA));

            //a generálás dátuma és időpontja rövid formátumban.
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd. HH:mm:ss").format(Calendar.getInstance().getTime());
            line = "Készült: " + timeStamp + "\n";
            document.add(new Paragraph(line, FONT_SIMA));

            document.close();

            if (JOptionPane.showConfirmDialog(new JFrame(),
                    "Fájl elmentve:\n" + file + "\n\nMegnyitja?\n", "Fájl mentése", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(file);
                }
            }

            return true;
        } catch (FileNotFoundException | DocumentException ex) {
            JOptionPane.showMessageDialog(new JFrame(), "Érvénytelen fájlnév!", "Hiba!", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            Logger.getLogger(Pdf.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public static PdfPTable tablazatElkeszitese(Tablazat tablazat) {
        PdfPTable pdfTablazat = new PdfPTable(tablazat.X);
        fejlecHozzadadasa(pdfTablazat, tablazat.getFejlec());
        adatokHozzadadasa(pdfTablazat, tablazat.getAdatok());
        return pdfTablazat;
    }

    private static void fejlecHozzadadasa(PdfPTable tabla, Object[] fejlecek) {
        for (Object fejlec : fejlecek) {
            PdfPCell header = new PdfPCell();
            header.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            header.setBackgroundColor(BaseColor.LIGHT_GRAY);
            header.setBorderWidth(2);
            header.setPhrase(new Paragraph(fejlec.toString()));
            tabla.addCell(header);
        }
    }

    private static void adatokHozzadadasa(PdfPTable tabla, Object[][] adatok) {
        for (Object[] adat : adatok) {
            for (int j = 0; j < adatok[0].length; j++) {
                Object obj = adat[j];
                PdfPCell cella = new PdfPCell();
                cella.setPhrase(new Paragraph(obj.toString()));
                if (!obj.getClass().equals(String.class)) {
                    cella.setVerticalAlignment(Element.ALIGN_RIGHT);
                }
                tabla.addCell(cella);
            }
        }
    }

}
