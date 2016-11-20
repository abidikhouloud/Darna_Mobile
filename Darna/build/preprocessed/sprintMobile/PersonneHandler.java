package sprintMobile;

import java.util.Vector;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class PersonneHandler extends DefaultHandler {

    String idTag = "close";
    String nomTag = "close";
    String prenTag = "close";
    String DateTag = "close";
    String TelTag = "close";
    String passwordTag = "close";
    String EmailTag = "close";
    String typeTag = "close";

    private Vector users;

    public PersonneHandler() {
        users = new Vector();

    }

    public Personne[] getUser() {
        Personne[] personness = new Personne[users.size()];
        users.copyInto(personness);
        return personness;

    }

    // VARIABLES TO MAINTAIN THE PARSER'S STATE DURING PROCESSING
    private Personne currentUser;

    // XML EVENT PROCESSING METHODS (DEFINED BY DefaultHandler)
    // startElement is the opening part of the tag "<tagname...>"
    //les balises ouvrantes
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //qname c'est la balise xml /instancier persone à partir du fichier xml
        if (qName.equals("user")) {
            currentUser = new Personne();
            //2ème methode pour parser les attributs
            currentUser.setId(attributes.getValue("id"));
            currentUser.setNom(attributes.getValue("nom"));
            currentUser.setPrenom(attributes.getValue("prenom"));
            currentUser.setDate(attributes.getValue("datenaiss"));
            currentUser.setPassword(attributes.getValue("password"));
            currentUser.setEmail(attributes.getValue("email"));
            currentUser.setTel(attributes.getValue("tel"));
            currentUser.setType(attributes.getValue("type"));

            /**
             * *
             */
        } else if (qName.equals("id")) {
            idTag = "open";
        } else if (qName.equals("nom")) {
            nomTag = "open";
        } else if (qName.equals("prenom")) {
            prenTag = "open";
        } else if (qName.equals("datenaiss")) {
            DateTag = "open";
        } else if (qName.equals("email")) {
            EmailTag = "open";
        } else if (qName.equals("password")) {
            passwordTag = "open";
        } else if (qName.equals("tel")) {
            TelTag = "open";
        } else if (qName.equals("type")) {
            typeTag = "open";
        }
    }
//les balises fermantes

    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("user")) {
            // we are no longer processing a <reg.../> tag
            users.addElement(currentUser);
            currentUser = null;
        } else if (qName.equals("id")) {
            idTag = "close";
        } else if (qName.equals("nom")) {
            nomTag = "close";
        } else if (qName.equals("prenom")) {
            prenTag = "close";
        } else if (qName.equals("datenaiss")) {
            DateTag = "close";
        } else if (qName.equals("email")) {
            EmailTag = "close";
        } else if (qName.equals("password")) {
            passwordTag = "close";
        } else if (qName.equals("tel")) {
            TelTag = "close";
        } else if (qName.equals("type")) {
            typeTag = "close";
        }

    }
    // "characters" are the text between tags

    public void characters(char[] ch, int start, int length) throws SAXException {
        // we're only interested in this inside a <phone.../> tag
        if (currentUser != null) {
            // don't forget to trim excess spaces from the ends of the string
            if (idTag.equals("open")) {
                String id = new String(ch, start, length).trim();
                currentUser.setId(id);
            } else if (nomTag.equals("open")) {
                String nom = new String(ch, start, length).trim();
                currentUser.setNom(nom);
            } else if (prenTag.equals("open")) {
                String pren = new String(ch, start, length).trim();
                currentUser.setPrenom(pren);

            }
        } else if (EmailTag.equals("open")) {
            String email = null;
            currentUser.setEmail(email);
        } else if (passwordTag.equals("open")) {
            String password = new String(ch, start, length).trim();;
            currentUser.setPassword(password);
        } else if (DateTag.equals("open")) {
            String date = new String(ch, start, length).trim();;
            currentUser.setDate(date);
        } else if (TelTag.equals("open")) {
            int tel = 0;
            currentUser.setTel(tel);
        } else if (typeTag.equals("open")) {
            String type = new String(ch, start, length).trim();
            currentUser.setType(type);
        }
    }

}
