/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprintMobile;

import javax.microedition.midlet.*;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;


public class AjoutAgence  implements CommandListener, Runnable{
    

    Display disp ;
    //Form 1
    Form f1 = new Form("Agence");
    //champs textes
   // TextField tfAdresse = new TextField("adresse", null, 100, TextField.ANY);
    Command cmdMenu = new Command("Accueil", Command.SCREEN, 0);
   ChoiceGroup tfAdresse = new ChoiceGroup("Adresse", ChoiceGroup.POPUP, new String[]{"tunis","ariana","zaghouene","beja","jandouba","seliena","sfax","sidi bouzid","sousse","kairouane","gafsa","medenin","gasserine","ariana","tataouine","mahdia","kef","tozeur","gbeli","nabeul","gabes"}, null);
    TextField tfTel = new TextField("tel", null, 100, TextField.ANY);
    TextField tfNom = new TextField("nom", null, 100, TextField.ANY);
    
    
   
    
    
    //commande
    Command cmdValider = new Command("valider", Command.SCREEN, 0);
    Command cmdBackAA = new Command("retour", Command.BACK, 0);

    Form f2 = new Form("bienvenue dans agence");
    
    Form f3 = new Form("Erreur");

    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);

    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/parsingDarna/ajoutAgence.php";
    StringBuffer sb = new StringBuffer();
    int ch;

    public AjoutAgence(Display disp) {
        this.disp=disp;
        this.startApp();
    }

    public void startApp() {
        f1.addCommand(cmdMenu);
         f2.addCommand(cmdMenu);
          f3.addCommand(cmdMenu);
        f1.append(tfAdresse);
        f1.append(tfTel);
        f1.append(tfNom);
        f1.addCommand(cmdValider);
        f1.addCommand(cmdBackAA);
        f1.setCommandListener(this);
        
        f2.addCommand(cmdBackAA);
        f2.setCommandListener(this);
        
        f3.addCommand(cmdBackAA);
        f3.setCommandListener(this);
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
        if (c == cmdBackAA) {
            
            new ParsingXmlAgence(disp);
        }
        if (c == cmdMenu) {
            
            new MenuEventA(disp);
        }
    }

    public void run() {
        try {
                hc = (HttpConnection) Connector.open(url+"?adresse="+tfAdresse.getString(tfAdresse.getSelectedIndex())+"&tel="+tfTel.getString().trim()+"&nom="+tfNom.getString().trim());
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
