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
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Houssem Eddine
 */
public class MidletParsingXmlFacture1  implements CommandListener, Runnable {

    
    
 DateField date = new DateField("date", DateField.DATE);
       String[] tabGerant={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tabClient={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tabOffre={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tabIdClient = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    String[] tabIdOffre={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
 Command cmdMenu = new Command("Accueil", Command.SCREEN, 0);
    ChoiceGroup tfOffre;
ChoiceGroup tfClient;
ChoiceGroup tfGerant;
        DataInputStream dis;
        
    List tfId = new List("", List.IMPLICIT);
int ch;
Command cmdDelete= new Command("supprimer", Command.SCREEN, 0);
    Command cmdUpdate= new Command("modifier", Command.SCREEN, 0);
    Command cmdModif= new Command("modifier", Command.SCREEN, 0);
    String url = "http://localhost/parseFacture/update.php";
    Display disp ;
    Command cmdParse = new Command("Factures", Command.SCREEN, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
        Command cmdAdd= new Command("ajouter", Command.SCREEN, 0);
    Facture[] alertes;
    List lst = new List("Factures", List.IMPLICIT);
    Form fModif = new Form("Modifier");
    Form f = new Form("Accueil");
    Form form = new Form("Infos Facture");
    Form loadingDialog = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();

    public MidletParsingXmlFacture1(Display disp) {
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
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsePers/getXmlPersons_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, personnesHandler);
            // display the result
           Personne[] alertes = personnesHandler.getClients();
 
            if (alertes.length > 0) {
                tabClient=new String[alertes.length];
                for (int i = 0; i < alertes.length; i++) {
                     tabClient[i]=alertes[i].getNom();
                     tabGerant[i]=alertes[i].getNom(); 
                     tabIdClient[i]=Integer.toString(alertes[i].getId()).trim();
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
                 tabIdOffre=new String[alertes.length];
                for (int i = 0; i < alertes.length; i++) {
                     tabOffre[i]=alertes[i].getDescription();
                     tabIdOffre[i]=Integer.toString(alertes[i].getId()).trim();
                }
                 
              
            }

        } catch (Exception e) {
            
            System.out.println("Exception:" + e.toString());
        }
        f.append("Click Factures to get your factures_list");
        f.addCommand(cmdParse);
         f.addCommand(cmdMenu);
        form.addCommand(cmdMenu);
        fModif.addCommand(cmdMenu);
        lst.addCommand(cmdMenu);
        f.setCommandListener(this);
        form.addCommand(cmdUpdate);
        form.addCommand(cmdDelete);
        fModif.addCommand(cmdModif);
        lst.addCommand(cmdAdd);
        lst.setCommandListener(this);
        fModif.append(date);
        tfGerant =new ChoiceGroup("Gérant", ChoiceGroup.POPUP,tabGerant,null);
         tfClient=new ChoiceGroup("Client",ChoiceGroup.POPUP,tabClient,null);
           fModif.append(tfClient);
            tfOffre=new ChoiceGroup("Offre",ChoiceGroup.POPUP,tabOffre,null);
           fModif.append(tfOffre);
           fModif.addCommand(cmdBack);
        fModif.setCommandListener(this);
        
        form.addCommand(cmdBack);
        form.setCommandListener(this);
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
        
        if (c==cmdAdd)
        {new MidletAjoutFacture(disp);}
        
         if (c == cmdUpdate) {
             form.deleteAll();
             disp.setCurrent(fModif);
            }
if(c==cmdModif)
{
            try {
                modifierFacture(lst.getSelectedIndex());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
lst.deleteAll();
disp.setCurrent(lst);
Thread th=new Thread(this);
th.start();

}
if(c==cmdDelete)
    
{
            try {
                deleteFacture(lst.getSelectedIndex());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
             lst.deleteAll();
       disp.setCurrent(lst);
       Thread th1=new Thread(this);
       th1.start();
}
        if (c == List.SELECT_COMMAND) {
            form.append("Informations Factures : \n");
            form.append(showPersonne(lst.getSelectedIndex()));
            disp.setCurrent(form);
        }
         if(c==cmdMenu)
        {
        new MenuEventg(disp);
        }

        if (c == cmdBack) {
            form.deleteAll();
            disp.setCurrent(lst);
        }

    }

    public void run() {
        try {
            // this will handle our XML
            FactureHandler alertesHandler = new FactureHandler();
            // get a parser object
            
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parseFacture/getXmlPersons_Attributes.php");

            DataInputStream dis = new DataInputStream(hc.openDataInputStream());

            parser.parse(dis, alertesHandler);

            // display the result
          
            alertes = alertesHandler.getFacture();
              
        
            
            if (alertes.length > 0) {
                for (int i = 0; i < alertes.length; i++) {
                  for (int j = 0; j < tabIdClient.length; j++) {
                      try{
                  int a=Integer.parseInt(tabIdClient[j].trim());
                  if(a==alertes[i].getClient().getId())
                  {
                  Client c=new Client();
                  c.setId(alertes[i].getClient().getId());
                  c.setNom(tabClient[j]);
                  alertes[i].setClient(c);
                      System.out.println(c.getId()+"\n");
                  }}
                      catch(NumberFormatException ex)
                         {System.out.println();}
                      try{
                  int b=Integer.parseInt(tabIdClient[j].trim());
                   if(b==alertes[i].getGerant().getId())
                  {
                  Gerant g=new Gerant();
                  g.setId(alertes[i].getGerant().getId());
                  g.setNom(tabClient[j]);
                  alertes[i].setGerant(g);
                  
                  }
                      }
                      catch(NumberFormatException ex)
                         {System.out.println();}
                    }
                     for (int k = 0; k < tabIdOffre.length; k++) {
                         try{
                  int cc=Integer.parseInt(tabIdOffre[k].trim());
                        
                    if(cc==alertes[i].getOffre().getId())
                  {
                  Offre c=new Offre();
                  c.setId(alertes[i].getOffre().getId());
                  c.setDescription(tabOffre[k]);
                  alertes[i].setOffre(c);
                  
                  }}
 catch(NumberFormatException ex)
                         {System.out.println();}
                   
                    }
                    

                  lst.append("Date :  "
                            +alertes[i].getDate()+" \n Client : "
                            +alertes[i].getClient().getNom()+"  \n Gérant : "
                            +alertes[i].getGerant().getNom()+" \n Offre : "
                            +alertes[i].getOffre().getDescription()+" "
                           
                            , null);
tfId.append(Integer.toString(alertes[i].getId()).trim(), null);
                   

                
            }
            }
        } catch (Exception e ) {
           
            System.out.println("Exception11:" + e.toString());
        }
        disp.setCurrent(lst);
    }

    private String showPersonne(int i) {
        String res = "";
       
           
            sb.append("* Date : ");
            sb.append(alertes[i].getDate());
            sb.append("\n");
            sb.append("* Client : ");
            sb.append(alertes[i].getClient().getNom());
            sb.append("\n");
             sb.append("* Gérant : ");
            sb.append(alertes[i].getGerant().getNom());
            sb.append("\n");
             sb.append("* Offre : ");
                sb.append(alertes[i].getOffre().getDescription());
            
          
            
           
            
        
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
    
    public String modifierFacture(int i) throws IOException
    {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(date.getDate());

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
   
   
    String res = "";
        if (alertes.length > 0) {
            HttpConnection    hc = (HttpConnection) Connector.open(url+"?id="+alertes[i].getId()+"&date="+Integer.toString(day).trim()+"-"+Integer.toString(month).trim()+"-"+Integer.toString(year).trim()+"&idClient="+tabClient[tfClient.getSelectedIndex()].trim()+"&idOffre="+tabOffre[tfOffre.getSelectedIndex()].trim()+"&idGerant="+tabClient[tfGerant.getSelectedIndex()].trim());
            System.out.println(url+"?id="+alertes[i].getId()+"&date="+Integer.toString(day).trim()+"-"+Integer.toString(month).trim()+"-"+Integer.toString(year).trim()+"&idClient="+tabClient[tfClient.getSelectedIndex()].trim()+"&idOffre="+tabOffre[tfOffre.getSelectedIndex()].trim()+"&idGerant="+tabClient[tfGerant.getSelectedIndex()].trim());
            dis = new DataInputStream(hc.openDataInputStream());
           while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if ("OK".equals(sb.toString().trim())) {
                disp.setCurrent(form);
            } 
            sb = new StringBuffer("");
        }
        return res;
    
    }
    
    
    
    
    private String deleteFacture(int i) throws IOException {
        String res = "";
        if (alertes.length > 0) {
          
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parseFacture/delete.php?idFacture="+tfId.getString(i));
              dis = new DataInputStream(hc.openDataInputStream());
             // System.out.println("http://localhost/parsingBest/delete.php?id="+id);
            while ((ch = dis.read()) != -1) {
                sb.append((char) ch);
            }
            if ("OK".equals(sb.toString().trim())) {
                disp.setCurrent(form);
            }
        }
       // res = sb.toString();
        //sb = new StringBuffer("");
        return res;
    }
    
    
    
}
