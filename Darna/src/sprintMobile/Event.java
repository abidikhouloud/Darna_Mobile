package sprintMobile;
import java.io.IOException;
import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;
import sprintMobile.MenuEventg;

import javax.microedition.midlet.*;
import javax.microedition.midlet.MIDlet;



public class Event   {

    Display disp ;
 
    Alert sp = new Alert("Welcome");
    public static MIDlet midlet;
    
    
    //public Event() {}
    
   Event(Display disp) {
        this.disp=disp;
        this.startApp();

    }

   

    public void startApp() {
  

 MenuEventg m=new MenuEventg( disp);
    }

    public void pauseApp() {
    }

    public void destroyApp(boolean unconditional) {
    }
    
    
}



    
   
    
