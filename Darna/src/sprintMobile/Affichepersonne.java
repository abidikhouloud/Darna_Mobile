package sprintMobile;

import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 *
 * @author sarah
 */
public class Affichepersonne  extends Form implements CommandListener,Runnable{
    int update=0;
    int delete=0;
    int Index;
    int ch;
     
      private Display display;

    
    private boolean firstTime = true;

    
    private boolean restartOnResume = false;
    TextField tfPrenom = new TextField("prenom", null, 100, TextField.ANY);
    TextField tfNom = new TextField("nom", null, 100, TextField.ANY);
    DateField tfDateN = new DateField("date naissancen", DateField.DATE);
    TextField tfTel = new TextField("Telephone", null, 100, TextField.NUMERIC);
    TextField tfPass = new TextField("Password :", null, 100, TextField.PASSWORD);
    TextField tfMail = new TextField("Mail", "nom.prenom@esprit.tn", 100, TextField.EMAILADDR);
 TextField tfAdresse= new TextField("Adresse", null, 100, TextField.ANY);
    
    
    Command cmdParse = new Command("client", Command.SCREEN, 0);
    Command cmdBack =  new Command("Back"    , Command.BACK  , 0);
        Command cmdBack2 =  new Command("Back"    , Command.BACK  , 0);

    Command cmdAjout =  new Command("ajouter"  , Command.OK  , 0);
    Command cmdSupp = new Command("supprimer", Command.SCREEN, 0);
    Command cmdList = new Command("Liste des clients", Command.SCREEN, 0);
    Command cmdUpdate = new Command("Modifier", Command.SCREEN, 0);
    Command cmdConfModif = new Command("confirmer Modification", Command.SCREEN, 0);



    Client[] clients;
    List lst = new List("client", List.IMPLICIT);
    Form f = new Form("Accueil");
    Form info = new Form("Infos clients");
    Form loadingDialog = new Form("Please Wait");
    StringBuffer sb = new StringBuffer();
    StringBuffer sb1 = new StringBuffer();
    Form f3 = new Form("Erreur");
    Form f2 = new Form("supprimee");
    Form f4 = new Form("modifiee");
    Form modif = new Form("Modifier client");
Alert alm=new Alert("Modifié");

   Alert alsup=new Alert("supprimé");


   

    Personne userConnecte = Authentification.userConnecte;
    
    public Affichepersonne(String title,Personne userConnecte, Display display) {
        super(title);
        this.userConnecte=userConnecte;
        this.addCommand(cmdParse);
        this.setCommandListener(this);
       this.display=display;
        this.startApp();
        
        info.addCommand(cmdList);
        info.addCommand(cmdSupp);
        info.addCommand(cmdUpdate);
        info.setCommandListener(this);

        f2.addCommand(cmdList);
        f2.setCommandListener(this);
        
        modif.append(tfNom);
        modif.append(tfPrenom);
        //modif.append(tfAdresse);
        //modif.append(tfMail);
       // modif.append(tfTel);
       // modif.append(tfDateN);
        //modif.append(tfPass);
        modif.addCommand(cmdConfModif);
        modif.setCommandListener(this);
        lst.addCommand(cmdBack2);
       lst.setCommandListener(this);
        
       
    }

   
        public void pauseApp() {
         //----------
       
        
    }

    public void commandAction(Command c, Displayable d) {
          if (c == cmdParse) {
            MenuEventA.getDisplay().setCurrent(loadingDialog);
            Thread th = new Thread(this);
            th.start();
         }
        if(c==cmdBack2)
        {
       new MenuEventA(display);
        }
           
         if(c==cmdSupp)
             
        {
            Index=lst.getSelectedIndex();            
            delete=1;
            Thread th = new Thread(this);
            th.start();
        }
        
        if (c == List.SELECT_COMMAND) {
            info.append("Informations client: \n");
            info.append(showPersonne(lst.getSelectedIndex()));
            MenuEventA.getDisplay().setCurrent(info);
        }

        if (c == cmdBack) {
            info.deleteAll();
            MenuEventA.getDisplay().setCurrent(lst);
        }   
         if (c == cmdList) {
            MenuEventA.getDisplay().setCurrent(lst);        
                           }
        
         if(c== cmdUpdate){
           
          
           tfNom.setString(clients[lst.getSelectedIndex()].getNom());
           tfPrenom.setString(clients[lst.getSelectedIndex()].getPrenom());
            
           
           
           Index=lst.getSelectedIndex();
                      MenuEventA.getDisplay().setCurrent(modif);


           
            
        }
         if (c== cmdConfModif){
             update=1;
            Thread th = new Thread(this);
            th.start();
         }
        
        
        }

