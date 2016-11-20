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


public class MenuEventC implements CommandListener, IFormListener {

//    static Utilisateur clientConnecté = null;
    Form authentifForm = new Form("authentification");
    TextField pseudo = new TextField("pseudo", null, 30, TextField.ANY);
    TextField motDePasse = new TextField("mot de passe", null, 30, TextField.PASSWORD);
    Command cmOk = new Command("valider", Command.SCREEN, 0);
        Command cmdexit = new Command("deconnect", Command.EXIT, 0);

    Command insciption = new Command("S'inscrire", Command.BACK, 0);
    private Display display;

      public static Personne userConnecte = Authentification.userConnecte;

    
     private Ticker tk;

    public MenuEventC(Display display) {
         this.display = display;
         menuList.setTitle("Espace Client");
    
        menuList.addItem(proposer);
        menuList.setTicker(tk);
        menuList.addItem(sinscrir);
        //menuList.addItem(recherche);
        menuList.addItem(stat);
        //menuList.addItem(photos);
        menuList.addItem(transporteur);
        
        menuList.addItem(offres);
        
         menuList.addItem(exit);
       menuList.addCommand(cmOk);
        menuList.addCommand(cmdexit);

        menuList.setCommandListener(this);
        menuList.addPopperListener(this);
        this.display.setCurrent(menuList);
        display.setCurrent( menuList);
        
    }

    
  
    IFormCanvas menuList = new IFormCanvas(3, 3);
    TextButton proposer = new TextButton("Agence", "/icons/agence.png");
    TextButton sinscrir = new TextButton("Alerte", "/icons/alerte.png");
        TextButton Reclamation = new TextButton("Reclamation", "/icons/reclamation.png");
    TextButton offres = new TextButton("Offres", "/icons/offre.png");
    TextButton transporteur = new TextButton("Transporteur","/icons/transporteur.png");
     TextButton stat = new TextButton("Statistique","/icons/statistique.png");
     TextButton localisation = new TextButton("Localisation","/icons/map.png");
Authentification a;
    TextButton exit = new TextButton("Rendez Vous", "/icons/rendezVous.png");
    public MenuEventC( Display display,Ticker tk,Authentification a) {

         this.display = display;
     this.a=a;
        menuList.setTitle("Espace Client");
    
        menuList.addItem(proposer);
        menuList.setTicker(tk);
        menuList.addItem(sinscrir);
        //menuList.addItem(recherche);
        menuList.addItem(stat);
        //menuList.addItem(photos);
        menuList.addItem(transporteur);
          menuList.addItem(Reclamation);
        menuList.addItem(offres);
                menuList.addItem(localisation);

         menuList.addItem(exit);
       // menuList.addItem(exit);
        menuList.setCommandListener(this);
        menuList.addPopperListener(this);
        this.display.setCurrent(menuList);
      
    }
    
   


    public void commandAction(Command c, Displayable d) {
        if(c==cmdexit)
        {
       a.startApp();
        }
    }

    public void selected(ImageButton ib) {

    }

  
public void clicked(ImageButton ib) {
        if (ib == proposer) {
      new ParsingXmlAgence(display);
        }
        
         if (ib == sinscrir) {
         new MidletParsingAlerte1(display);
        }
           
           
            if (ib == stat) {
            new MidletStatistique(display);
            }
            
            if (ib == transporteur) {
      
                new AfficherTransporteur(display);
                
    }
          if (ib == Reclamation) {
      
                new AfficherReclamation(display);
                
    }
            
           if (ib == exit) {
     new ParsingXmlRendezVous(display);
    }
           
               
           if (ib == localisation) {
     new DynamicMap(display);
     
     
    }
        if (ib == offres)
        {
         new MidletMenu(display);
        }
           
           
    }}
           
        
           
        
         
       
    
    
   
