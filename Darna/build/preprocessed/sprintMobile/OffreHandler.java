

package sprintMobile;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 *
 * @author Khalil
 */
public class OffreHandler extends DefaultHandler{
    
    private Vector offres;
    String idTag = "close";
    String descriptionTag = "close";
    String dateTag = "close";
  /*  String regionTag = "close";
    String prixTag = "close";
    String surfaceTag="close";
    String engagementTag="close";
    String gerantTag="close";
    String nbrChambreTag="close";
    String surfaceCouverteTag="close";
    */
    public OffreHandler()  {
        offres = new Vector();
      
    }
    
    
     public Offre[] getOffre() {
        Offre[] offress = new Offre[offres.size()];
        offres.copyInto(offress);
        return offress;
    }
     private Offre currentOffre;
     
     
      public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("person")) {
            currentOffre = new Offre();
            
                    //2ème methode pour parser les attributs
            currentOffre.setDescription(attributes.getValue("id"));
            currentOffre.setDescription(attributes.getValue("description"));
            currentOffre.setDate(attributes.getValue("date"));
         // currentOffre.setRegion(attributes.getValue("region"));
         /* currentLogement.setPrix(attributes.getValue("prix"));
            currentLogement.setDescription(attributes.getValue("description"));
            currentLogement.setDescription(attributes.getValue("description"));
            currentLogement.setDescription(attributes.getValue("description"));
            currentLogement.setDescription(attributes.getValue("description"));
            currentLogement.setDescription(attributes.getValue("description"));
            currentLogement.setDescription(attributes.getValue("description"));
            currentLogement.setDescription(attributes.getValue("description"));
            currentLogement.setDescription(attributes.getValue("description"));

            */
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("description")) {
            descriptionTag = "open";
        } else if (qName.equals("date")) {
            dateTag = "open";
        }
    }
     
      
      
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("person")) {
            // we are no longer processing a <reg.../> tag
            offres.addElement(currentOffre);
            currentOffre = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("description")) {
            descriptionTag = "close";
        } else if (qName.equals("date")) {
            dateTag = "close";
        }
    }
      
      
      
      
       public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentOffre != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentOffre.setId(Integer.parseInt(id));
            } else
                if (descriptionTag.equals("open")) {
                String description = new String(ch, start, length).trim();
                currentOffre.setDescription(description);
            } else
                    if (dateTag.equals("open")) {
                String date = new String(ch, start, length).trim();
                currentOffre.setDate(date);
            }
        }
    }
}
