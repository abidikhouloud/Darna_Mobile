/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sprintMobile;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class MidletAjoutAlerte  implements CommandListener, Runnable{

    Display disp ;
    //Form 1
    Form f1 = new Form("Ajout Alerte");
    TextField prixMin = new TextField("prixMin", null, 100, TextField.NUMERIC);
    TextField prixMax = new TextField("prixMax", null, 100, TextField.NUMERIC);
   // TextField engagement = new TextField("engagement", null, 100, TextField.ANY);
    //TextField typedeBien = new TextField("typedeBien", null, 100, TextField.ANY);
    TextField surface = new TextField("surface", null, 100, TextField.NUMERIC);
    String[] tab={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tabOffre={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] regin11={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};

    ChoiceGroup tfClient;
ChoiceGroup tfOffre;
String[] tabEngagement={"location","vente"};
String[] tabType={"Entreprise","Logement","Terrain"};
 ChoiceGroup engagement=new ChoiceGroup("Engagement",ChoiceGroup.POPUP,tabEngagement,null);
  ChoiceGroup typedeBien=new ChoiceGroup("Type",ChoiceGroup.POPUP,tabType,null);
  String [] regionn ={"tunis","ariana","zaghouene","beja","jandouba","seliena","sfax","sidi bouzid","sousse","kairouane","gafsa","medenin","gasserine","ariana","tataouine","mahdia","kef","tozeur","gbeli","nabeul","gabes"};
 ChoiceGroup region=new ChoiceGroup("Region",ChoiceGroup.POPUP,regionn,null);

//    List tfClient= new List("ttt", List.MULTIPLE, tab, null);
  Command cmdMenu = new Command("Accueil", Command.SCREEN, 0);
    Command cmdValider = new Command("valider", Command.SCREEN, 0);
    Command cmdBack = new Command("cmdBack", Command.BACK, 0);

    Form f2 = new Form("Welcome");
    Form f3 = new Form("Erreur");

    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);

    public MidletAjoutAlerte(Display disp) {
        this.disp=disp;
       this.startApp();
    }

    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/parseAgence/ajout.php";
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
                tab=new String[alertes.length];
                for (int i = 0; i < alertes.length; i++) {
                     tab[i]=alertes[i].getNom();
                     
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
               
f1.append(prixMin);
f1.addCommand(cmdMenu);
f2.addCommand(cmdMenu);
        f1.append(prixMax);
        f1.append(region);
        f1.append(engagement);
        f1.append(typedeBien);
        f1.append(surface);
         tfClient=new ChoiceGroup("Client",ChoiceGroup.POPUP,tab,null);
               f1.append(tfClient);
                tfOffre=new ChoiceGroup("Offre",ChoiceGroup.POPUP,tabOffre,null);
               f1.append(tfOffre);
        f1.addCommand(cmdValider);
         f1.addCommand(cmdBack);
        f1.setCommandListener(this);
       
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
            
             new MidletParsingAlerte1(disp);
        }
        if(c==cmdMenu)
        {
        new MenuEventg(disp);
        }
    
    }

    public void run() {
        
       
        String typ="";
        if("Entreprise".equals(tabType[typedeBien.getSelectedIndex()].trim()))
        {
            typ="e";
        }
        if("Logement".equals(tabType[typedeBien.getSelectedIndex()].trim()))
        {
            typ="l";
        }
        if("Terrain".equals(tabType[typedeBien.getSelectedIndex()].trim()))
        {
            typ="t";
        }
        try {
            
                hc = (HttpConnection) Connector.open(url+"?prixMin="+prixMin.getString().trim()+"&prixMax="+prixMax.getString().trim()+"&region="+regionn[region.getSelectedIndex()]+"&engagement="+tabEngagement[engagement.getSelectedIndex()].trim()+"&typedeBien="+typ.trim()+"&surface="+surface.getString().trim()+"&idClient="+tab[tfClient.getSelectedIndex()].trim()+"&idOffre="+tabOffre[tfOffre.getSelectedIndex()].trim());
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                   new MidletParsingAlerte1(disp);
                }else{
                    disp.setCurrent(f3);
                }
                sb = new StringBuffer();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
         
    }
}
