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
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.ChoiceGroup;
//import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.Ticker;
import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VideoControl;
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.midlet.*;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

/**
 * @author amor
 */
public class Authentification extends MIDlet implements CommandListener ,Runnable{
 Display disp = Display.getDisplay(this);
 //Commandess
 Command cmdBack = new Command("Back", Command.BACK, 0);
 Command cmdNext = new Command("Next", Command.SCREEN, 0);
 Command cmdLog=new Command("Login",Command.OK,1);
 Command cmdInscri=new Command("s'inscrire",Command.OK,0);
 Command cmdExit=new Command("Exit",Command.BACK,0);
 Command cmdValider = new Command("valider", Command.SCREEN, 0);
 Command cmdModif=new Command("Modifier Compte",Command.OK,0);
 Command cmdAfficher=new Command("Modifier Compte",Command.OK,0);
 Command cmdlogout=new Command("deconnect",Command.OK,0);
     TextField email2=new TextField("Email", null, 30, TextField.EMAILADDR);
    Command passOublie=new Command("mot de passe oublié", Command.SCREEN, 0);
    Command reccuPass=new Command("Récupérer mon mot de passe", Command.SCREEN, 0);
        Command précédente=new Command("précédente", Command.BACK, 0);


//appel au midlet cruds personne
 public static Display display;
      public Affichepersonne affich;       
    
    private boolean firstTime = true;

    
    private boolean restartOnResume = false;
//     MessageConnection clientConn;

//image
 Image img_auth; 
   ImageItem imgt_auth;  
   Image[] images=new Image[3];
 
 //TextFieldsInscription
 TextField tfNom = new TextField("nom", null, 100, TextField.ANY);
 TextField tfPrenom = new TextField("prenom", null, 100, TextField.ANY);
 DateField tfDateN=new DateField("date naissance", DateField.DATE);
 TextField tfTel= new TextField("Telephone", null, 100, TextField.NUMERIC);
 TextField tfPass = new TextField("Password :", null, 100, TextField.PASSWORD);
 TextField tfMail = new TextField("Mail", "nom.prenom@esprit.tn", 100, TextField.EMAILADDR);
 TextField tfAdresse= new TextField("Adresse", null, 100, TextField.ANY);
 //TexfieldsAuthentification
 TextField tf_log_auth = new TextField("Login", null, 30, TextField.ANY);
 TextField tf_passw = new TextField("Password", null, 30, TextField.PASSWORD);
 String[] tabE = {"Afficher Mes infos", "Offre", "Rendez-vous",""};
 List l2 = new List("Espace Darna", List.IMPLICIT, tabE, null);

//Forms
Ticker Tk1 = new Ticker("Bienvenue");
Form f1 = new Form("Inscription Darna");
Form f2= new Form("Bonjour");
Form f3 = new Form("Erreur");
Form fa=new Form("Authentification");
Form f4=new Form("Espace Gerant");
Form f5=new Form("Espace Admin");
Form fmodif=new Form("Modifier mes Informations");
Form finfo = new Form("Mes Information");
Alert al=new Alert("welcome");
Form authPass=new Form("");

    Player player2;
    Player player;

  public static Personne userConnecte = Authentification.userConnecte;
   
    Alert alerta = new Alert("Error", "Sorry", null, AlertType.ERROR);
          MessageConnection clientConn; 


    //Connexion
    HttpConnection hc;
    DataInputStream dis;
    String url = "http://localhost/parsingDarna/fay/inscription.php";
    String url2 = "http://localhost/parsingDarna/fay/login.php";
    String urlr = "http://localhost/parsingDarna/fay/sendMailClient.php";
    StringBuffer sb = new StringBuffer();
    Ticker demandepwd= new Ticker("consulter votre mail pour récupérer le nouveau mot de passe");

