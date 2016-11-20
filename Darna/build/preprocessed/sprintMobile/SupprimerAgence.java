/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprintMobile;

import javax.microedition.midlet.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Raja
 */
public class SupprimerAgence  implements CommandListener, Runnable {
    
        Display disp ;
    //Form 1
             Command cmdMenu = new Command("Accueil", Command.SCREEN, 0);

         Form f1 = new Form("Bienvenue");
    List tf = new List("id:",List.IMPLICIT);
     
    
    ChoiceGroup tfAdresse = new ChoiceGroup("Adresse", ChoiceGroup.POPUP, new String[]{"tunis","ariana","zaghouene","beja","jandouba","seliena","sfax","sidi bouzid","sousse","kairouane","gafsa","medenin","gasserine","ariana","tataouine","mahdia","kef","tozeur","gbeli","nabeul","gabes"}, null);
    
   // TextField tfTel = new TextField("tel", null, 100, TextField.ANY);
   //TextField tfNom = new TextField("nom", null, 100, TextField.ANY);
    
   
    Command cmdSupprimer = new Command("supprimer", Command.SCREEN, 0);
    Command cmdBack = new Command("cmdBack", Command.BACK, 0);

    Form f2 = new Form("agence supprimer");
    Form f3 = new Form("Erreur");

    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);

    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/parsingDarna/supprimerAgence.php";
    StringBuffer sb = new StringBuffer();
    int ch;

    public SupprimerAgence(Display disp) {
        this.disp=disp;
        this.startApp();
    }

    

    public void startApp() {
        
        
        
        
      f1.addCommand(cmdMenu);
        f2.addCommand(cmdMenu);
          tf.addCommand(cmdMenu);
        f1.addCommand(cmdSupprimer);
        f1.setCommandListener(this);
        
        tf.addCommand(cmdSupprimer);
        f2.addCommand(cmdBack);
        f2.setCommandListener(this);
        tf.setCommandListener(this);
        
        disp.setCurrent(f1);
       
        
       
             try {
            // this will handle our XML
            AgenceHandler agencesHandler = new AgenceHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsingDarna/getXmlAgences_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, agencesHandler);
            // display the result
           Agence[] agences = agencesHandler.getAgence();

            if (agences.length > 0) {
                for (int i = 0; i < agences.length; i++) {
                  tf.append(agences[i].getNom(), null);

                }
            }
            disp.setCurrent(tf);

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
       // disp.setCurrent(tfNom);
        
        
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        
         if (c == cmdSupprimer) {
            Thread th = new Thread(this);
            th.start();
        }
        if (c == cmdBack) {
            
   new ParsingXmlAgence(disp);        }
        if(c==cmdMenu)
        {
        new MenuEventg(disp);
        }
        
    }

    public void run() {
        
        try {
    hc = (HttpConnection) Connector.open(url+"?nom="+tf.getString(ch));
                            dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                    disp.setCurrent(f2);
                }else{
                    disp.setCurrent(f3);
                }
                sb = new StringBuffer();
            } catch (IOException ex) {
            }
    }

      
        
    }




