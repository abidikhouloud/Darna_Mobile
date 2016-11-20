package sprintMobile;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * @author Khalil
 */

public class ListEntreprise implements CommandListener,Runnable{
 

    
    
Display disp;

      Ticker myTicker = new Ticker("");
    Command cmdParse = new Command("Entreprise", Command.SCREEN, 0);
     Command cmdParse3 = new Command("Modifier", Command.SCREEN, 0);
         Command cmdParse2 = new Command("Supprimer", Command.SCREEN, 0);
         Command cmdParseAjout = new Command("Ajouter Entreprise", Command.SCREEN, 0);
          Command cmdRecherche = new Command("Chercher Logement", Command.SCREEN, 0);
       Command cmdMenuPricimal = new Command("Menu Principal", Command.SCREEN, 0);
      
       
      Command cmdParse4 = new Command("valider", Command.SCREEN, 0);
      
      
    Command cmdBack = new Command("Back", Command.BACK, 0);
    Logement[] logements;
    Entreprise[] entreprises;
    List lst = new List("Entreprise", List.IMPLICIT);
    Form f = new Form("Accueil");
    Form form = new Form("details des entreprises");
    Form loadingDialog = new Form("Attendez VP");
    StringBuffer sb = new StringBuffer();

    
    int id;
      int ch;
     List lstComms = new List("Entreprise", List.IMPLICIT);
    Form form1;
    Display disp1;
    Command vib, exit;
    
    
    
    Form f1 = new Form("Modifier Entreprise");
    TextField tfDescription = new TextField("Description", null, 100, TextField.ANY);
    DateField tfDate = new DateField("Date",DateField.DATE );
    String [] table = {"Tunis","Ben Arous","Mannouba","Ariana","Nabeul","Bizerte","Beja","jendouba","Kef","kasserine"
    ,"sidi Bouzid","zaghouan","sousse","Kairouan","Mahdia","Moastir","Gabes","Gafsa","Tataouin","Medenin","Seliana",
    "Gebili","sfax","tozeur"}; 
    String[] table1 = {"Location","Vente"};
    ChoiceGroup  chG  = new ChoiceGroup("engagement", ChoiceGroup.POPUP,table,null);
      ChoiceGroup  chEngagement  = new ChoiceGroup("engagement", ChoiceGroup.POPUP,table1,null);
      TextField tfPrix = new TextField("Prix", null, 100, TextField.NUMERIC);
      TextField tfSurface = new TextField("surface", null, 100, TextField.NUMERIC);
      TextField tfSurfaceCouverte = new TextField("Surface Couverte ", null, 100, TextField.NUMERIC);  
      
     Command cmdValider = new Command("valider", Command.SCREEN, 0);

    public ListEntreprise(Display disp) {
        this.disp = disp;
        this.startApp();
    }
    
    

    
    
    public void startApp() {
        
        
        try {
            Image image = Image.createImage("entreprise.png");   
            Graphics g = image.getGraphics();    
            g.drawImage(image, 0, 0, 0);   
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Creation de l'image echoué");
        }
        
        
        f.append("List Entreprise");
  form.addCommand(cmdParse2);
        //f.addCommand(cmdParse1);
        f.addCommand(cmdParse);
        f.setCommandListener(this);
        lst.setCommandListener(this);
        f.addCommand(cmdBack);
        f.setCommandListener(this);
        disp.setCurrent(f);
      
        form.setCommandListener(this);
        form.addCommand(cmdBack);
          /*f1.append(tfDescription);
        f1.append(tfDate);
        f1.addCommand(cmdValider);
        f1.setCommandListener(this);
        f2.addCommand(cmdBack);
        f2.setCommandListener(this);
        f1.append(chG);*/
        
        
         form.addCommand(cmdParse3);
               form.addCommand(cmdParse2);
               f.addCommand(cmdParseAjout);
               f.addCommand(cmdRecherche);
               
    disp.setCurrent(f);
        
          f1.append(tfDescription);
       // f1.append(tfDate);
        f1.append(tfPrix);
         f1.append(chG);
        f1.append(tfSurface);
       
        f1.append(tfSurfaceCouverte);
        f1.append(chEngagement);
        f1.addCommand(cmdValider);
         form.addCommand(cmdMenuPricimal);
       
        
        f1.setCommandListener(this);
       f1.addCommand(cmdMenuPricimal);
        f1.addCommand(cmdParse);
        lst.addCommand(cmdBack);
          
        form.setCommandListener(this);
        f1.setCommandListener(this);
       disp.setCurrent(f);
        
       
       
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
    
         if (c == cmdParse) {
            disp.setCurrent(loadingDialog);
           afficherEntreprise();
        }
         
         if(c == cmdParse2){
            System.out.println("test");
       
        supprimerEntreprise();
         disp.setCurrent(loadingDialog);
           new ListEntreprise(disp);
    }
         
         if (c== cmdParse3)
         {
             try {
                 modifierEntreprise();
             } catch (IOException ex) {
                 ex.printStackTrace();
             }
         }

         if(c == cmdParseAjout){
         
             new AjoutEntreprise(disp);
             
         }
         
         if(c == cmdRecherche){
         
             new ChercherEntreprise(disp);
             
         }
         
         if(c ==cmdValider)
         {
        afficherEntreprise();
         } 
         
         if (c == cmdBack) {
            
            new ListEntreprise(disp);
         }
         
         
         if (c == cmdMenuPricimal)
         {
         
         //Menu principal
         }
         
         
        if (c == List.SELECT_COMMAND) {
            form.append("Informations Entreprise: \n");
            form.append(showPersonne(lst.getSelectedIndex()));
            disp.setCurrent(form);
         

        }

        if (c == cmdBack) {
            form.deleteAll();
            disp.setCurrent(lst);
          new MidletMenu1(disp);
        }

        
        
    }

