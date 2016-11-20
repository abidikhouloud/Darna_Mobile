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
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * @author Houssem Eddine
 */
public class ParsingXmlRendezVous  implements CommandListener, Runnable {

  String [] tabLieu={"tunis","ariana","zaghouene","beja","jandouba","seliena","sfax","sidi bouzid","sousse","kairouane","gafsa","medenin","gasserine","ariana","tataouine","mahdia","kef","tozeur","gbeli","nabeul","gabes"};  
ChoiceGroup tfLieux = new ChoiceGroup("Lieux", ChoiceGroup.POPUP, tabLieu, null);
DateField tfDate = new DateField("date", DateField.DATE);
     Command cmdMenu = new Command("Accueil", Command.SCREEN, 0);

    String[] tabClient={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tabOffre={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tab={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tab1={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    
ChoiceGroup tfOffre;
ChoiceGroup tfClient;

        DataInputStream dis;
         List tfNom = new List("id:", List.IMPLICIT);
    List tfId = new List("", List.IMPLICIT);
int ch;

    Command cmdAjout = new Command("Ajout", Command.SCREEN, 0);
    Command cmdSup = new Command("Supprimer", Command.SCREEN, 0);

    Command cmdUpdate= new Command("modifier", Command.SCREEN, 0);
    Command cmdModif= new Command("modifier", Command.SCREEN, 0);
    String url ="http://localhost/parsingDarna/modifierRendezVous.php";
    Display disp ;
    Command cmdParse = new Command("RendezVous", Command.SCREEN, 0);
    Command cmdBackR = new Command("retour", Command.BACK, 0);
    RendezVous[] RendezVouss;
    List lst = new List("Rendez vous", List.IMPLICIT);
    Form fModif = new Form("Modifier");
    Form f = new Form("Accueil");
    Form form = new Form("Infos Rendez Vous");
    Form loadingDialog = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();

    ParsingXmlRendezVous(Display disp) {
        this.disp=disp;
        this.startApp();

    }
    
    
    

    public void startApp() 
    
    {
        
        
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
         
        f.append("Click Rendez Vous to get your RendezVous_list");
        f.addCommand(cmdParse);
        f.addCommand(cmdAjout);
        f.addCommand(cmdBackR);
      
        
       form.addCommand(cmdSup);
       form.addCommand(cmdBackR);
       
        form.addCommand(cmdUpdate);
         form.addCommand(cmdMenu);
      f.addCommand(cmdMenu);
         f.setCommandListener(this);
       fModif.addCommand(cmdMenu);
        lst.addCommand(cmdMenu);
        fModif.addCommand(cmdModif);
        lst.setCommandListener(this);
        fModif.append(tfLieux);
       
        fModif.append(tfDate);
         tfClient=new ChoiceGroup("Client",ChoiceGroup.POPUP,tabClient,null);
           fModif.append(tfClient);
            tfOffre=new ChoiceGroup("Offre",ChoiceGroup.POPUP,tabOffre,null);
           fModif.append(tfOffre);
       
        form.addCommand(cmdBackR);
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
         if(c==cmdMenu)
         {
         new MenuEventC(disp);
         }
         
         if(c==cmdAjout)
         {new AjoutRendezVous(disp);}
         
          if(c==cmdSup)
         {new SupprimerRendezVous(disp);}
         
        
         if (c == cmdUpdate) {
             form.deleteAll();
             disp.setCurrent(fModif);
            }
if(c==cmdModif)
{
            try {
                modifierRendezVous(lst.getSelectedIndex());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

disp.setCurrent(lst);
Thread th=new Thread(this);
th.start();

}

        if (c == List.SELECT_COMMAND) {
            form.append("Informations rendez vouss : \n");
            form.append(showRendezVous(lst.getSelectedIndex()));
            disp.setCurrent(form);
        }

        if (c == cmdBackR) {
                      new ParsingXmlRendezVous(disp);

        }

    }

    public void run() {
        try {
            // this will handle our XML
            RendezVousHandler rendezVousHandler = new RendezVousHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsingDarna/getXmlRendezVous_attributes.php");
             dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, rendezVousHandler);
            // display the result
            RendezVouss = rendezVousHandler.getRendezVous();

            if (RendezVouss.length > 0) {
                for (int i = 0; i < RendezVouss.length; i++) {
                    lst.append(
                            RendezVouss[i].getId()+" "
                            +RendezVouss[i].getLieu()+" "
                            +RendezVouss[i].getDate()+" "
                            
                            
                            , null);
              
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lst);
    }

    private String showRendezVous(int i) {
        String res = "";
        if (RendezVouss.length > 0) 
        {
           
            sb.append("* ID: ");
            sb.append(RendezVouss[i].getId());
            sb.append("\n");
            
            sb.append("* Lieux : ");
            sb.append(RendezVouss[i].getLieu());
            sb.append("\n");
            
            
            sb.append("* DATE : ");
            sb.append(RendezVouss[i].getDate());
            sb.append("\n");
           
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
    
     public String modifierRendezVous(int i) throws IOException
    {
   
   Calendar calendar = Calendar.getInstance();
    calendar.setTime(tfDate.getDate());

    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
   
    String res = "";
    System.out.println(url.trim()+"?id="+RendezVouss[i].getId()+"&lieu="+tabLieu[tfLieux.getSelectedIndex()].trim()+"&date="+Integer.toString(day).trim()+"-"+Integer.toString(month).trim()+"-"+Integer.toString(year).trim()+"&idClient="+tabClient[tfClient.getSelectedIndex()].trim()+"&idOffre="+tabOffre[tfOffre.getSelectedIndex()].trim());  

            if(RendezVouss.length > 0)
            {
            HttpConnection  hc = (HttpConnection)Connector.open(url.trim()+"?id="+RendezVouss[i].getId()+"&lieu="+tabLieu[tfLieux.getSelectedIndex()].trim()+"&date="+Integer.toString(day).trim()+"-"+Integer.toString(month).trim()+"-"+Integer.toString(year).trim()+"&idClient="+tabClient[tfClient.getSelectedIndex()].trim()+"&idOffre="+tabOffre[tfOffre.getSelectedIndex()].trim());
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
}
