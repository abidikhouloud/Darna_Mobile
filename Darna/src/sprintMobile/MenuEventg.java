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


public class MenuEventg implements CommandListener, IFormListener {

//    static Utilisateur clientConnecté = null;
    Form authentifForm = new Form("authentification");
    TextField pseudo = new TextField("pseudo", null, 30, TextField.ANY);
    TextField motDePasse = new TextField("mot de passe", null, 30, TextField.PASSWORD);
    Command cmOk = new Command("valider", Command.SCREEN, 0);
    Command insciption = new Command("S'inscrire", Command.BACK, 0);
    Command exit=new Command("exit", Command.SCREEN, 0);
    private Display display;

      public static Personne userConnecte = Authentification.userConnecte;

    
     private Ticker tk;

    
  
    IFormCanvas menuList = new IFormCanvas(3, 3);
    TextButton photos = new TextButton("Facture","/icons/facture.png");
    TextButton offres = new TextButton("Offres","/icons/offre.png");
    
    public MenuEventg( Display display) {

          this.display = display;
     
        menuList.setTitle("Esapace Gerant ");
    
       
        
        menuList.addItem(photos);
      menuList.addItem(offres);
      //
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

  
public void clicked(ImageButton ib) {
      
      
            if (ib == photos) {
                new MidletParsingXmlFacture1(display); 
            }
                       if(ib == offres){
                       new MidletMenu1(display);
                       }
 
            
          
   
           
           
    }}
           
        
           
        
         
       
    
    
   