    public void run() {
    }
    
    public void afficherEntreprise()
    {
        
        
         try {//les threads
            // this will handle our XML
            EntrepriseHandler entrepriseHandler = new EntrepriseHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parse1/entreprise/getXmlPersons_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
           System.out.println("bonjour");
            parser.parse(dis, entrepriseHandler);
            // display the result
                         System.out.println("bonjour");

            entreprises = entrepriseHandler.getEntreprises();
           
            if (entreprises.length > 0 ) {
                for (int i = 0; i < entreprises.length; i++) {
                     //System.out.println("bonjour");
                    if(entreprises[i].getType().compareTo("e")!=-1)
                    {
                        System.out.println(entreprises[i].getId());
                    lst.append(entreprises[i].getId()+" " +entreprises[i].getDescription() , null);

                }
            }
            }
           
        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lst);
        
    }

    public void supprimerEntreprise()
    {
     
        try {
               HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parse1/terrain/supprimerTerrain.php?id="+id);
                
               DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("OK".equals(sb.toString().trim())) {                  
                    
                    lstComms.deleteAll();
                    myTicker.setString("Terrain supprimé");
                   
                    
                }else{
                    System.out.println("non");
                    //myTicker.setString("Erreur");
                }
                sb = new StringBuffer();
            } catch (IOException ex) { 
            ex.printStackTrace();
        } 
    }
    
    
       public void modifierEntreprise() throws IOException
    {
           
        
        if (entreprises.length > 0) {
            HttpConnection hc2 = (HttpConnection) Connector.open("http://localhost/parse1/entreprise/ModifierEntreprise.php" +"?id="+id+ "&description=" + tfDescription.getString().trim() + "&region=" + table[chG.getSelectedIndex()].trim() + "&prix=" + tfPrix.getString().trim() + "&surface=" + tfSurface.getString().trim() +  "&surfaceCouverte=" + tfSurfaceCouverte.getString().trim() + "&engagement="+ table1[chEngagement.getSelectedIndex()].trim());
             DataInputStream  dis2 = new DataInputStream(hc2.openDataInputStream());
            while ((ch = dis2.read()) != -1) {
                sb.append((char) ch);
            }
            if ("OK".equals(sb.toString().trim())) {
                 System.out.println("modifier 111111");
                disp.setCurrent(form);
            }
            sb = new StringBuffer("");
        }
        disp.setCurrent(f1);
     

    }   
    
    
    private String showPersonne(int i) {
        String res = "";
        if (entreprises.length > 0) {
            id=entreprises[i].getId();
            
            sb.append("*"); 
            sb.append(entreprises[i].getId());
            sb.append("\n");
            sb.append("* ");
            sb.append(entreprises[i].getDescription());
            sb.append("\n");
            sb.append("* ");
            sb.append(entreprises[i].getPrix());
            
            sb.append("\n");
            
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
}
    
    

