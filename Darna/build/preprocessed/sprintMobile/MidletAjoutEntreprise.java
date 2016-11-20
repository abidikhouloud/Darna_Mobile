/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprintMobile;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;

/**
 * @author Khalil
 */
public class MidletAjoutEntreprise extends MIDlet implements Runnable,CommandListener {

      Display disp = Display.getDisplay(this);
    //Form 1
    Form f1 = new Form("Ajouter Entreprise");
    TextField tfDescription = new TextField("Description", null, 100, TextField.ANY);
    DateField tfDate = new DateField("Date",DateField.DATE );
    String [] table = {"Tunis","Ben Arous","Mannouba","Ariana","Nabeul","Bizerte","Beja","jendouba","Kef","kasserine"
    ,"sidi Bouzid","zaghouan","sousse","Kairouan","Mahdia","Moastir","Gabes","Gafsa","Tataouin","Medenin","Seliana",
    "Gebili","sfax","tozeur"}; 
    String[] table1 = {"Location","Vente"};
    ChoiceGroup  chG  = new ChoiceGroup("Region", ChoiceGroup.POPUP,table,null);
      ChoiceGroup  chEngagement  = new ChoiceGroup("engagement", ChoiceGroup.POPUP,table1,null);
      TextField tfPrix = new TextField("Prix", null, 100, TextField.ANY);
      TextField tfSurface = new TextField("surface", null, 100, TextField.ANY);
      TextField tfSurfaceCouv = new TextField("Surface couverte ", null, 100, TextField.ANY);        
       TextField tfnbreEtage = new TextField("Nombre Etages ", null, 100, TextField.ANY);   
       
    Command cmdValider = new Command("valider", Command.SCREEN, 0);
    Command cmdBack = new Command("cmdBack", Command.BACK, 0);
    Form f2 = new Form("Welcome");
    Form f3 = new Form("Erreur");
    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);
    
     HttpConnection hc;
    DataInputStream dis;
    String url = "http://127.0.0.1/parse1/entreprise/ajoutEntreprise.php";
        String urlentrepriselist = "http://127.0.0.1/parse1/entreprise/getXmlPersons_Attributes.php";
    StringBuffer sb1 = new StringBuffer();
    int ch;
    
    
    
    public void startApp() {
         f1.append(tfDescription);
        f1.append(tfDate);
        f1.append(tfPrix);
         f1.append(chG);
        f1.append(tfSurface);
        f1.append(tfSurfaceCouv);
        f1.append(tfnbreEtage);
        f1.append(chEngagement);
        f1.addCommand(cmdValider);
       
        f1.setCommandListener(this);
        f2.addCommand(cmdBack);
        f2.setCommandListener(this);
        disp.setCurrent(f1);
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
        
    }

    
    public void AjoutEntreprise(){
StringBuffer sbu = new StringBuffer();
        int ch;
        String request;
        String dateee;
        String engagement;
           Calendar calendar = Calendar.getInstance();
    calendar.setTime(tfDate.getDate());
                int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
        
         engagement=table[chG.getSelectedIndex()] ;
        try {  System.out.println("bonjour");
            request = (url + "?description=" + tfDescription.getString().trim() + "&date=" +Integer.toString(day).trim()+"-"+Integer.toString(month).trim()+"-"+Integer.toString(year).trim()+ "&region=" + table[chG.getSelectedIndex()].trim() + "&prix=" + tfPrix.getString().trim() + "&surface=" + tfSurface.getString().trim() + "&nbr_etage="+ tfnbreEtage.getString().trim()+  "&surfaceCouverte=" + tfSurfaceCouv.getString().trim() + "&engagement="+ table1[chEngagement.getSelectedIndex()].trim()).trim();
            
            
          
            hc = (HttpConnection) Connector.open(request.trim());
       
           
            dis = new DataInputStream(hc.openDataInputStream());
            while ((ch = dis.read()) != -1) {
                sbu.append((char) ch);
               
            }
             
        } catch (IOException ex) {
            ex.printStackTrace();
        }    }

    public void commandAction(Command c, Displayable d) {
         if (c == cmdValider) {
           AjoutEntreprise();
        }
        if (c == cmdBack) {
            
            new MidletMenu1(disp);
        }
    }

    public void run() {
    }

   
}