    int ch;
    public void startApp() {
        //authentification
        fa.addCommand(cmdExit);
        fa.addCommand(cmdLog);
        fa.addCommand(cmdInscri);
        fa.addCommand(passOublie);
        fa.addCommand(reccuPass);

        fa.setCommandListener(this);
        authPass.addCommand(reccuPass);
        authPass.append(email2);
         authPass.addCommand(précédente);
        authPass.setCommandListener(this);
         try {
         img_auth =Image.createImage("/acceuil.JPG");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        imgt_auth = new ImageItem("", img_auth, ImageItem.LAYOUT_CENTER, null);
        fa.append(img_auth);
        fa.append(tf_log_auth);
        fa.append(tf_passw);
        
        //espace Darna
        //images[0]=Image.createImage("/images/rec.png");
        l2.addCommand(cmdBack);
        l2.addCommand(cmdExit);
        l2.addCommand(cmdAfficher);
        l2.setCommandListener(this);
        
        //inscription
        f1.append(tfNom);
        f1.append(tfPrenom);
        f1.append(tfDateN);
        f1.append(tfTel);
        f1.append(tfPass);
        f1.append(tfMail);
        f1.append(tfAdresse);
        f2.setTicker(Tk1);
        f1.addCommand(cmdValider);
        f1.setCommandListener(this);
        f1.addCommand(cmdBack);
        f1.setCommandListener(this);
        
        //disp.setCurrent(fa);
        disp.setCurrent(new SplashScreen(this));
    }

    
    
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
        notifyDestroyed();
    }

