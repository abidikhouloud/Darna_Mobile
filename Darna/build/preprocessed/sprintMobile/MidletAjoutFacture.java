/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sprintMobile;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MidletAjoutFacture implements CommandListener, Runnable{

    Display disp ;
    //Form 1
    Form f1 = new Form("Facture");
    DateField date = new DateField("date", DateField.DATE);
       String[] tabGerant={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tabClient={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tabOffre={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
ChoiceGroup tfOffre;
ChoiceGroup tfClient;
ChoiceGroup tfGerant;

    Command cmdValider = new Command("valider", Command.SCREEN, 0);
    Command cmdBack = new Command("retour", Command.BACK, 0);
 Command cmdMenu = new Command("Accueil", Command.SCREEN, 0);
    Form f2 = new Form("Welcome");
    Form f3 = new Form("Erreur");

    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);

    public MidletAjoutFacture(Display disp) {
    this.disp=disp;
    this.startApp();
    }

    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/parseFacture/ajout.php";
    StringBuffer sb = new StringBuffer();

   
    int ch;

    public void startApp() {
        
        
        
         try {
                  
            // this will handle our XML
            ClientHandler personnesHandler = new ClientHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsePers/getXmlPersons_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, personnesHandler);
            // display the result
           Personne[] alertes = personnesHandler.getClients();
 
            if (alertes.length > 0) {
                tabGerant=new String[alertes.length];
                tabClient=new String[alertes.length];
                for (int i = 0; i < alertes.length; i++) {
                     tabClient[i]=alertes[i].getNom();
                     
                }
                for (int i = 0; i < alertes.length; i++) {
                     tabGerant[i]=alertes[i].getNom();
                     
                }  
              
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
         
         
           try {
                  
            // this will handle our XML
            OffreHandler personnesHandler = new OffreHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsePers/getXmlPersons_Attributes1.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, personnesHandler);
            // display the result
           Offre[] alertes = personnesHandler.getOffre();
 
            if (alertes.length > 0) {
                tabOffre=new String[alertes.length];
                for (int i = 0; i < alertes.length; i++) {
                     tabOffre[i]=alertes[i].getDescription();
                     
                }
                 
              
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        f1.addCommand(cmdMenu);
f2.addCommand(cmdMenu);
        f1.append(date);
         tfClient=new ChoiceGroup("Client",ChoiceGroup.POPUP,tabClient,null);
         
               f1.append(tfClient);
         tfGerant=new ChoiceGroup("Gerant",ChoiceGroup.POPUP,tabGerant,null);
               f1.append(tfGerant);
                 tfOffre=new ChoiceGroup("Offre",ChoiceGroup.POPUP,tabOffre,null);
               f1.append(tfOffre);
        f1.addCommand(cmdValider);
        f1.setCommandListener(this);
        f1.addCommand(cmdBack);
        f2.setCommandListener(this);
        disp.setCurrent(f1);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        if (c == cmdValider) {
            Thread th = new Thread(this);
            th.start();
        }
        if (c == cmdBack) {
            
           new MidletParsingXmlFacture1(disp);
        }
        if(c==cmdMenu)
        {
        new MenuEventg(disp);
        }
    }

    public void run() {
        try {

            Calendar calendar = Calendar.getInstance();
    calendar.setTime(date.getDate());

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
           // System.out.println(url+"?date="+day+"-"+month+"-"+year+"&idClient="+tabClient[tfClient.getSelectedIndex()].trim()+"&idOffre="+tabOffre[tfOffre.getSelectedIndex()].trim()+"&idGerant="+tabGerant[tfGerant.getSelectedIndex()].trim());
                hc = (HttpConnection) Connector.open(url+"?date="+Integer.toString(day).trim()+"-"+Integer.toString(month).trim()+"-"+Integer.toString(year).trim()+"&idClient="+tabClient[tfClient.getSelectedIndex()].trim()+"&idOffre="+tabOffre[tfOffre.getSelectedIndex()].trim()+"&idGerant="+tabGerant[tfGerant.getSelectedIndex()].trim());
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                    new MidletParsingXmlFacture1(disp);
                }else{
                    disp.setCurrent(f3);
                }
                sb = new StringBuffer();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}
