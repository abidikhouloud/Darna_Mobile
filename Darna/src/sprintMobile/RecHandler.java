/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Handler;
import Entity.reclamation;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;



public class RecHandler extends DefaultHandler{
    private Vector Recs;
    String idTag = "close";
    String questionTag="close";
    String reponseTag = "close";
    

    public RecHandler() {
    Recs = new Vector();
    }

    public reclamation[] getreclamation() {
        reclamation[]Recss = new reclamation[Recs.size()];
        Recs.copyInto(Recss);
        return Recss;
    }
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private reclamation currentRec;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("reclamation")) {
            currentRec = new reclamation();
            
            currentRec.setId(attributes.getValue("id"));
            currentRec.setquestion(attributes.getValue("question"));
            
            currentRec.setreponse(attributes.getValue("reponse"));
            System.out.println(currentRec);
            /****/
            
        } 
        
    }

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("reclamation")) {
            // we are no longer processing a <reg.../> tag
            Recs.addElement(currentRec);
            currentRec = null;
        } 
    }
   
    
    
}