    public void run() {
          if(delete==0){  
              if(update==0){
    
         try {
            // this will handle our XML
            // c'est une classe qui utilise le parseur pour desassembler le flus recue
            //sax qui est importer est un parseur xml
            ClientHandler clientsHandler = new ClientHandler();
            // get a parser object
            SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
            // get an InputStream from somewhere (could be HttpConnection, for example)
            HttpConnection hc = (HttpConnection) Connector.open("http://localhost/parsingDarna/fay/getXmlPersons_Attributes1.php");
            DataInputStream dis = new DataInputStream(hc.openDataInputStream());
            parser.parse(dis, clientsHandler);
            // display the result
            clients = clientsHandler.getClients();

            if (clients.length > 0) {
                for (int i = 0; i < clients.length; i++) {
                       lst.append(clients[i].getNom()+" "
                             +clients[i].getPrenom()+" "+clients , null) ;                     


                }
            }
                        


        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
        MenuEventA.getDisplay().setCurrent(lst);
    }
     if(update==1){
            try {
            
           
                HttpConnection hc = (HttpConnection)Connector.open("http://localhost/parsingDarna/fay/modifier.php?nom="+tfNom.getString().trim()+"&prenom="+tfPrenom.getString().trim()+"&id="+clients[Index].getId());
                DataInputStream dis = new DataInputStream(hc.openDataInputStream());
                
                while ((ch = dis.read()) != -1) {
                    sb1.append((char)ch);
                }
                
                if ("OK".equals(sb1.toString().trim())) {
                   
                    
                    MenuEventA.getDisplay().setCurrent(alm,info);
                }else{
                    MenuEventA.getDisplay().setCurrent(f3);
              
                }
                sb1 = new StringBuffer();
                
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
     }
           
            if(delete==1){
                try {
            //System.out.println("http://localhost/taxij2me/supprimer.php"+"?id="+personnes[Index].getId());
            HttpConnection    hc = (HttpConnection)Connector.open("http://localhost/parsingDarna/fay/supprimer.php"+"?id="+clients[Index].getId());
            DataInputStream   dis = new DataInputStream(hc.openDataInputStream());
                
                while ((ch = dis.read()) != -1) {
                    sb1.append((char)ch);
                }
                
                if ("OK".equals(sb1.toString().trim())) {
                    MenuEventA.getDisplay().setCurrent(alsup,lst);
                    //Midlet.getDisplay().setCurrent(lst);//redirection pour affichage liste
                    System.out.println("client supprime");
                }else{
                    MenuEventA.getDisplay().setCurrent(f3);
                    System.out.println("erreur");
                }
                sb1 = new StringBuffer();
                delete=0;
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            }
        
        
        
    }
        
    
  
    
    
    
    

    
  private String showPersonne(int i) {
       String res = "";
        if (clients.length > 0) {
            sb.append("* ");
            sb.append(clients[i].getId());
            sb.append("* ");
            sb.append(clients[i].getNom());
            sb.append("\n");
            sb.append("\n");
            sb.append("* ");
            sb.append(clients[i].getPrenom());
            sb.append("\n");

           

        }
        res = sb.toString();
        sb = new StringBuffer("");
        return res;
    }
  
  
          public String suppmedicament(int i) {
        String id_supp = "";
        if (clients.length > 0) {
            sb.append("*Id : ");
            sb.append(clients[i].getId());
            
        }
        id_supp = sb.toString();
        sb = new StringBuffer("");
        return id_supp;
    }

    private void startApp() {

        
       
        display.setCurrent(lst);

    
    
    }
}
