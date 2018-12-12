package aruhaz;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
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
    public final static String TERMEKEK_PATH_STRING = "./files/termek.xml";
    public final static String KATEGORIAK_PATH_STRING = "./files/kategoria.xml";
   // public final static String KATEGORIAK_PATH_STRING = "./files/termek.xml";

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
    }

    // kategóriák feltöltése
    private void kategoriakBetoltese() {
        Document dokumentum = dokumentumFajlbol(new File(KATEGORIAK_PATH_STRING));

        NodeList kategoriaNodeList = dokumentum.getDocumentElement().getElementsByTagName("Kategoria");

        for (int i = 0; i < kategoriaNodeList.getLength(); i++) {
            Element kategoria = (Element) kategoriaNodeList.item(i);
            kategoriak.add(adatKivetel(kategoria, "Tipus"));
        }
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
    
    //soron következő index lekérése és frissítése
    private int kovIndex() {
        int index = 0;
        Element root = (Element) termekekDoc.getElementsByTagName("Termekek").item(0);
        index = Integer.parseInt(root.getAttribute("kovId"));
        root.setAttribute("kovId", (index + 1) + "");
        return index;
    } 
    
    public void termekHozzadasa(String telepules, String nev, String kategoria, String leiras, String ar, String kep) {
        int index = kovIndex();
        termekek.add(new Termek(index +"",  telepules, nev, leiras, kategoria, ar, kep));
        
        Element termek = termekekDoc.createElement("Termek");

        adatFelvitelSzulohoz(termek, "Id", index + "");
        adatFelvitelSzulohoz(termek, "Telepules", telepules + "");
        adatFelvitelSzulohoz(termek, "Nev", nev);
        adatFelvitelSzulohoz(termek, "Kategoria", kategoria);
        adatFelvitelSzulohoz(termek, "Leiras", leiras);
        adatFelvitelSzulohoz(termek, "Kep", kep);
        adatFelvitelSzulohoz(termek, "Ar", ar + "");

        Element root = (Element) termekekDoc.getElementsByTagName("Termekek").item(0);
        root.appendChild(termekekDoc.importNode(termek, true));

        dokumentumFajlbaIrasa(termekekDoc, TERMEKEK_PATH_STRING);
    }

    private void adatFelvitelSzulohoz(Element szulo, String azonosito, String data){
            Element element = termekekDoc.createElement(azonosito);
            element.appendChild(termekekDoc.createTextNode(data));
            szulo.appendChild(element);
    }
    
    //adott id-vel rendelkező elem törlése
    public void termekTorlese(String id) {
        Element root = (Element) termekekDoc.getElementsByTagName("Termekek").item(0);
        NodeList termekekNodeList = root.getChildNodes();
        
        boolean sikeresTorles = false;
        for (int i = 0; i < termekekNodeList.getLength() && !sikeresTorles; i++) {
            if (termekekNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {//nem tag elemek kiszorítása
                Element csomopont = (Element) termekekNodeList.item(i);
                                
                if (adatKivetel(csomopont, "Id").equals(id)) {
                    root.removeChild(csomopont);
                    Iterator<Termek> iter = termekek.iterator();
                    
                    while (iter.hasNext()) {
                        Termek termek = iter.next();
                        if (termek.ID == Integer.parseInt(id)) {
                            iter.remove();
                        }
                    }
                    sikeresTorles = true;
                }
            }
        }
        if (sikeresTorles) {
             dokumentumFajlbaIrasa(termekekDoc, TERMEKEK_PATH_STRING);
        } else {
            hibaUzenet("A megadott azonosító nem található!");
        }
    }
    
    //dokumentumot visszaírja fájlba
    private void dokumentumFajlbaIrasa(Document document, String teljesEleresiUtvonal){
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

    //egy termék árának megváltoztatása
    public void arvaltoztatas(int id, double szazalek) {
        getTermekById(id).szazalekosArvaltoztatas(szazalek);
        
        NodeList termekekNodeList = termekekDoc.getDocumentElement().getElementsByTagName("Termek");
        
        boolean sikeresArModositas = false;
        for (int i = 0; i < termekekNodeList.getLength() && !sikeresArModositas; i++) {
            
            if (termekekNodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element termek = (Element) termekekNodeList.item(i);
                                
                if (adatKivetel(termek, "Id").equals(id + "")) {
                    Node ar =  termek.getElementsByTagName("Ar").item(0).getFirstChild();
                    ar.setNodeValue( getTermekById(id).getAr() + ""); 
                    sikeresArModositas = true;
                }
            }
        }
        if (sikeresArModositas) {
            dokumentumFajlbaIrasa(termekekDoc, TERMEKEK_PATH_STRING);
        } else {
            hibaUzenet("A megadott azonosító nem található!");
        }
    }

    //összes ár megváltoztatása
    public void arvaltoztatas(double szazalek) {
        termekek.forEach((termek) -> { arvaltoztatas(termek.ID, szazalek); });
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



    public ArrayList<String> getKategoriak() {
        return kategoriak;
    }

    public ArrayList<Termek> getTermekek() {
        return termekek;
    }

    public ArrayList<String> getTelepulesek() {
        return telepulesek;
    }
    
     private void hibaUzenet(String hibauzenet) {
        JOptionPane.showMessageDialog(new JFrame(), hibauzenet, "Hiba!", JOptionPane.ERROR_MESSAGE);
    }

}
