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
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
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
public class MidletParsingAlerte1  implements CommandListener, Runnable {

    
    
    TextField prixMin = new TextField("prixMin", null, 100, TextField.DECIMAL);
    TextField prixMax = new TextField("prixMax", null, 100, TextField.DECIMAL);
        TextField region = new TextField("region", null, 100, TextField.ANY);
   // TextField engagement = new TextField("engagement", null, 100, TextField.ANY);
    //TextField typedeBien = new TextField("typedeBien", null, 100, TextField.ANY);
    TextField surface = new TextField("surface", null, 100, TextField.DECIMAL);
    String[] tab={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tabOffre={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};
    String[] tabIdClient = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
    String[] tabIdOffre={"","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","","",""};

    ChoiceGroup tfClient;
ChoiceGroup tfOffre;
String[] tabEngagement={"location","vente"};
String[] tabType={"Entreprise","Logement","Terrain"};
 ChoiceGroup engagement=new ChoiceGroup("Engagement",ChoiceGroup.POPUP,tabEngagement,null);
  ChoiceGroup typedeBien=new ChoiceGroup("Type",ChoiceGroup.POPUP,tabType,null);
        DataInputStream dis;
         List tfNom = new List("id:", List.IMPLICIT);
    List tfId = new List("", List.IMPLICIT);
int ch;
Command cmdDelete= new Command("supprimer", Command.SCREEN, 0);
    Command cmdUpdate= new Command("modifier", Command.SCREEN, 0);
    Command cmdAdd= new Command("ajouter", Command.SCREEN, 0);
    Command cmdModif= new Command("modifier", Command.SCREEN, 0);
    Command cmdRetour= new Command("retour", Command.SCREEN, 0);
    String url = "http://localhost/parseAgence/update.php";
    Display disp ;
    Command cmdParse = new Command("Alertes", Command.SCREEN, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
    Alerte[] alertes;
     Command cmdMenu = new Command("Accueil", Command.SCREEN, 0);

    List lst = new List("Alertes", List.IMPLICIT);
    Form fModif = new Form("Modifier");
    Form f = new Form("Accueil");
    Form form = new Form("Infos Alertes");
    Form loadingDialog = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();

    public MidletParsingAlerte1(Display disp) {
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
                tab=new String[alertes.length];
                tabIdClient=new String[alertes.length];
                for (int i = 0; i < alertes.length; i++) {
                     tab[i]=alertes[i].getNom();
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
                   lst.addCommand(cmdAdd);
        f.append("Click Alertes to get your alertes_list");
        f.addCommand(cmdMenu);
        form.addCommand(cmdMenu);
        fModif.addCommand(cmdMenu);
        lst.addCommand(cmdMenu);
        f.addCommand(cmdParse);
        f.setCommandListener(this);
        form.addCommand(cmdUpdate);
        form.addCommand(cmdDelete);
        fModif.addCommand(cmdModif);
        fModif.addCommand(cmdRetour);
        lst.setCommandListener(this);
        lst.setCommandListener(this);
        fModif.append(prixMin);
        fModif.append(prixMax);
        fModif.append(region);
        fModif.append(engagement);
        fModif.append(typedeBien);
        fModif.append(surface);
         tfClient=new ChoiceGroup("Client",ChoiceGroup.POPUP,tab,null);
           fModif.append(tfClient);
            tfOffre=new ChoiceGroup("Offre",ChoiceGroup.POPUP,tabOffre,null);
           fModif.append(tfOffre);
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
        if (c == cmdRetour) {
            disp.setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
        }
        
         if (c == cmdUpdate) {
             form.deleteAll();
             disp.setCurrent(fModif);
            }
if(c==cmdModif)
{
            try {
                modifierAlerte(lst.getSelectedIndex());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
lst.deleteAll();
disp.setCurrent(lst);
Thread th=new Thread(this);
th.start();

}
 if(c==cmdMenu)
        {
        new MenuEventC(disp);
        }

if(c==cmdAdd)
{
            MidletAjoutAlerte midletAjoutAlerte = new MidletAjoutAlerte(disp);
              

}


if(c==cmdDelete)
    
{
            try {
                deleteAlerte(lst.getSelectedIndex());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
             lst.deleteAll();
       disp.setCurrent(lst);
       Thread th1=new Thread(this);
       th1.start();
}
        if (c == List.SELECT_COMMAND) {
            form.append("Informations Alertes : \n");
            form.append(showPersonne(lst.getSelectedIndex()));
            disp.setCurrent(form);
        }

        if (c == cmdBack) {
            form.deleteAll();
            disp.setCurrent(lst);
        }

    }

    public void run() {
        try {
            // this will handle our XML
            AlerteHandler alertesHandler = new AlerteHandler();
            // get a parser object
            
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parseAgence/getXmlPersons_Attributes.php");

            DataInputStream dis = new DataInputStream(hc.openDataInputStream());

            parser.parse(dis, alertesHandler);

            // display the result
            alertes = alertesHandler.getAlerte();
           // System.out.println(alertes[0].getPrixMax()+"prix"+alertes[1].getEngagement());
            
            if (alertes.length > 0) {
                for (int i = 0; i < alertes.length; i++) {
                    
                    for (int j = 0; j < tabIdClient.length; j++) {
                  
                  if(Integer.parseInt(tabIdClient[j].trim())==alertes[i].getClient().getId())
                  {
                  Client c=new Client();
                  c.setId(alertes[i].getClient().getId());
                  c.setNom(tab[j]);
                  alertes[i].setClient(c);
                      System.out.println(c.getId()+"\n");
                  }
                    }
                     for (int k = 0; k < tabIdOffre.length; k++) {
                  
                    if(Integer.parseInt(tabIdOffre[k].trim())==alertes[i].getOffre().getId())
                  {
                  Offre c=new Offre();
                  c.setId(alertes[i].getOffre().getId());
                  c.setDescription(tabOffre[k]);
                  alertes[i].setOffre(c);
                  
                  }
                  
                    }
                    String tip="";
                    if(alertes[i].getTypedeBien().equals("l"))
                    {tip="Logement";}
                     if(alertes[i].getTypedeBien().equals("e"))
                    {tip="Entreprise";}
                      if(alertes[i].getTypedeBien().equals("t"))
                    {tip="Terrain";}
                    
                  lst.append("Prix Min : "
                            +alertes[i].getPrixMin()+"\n Prix Max :  "
                            +alertes[i].getPrixMax()+" \n Region :"
                            +alertes[i].getRegion()+" \n Engagement :"
                            +alertes[i].getEngagement()+" \n Type :"
                            +tip+" \n Surface :"
                            +alertes[i].getSurface()+" \n Client :"
                            +alertes[i].getClient().getNom()+" \n Offre :"
                            +alertes[i].getOffre().getDescription()+" "
                            , null);
                  tfNom.append(
                           alertes[i].getPrixMax()+" "+alertes[i].getRegion()
                          +" "+alertes[i].getEngagement()+" "+alertes[i].getTypedeBien()+" "+alertes[i].getSurface(), null);
tfId.append(Integer.toString(alertes[i].getId()), null);

                }
            }

        } catch (Exception e) {
            System.out.println("Exception1:" + e.toString());
        }
        disp.setCurrent(lst);
    }

    private String showPersonne(int i) {
        String res = "";
        if (alertes.length > 0) {
           
            sb.append("* Prix min: ");
            sb.append(alertes[i].getPrixMin());
            sb.append("\n");
            sb.append("* Prix max: ");
            sb.append(alertes[i].getPrixMax());
            sb.append("\n");
             sb.append("* Region : ");
            sb.append(alertes[i].getRegion());
            sb.append("\n");
             sb.append("* Engagement : ");
             if(alertes[i].getEngagement().equals("l"))
            sb.append("location");
              if(alertes[i].getEngagement().equals("v"))
            sb.append("vente");
            sb.append("\n");
             sb.append("* Type : ");
             if(alertes[i].getTypedeBien().equals("l"))
            sb.append("Logement");
             if(alertes[i].getTypedeBien().equals("e"))
            sb.append("Entreprise");
             if(alertes[i].getTypedeBien().equals("t"))
            sb.append("Terrain");
            sb.append("\n");
             sb.append("* Surface : ");
            sb.append(alertes[i].getSurface());
            sb.append("\n");
             sb.append("* ");
                 sb.append("* Client : ");
            sb.append(alertes[i].getClient().getNom());
            sb.append("\n");
                sb.append("* Offre: ");
            sb.append(alertes[i].getOffre().getDescription());
            sb.append("\n");
             prixMax.setString(Double.toString(alertes[i].getPrixMax()));
             prixMin.setString(Double.toString(alertes[i].getPrixMin()));
             region.setString(alertes[i].getRegion());
             if ("location".equals(alertes[i].getEngagement().trim()))
             { 
                 engagement.setSelectedIndex(0, false);
                     
                     }
             else
             {engagement.setSelectedIndex(1, false);}
             
             if(alertes[i].getTypedeBien().trim().equals("Entreprise"))
             {typedeBien.setSelectedIndex(0, false);}
             if(alertes[i].getTypedeBien().trim().equals("Logement"))
             {typedeBien.setSelectedIndex(1, false);}
             if(alertes[i].getTypedeBien().trim().equals("Terrain"))
             {typedeBien.setSelectedIndex(2, false);}
             surface.setString(Double.toString(alertes[i].getSurface()));
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
    
    public String modifierAlerte(int i) throws IOException
    {
   
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
   
    String res = "";
        if (alertes.length > 0) {
            HttpConnection    hc = (HttpConnection) Connector.open(url+"?id="+alertes[i].getId()+"&prixMin="+prixMin.getString().trim()+"&prixMax="+prixMax.getString().trim()+"&region="+region.getString().trim()+"&engagement="+engagement.getString(engagement.getSelectedIndex())+"&typedeBien="+typ.trim()+"&surface="+surface.getString()+"&idClient="+tab[tfClient.getSelectedIndex()].trim()+"&idOffre="+tabOffre[tfOffre.getSelectedIndex()].trim());
            System.out.println(url+"?id="+alertes[i].getId()+"&prixMin="+alertes[i].getPrixMin()+"&prixMax="+alertes[i].getPrixMax()+"&region="+alertes[i].getRegion().trim()+"&engagement="+alertes[i].getEngagement().trim()+"&typedeBien="+alertes[i].getTypedeBien().trim()+"&surface="+alertes[i].getSurface()+"&idClient="+tab[tfClient.getSelectedIndex()].trim()+"&idOffre="+tabOffre[tfOffre.getSelectedIndex()].trim());  
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
    
    
    
    
    private String deleteAlerte(int i) throws IOException {
        String res = "";
        if (alertes.length > 0) {
          
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parseAgence/delete.php?PrixMin="+tfId.getString(tfNom.getSelectedIndex()));
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
