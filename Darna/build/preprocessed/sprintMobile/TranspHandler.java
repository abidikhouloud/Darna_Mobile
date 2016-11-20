/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;
import Entity.transporteur;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class TranspHandler extends DefaultHandler{
    private Vector Trans;
    String idTag = "close";
    String telTag="close";
    String adresseTag = "close";
    

    public TranspHandler() {
    Trans = new Vector();
    }

    public transporteur[] getTransporteur() {
        transporteur[]transs = new transporteur[Trans.size()];
        Trans.copyInto(transs);
        return transs;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private transporteur currentTransp;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("transporteur")) {
            currentTransp = new transporteur();
            
            currentTransp.setId(attributes.getValue("id"));
           
            
            currentTransp.setTel(attributes.getValue("tel"));
            
            
            currentTransp.setadresse(attributes.getValue("adresse"));
            System.out.println(currentTransp);
            /****/
            
        } 
        
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("transporteur")) {
            // we are no longer processing a <reg.../> tag
            Trans.addElement(currentTransp);
            currentTransp = null;
        } 
    }
   
    
    
}
