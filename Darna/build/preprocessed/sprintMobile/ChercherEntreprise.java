    package sprintMobile;

import java.io.DataInputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

    
    public class ChercherEntreprise  implements CommandListener, Runnable {



    
    
Display disp ;

String [] table = {"Tunis","Ben Arous","Mannouba","Ariana","Nabeul","Bizerte","Beja","jendouba","Kef","kasserine"
    ,"sidi Bouzid","zaghouan","sousse","Kairouan","Mahdia","Moastir","Gabes","Gafsa","Tataouin","Medenin","Seliana",
    "Gebili","sfax","tozeur"}; 
  ChoiceGroup  chG  = new ChoiceGroup("Region", ChoiceGroup.POPUP,table,null);
    Command cmdParse = new Command("Recherche", Command.SCREEN, 0);
    Command cmdParse1 = new Command("Entreprises", Command.SCREEN, 0);
    Command cmdBack = new Command("Back", Command.BACK, 0);
    Logement[] logements;
    Entreprise[] entreprises;
    List lst = new List("Logements", List.IMPLICIT);
    Form f = new Form("Accueil");
    Form f1 = new Form("recherche");
    Form form = new Form("details du Logements");
    Form loadingDialog = new Form("Attendez SVP");
    StringBuffer sb = new StringBuffer();
    
    
    
    Form form1;
Display disp1;
Command vib,exit;

    public ChercherEntreprise(Display disp) {
        this.disp = disp;
         this.startApp();
    }

   
    
    public void startApp() {
        
        f.append(chG);

        f.addCommand(cmdParse);

        f.setCommandListener(this);
        //lst.setCommandListener(this);
        f.addCommand(cmdBack);
        f1.addCommand(cmdBack);
        form.addCommand(cmdBack);
        form.setCommandListener(this);
         lst.addCommand(cmdBack);
        lst.setCommandListener(this);
        f.setCommandListener(this);
        f1.setCommandListener(this);
        form.setCommandListener(this);
        disp.setCurrent(f);
        /*f1.append(tfDescription);
         f1.append(tfDate);
         f1.addCommand(cmdValider);
         f1.setCommandListener(this);
         f2.addCommand(cmdBack);
         f2.setCommandListener(this);
         f1.append(chG);*/
        disp.setCurrent(f);
        
        
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
    
    public void commandAction(Command c, Displayable d) {

        {

                if (c == cmdParse) {
                    {
                        disp.setCurrent(loadingDialog);
                        afficherLogement();

                    }
                

            }
        }

        if (c == List.SELECT_COMMAND) {
            form.append("Informations Personne: \n");
            form.append(showPersonne(lst.getSelectedIndex()));
            disp.setCurrent(form);
         

        }

        if (c == cmdBack) {
           new MidletMenu(disp);
        }


    }

    public void run() {
    }
    
    
    public void afficherLogement(){
        try {//les threads
            // this will handle our XML
            
            LogementHandler logementHandler = new LogementHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            
            
            
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parse1/entreprise/recherentreprise.php?region="+table[chG.getSelectedIndex()]);
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, logementHandler);
            // display the result
             
            logements = logementHandler.getLogement();
           
                if (logements.length > 0 ) {
                    for (int i = 0; i < logements.length; i++) {
                    //if (logements[i].getType().compareTo("l")!=-1 && logements[i].getRegion().equals(tfRecherche.toString())){
                        {
                            lst.append(logements[i].getId()+" " +logements[i].getDescription()+" "
                            +logements[i].getDate() + logements[i].getNbrChambre()
                            , null);

                    }
                }
            }
           
        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        disp.setCurrent(lst);
        
    }

  
        
        

    
    
    private String showPersonne(int i) {
        String res = "";
        if (logements.length > 0) {
            sb.append("* "); 
            sb.append(logements[i].getId());
            sb.append("\n");
            sb.append("* ");
            sb.append(logements[i].getDescription());
            sb.append("\n");
            sb.append("* ");
            sb.append(logements[i].getDate());
            sb.append("\n");
              sb.append("* ");
            sb.append(logements[i].getNbrChambre());
            sb.append("\n");
        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
    
    
}
    
