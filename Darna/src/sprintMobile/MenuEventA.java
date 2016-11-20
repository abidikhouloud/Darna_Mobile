package sprintMobile;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import net.sourceforge.mewt.button.ImageButton;
import net.sourceforge.mewt.button.TextButton;
import net.sourceforge.mewt.iform.IFormCanvas;
import net.sourceforge.mewt.iform.IFormListener;


public class MenuEventA implements CommandListener, IFormListener {

//    static Utilisateur clientConnecté = null;
    Form authentifForm = new Form("authentification");
    TextField pseudo = new TextField("pseudo", null, 30, TextField.ANY);
    TextField motDePasse = new TextField("mot de passe", null, 30, TextField.PASSWORD);
    Command cmOk = new Command("valider", Command.SCREEN, 0);
    Command insciption = new Command("S'inscrire", Command.BACK, 0);
   // private Display display;
 public static Display display;

      public static Personne userConnecte = Authentification.userConnecte;

    
     private Ticker tk;

     public Affichepersonne affich;       
    
    private boolean firstTime = true;

    
    private boolean restartOnResume = false;
  
    IFormCanvas menuList = new IFormCanvas(3, 3);
    TextButton recherche = new TextButton("personnes","/icons/personne.png");
     TextButton stat = new TextButton("Statistique","/icons/statistique.png");

    public MenuEventA( Display display) {

          this.display = display;
     
        menuList.setTitle("Espace Admin");
    
      
        
        menuList.addItem(recherche);
        menuList.addItem(stat);
        
       // menuList.addItem(exit);
        menuList.setCommandListener(this);
        menuList.addPopperListener(this);
        this.display.setCurrent(menuList);
        display.setCurrent( menuList);
    }
    
   


    public void commandAction(Command c, Displayable d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void selected(ImageButton ib) {

    }

  public static Display getDisplay(){
            return display;
        }
public void clicked(ImageButton ib) {
//        if (ib == proposer) {
//      new ParsingXmlAgence(display);
//        }
        
      
           if (ib == recherche) {
         // new Affichepersonne("client",userConnecte,display);
                affich=new Affichepersonne("client",userConnecte,display);
        display.setCurrent(affich);
         if (firstTime) {
            display.setCurrent(affich);
            firstTime = false;
        } else {
            

            restartOnResume = false;
        }
        
           }
           
            if (ib == stat) {
            new MidletStatistique(display);
            }
            
          
           
           
    }}
           
        
           
        
         
       
    
    
   
