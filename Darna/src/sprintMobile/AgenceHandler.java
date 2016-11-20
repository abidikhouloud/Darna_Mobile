
package sprintMobile;

/**
 *
 * @author Raja
 */

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class AgenceHandler extends DefaultHandler{
    private Vector agences;
    String idTag = "close";
    String adresseTag = "close";
    String telTag = "close";
    String nomTag = "close";
    public AgenceHandler() {
        agences = new Vector();
    }

    public Agence[] getAgence() {
        Agence[] agencess = new Agence[agences.size()];
        agences.copyInto(agencess);
        return agencess;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Agence currentAgence;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if (qName.equals("agence")) {
            currentAgence = new Agence();
             currentAgence.setId(Integer.parseInt(attributes.getValue("id").trim()));
            currentAgence.setAdresse(attributes.getValue("adresse"));
            currentAgence.setTel(attributes.getValue("tel"));
            currentAgence.setNom(attributes.getValue("nom"));
            
            //2ème methode pour parser les attributs
           
            /****/
            
        } else if (qName.equals("agence")) 
        {
            idTag = "open";
          
          
        }
    
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("agence")) {
            // we are no longer processing a <reg.../> tag
            agences.addElement(currentAgence);
            currentAgence = null;
        } else if (qName.equals("agence")) {
            idTag = "close";
        } else if (qName.equals("adresse")) {
            adresseTag = "close";
        } else if (qName.equals("tel")) {
            telTag = "close";
        }
        
        else if (qName.equals("nom")) {
            nomTag = "close";
        }
    }
    // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentAgence != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentAgence.setId(id);
            } else
                if (adresseTag.equals("open")) {
                String adresse = new String(ch, start, length).trim();
                currentAgence.setAdresse(adresse);
            } else
                    if (telTag.equals("open")) {
                String tel = new String(ch, start, length).trim();
                currentAgence.setTel(tel);
            }
              if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentAgence.setNom(nom);
            }
        }
    }
    
}