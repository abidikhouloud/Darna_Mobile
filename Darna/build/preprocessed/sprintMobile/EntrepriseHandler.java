package sprintMobile;

import java.util.Vector;
import javax.microedition.xml.rpc.Type;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import sprintMobile.Entreprise;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Khalil
 */
public class EntrepriseHandler extends DefaultHandler{
    


    private Vector entreprises;
    String idTag = "close";
    String descriptionTag = "close";
    String dateTag = "close";
    String regionTag = "close";
    String prixTag = "close";
   // String surfaceTag = "close";
    String engagementTag = "close";
    // String gerantTag = "close";
    String nbr_etageTag="close"; 
   // String surfaceCouverteTag = "close";
    String typeTag="close"; 

    public EntrepriseHandler() {
       entreprises = new Vector();

    }

    public Entreprise[] getEntreprises() {
        Entreprise[] listEntreprises = new Entreprise[entreprises.size()];
        entreprises.copyInto(listEntreprises);
        return listEntreprises;
    }
    private Entreprise currentEntreprise;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("person")) {
            currentEntreprise = new Entreprise();

            currentEntreprise.setId(Integer.parseInt(attributes.getValue("id")));
            currentEntreprise.setDescription(attributes.getValue("description"));
//            currentEntreprise.setDate(attributes.getValue("date"));
//            currentEntreprise.setRegion(attributes.getValue("region"));
//            currentEntreprise.setPrix(Double.parseDouble(attributes.getValue("prix")));
//            //currentEntreprise.setSurface(Double.parseDouble(attributes.getValue("surface")));
//            //currentEntreprise.setG(attributes.getValue("gerant"));
//            currentEntreprise.setEngagement((attributes.getValue("engagement")));
//            currentEntreprise.setNbr_etage(Integer.parseInt(attributes.getValue("nbr_etage")));
//            //currentEntreprise.setSurfaceCouverte(Double.parseDouble(attributes.getValue("surfaceCouverte")));
currentEntreprise.setType(attributes.getValue("description"));
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("description")) {
            descriptionTag = "open";
        } 
//        else if (qName.equals("date")) {
//            dateTag = "open";
//        }
//        else if (qName.equals("region")) {
//            regionTag = "open";
//        } else if (qName.equals("prix")) {
//            prixTag = "open";
//        } 
////        else if (qName.equals("surface")) {
////            surfaceTag = "open";
////        }
//        else if (qName.equals("engagement")) {
//            engagementTag = "open";
//        } else if (qName.equals("nbr_etage")) {
//            nbr_etageTag = "open";
//        } 
////        else if (qName.equals("surfaceCouverte")) {
////            surfaceCouverteTag = "open";
////
////        }
        else if (qName.equals("type")) {
            typeTag = "open";

       }
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("person")) {
            entreprises.addElement(currentEntreprise);
            currentEntreprise = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("description")) {
            descriptionTag = "close";
        }
//        else if (qName.equals("date")) {
//            dateTag = "close";
//        } 
//        else if (qName.equals("region")) {
//            regionTag = "close";
//        } else if (qName.equals("prix")) {
//            prixTag = "close";
//        } 
////        else if (qName.equals("surface")) {
////            surfaceTag = "close";
////        }
//        else if (qName.equals("engagement")) {
//            engagementTag = "close";
//        } else if (qName.equals("nbr_etage")) {
//            nbr_etageTag = "close";
//        } 
////        else if (qName.equals("surfaceCouverte")) {
////            surfaceCouverteTag = "close";
////
////        }
          else if (qName.equals("surfaceCouverte")) {
            typeTag = "close";

          }
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentEntreprise != null) {
            if (idTag.equals("open")) {
                int id = Integer.parseInt(new String(ch, start, length).trim());
                currentEntreprise.setId(id);
            } else if (descriptionTag.equals("open")) {
                String description = new String(ch, start, length).trim();
                currentEntreprise.setDescription(description);
            }
//            else if (dateTag.equals("open")) {
//                String date = new String(ch, start, length).trim();
//                currentEntreprise.setDate(date);
//            } else if (regionTag.equals("open")) {
//                String region = new String(ch, start, length).trim();
//                currentEntreprise.setRegion(region);
//            } else if (prixTag.equals("open")) {
//                double prix = Double.parseDouble(new String(ch, start, length).trim());
//                currentEntreprise.setPrix(prix);
//            } 
////            else if (surfaceTag.equals("open")) {
////                double surface = Double.parseDouble(new String(ch, start, length).trim());
////                currentEntreprise.setSurface(surface);
////            }
//            else if (engagementTag.equals("open")) {
//                String engagement = new String(ch, start, length).trim();
//                currentEntreprise.setEngagement(engagement);
//            } else if (nbr_etageTag.equals("open")) {
//                int nbr_etage = Integer.parseInt(new String(ch, start, length).trim());
//                currentEntreprise.setNbr_etage(nbr_etage);
//            }
////            else if (surfaceCouverteTag.equals("open")) {
////                double surfaceCouverte = Double.parseDouble(new String(ch, start, length).trim());
////                currentEntreprise.setSurfaceCouverte(surfaceCouverte);
////
////            }
            
             else if (typeTag.equals("open")) {
              String type = (new String(ch, start, length).trim());
                currentEntreprise.setType(type);
            }
        }
    
    }
}

    