    public void commandAction(Command c, Displayable d) {
        if(c==passOublie)
        {disp.setCurrent(authPass);}
         if(c==précédente)
        {disp.setCurrent(fa);}
         
        if (c==reccuPass)
      {  try {
                hc = (HttpConnection) Connector.open(urlr+"?to="+email2.getString()+"&sujet=PasswordRecovery");
                System.out.println(urlr+"?to="+email2.getString()+"&sujet=PasswordRecovery");
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("ok".equals(sb.toString().trim())) {
                   System.out.println("email envoyé");
                   authPass.setTicker(demandepwd);
                    
                }else{
                   
                    System.out.println("email non envoyé");
                }
                sb = new StringBuffer();
            } 
      catch (IOException ex) {
                ex.printStackTrace();
            }
            /// send sms
       
        String mno="123456789";
                  String msg="Consulter votre boite de réception pour récupérer votre mot de passe";
                                          try {
                           clientConn=(MessageConnection)Connector.open("sms://"+mno);
                           clientConn.setMessageListener(null);
                        }
                        catch(Exception e) {
                              System.out.println("erruuuuuuuuuuur: "+e.toString());
                                         }
                        try {
                              TextMessage textmessage = (TextMessage) clientConn.newMessage(MessageConnection.TEXT_MESSAGE);
                              textmessage.setAddress("sms://"+mno);
                              textmessage.setPayloadText(msg);
                              clientConn.send(textmessage);
                          

                        }
                        catch(Exception e)
                        {
                            System.out.println(e.toString());
                        }
        
        }
        
         
        if(d==l2){
        if(c==List.SELECT_COMMAND)
        {
        if("Afficher Mes infos".equals(l2.getString(l2.getSelectedIndex())))
        {
        display=Display.getDisplay(this);
        affich=new Affichepersonne("client",userConnecte,display);
        display.setCurrent(affich);
         if (firstTime) {
            display.setCurrent(affich);
            firstTime = false;
        } else {
            

            restartOnResume = false;
        }
        
        }
        }
        
        }
        
        
         if(c==cmdExit)
        {
           
            notifyDestroyed();
        }
        if(c==cmdLog)
        { Thread t =new Thread(new Runnable() {
            public void run() {
             auth();             }
            });
         t.start();
        }
       
        if(c==cmdInscri)
        {
                   
disp.setCurrent(f1);
        
        } 
        
        if (c == cmdBack && d==f1)
        {
            
            disp.setCurrent(fa);
        }
        if (c == cmdlogout && d==l2)
        {
            
            disp.setCurrent(fa);
        }
        if (c == cmdValider) {
            try {
                Thread th = new Thread(this);
                th.start();
                
                
                player2 = Manager.createPlayer(getClass().getResourceAsStream("out/audio/welcome.wav"), "audio/x-wav");
                player2.setLoopCount(1);
                player2.start();
                //Thread.sleep(10000);//set for 5 seconds
                player.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (MediaException ex) {
                ex.printStackTrace();
            }
                    
} 

            
            
        
        userConnecte=user;
        
        
        
        
        
        
        
        

    }

    public void run() {
        
         try {
             Calendar cal= Calendar.getInstance();
    cal.setTime(tfDateN.getDate());
    String date = cal.get(Calendar.YEAR) + "-" + ( cal.get(Calendar.MONTH) + 1 ) + "-" + cal.get(Calendar.DAY_OF_MONTH);
             
     hc = (HttpConnection) Connector.open(url+"?nom="+tfNom.getString().trim()+"&prenom="+tfPrenom.getString().trim()+"&datenaiss="+date+"&tel="+tfTel.getString().trim()+"&password="+tfPass.getString().trim()+"&email="+tfMail.getString().trim()+"&adresse="+tfAdresse.getString().trim());
                dis = new DataInputStream(hc.openDataInputStream());
                while ((ch = dis.read()) != -1) {
                    sb.append((char)ch);
                }
                if ("OK".equals(sb.toString().trim())) {
                   disp.setCurrent(al,fa);
                    
                }else{
                    disp.setCurrent(f3);
                }
                sb = new StringBuffer();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        
        
        
        
        
        
    }
    
            Personne[] users;
         Personne user; 
    

            public void auth(){
             
                try {
                       PersonneHandler userHand = new PersonneHandler();
                        SAXParser parser = SAXParserFactory.newInstance().newSAXParser();
                         hc = (HttpConnection) Connector.open(url2+"?nom="+tf_log_auth.getString().trim()+"&password="+tf_passw.getString().trim());
                         dis = new DataInputStream(hc.openDataInputStream());
                        parser.parse(dis, userHand);
                        users = userHand.getUser();
                        System.out.println(users[0]);
                        if (users.length != 0) {
                            user = users[0];
                            
                           if("c".equals(users[0].getType())){
                            Ticker tk=new Ticker("Bienvenue"+" "+user.getNom()); 
                           
                            
                           new MenuEventC(disp,tk,this);
                           
                           
                            
                           }else if("g".equals(users[0].getType())){
                            Ticker tk=new Ticker("Bienvenue"+" "+user.getNom()); 
                            f4.setTicker(tk);
                            new MenuEventg(disp);
                           }else if("a".equals(users[0].getType())){
                            Ticker tk=new Ticker("Bienvenue"+" "+user.getNom()); 
                            f5.setTicker(tk);
                             new MenuEventA(disp);     
                            
                            //disp.setCurrent(f5);
                                

     
                      
                           }
                            
                userConnecte=user;
                
                  

            
            
                        } else {
                            Alert al = new Alert("ERROR", "verifiez vos données", null, AlertType.INFO);
                            disp.setCurrent(al);
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        disp.setCurrent(new Alert("Error", "Impossible d'etablir la connection", null, AlertType.ERROR));
                    }
    
    }
            public static Display getDisplay(){
            return display;
        }

    
            
public class SplashScreen extends Canvas implements Runnable {
    private Image mImage;
    private Authentification midletAu;
  
    public SplashScreen(Authentification midletAu){
        this.midletAu = midletAu;
        try{
        mImage = Image.createImage("/images/splash.jpg");
        Thread t = new Thread(this);
        t.start();
        
        
     
                
             
                    
        
        
        }
         catch (IOException ex) {
                ex.printStackTrace();
            } 
      
    }
    /**
     * Paints the image centered on the screen.
     */
    public void paint(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        //set background color to overdraw what ever was previously displayed
        g.setColor(0x000000);
        g.fillRect(0,0, width, height);
        g.drawImage(mImage, width / 2, height / 2,
                Graphics.HCENTER | Graphics.VCENTER);
    }
    /**
     * Dismisses the splash screen with a key press or a pointer press
     */
    public void dismiss() {
        if (isShown())
            Display.getDisplay(midletAu).setCurrent(fa);

    }
    /**
     * Default timeout with thread
     */
    public void run() {
        try {
            
             player2 = Manager.createPlayer(getClass().getResourceAsStream("/audio/pub.wav"), "audio/x-wav");
                player2.setLoopCount(1);
                Thread.sleep(5000);//set for 3 seconds
                              dismiss(); 

                player2.start();
                //Thread.sleep(10000);//set for 5 seconds
                player.close();
                
               
            
        }
        
        
        catch (InterruptedException e) {
            System.out.println("InterruptedException");
            e.printStackTrace();
        }
           catch (IOException ex) {
                ex.printStackTrace();
            } catch (MediaException ex) {
                ex.printStackTrace();
            }  
        
           
                
               
                
                       
     
       
    }

}

//    public Authentification(Display disp) {
//    this.disp=disp;
//    this.startApp();
//    }

}
