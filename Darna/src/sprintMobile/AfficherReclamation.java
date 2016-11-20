/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sprintMobile;

import Handler.RecHandler;
import Entity.reclamation;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Yahya
 */

  public class AfficherReclamation implements CommandListener, Runnable {

    Display disp;
    Command cmdParse = new Command("Reclamation", Command.SCREEN, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
      reclamation[] deps;
    List lst = new List("Reclamation", List.IMPLICIT);
    Form f = new Form("Accueil");
    Form form = new Form("Infos Reclamation");
Command cmdmodifier=new Command("Modifier", Command.SCREEN, 0);
    Command cmdSupprimer=new Command("Supprimer", Command.SCREEN, 1);
    Form loadingDialog = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();
    Ticker affTicker=new Ticker("clicker sur une Reclamation afin de la modifier ou la supprimer ");
    int idD;
      
      //Modification
    
     Form FormModification=new Form("Modifier Reclamation");
    
    TextField tfquestion=new TextField("question", null, 25, TextField.NUMERIC);
    TextField tfreponse=new TextField("reponse",null , 50, TextField.ANY);
   
    Command cmdRetour=new Command("menu principal", null, Command.SCREEN,0);
    Command cmdListe=new Command("Liste Reclamations", null, Command.SCREEN,1);
       Command cmdBackM = new Command("Back", Command.BACK, 0);

Ticker tickerModif=new Ticker("");
Command cmdValiderModifier=new Command("Valider", Command.SCREEN, 0);
//fin    

    public AfficherReclamation(Display disp) {
        this.disp = disp;
         this.startApp();
    }
      
      
      
    public void startApp() {
        
        f.append("Clique Reclamation pour afficher la liste des Reclamations");
        f.addCommand(cmdParse);
        f.setCommandListener(this);
        lst.setCommandListener(this);
        form.addCommand(cmdBack);
        form.addCommand(cmdmodifier);
        form.addCommand(cmdSupprimer);
        form.setCommandListener(this);
        form.setTicker(affTicker);
        disp.setCurrent(f);
        //modification
        
        FormModification.append(tfquestion);
        FormModification.append(tfreponse);
        FormModification.addCommand(cmdBackM);
        FormModification.addCommand(cmdValiderModifier);
        FormModification.setCommandListener(this);
        FormModification.setTicker(tickerModif);
        //fin modification
        
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

        if (c == List.SELECT_COMMAND) {
            form.append("Informations Reclamation: \n");
            form.append(showDep(lst.getSelectedIndex()));
            disp.setCurrent(form);
            
            
        }
if (c == cmdBack) {
            form.deleteAll();
            disp.setCurrent(lst);
        }
if (c == cmdBackM) {
            form.deleteAll();
            disp.setCurrent(lst);
        }
        
        if(c==cmdSupprimer)
        {
            disp.setCurrent(loadingDialog);
        try {
//            for(int i =0;i<lst.size();i++)
//            {lst.delete(i);}
          lst = new List("Recalamtions", List.IMPLICIT);
  lst.setCommandListener(this);
        lst.setTicker(affTicker);
            // this will handle our XML
            RecHandler recHandler = new RecHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            
            HttpConnection hc_sup;
    DataInputStream dis_sup;
              
    hc_sup= (HttpConnection) Connector.open("http://localhost/parse/Supprimerrec.php?idD="+idD);
            System.out.println("http://localhost/parse/Supprimerrec.php?idD="+idD);
    dis_sup = new DataInputStream(hc_sup.openDataInputStream());
            affTicker.setString("la reclamation : "+idD+"a été supprimer");
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parse/getXmlrec.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, recHandler);
            // display the result
         
            deps = recHandler.getreclamation();
               
          if (deps.length > 0) {
                for (int i = 0; i < deps.length; i++) {
                    lst.append(deps[i].getId()+" "
                            +deps[i].getquestion()+" "+deps[i].getreponse(), null);

                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
            
          form.deleteAll();
          affTicker.setString("cliquer sur une Reclamation afin de la modifier ou la supprimer ");
            disp.setCurrent(lst);
            
        }
        
        //modification
        if(c==cmdmodifier)
        {
            tickerModif.setString("modification de la reclamation : "+idD);
        disp.setCurrent(FormModification);
        }
        if(c==cmdValiderModifier)
        {
            HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/parse/modifierrec.php";
    StringBuffer sb = new StringBuffer();
    int ch;
    
    try {
                hc = (HttpConnection) Connector.open(url+"?id="+idD+"&question="+tfquestion.getString().trim()+"&reponse="+tfreponse.getString().trim());
                 System.out.println(url+"?id="+idD+"&question="+tfquestion.getString().trim()+"&adresse="+tfreponse.getString().trim());
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                  tickerModif.setString("Modification du la Reclamation : "+idD+" est terminée");
                }else{
 tickerModif.setString("Erreur de Modification");                }
                sb = new StringBuffer();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
       lst.deleteAll();
                   lst.setCommandListener(this);
            Thread th = new Thread(this);
            th.start();
            
    
        }
        
        //fin modification
        
        
       
    }

    public void run() {
        try {
            // this will handle our XML
            RecHandler depHandler = new RecHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parse/getXmlrec.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, depHandler);
            // display the result
            deps = depHandler.getreclamation();

            if (deps.length > 0) {
                for (int i = 0; i < deps.length; i++) {
                    lst.append(deps[i].getId()+" "
                            +deps[i].getquestion()+" "+deps[i].getreponse(), null);

                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lst);
    }

    private String showDep(int i) {
      
        // init form
        form.deleteAll();
        form.addCommand(cmdBack);
        form.addCommand(cmdmodifier);
        form.addCommand(cmdSupprimer);
        form.setCommandListener(this);
        form.setTicker(affTicker);
        
       //fin int 
        String res = "";
        if (deps.length > 0) {
            sb.append("Dep Ref : ");
            sb.append(deps[i].getId());
            idD=deps[i].getId();
            sb.append("\n");
            sb.append("question : ");
            sb.append(deps[i].getquestion());
            tfquestion.setString(String.valueOf(deps[i].getquestion()));
            sb.append("\n");
            sb.append("reponse : ");
            sb.append(deps[i].getreponse());
            tfreponse.setString(deps[i].getreponse());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
}
