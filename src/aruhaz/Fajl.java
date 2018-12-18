package aruhaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Fajl {

    public final String TERMEKEK_PATH_STRING;
    public final String KATEGORIAK_PATH_STRING;

    private Document termekekDoc;

    public Fajl(String TERMEKEK_PATH_STRING, String KATEGORIAK_PATH_STRING) {
        this.TERMEKEK_PATH_STRING = TERMEKEK_PATH_STRING;
        this.KATEGORIAK_PATH_STRING = KATEGORIAK_PATH_STRING;
    }

    //termékek feltöltése
    public ArrayList<Termek> termekekBetoltese() {
        ArrayList<Termek> termekek = new ArrayList();
        termekekDoc = dokumentumFajlbol(new File(TERMEKEK_PATH_STRING));

        NodeList termekNodeList = termekekDoc.getDocumentElement().getElementsByTagName("Termek");
        for (int i = 0; i < termekNodeList.getLength(); i++) {
            Element termek = (Element) termekNodeList.item(i);

            termekek.add(new Termek(
                    adatKivetel(termek, "Id"),
                    adatKivetel(termek, "Telepules"),
                    adatKivetel(termek, "Nev"),
                    adatKivetel(termek, "Leiras"),
                    adatKivetel(termek, "Kategoria"),
                    adatKivetel(termek, "Ar"),
                    adatKivetel(termek, "Kep")
            ));
        }

        //Köv index beallítasa
        Element root = (Element) termekekDoc.getElementsByTagName("Termekek").item(0);
        Termek.setKovID(Integer.parseInt(root.getAttribute("kovId")));

        return termekek;
    }

    // kategóriák feltöltése
    public ArrayList<String> kategoriakBetoltese() {
        ArrayList<String> kategoriak = new ArrayList();
        Document dokumentum = dokumentumFajlbol(new File(KATEGORIAK_PATH_STRING));

        NodeList kategoriaNodeList = dokumentum.getDocumentElement().getElementsByTagName("Kategoria");

        for (int i = 0; i < kategoriaNodeList.getLength(); i++) {
            Element kategoria = (Element) kategoriaNodeList.item(i);
            kategoriak.add(adatKivetel(kategoria, "Tipus"));
        }
        return kategoriak;
    }

    //települések feltöltése
    public ArrayList<String> telepulesekBetoltese() {
        ArrayList<String> telepulesek = new ArrayList();
        String fajlnev = "./files/telepulesek.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(new File(fajlnev)))) {
            String sor;
            int index = 0;
            while ((sor = br.readLine()) != null && !sor.contains("Összesen")) {
                if (2 < index) {
                    telepulesek.add(sor.split("\\;")[0]);
                }
                index++;
            }
        } catch (FileNotFoundException ex) {
            hibaUzenet("A forrásfájl nem található! (" + fajlnev + ")");
        } catch (IOException ex) {
            hibaUzenet("Ismeretlen hiba!");
        }
        return telepulesek;
    }

    //dokumentum elkészítése a megadott fájlból
    private Document dokumentumFajlbol(File fajl) {
        Document dokumentum = null;
        try {
            dokumentum = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(fajl);
        } catch (ParserConfigurationException e) {
            hibaUzenet("A transzformer konfigurálása közben hiba lépett fel!");
        } catch (SAXException e) {
            hibaUzenet("Az xml fájl értelemzése közben hiba lépett fel!");
        } catch (IOException e) {
            hibaUzenet("A fájl olvasása közben hiba lépett fel!");
        }
        return dokumentum;
    }

    // termek hozzadasa a modellhez és a fájlhoz
    public void termekHozzadasa(Termek ujTermek) {
        //kovID módosítása
        Element root = (Element) termekekDoc.getElementsByTagName("Termekek").item(0);
        root.setAttribute("kovId", (ujTermek.ID + 1) + "");

        //termék hozzáadása
        Element termek = termekekDoc.createElement("Termek");

        adatFelvitelSzulohoz(termek, "Id", ujTermek.ID);
        adatFelvitelSzulohoz(termek, "Telepules", ujTermek.getTelepules());
        adatFelvitelSzulohoz(termek, "Nev", ujTermek.getNev());
        adatFelvitelSzulohoz(termek, "Kategoria", ujTermek.getKategoria());
        adatFelvitelSzulohoz(termek, "Leiras", ujTermek.getLeiras());
        adatFelvitelSzulohoz(termek, "Kep", ujTermek.getKep());
        adatFelvitelSzulohoz(termek, "Ar", ujTermek.getAr());

        root = (Element) termekekDoc.getElementsByTagName("Termekek").item(0);
        root.appendChild(termekekDoc.importNode(termek, true));

        dokumentumFajlbaIrasa(termekekDoc, TERMEKEK_PATH_STRING);
    }

    //adott id-vel rendelkező elem törlése
    public void termekTorlese(String id) {
        Element root = (Element) termekekDoc.getElementsByTagName("Termekek").item(0);

        NodeList termekekNodeList = root.getChildNodes();

        for (int i = 0; i < termekekNodeList.getLength(); i++) {
            if (termekekNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {//nem tag elemek kiszorítása
                Element csomopont = (Element) termekekNodeList.item(i);

                if (adatKivetel(csomopont, "Id").equals(id)) {
                    root.removeChild(csomopont);
                    break;
                }
            }
        }
        dokumentumFajlbaIrasa(termekekDoc, TERMEKEK_PATH_STRING);
    }

    //egy termék árának megváltoztatása
    public void arvaltoztatas(int id, int ujAr) {
        NodeList termekekNodeList = termekekDoc.getDocumentElement().getElementsByTagName("Termek");

        for (int i = 0; i < termekekNodeList.getLength(); i++) {

            if (termekekNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element termek = (Element) termekekNodeList.item(i);

                if (adatKivetel(termek, "Id").equals(id + "")) {
                    Node ar = termek.getElementsByTagName("Ar").item(0).getFirstChild();
                    ar.setNodeValue(ujAr + "");
                    break;
                }
            }
        }
        dokumentumFajlbaIrasa(termekekDoc, TERMEKEK_PATH_STRING);
    }

    //helper methods 
    
    //adott elemhez adott azonosítoval és adattagot ad
    private void adatFelvitelSzulohoz(Element szulo, String azonosito, Object adat) {
        Element elem = termekekDoc.createElement(azonosito);
        elem.appendChild(termekekDoc.createTextNode(adat.toString()));
        szulo.appendChild(elem);
    }

    //egy elem megadott attribútumával tér vissza
    private String adatKivetel(Element elem, String attributum) {
        return elem.getElementsByTagName(attributum).item(0).getFirstChild().getNodeValue();
    }

    //dokumentumot visszaírja fájlba
    private void dokumentumFajlbaIrasa(Document document, String teljesEleresiUtvonal) {
        try {
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer tr = tf.newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.setOutputProperty(OutputKeys.METHOD, "xml");
            tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(teljesEleresiUtvonal));

            tr.transform(source, result);
        } catch (FileNotFoundException ex) {
            hibaUzenet("Az xml fájl nem található!");
        } catch (TransformerException ex) {
            hibaUzenet("Transzformer hiba!");
        }
    }

    private void hibaUzenet(String hibauzenet) {
        JOptionPane.showMessageDialog(null, hibauzenet, "Hiba!", JOptionPane.ERROR_MESSAGE);
    }
}
