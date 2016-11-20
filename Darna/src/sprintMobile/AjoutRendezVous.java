/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprintMobile;



/**
 * @author Raja
 */
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class AjoutRendezVous  implements CommandListener, Runnable{

    Display disp ;
    //Form 1
    Form f1 = new Form("Rendez vous");
     Command cmdMenu = new Command("Accueil", Command.SCREEN, 0);
    ChoiceGroup tfLieux = new ChoiceGroup("Lieux", ChoiceGroup.POPUP, new String[]{"tunis","ariana","zaghouene","beja","jandouba","seliena","sfax","sidi bouzid","sousse","kairouane","gafsa","medenin","gasserine","ariana","tataouine","mahdia","kef","tozeur","gbeli","nabeul","gabes"}, null);
    DateField tfDate = new DateField("date", DateField.DATE);
    
    String[] tabClient={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tabOffre={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tab={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tab1={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};

   HttpConnection hc;
ChoiceGroup tfOffre;
ChoiceGroup tfClient;
//ChoiceGroup tfGerant;

    Command cmdValider = new Command("valider", Command.SCREEN, 0);
    Command cmdBackAR = new Command("retour", Command.BACK, 0);

    Form f2 = new Form("Welcome");
    Form f3 = new Form("Erreur");

    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);

    //Connexion
    
    DataInputStream dis;
    String url = "http://localhost/parsingDarna/ajoutRendezVous.php";
    StringBuffer sb = new StringBuffer();
    int ch;
    
    public AjoutRendezVous(Display disp) {
        this.disp=disp;
        this.startApp();
    }

    public void startApp() {
        
        
           try {
                  
            // this will handle our XML
            ClientHandler personnesHandler = new ClientHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsingDarna/getXmlPersonnes_attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, personnesHandler);
            // display the result
           Personne[] alertes = personnesHandler.getClients();
 
            if (alertes.length > 0) {
                tabClient=new String[alertes.length];
                for (int i = 0; i < alertes.length; i++) {
                     tabClient[i]=alertes[i].getNom();
                     tab[i]=Integer.toString(alertes[i].getId()).trim();
                     
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
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsingDarna/getXmlOffres_attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, personnesHandler);
            // display the result
           Offre[] alertes = personnesHandler.getOffre();
 
            if (alertes.length > 0) {
                tabOffre=new String[alertes.length];
                for (int i = 0; i < alertes.length; i++) {
                     tabOffre[i]=alertes[i].getDescription();
                     tab1[i]=Integer.toString(alertes[i].getId());
                }
                 
              
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
         
         
           
        
             
             
        f1.addCommand(cmdMenu);
          f2.addCommand(cmdMenu);
            f3.addCommand(cmdMenu);
        f1.append(tfLieux);
        f1.append(tfDate);
         tfClient=new ChoiceGroup("Client",ChoiceGroup.POPUP,tabClient,null);
         
               f1.append(tfClient);
       //  tfGerant=new ChoiceGroup("Gerant",ChoiceGroup.POPUP,tabGerant,null);
               //f1.append(tfGerant);
                 tfOffre=new ChoiceGroup("Offre",ChoiceGroup.POPUP,tabOffre,null);
               f1.append(tfOffre);
        f1.addCommand(cmdValider);
        f1.addCommand(cmdBackAR);
        f1.setCommandListener(this);
        f2.addCommand(cmdBackAR);
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
        if (c == cmdBackAR) {
            
          new ParsingXmlRendezVous(disp);
        }
        if(c==cmdMenu)
        {
        new MenuEventg(disp);
        }
    }

    public void run() {
        try {

            Calendar calendar = Calendar.getInstance();
    calendar.setTime(tfDate.getDate());

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    
            System.out.println(url+"?lieu="+tfLieux.getString(tfLieux.getSelectedIndex())+"&date="+Integer.toString(day).trim()+"-"+Integer.toString(month).trim()+"-"+Integer.toString(year).trim()+"&idClient="+tab[tfClient.getSelectedIndex()].trim()+"&idOffre="+tab1[tfOffre.getSelectedIndex()].trim());
           // System.out.println(url+"?date="+day+"-"+month+"-"+year+"&idClient="+tabClient[tfClient.getSelectedIndex()].trim()+"&idOffre="+tabOffre[tfOffre.getSelectedIndex()].trim()+"&idGerant="+tabGerant[tfGerant.getSelectedIndex()].trim());
                hc = (HttpConnection) Connector.open(url+"?lieu="+tfLieux.getString(tfLieux.getSelectedIndex())+"&date="+Integer.toString(day).trim()+"-"+Integer.toString(month).trim()+"-"+Integer.toString(year).trim()+"&idClient="+tab[tfClient.getSelectedIndex()].trim()+"&idOffre="+tab1[tfOffre.getSelectedIndex()].trim());
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) 
                {
                    sb.append((char)ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                    disp.setCurrent(f2);
                }else{
                    disp.setCurrent(f3);
                }
                sb = new StringBuffer();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}
