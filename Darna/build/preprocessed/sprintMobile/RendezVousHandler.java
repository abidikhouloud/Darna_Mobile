/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprintMobile;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Raja
 */
public class RendezVousHandler extends DefaultHandler {
    
     private Vector rendezVou;
    String idTag = "close";
    String lieuTag = "close";
    String dateTag = "close";
    String personneTag = "close";
    String offreTag = "close";
    
    
    
    public RendezVousHandler() {
        rendezVou= new Vector();
    }

    public RendezVous[] getRendezVous() {
        RendezVous[] rendezVouss = new RendezVous[rendezVou.size()];
        rendezVou.copyInto(rendezVouss);
        return rendezVouss;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private RendezVous currentRendezVous;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        if (qName.equals("rendezVous")) {
            currentRendezVous = new RendezVous();
             currentRendezVous.setId(Integer.parseInt(attributes.getValue("id")));
            currentRendezVous.setLieu(attributes.getValue("lieu"));
            currentRendezVous.setDate(attributes.getValue("date"));
            currentRendezVous.setPersonne(attributes.getValue("personne"));
            Offre o=new Offre();
            o.setDescription(attributes.getValue("description"));
            currentRendezVous.setOffre(o);
            //2ème methode pour parser les attributs
           
            /****/
            
        } else if (qName.equals("rendezVous")) 
        {
            idTag = "open";
          
          
        }
    
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("rendezVous")) {
            // we are no longer processing a <reg.../> tag
            rendezVou.addElement(currentRendezVous);
            currentRendezVous = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("lieu")) {
            lieuTag = "close";
        } else if (qName.equals("date")) {
            dateTag = "close";
        }
        
        
    }
    // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentRendezVous != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentRendezVous.setId(id);
            } else
                if (lieuTag.equals("open")) {
                String lieu = new String(ch, start, length).trim();
                currentRendezVous.setLieu(lieu);
            } else
                    if (dateTag.equals("open")) {
                String date = new String(ch, start, length).trim();
                currentRendezVous.setDate(date);
            }
              
        }
    }
    
  
    
    
    
}
