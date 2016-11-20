package sprintMobile;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class AlerteHandler extends DefaultHandler{
    private Vector personnes;
    String idTag = "close";
    String prixMinTag = "close";
    String prixMaxTag = "close";
     String regionTag = "close";
    String engagementTag = "close";
    String typedeBienTag = "close";
    String surfaceTag = "close";
    String idOffreTag = "close";

    public AlerteHandler() {
        personnes = new Vector();

    }

    public Alerte[] getAlerte() {
        Alerte[] personness = new Alerte[personnes.size()];
        personnes.copyInto(personness);
      
        return personness;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Alerte currentPersonne;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("alerte")) {
            currentPersonne = new Alerte();
            //2ème methode pour parser les attributs
                                                                        

            currentPersonne.setId(Integer.parseInt(attributes.getValue("id").trim()));
            currentPersonne.setPrixMax(Double.parseDouble(attributes.getValue("prixMax").trim()));
            currentPersonne.setPrixMin(Double.parseDouble(attributes.getValue("prixMin").trim()));
            currentPersonne.setRegion(attributes.getValue("region").trim());
            currentPersonne.setEngagement(attributes.getValue("engagement").trim());
            currentPersonne.setTypedeBien(attributes.getValue("typedeBien").trim());
            currentPersonne.setSurface(Double.parseDouble(attributes.getValue("surface").trim()));
            Offre o =new Offre();
            o.setId(Integer.parseInt(attributes.getValue("idOffre").trim()));
            currentPersonne.setOffre(o);
             Client c =new Client();
            c.setId(Integer.parseInt(attributes.getValue("idClient").trim()));
            currentPersonne.setClient(c);
            

            /****/
            
        } else if (qName.equals("id")) {
            idTag = "open";
        }
        else if (qName.equals("prixMin")) {
            prixMinTag = "open";
        } 
        else if (qName.equals("prixMax")) {
            prixMaxTag = "open";
        } 
        else if (qName.equals("region")) {
            regionTag = "open";
        } 
        else if (qName.equals("engagement")) {
            engagementTag = "open";
        } else if (qName.equals("typedeBien")) {
            typedeBienTag = "open";
        } else if (qName.equals("surface")) {
            surfaceTag = "open";
        }
       
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("alerte")) {
            // we are no longer processing a <reg.../> tag
            personnes.addElement(currentPersonne);
            currentPersonne = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        }
        else if (qName.equals("prixMin")) {
            prixMinTag = "close";
        } 
        else if (qName.equals("prixMax")) {
            prixMaxTag = "close";
        } 
        else if (qName.equals("region")) {
            regionTag = "close";
        } 
        else if (qName.equals("engagement")) {
            engagementTag = "close";
        } else if (qName.equals("typedeBien")) {
            typedeBienTag = "close";
        } else if (qName.equals("surface")) {
            surfaceTag = "close";
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
                if (prixMinTag.equals("open")) {
                String min = new String(ch, start, length).trim();
                currentPersonne.setPrixMin(Double.parseDouble(min.trim()));
            } else
                    if (prixMaxTag.equals("open")) {
                String max = new String(ch, start, length).trim();
                currentPersonne.setPrixMax(Double.parseDouble(max.trim()));
            }
            else
                    if (regionTag.equals("open")) {
                String region = new String(ch, start, length).trim();
                currentPersonne.setRegion(region.trim());
            }
            else
                    if (engagementTag.equals("open")) {
                String eng = new String(ch, start, length).trim();
                currentPersonne.setEngagement(eng.trim());
            }
            else
                    if (typedeBienTag.equals("open")) {
                String type = new String(ch, start, length).trim();
                currentPersonne.setTypedeBien(type.trim());
            }
            else
                    if (surfaceTag.equals("open")) {
                String surface = new String(ch, start, length).trim();
                currentPersonne.setSurface(Double.parseDouble(surface.trim()));
            }
        }
    }
    
}
