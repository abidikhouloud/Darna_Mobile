  package sprintMobile;

import java.util.Calendar;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;
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
import javax.microedition.lcdui.Ticker;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.microedition.lcdui.DateField;
/**
 * @author Khalil
 */

  
  
public class ListTerrain implements CommandListener, Runnable{
  


   
Display disp;

    Ticker myTicker = new Ticker("");
    Command cmdParse = new Command("Terrains", Command.SCREEN, 0);
    Command cmdParse1 = new Command("Terrain", Command.SCREEN, 0);
    Command cmdParse2 = new Command("Supprimer", Command.SCREEN, 0);
      Command cmdParse3 = new Command("Modifier", Command.SCREEN, 0);
      Command cmdParse4 = new Command("valider", Command.SCREEN, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
    Logement[] logements;
    Entreprise[] entreprises;
    Terrain[] terrains;
    
    
    Command cmdParseAjout = new Command("Ajouter Logement", Command.SCREEN, 0);
        Command cmdRecherche = new Command("Chercher Logement", Command.SCREEN, 0);
       Command cmdMenuPricimal = new Command("Menu Principal", Command.SCREEN, 0);
    
    
    List lst = new List("Terrains", List.IMPLICIT);
    Form f = new Form("Accueil");
    Form form = new Form("details du Terrains");
    Form loadingDialog = new Form("Attendez SVP");
    StringBuffer sb = new StringBuffer();
     List lstComms = new List("Terrains", List.IMPLICIT);
    
      int id;
      int ch;
      
      String description;
      String vocation;
      String engagement;
      double surface;
      double prix;
      String region;
      
    Form form1;
    Display disp1;
    Command vib, exit;

    
    
    
    
    
     Form f1 = new Form("Modifier Terrain");
    TextField tfDescription = new TextField("Description", null, 100, TextField.ANY);
    DateField tfDate = new DateField("Date",DateField.DATE );
    String [] table = {"Tunis","Ben Arous","Mannouba","Ariana","Nabeul","Bizerte","Beja","jendouba","Kef","kasserine"
    ,"sidi Bouzid","zaghouan","sousse","Kairouan","Mahdia","Moastir","Gabes","Gafsa","Tataouin","Medenin","Seliana",
    "Gebili","sfax","tozeur"}; 
    String[] table1 = {"Location","Vente"};
    ChoiceGroup  chG  = new ChoiceGroup("engagement", ChoiceGroup.POPUP,table,null);
      ChoiceGroup  chEngagement  = new ChoiceGroup("engagement", ChoiceGroup.POPUP,table1,null);
      TextField tfPrix = new TextField("Prix", null, 100, TextField.ANY);
      TextField tfSurface = new TextField("surface", null, 100, TextField.ANY);
      TextField tfVocation = new TextField("vocation ", null, 100, TextField.ANY);  
     Command cmdValider = new Command("valider", Command.SCREEN, 0);

     
     
     
     
     
    public ListTerrain(Display disp) {
        this.disp = disp;
         this.startApp();
    }
    
    
  
    
    public void startApp() {
       
        f.append("List Terrains");

        f.addCommand(cmdParse);
        form.addCommand(cmdParse2);
         form.addCommand(cmdParse3);
        f.setCommandListener(this);
      
        lst.setCommandListener(this);
        f.addCommand(cmdBack);
        f.setCommandListener(this);
        form.setCommandListener(this);
        
       f1.append(tfDescription);
       // f1.append(tfDate);
        f1.append(tfPrix);
         f1.append(chG);
        f1.append(tfSurface);
       
        f1.append(tfVocation);
        f1.append(chEngagement);
        f1.addCommand(cmdValider);
       
        f1.setCommandListener(this);
       
        
        
        disp.setCurrent(f);
          /*f1.append(tfDescription);
        f1.append(tfDate);
        f1.addCommand(cmdValider);
        f1.setCommandListener(this);
        f2.addCommand(cmdBack);
        f2.setCommandListener(this);
        f1.append(chG);*/
        
         form.setCommandListener(this);
        form.addCommand(cmdBack);
           form.addCommand(cmdParse3);
               form.addCommand(cmdParse2);
               f.addCommand(cmdParseAjout);
               f.addCommand(cmdRecherche);
               
               f1.addCommand(cmdMenuPricimal);
        f1.addCommand(cmdParse);
        lst.addCommand(cmdBack);
        f.addCommand(cmdBack);
         f1.addCommand(cmdBack);

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
            afficherTerrain();
        }

        if(c == cmdParse2){
            System.out.println("test");
       
        supprimerTerrain();
         disp.setCurrent(loadingDialog);
            afficherTerrain();
    }
        
          if(c == cmdParse3){
            System.out.println("test");
       
           
              form.deleteAll();
             disp.setCurrent(f1);;
            
            try {
                modifierTerrain();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    
          }
          if(c== cmdRecherche)
         {
         new ChercherTerrain(disp);
         }
            if (c == cmdBack) {
            
            new MidletMenu1(disp);
         }
              if(c == cmdParseAjout){
         
             new AjouterTerrain(disp);
             
         }
        
          
          if (c == cmdValider) {
            try {
                modifierTerrain();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
           

        }
        
        if (c == List.SELECT_COMMAND) {
            form.append("Informations Personne: \n");
            form.append(showPersonne(lst.getSelectedIndex()));
            disp.setCurrent(form);
         

        }

       


    }

    public void run() {
        
    }
    public void afficherTerrain()
    {
        
        try {//les threads
            // this will handle our XML
            TerrainHandler terrainHandler = new TerrainHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parse1/terrain/getXmlPersons_Attributes.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
          System.out.println("bonjour");
            parser.parse(dis, terrainHandler);
            // display the result
              
            terrains = terrainHandler.getTerrains();
            System.out.println(terrains[0].getRegion());
            if (terrains.length > 0 ) {
                for (int i = 0; i < terrains.length; i++) {
            
                    lst.append(terrains[i].getId()+" " +terrains[i].getDescription()+" "
                            +terrains[i].getDate() + terrains[i].getRegion() +
                            +terrains[i].getPrix()+ terrains[i].getSurface()
                            +terrains[i].getEngagement() 
                            + terrains[i].getVocation()
                            , null);

                    
                }
            }
           
        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lst);
        
    }
       
    
    public void supprimerTerrain()
    {
        try {
               HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parse1/terrain/supprimerTerrain.php"+"?id="+id);
                
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
        
    public void modifierTerrain() throws IOException
    {
           
        
        String url="http://localhost/parse1/terrain/ModifierTerrain.php" ;
        if (terrains.length > 0) {
            HttpConnection hc2 = (HttpConnection) Connector.open("http://localhost/parse1/terrain/ModifierTerrain.php" +"?id="+id+ "&description=" + tfDescription.getString().trim() + "&region=" + table[chG.getSelectedIndex()].trim() + "&prix=" + tfPrix.getString().trim() + "&surface=" + tfSurface.getString().trim() +  "&vocation=" + tfVocation.getString().trim() + "&engagement="+ table1[chEngagement.getSelectedIndex()].trim());
  
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
        if (terrains.length > 0) {
            id=terrains[i].getId();
            sb.append("* "); 
            sb.append(terrains[i].getId());
            sb.append("\n");
            sb.append("* ");
            
            sb.append(terrains[i].getDescription());
            sb.append("\n");
            sb.append("* ");
            sb.append(terrains[i].getDate());
            sb.append("\n");
              sb.append("* ");
            sb.append(terrains[i].getSurface());
            sb.append("\n");
            sb.append("* ");
            sb.append(terrains[i].getEngagement());
            sb.append("\n");
            sb.append("* ");
            sb.append(terrains[i].getPrix());
            sb.append("\n");
            sb.append("* ");
            sb.append(terrains[i].getRegion());
            sb.append("\n");

            tfDescription.setString(terrains[i].getDescription());
            tfPrix.setString(Double.toString(terrains[i].getPrix()));
            tfSurface.setString(Double.toString(terrains[i].getSurface()));
            tfVocation.setString(terrains[i].getVocation());
            
            
            
            }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
    
    
}


