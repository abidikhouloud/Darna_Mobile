/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sprintMobile;

//import com.jappit.midmaps.test.GoogleMapsTestForm;
//import com.jappit.midmaps.test.GoogleMapsZoomCanvas;
import java.io.DataInputStream;
import java.io.IOException;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

/**
 * @author mo3
 */
public class DynamicMapMidlet1 extends MIDlet implements CommandListener{
    
    Display disp = Display.getDisplay(this);
    
    TextField TFDepart=new TextField("Lieu de depart",null,40,TextField.ANY);
    TextField TFArrivee=new TextField("Lieu d'arrivee","chine",40,TextField.ANY);
    Command cmdAffiche = new Command("Affiche", Command.SCREEN, 0);
    Form form = new Form("Dynamic map");
    
    StringBuffer sb = new StringBuffer("");
    HttpConnection hc;
    DataInputStream ds;
    
    StringBuffer sb2 = new StringBuffer("");
    HttpConnection hc2;
    DataInputStream ds2;
    
    String urlLat1 = "http://127.0.0.1/parse1/map/mapLat1.php";
    String reponse1;
    
    String urlLong1 = "http://127.0.0.1/parse1/map/mapLong1.php";
    String reponse2;
    int ch;
    int ch2;
    int ch3;
    int ch4;
    
    StringBuffer sb3 = new StringBuffer("");
    HttpConnection hc3;
    DataInputStream ds3;
    
    StringBuffer sb4 = new StringBuffer("");
    HttpConnection hc4;
    DataInputStream ds4;
    
    String reponse3;
    String reponse4;

    public void startApp() {
        
        form.addCommand(cmdAffiche);
        form.setCommandListener(this);
        form.append(TFDepart);
        
        disp.setCurrent(form); 
    }
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command cmnd, Displayable dsplbl) {
        
        if (cmnd == cmdAffiche) {
            runMap();
            runMap2();
            runMap3();
            runMap4();
            form.deleteAll();

            /////////////
            int r = 6371;
            float p=(float) 40030.17;
            float a=(float) 111.16;
            double distance =0;
            double tarif=1050;
            distance= (double) (Math.sqrt((Double.parseDouble(reponse2)-Double.parseDouble(reponse4))*(Double.parseDouble(reponse2)-Double.parseDouble(reponse4)))+((Double.parseDouble(reponse1)-Double.parseDouble(reponse3))*(Double.parseDouble(reponse1)-Double.parseDouble(reponse3))) * a);
            double cout=tarif*distance;
            /////////////
            
            //Alert alert = new Alert(cout+"");
            //disp.setCurrent(alert);
            
            disp.setCurrent(new GoogleMapsZoomCanvas(this, dsplbl, Double.parseDouble(reponse1), Double.parseDouble(reponse2), Double.parseDouble(reponse3), Double.parseDouble(reponse4),disp));
        
        }
    }

    public void runMap() {
        
        try {
            
            hc =(HttpConnection) Connector.open(urlLat1+"?adresse="+TFDepart.getString());
            ds = new DataInputStream(hc.openDataInputStream());
            int ch;
            
            while ((ch = ds.read()) != -1) {                
                sb.append((char)ch);
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
         reponse1 = sb.toString();
      
        
    }
    
    
    public void runMap2(){
        
        try {
            
            hc2 =(HttpConnection) Connector.open(urlLong1+"?adresse="+TFDepart.getString());
            ds2 = new DataInputStream(hc2.openDataInputStream());
            int ch2;
            
            while ((ch2 = ds2.read()) != -1) {                
                sb2.append((char)ch2);
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
         reponse2 = sb2.toString();
         
    }
    
    public void runMap3(){
        
        try {
            
            hc3 =(HttpConnection) Connector.open(urlLat1+"?adresse="+TFArrivee.getString());
            ds3 = new DataInputStream(hc3.openDataInputStream());
            int ch3;
            
            while ((ch3 = ds3.read()) != -1) {                
                sb3.append((char)ch3);
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
         reponse3 = sb3.toString();
         
    }
    
    
    public void runMap4(){
        
        try {
            
            hc4 =(HttpConnection) Connector.open(urlLong1+"?adresse="+TFArrivee.getString());
            ds4 = new DataInputStream(hc4.openDataInputStream());
            int ch4;
            
            while ((ch4 = ds4.read()) != -1) {                
                sb4.append((char)ch4);
            }
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
         reponse4 = sb4.toString();
         
    }
}
