package sprintMobile;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Khalil
 */
public class LogementHandler extends DefaultHandler {

    private Vector logements;
    String idTag = "close";
    String descriptionTag = "close";
    String dateTag = "close";
//    String regionTag = "close";
//    String prixTag = "close";
//    String surfaceTag = "close";
//    String engagementTag = "close";
//    String gerantTag = "close";
//    String nbrChambreTag = "close";
//    String surfaceCouverteTag = "close";
    String typeTag="close";
    
    
    public LogementHandler() {
      logements = new Vector();
    }

    public Logement[] getLogement() {
        Logement[] listLogement = new Logement[logements.size()];
        logements.copyInto(listLogement);
        System.out.println("2");
        return listLogement;

    }
    private Logement currentLogement;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("person")) {
            currentLogement = new Logement();
            currentLogement.setId(Integer.parseInt(attributes.getValue("id")));
            currentLogement.setDescription(attributes.getValue("description"));
            currentLogement.setDate(attributes.getValue("date"));
//            currentLogement.setRegion(attributes.getValue("region"));
//            currentLogement.setPrix(Double.parseDouble(attributes.getValue("prix")));
//            currentLogement.setSurface(Double.parseDouble(attributes.getValue("surface")));
//            //currentLogement.setG(attributes.getValue("gerant"));
//            currentLogement.setEngagement((attributes.getValue("engagement")));
//            currentLogement.setNbrChambre(Integer.parseInt(attributes.getValue("nbrChambre")));
//            currentLogement.setSurfaceCouverte(Double.parseDouble(attributes.getValue("surfaceCouverte")));
            currentLogement.setType(attributes.getValue("type"));

        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("description")) {
            descriptionTag = "open";
        } else if (qName.equals("date")) {
            dateTag = "open";
        }
//        else if (qName.equals("region")) {
//            regionTag = "open";
//        } else if (qName.equals("prix")) {
//            prixTag = "open";
//        } else if (qName.equals("surface")) {
//            surfaceTag = "open";
//        } else if (qName.equals("engagement")) {
//            engagementTag = "open";
//        } else if (qName.equals("nbrChambre")) {
//            nbrChambreTag = "open";
//        } else if (qName.equals("surfaceCouverte")) {
//            surfaceCouverteTag = "open";
//
//        }
        else if (qName.equals("type")) {
            typeTag = "open";

       }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("person")) {
            logements.addElement(currentLogement);
            currentLogement = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("description")) {
            descriptionTag = "close";
        } else if (qName.equals("date")) {
            dateTag = "close";
        } 
//        else if (qName.equals("region")) {
//            regionTag = "close";
//        } else if (qName.equals("prix")) {
//            prixTag = "close";
//        } else if (qName.equals("surface")) {
//            surfaceTag = "close";
//        } else if (qName.equals("engagement")) {
//            engagementTag = "close";
//        } else if (qName.equals("nbrChambre")) {
//            nbrChambreTag = "close";
//        }    surfaceCouverteTag = "close";
//
//        } else if (qName.equals("surfaceCouverte")) {
//            surfaceCouverteTag = "close";
//
//        }
         else if (qName.equals("type")) {
            typeTag = "close";

       }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentLogement != null) {
            if (idTag.equals("open")) {
                int id = Integer.parseInt(new String(ch, start, length).trim());
                currentLogement.setId(id);
            } else if (descriptionTag.equals("open")) {
                String description = new String(ch, start, length).trim();
                currentLogement.setDescription(description);
            } else if (dateTag.equals("open")) {
                String date = new String(ch, start, length).trim();
                currentLogement.setDate(date);
            }
//            else if (regionTag.equals("open")) {
//                String region = new String(ch, start, length).trim();
//                currentLogement.setRegion(region);
//            } else if (prixTag.equals("open")) {
//                double prix = Double.parseDouble(new String(ch, start, length).trim());
//                currentLogement.setPrix(prix);
//            } else if (surfaceTag.equals("open")) {
//                double surface = Double.parseDouble(new String(ch, start, length).trim());
//                currentLogement.setSurface(surface);
//            } else if (dateTag.equals("open")) {
//                String date = new String(ch, start, length).trim();
//                currentLogement.setDate(date);
//            } else if (engagementTag.equals("open")) {
//                String engagement = new String(ch, start, length).trim();
//                currentLogement.setEngagement(engagement);
//            } else if (nbrChambreTag.equals("open")) {
//                int nbrChambre = Integer.parseInt(new String(ch, start, length).trim());
//                currentLogement.setNbrChambre(nbrChambre);
//            } else if (surfaceCouverteTag.equals("open")) {
//                double surfaceCouverte = Double.parseDouble(new String(ch, start, length).trim());
//                currentLogement.setSurfaceCouverte(surfaceCouverte);
//
//            }
            else if (typeTag.equals("open")) {
                String type = new String(ch, start, length).trim();
                currentLogement.setType(type);

           }
        }
    }
}
