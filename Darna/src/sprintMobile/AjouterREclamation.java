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
public class AjouterREclamation implements CommandListener{
    Display disp;
Form myForm =new Form("Ajouter une reclamation");
    TextField tfquestion=new TextField("question", null, 25, TextField.ANY);
    TextField tfreponse=new TextField("reponse",null , 50, TextField.ANY);
    Command cmdAjout=new Command("Ajouter", null, Command.OK, 0);
    Command cmdRetour=new Command("menu principal", null, Command.SCREEN,0);
    Command cmdListe=new Command("Liste Reclamations", null, Command.SCREEN,1);
    Ticker ajoutTicker=new Ticker("cliquer sur 'Ajouter' pour ajouter une Reclamation");
    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/parse/ajoutrec.php";
    StringBuffer sb = new StringBuffer();
    int ch;
//fin Connexion

    public AjouterREclamation(Display disp) {
        this.disp = disp;
    }
    
    
    
    
    public void startApp() {
     myForm.append(tfquestion);
    
     myForm.append(tfreponse);
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
                hc = (HttpConnection) Connector.open(url+"?question="+tfquestion.getString().trim()+"&reponse="+tfreponse.getString().trim());
                 System.out.println(url+"?question="+tfquestion.getString().trim()+"&reponse="+tfreponse.getString().trim());
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                    tfquestion.setString("");
                    tfreponse.setString("");
                    ajoutTicker.setString("Reclamation ajoutée avec succès ");
                }else{
                   
                     ajoutTicker.setString("Erreur lors de l'ajout d'une Reclamation");
                }
                sb = new StringBuffer();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
             
    }
    
    }

