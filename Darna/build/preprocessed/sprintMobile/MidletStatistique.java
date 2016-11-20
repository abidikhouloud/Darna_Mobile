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
import javax.microedition.midlet.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.microedition.lcdui.*;
/**
 * @author ASUS
 */
public class MidletStatistique  implements CommandListener{
    Display disp ;
    
    int nbLocation=0;
    int nbVente=0;
    Offre[] alertes;
    Command cmdRectangle = new Command("Rectangle", Command.SCREEN, 1);
    Command cmdCercle = new Command("Cercle", Command.SCREEN, 1);
    private Form form =new Form("Statistique");
Image img;
 Command cmdMenu = new Command("Accueil", Command.SCREEN, 0);
    public MidletStatistique(Display disp) {
        try {
            this.img = Image.createImage("images/stat.png");
            this.disp=disp;
            this.startApp();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void startApp() {
        form.addCommand(cmdMenu);
        form.addCommand(cmdCercle);
        form.addCommand(cmdRectangle);
        form.append(new ImageItem(null, img, ImageItem.LAYOUT_CENTER, null));
        form.setCommandListener(this);
        disp.setCurrent(form);
               
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
if(c==cmdRectangle)
{
  rectangle();

}
if(c==cmdCercle)
{cercle();}


     if(c==cmdMenu)
        {
        new MenuEventA(disp);
        }
    
    }
    
    public void rectangle()
    {
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
            alertes = personnesHandler.getOffre();

            if (alertes.length > 0) {
                
                for (int i = 0; i < alertes.length; i++) {
                     
                   if (alertes[i].getEngagement().trim().equals("vente"))
                   {
                      
                   nbVente++;
                   }
                   else
                   {nbLocation++;}
                }
                 
            }
            

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
       
         MyCanvas1 canv=new MyCanvas1(nbVente,nbLocation,alertes.length,disp);
              canv.repaint();
              disp.setCurrent(canv);
    
    }
    
    
     public void cercle()
    {
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
            alertes = personnesHandler.getOffre();

            if (alertes.length > 0) {
                
                for (int i = 0; i < alertes.length; i++) {
                     
                   if (alertes[i].getEngagement().trim().equals("vente"))
                   {
                       
                   nbVente++;
                   }
                   else
                   {nbLocation++;}
                }
                 
            }
            

        } catch (Exception e) {
            System.out.println("Exception:" + e.toString());
        }
       
             MyCanvas2 canv=new MyCanvas2(nbVente,nbLocation,alertes.length,disp);
              canv.repaint();
              disp.setCurrent(canv);
    
    }

   
    
    
}
