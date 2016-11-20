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
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.SAXException;

/**
 * @author Raja
 */
public class ParsingXmlAgence  implements CommandListener, Runnable {

    Display disp ;
    
   ChoiceGroup tfAdresse = new ChoiceGroup("Adresse", ChoiceGroup.POPUP, new String[]{"tunis","ariana","zaghouene","beja","jandouba","seliena","sfax","sidi bouzid","sousse","kairouane","gafsa","medenin","gasserine","ariana","tataouine","mahdia","kef","tozeur","gbeli","nabeul","gabes"}, null);
         Command cmdMenu = new Command("Accueil", Command.SCREEN, 0);

    Command cmdAgence = new Command("Agence", Command.SCREEN, 0);
    Command cmdBack1 = new Command("retour", Command.BACK, 0);
    Agence[] agences;
    List lst = new List("Agences", List.IMPLICIT);
    Form f = new Form("Accueil");
    Form form = new Form("Infos Agence");
    Form loadingDialog = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();
    
    Command cmdAjout = new Command("Ajout", Command.SCREEN, 0);
    Command cmdSup = new Command("Supprimer", Command.SCREEN, 0);
     DataInputStream dis;
         List tabNom = new List("id:", List.IMPLICIT);
    List tfId = new List("", List.IMPLICIT);
    List tabTel = new List("tel", List.IMPLICIT);
     TextField tfTel = new TextField("tel", null, 100, TextField.ANY);
    TextField tfNom = new TextField("nom", null, 100, TextField.ANY);
    
int ch;
    Command cmdUpdate= new Command("modifier", Command.SCREEN, 0);
    Command cmdModif= new Command("modifier", Command.SCREEN, 0);
    String url ="http://localhost/parsingDarna/modifierAgence.php";

    Command cmdParse = new Command("Agence", Command.SCREEN, 0);
   
    
    Form fModif = new Form("Modifier");

    ParsingXmlAgence(Display disp) {
        this.disp=disp;
        this.startApp();

    }
    
    public void startApp() {
       
        
        form.addCommand(cmdBack1);
        disp.setCurrent(f);
   
       f.append("Click Agence to get your Agence_list");
       f.addCommand(cmdParse);
       f.addCommand(cmdAjout);
       f.addCommand(cmdBack1);
       f.setCommandListener(this);
       form.addCommand(cmdUpdate);
       
       form.addCommand(cmdSup);
       form.addCommand(cmdBack1);
       fModif.addCommand(cmdModif);
     
        fModif.append(tfAdresse);
    
        fModif.append(tfTel);
     form.addCommand(cmdMenu);
      f.addCommand(cmdMenu);
       fModif.addCommand(cmdMenu);
        lst.addCommand(cmdMenu);
        fModif.append(tfNom);
        fModif.setCommandListener(this);
        form.addCommand(cmdBack1);
        form.setCommandListener(this);
        lst.setCommandListener(this);
        disp.setCurrent(f);
       
        
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {

        
         if (c == cmdParse) {
            disp.setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
        }
         
         if(c==cmdAjout)
         {new AjoutAgence(disp);}
         
          if(c==cmdSup)
         {new SupprimerAgence(disp);}
         
        
         if (c == cmdUpdate) {
             form.deleteAll();
             disp.setCurrent(fModif);
            }
         if(c==cmdMenu)
             
         {new MenuEventC(disp);}
         
if(c==cmdModif)
{
            try {
                modifierAgence(lst.getSelectedIndex());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

disp.setCurrent(lst);
Thread th=new Thread(this);
th.start();

}

        if (c == List.SELECT_COMMAND) {
            form.append("Informations Agence : \n");
            form.append(showAgence(lst.getSelectedIndex()));
            disp.setCurrent(form);
        }

        if (c == cmdBack1) {
          new ParsingXmlAgence(disp);
        }

    }

    public void run(){
   
        try {
            // this will handle our XML
            AgenceHandler agenceHandler = new AgenceHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsingDarna/getXmlAgences_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, agenceHandler);
            // display the result
            agences = agenceHandler.getAgence();
         
            if (agences.length > 0) {
                for (int i = 0; i < agences.length; i++) {
                    lst.append(agences[i].getAdresse()+" "+agences[i].getTel()+" "+agences[i].getNom(),null);

                }
            }
            
            
            
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        } catch (SAXException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        disp.setCurrent(lst);
    }

    private String showAgence(int i) {
        String res = "";
        if (agences.length > 0) {
            sb.append("* ");
            sb.append(agences[i].getId());
            sb.append("\n");
            sb.append("* ");
            sb.append(agences[i].getAdresse());
            sb.append("\n");
            sb.append("* ");
            sb.append(agences[i].getTel());
            sb.append("\n");
             sb.append("* ");
            sb.append(agences[i].getNom());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
    
     public String modifierAgence(int i) throws IOException
    {
   
   
    String res = "";
                    System.out.println(url.trim()+"?adresse="+tfAdresse.getString(tfAdresse.getSelectedIndex()).trim()+"&id="+agences[i].getId()+"&tel="+tfTel.getString().trim()+"&nom="+tfNom.getString().trim());  

           
            HttpConnection  hc = (HttpConnection)Connector.open(url.trim()+"?adresse="+tfAdresse.getString(tfAdresse.getSelectedIndex()).trim()+"&id="+agences[i].getId()+"&tel="+tfTel.getString().trim()+"&nom="+tfNom.getString().trim());
            dis = new DataInputStream(hc.openDataInputStream());
           while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if ("OK".equals(sb.toString().trim())) {
                disp.setCurrent(form);
            } 
            sb = new StringBuffer("");
            
        return res;
    
}
}
