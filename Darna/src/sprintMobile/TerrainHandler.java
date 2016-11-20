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
 * @author Khalil
 */
public class TerrainHandler extends DefaultHandler{
    

    private Vector terrains;
    String idTag = "close";
    String descriptionTag = "close";
    String dateTag = "close";
    String regionTag = "close";
    String prixTag = "close";
    String surfaceTag = "close";
    String engagementTag = "close";
    String vocationTag = "close";
    String typeTag = "close";
    public TerrainHandler() {
       terrains = new Vector();

    }

    public Terrain[] getTerrains() {
        Terrain[] listTerrains = new Terrain[terrains.size()];
        terrains.copyInto(listTerrains);
        return listTerrains;
    }
    private Terrain currentTerrain;

    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("person")) {
            currentTerrain = new Terrain();

            currentTerrain.setId(Integer.parseInt(attributes.getValue("id")));
            currentTerrain.setDescription(attributes.getValue("description"));
            currentTerrain.setDate(attributes.getValue("date"));
            currentTerrain.setRegion(attributes.getValue("region"));
            currentTerrain.setPrix(Double.parseDouble(attributes.getValue("prix")));
            currentTerrain.setSurface(Double.parseDouble(attributes.getValue("surface")));
            //currentTerrain.setG(attributes.getValue("gerant"));
            currentTerrain.setEngagement((attributes.getValue("engagement")));
            currentTerrain.setVocation(attributes.getValue("vocation"));
            currentTerrain.setType(attributes.getValue("type"));
            
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("description")) {
            descriptionTag = "open";
        } else if (qName.equals("date")) {
            dateTag = "open";
        } else if (qName.equals("region")) {
            regionTag = "open";
        } else if (qName.equals("prix")) {
            prixTag = "open";
        } else if (qName.equals("surface")) {
            surfaceTag = "open";
        } else if (qName.equals("engagement")) {
            engagementTag = "open";
        } else if (qName.equals("vocation")) {
            vocationTag = "open";
        } else if (qName.equals("type")) {
            typeTag = "open";
        }  
       
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("person")) {
            
            terrains.addElement(currentTerrain);
            
            currentTerrain = null;
             
        } else if (qName.equals("id")) {
            idTag = "close";
           
        } else if (qName.equals("description")) {
            descriptionTag = "close";
        } else if (qName.equals("date")) {
            dateTag = "close";
        } else if (qName.equals("region")) {
            regionTag = "close";
        } else if (qName.equals("prix")) {
            prixTag = "close";
        } else if (qName.equals("surface")) {
            surfaceTag = "close";
        } else if (qName.equals("engagement")) {
            engagementTag = "close";
        } else if (qName.equals("vocation")) {
            vocationTag = "close";
        } 
        else if (qName.equals("type")) {
            typeTag = "close";
        }
        
    }

    public void characters(char[] ch, int start, int length) throws SAXException {
          System.out.println("cc");
        if (currentTerrain != null) {
           
            if (idTag.equals("open")) {
                int id = Integer.parseInt(new String(ch, start, length).trim());
                currentTerrain.setId(id);
            } else if (descriptionTag.equals("open")) {
                String description = new String(ch, start, length).trim();
                currentTerrain.setDescription(description);
            } else if (dateTag.equals("open")) {
                String date = new String(ch, start, length).trim();
                currentTerrain.setDate(date);
            } else if (regionTag.equals("open")) {
                String region = new String(ch, start, length).trim();
                currentTerrain.setRegion(region);
            } else if (prixTag.equals("open")) {
                double prix = Double.parseDouble(new String(ch, start, length).trim());
                currentTerrain.setPrix(prix);
            } else if (surfaceTag.equals("open")) {
                double surface = Double.parseDouble(new String(ch, start, length).trim());
                currentTerrain.setSurface(surface);
            }
             else if (engagementTag.equals("open")) {
                String engagement = new String(ch, start, length).trim();
                currentTerrain.setEngagement(engagement);
            } else if (vocationTag.equals("open")) {
                String vocation = new String(ch, start, length).trim();
                currentTerrain.setVocation(vocation);
            } else if (typeTag.equals("open")) {
                String type = new String(ch, start, length).trim();
                currentTerrain.setType(type);
            } 
        }
    }
}