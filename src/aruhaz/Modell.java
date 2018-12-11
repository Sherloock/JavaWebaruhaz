package aruhaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Modell {

    private ArrayList<Termek> termekek = new ArrayList();
    private ArrayList<String> kategoriak = new ArrayList();
    private ArrayList<String> telepulesek = new ArrayList();
    private Document termekekDoc;

    public Modell() {
        termekekBetoltese();
        kategoriakBetoltese();
        telepulesekBetoltese();
    }

    //termékek feltöltése
    private void termekekBetoltese() {
        termekekDoc = dokumentumFajlbol(new File("./files/termek.xml"));

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
        System.out.println(termekek);
    }

    // kategóriák feltöltése
    private void kategoriakBetoltese() {
        Document dokumentum = dokumentumFajlbol(new File("./files/kategoria.xml"));

        NodeList kategoriaNodeList = dokumentum.getDocumentElement().getElementsByTagName("Kategoria");

        for (int i = 0; i < kategoriaNodeList.getLength(); i++) {
            Element kategoria = (Element) kategoriaNodeList.item(i);
            kategoriak.add(adatKivetel(kategoria, "Tipus"));
        }
        System.out.println(kategoriak);
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

    //egy elem megadott attribútumáva tér vissza
    private String adatKivetel(Element elem, String attributum) {
        return elem.getElementsByTagName(attributum).item(0).getFirstChild().getNodeValue();
    }

    //települések feltöltése
    private void telepulesekBetoltese() {
        int i = 0;
        String fajlnev = "./files/telepulesek.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(new File(fajlnev)))) {
            String sor;
            int index = 0;
            while ((sor = br.readLine()) != null && !sor.contains("Összesen")) {
                if (index > 3) {
                    telepulesek.add(sor.split("\\;")[0]);
                }
                index++;
            }
        } catch (FileNotFoundException ex) {
            hibaUzenet("A forrásfájl nem található! (" + fajlnev + ")");
        } catch (IOException ex) {
            hibaUzenet("Ismeretlen hiba!");
        }
        System.out.println(telepulesek);
    }

    private void hibaUzenet(String hibauzenet) {

        JOptionPane.showMessageDialog(new JFrame(), hibauzenet, "Hiba!", JOptionPane.ERROR_MESSAGE);
    }
    
    //soron következő index kinyerése és frissítése
    private int kovIndex() {
        int index = 0;
        Element root = (Element) termekekDoc.getElementsByTagName("Termekek").item(0);
        index = Integer.parseInt(root.getAttribute("kovId"));
        root.setAttribute("kovId", (index + 1) + "");
        return index;
    }
    
    
    public void rekordFelvitelFajlba(String telepules, String nev, String kategoria, String leiras, String ar, String kep) {
        int index = kovIndex();
        termekek.add(new Termek(index +"",  telepules, nev, leiras, kategoria, ar, kep));
        
        try {//egyes elemek előállítása
            Element termek = termekekDoc.createElement("Termek");
            
            adatFelvitelSzulohoz(termek, "ID", index + "");
            adatFelvitelSzulohoz(termek, "Telepules", telepules + "");
            adatFelvitelSzulohoz(termek, "Nev", nev);
            adatFelvitelSzulohoz(termek, "Kategoria", kategoria);
            adatFelvitelSzulohoz(termek, "Leiras", leiras);
            adatFelvitelSzulohoz(termek, "Kep", kep);
            adatFelvitelSzulohoz(termek, "Ar", ar + "");

            Element root = (Element) termekekDoc.getElementsByTagName("Termekek").item(0);
            root.appendChild(termekekDoc.importNode(termek, true));

            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer optimusz = tf.newTransformer();
            optimusz.setOutputProperty(OutputKeys.INDENT, "yes");
            optimusz.setOutputProperty(OutputKeys.METHOD, "xml");
            optimusz.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            optimusz.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");

            DOMSource source = new DOMSource(termekekDoc);
            StreamResult result = new StreamResult(new FileOutputStream("./files/termek.xml"));

            optimusz.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            hibaUzenet("A rtanszformer konfigurálása sikertelen!");
        } catch (TransformerException ex) {
            hibaUzenet("Transzformer hiba!");
        } catch (FileNotFoundException ex) {
            hibaUzenet("Az xml fájl nem található!");
        }
    }

    private void adatFelvitelSzulohoz(Element szulo, String azonosito, String data){
            Element element = termekekDoc.createElement(azonosito);
            element.appendChild(termekekDoc.createTextNode(data));
            szulo.appendChild(element);
    }
    
    public void torles(String termekNev) throws SAXException, IOException, ParserConfigurationException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        Document doc = db.parse("./files/kategoria.xml");

        NodeList nevLista = doc.getElementsByTagName("Termek");
        for (int i = 0; i < nevLista.getLength(); i++) {
            Element termek = (Element) nevLista.item(i);
            Element neve = (Element) termek.getElementsByTagName("Nev").item(0);
            String tNeve = neve.getTextContent();
            if (tNeve.equals(termekNev)) {
                termek.getParentNode().removeChild(termek);
            }
        }
    }

    //egy termék árának megváltoztatása
    public void arvaltoztatas(int id, int szazalek) {
        getTermekById(id).szazalekosArvaltoztatas(szazalek);
    }

    //összes ár megváltoztatása
    public void arvaltoztatas(int szazalek) {

        for (Termek termek : termekek) {
            arvaltoztatas(termek.ID, szazalek);
        }
    }

    //termék visszaadása az ID-je alalpján
    public Termek getTermekById(int id) {
        for (Termek termek : termekek) {
            if (termek.ID == id) {
                return termek;
            }
        }
        return null;
    }

    // termékek mátrixba rendezése a táblázathoz
    public String[][] GetTermekMatrix() {
        String[][] adatok = new String[termekek.size()][7];
        for (int i = 0; i < termekek.size(); i++) {
            adatok[i][0] = termekek.get(i).ID + "";
            adatok[i][1] = termekek.get(i).getTelepules();
            adatok[i][2] = termekek.get(i).getNev();
            adatok[i][3] = termekek.get(i).getKategoria();
            adatok[i][4] = termekek.get(i).getLeiras();
            adatok[i][5] = termekek.get(i).getAr() + " Ft";
            adatok[i][6] = termekek.get(i).getKep();
        }
        return adatok;
    }

    public ArrayList<String> getKategoriak() {
        return kategoriak;
    }

    public ArrayList<Termek> getTermekLista() {
        return termekek;
    }

    public ArrayList<String> getTelepulesek() {
        return telepulesek;
    }
}
