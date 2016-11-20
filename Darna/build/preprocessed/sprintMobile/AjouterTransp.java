/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprintMobile;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.*;

/**
 * @author Yahya
 */
public class AjouterTransp  implements CommandListener{
    Display disp;
Form myForm =new Form("Ajouter un transporteur");
    TextField tftel=new TextField("tel", null, 25, TextField.NUMERIC);
    TextField tfadd=new TextField("adresse",null , 50, TextField.ANY);
    Command cmdAjout=new Command("Ajouter", null, Command.OK, 0);
    Command cmdRetour=new Command("menu principal", null, Command.SCREEN,0);
    Command cmdListe=new Command("Liste Transporteurs", null, Command.SCREEN,1);
    Ticker ajoutTicker=new Ticker("clicker sur 'Ajouter' pour ajouter un Transporteur");
    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/parse/ajoutTransp.php";
    StringBuffer sb = new StringBuffer();
    int ch;
//fin Connexion

    public AjouterTransp(Display disp) {
        this.disp = disp;
         this.startApp();
    }
    
    
    
    public void startApp() {
     myForm.append(tftel);
    
     myForm.append(tfadd);
    myForm.addCommand(cmdAjout);
    myForm.addCommand(cmdRetour);
    myForm.addCommand(cmdListe);
    myForm.setTicker(ajoutTicker);
   myForm.setCommandListener(this);
    disp.setCurrent(myForm);
   
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
    if(c==cmdAjout)        
          
      try {
                hc = (HttpConnection) Connector.open(url+"?tel="+tftel.getString().trim()+"&adresse="+tfadd.getString().trim());
                 System.out.println(url+"?tel="+tftel.getString().trim()+"&addresse="+tfadd.getString().trim());
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                    tfadd.setString("");
                    tftel.setString("");
                    ajoutTicker.setString("transporteur ajouté avec succès ");
                }else{
                   
                     ajoutTicker.setString("Erreur lors de l'ajout d'un transporteur");
                }
                sb = new StringBuffer();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
             
    }
    
    }

