package sprintMobile;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class FactureHandler extends DefaultHandler{
    private Vector factures;
    String idTag = "close";
    String date = "close";
    

    public FactureHandler() {
        factures = new Vector();
    }

    public Facture[] getFacture() {
        Facture[] personness = new Facture[factures.size()];
        factures.copyInto(personness);
        return personness;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Facture currentPersonne;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
       
      
        
        if (qName.equals("facture")) {
            currentPersonne = new Facture();
            //2ème methode pour parser les attributs
            currentPersonne.setId(Integer.parseInt(attributes.getValue("idFacture")));
            currentPersonne.setDate(attributes.getValue("date"));
            Client c =new Client();
            c.setId(Integer.parseInt(attributes.getValue("idClient")));
            currentPersonne.setClient(c);
            Gerant g =new Gerant();
            g.setId(Integer.parseInt(attributes.getValue("idGerant")));
            currentPersonne.setGerant(g);
            Offre o =new Offre();
            o.setId(Integer.parseInt(attributes.getValue("idOffre")));
            currentPersonne.setOffre(o);
            
            
            

            /****/
            
        } else if (qName.equals("idFacture")) {
            idTag = "open";
        }
        else if (qName.equals("date")) {
            date = "open";
        } 
        
       
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("facture")) {
            // we are no longer processing a <reg.../> tag
            factures.addElement(currentPersonne);
            currentPersonne = null;
        } else if (qName.equals("idFacture")) {
            idTag = "close";
        }
        else if (qName.equals("date")) {
            date = "close";
        } 
        
    }
    // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentPersonne != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentPersonne.setId(Integer.parseInt(id.trim()));
            } else
                if (date.equals("open")) {
                String datee = new String(ch, start, length).trim();
                currentPersonne.setDate(datee.trim());
            }
        }
    }
    
}
