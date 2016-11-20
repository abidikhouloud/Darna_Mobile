package sprintMobile;
import sprintMobile.Client;
import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class ClientHandler extends DefaultHandler{
    private Vector clients;
    String idTag="close";
    String nomTag = "close";
    String prenTag = "close";
   


    

    public ClientHandler() {
        clients = new Vector();
    }
public Personne[] getPersonne() {
        Personne[] personness = new Personne[clients.size()];
        clients.copyInto(personness);
        return personness;
    }
   
    public Client[] getClients(){
        Client[] clientss = new Client [clients.size()];
        clients.copyInto(clientss);
        return clientss;
    }
    
    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Client currentClient;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    //les balises ouvrantes
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //qname c'est la balise xml /instancier persone à partir du fichier xml
        if (qName.equals("user")) {
            currentClient = new Client();
            //2ème methode pour parser les attributs
            currentClient.setId(attributes.getValue("id"));
            currentClient.setNom(attributes.getValue("nom"));
            currentClient.setPrenom(attributes.getValue("prenom"));

            


            
            /****/
            
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("nom")) {
            nomTag = "open";
        } else if (qName.equals("prenom")) {
            prenTag = "open";
        }
        
    }
//les balises fermantes
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("user")) {
            // we are no longer processing a <reg.../> tag
            clients.addElement(currentClient);
            currentClient = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("nom")) {
            nomTag = "close";
        } else if (qName.equals("prenom")) {
            prenTag = "close";
        }
        
        
     
        
    }
    // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentClient != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentClient.setId(id);
            } else
                if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentClient.setNom(nom);
            } else
                    if (prenTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                currentClient.setPrenom(pren);
                
            }
       
    }
    
    }}
