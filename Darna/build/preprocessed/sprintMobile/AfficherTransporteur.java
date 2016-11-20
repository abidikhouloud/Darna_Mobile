/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sprintMobile;

import Handler.TranspHandler;
import Entity.transporteur;
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

  public class AfficherTransporteur  implements CommandListener, Runnable {

    Display disp ;
    Command cmdParse = new Command("Transporteur", Command.SCREEN, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
      transporteur[] deps;
    List lst = new List("Trasnporteur", List.IMPLICIT);
    Form f = new Form("Accueil");
    Form form = new Form("Infos Transporteur");
Command cmdmodifier=new Command("Modifier", Command.SCREEN, 0);
    Command cmdSupprimer=new Command("Supprimer", Command.SCREEN, 1);
    Form loadingDialog = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();
    Ticker affTicker=new Ticker("clicker sur un Transporteur afin de le modifier ou la supprimer ");
    int idD;
      
      //Modification
    
     Form FormModification=new Form("Modifier Transporteur");
    
    TextField tftel=new TextField("tel", null, 25, TextField.NUMERIC);
    TextField tfadd=new TextField("Adresse",null , 50, TextField.ANY);
   
    Command cmdRetour=new Command("menu principal", null, Command.SCREEN,0);
    Command cmdListe=new Command("Liste Departements", null, Command.SCREEN,1);
       Command cmdBackM = new Command("Back", Command.BACK, 0);

Ticker tickerModif=new Ticker("");
Command cmdValiderModifier=new Command("Valider", Command.SCREEN, 0);
//fin    

    public AfficherTransporteur(Display disp) {
        this.disp = disp;
         this.startApp();
    }
      
      
      
    public void startApp() {
        
        f.append("Click Transporteur pour afficher la liste des transporteurs");
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
        
        FormModification.append(tftel);
        FormModification.append(tfadd);
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
            form.append("Informations Transporteur: \n");
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
          lst = new List("transporteurs", List.IMPLICIT);
  lst.setCommandListener(this);
        lst.setTicker(affTicker);
            // this will handle our XML
            TranspHandler transpHandler = new TranspHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            
            HttpConnection hc_sup;
    DataInputStream dis_sup;
              
    hc_sup= (HttpConnection) Connector.open("http://localhost/parse/Supprimertransp.php?idD="+idD);
            System.out.println("http://localhost/parse/Supprimertransp.php?idD="+idD);
    dis_sup = new DataInputStream(hc_sup.openDataInputStream());
            affTicker.setString("le transporteur : "+idD+"a été supprimer");
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parse/getXmlTransp.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, transpHandler);
            // display the result
         
            deps = transpHandler.getTransporteur();
               
          if (deps.length > 0) {
                for (int i = 0; i < deps.length; i++) {
                    lst.append(deps[i].getId()+" "
                            +deps[i].getTel()+" "+deps[i].getadresse(), null);

                }
            }

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
            
          form.deleteAll();
          affTicker.setString("cliquer sur un tranporteur afin de le modifier ou le supprimer ");
            disp.setCurrent(lst);
            
        }
        
        //modification
        if(c==cmdmodifier)
        {
            tickerModif.setString("modification du transporteur: "+idD);
        disp.setCurrent(FormModification);
        }
        if(c==cmdValiderModifier)
        {
            HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/parse/modifiertransp.php";
    StringBuffer sb = new StringBuffer();
    int ch;
    
    try {
                hc = (HttpConnection) Connector.open(url+"?id="+idD+"&tel="+tftel.getString().trim()+"&adresse="+tfadd.getString().trim());
                 System.out.println(url+"?id="+idD+"&tel="+tftel.getString().trim()+"&adresse="+tfadd.getString().trim());
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                  tickerModif.setString("Modification du Transporteur : "+idD+" est terminé");
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
            TranspHandler depHandler = new TranspHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parse/getXmlTransp.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, depHandler);
            // display the result
            deps = depHandler.getTransporteur();

            if (deps.length > 0) {
                for (int i = 0; i < deps.length; i++) {
                    lst.append(deps[i].getId()+" "
                            +deps[i].getTel()+" "+deps[i].getadresse(), null);

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
            sb.append("tel : ");
            sb.append(deps[i].getTel());
            tftel.setString(String.valueOf(deps[i].getTel()));
            sb.append("\n");
            sb.append("adresse : ");
            sb.append(deps[i].getadresse());
            tfadd.setString(deps[i].getadresse());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
}
