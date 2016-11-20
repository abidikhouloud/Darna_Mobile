/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sprintMobile;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.List;

/**
 *
 * @author ASUS
 */
public class MyCanvas2 extends Canvas implements CommandListener{
    Display disp;
int w=getWidth();
int h=getHeight();
int nbVente;
int nbLocation;
int nbTotal;
 Command cmdMenu = new Command("Accueil", Command.SCREEN, 0);
Command cmdRect = new Command("Rectangle", Command.SCREEN, 1);
Command cmdBack = new Command("Retour", Command.BACK, 0);
    public MyCanvas2(int nbVente, int nbLocation, int nbTotal,Display disp) {
        this.disp=disp;
        this.nbVente = nbVente;
        this.nbLocation = nbLocation;
        this.nbTotal = nbTotal;
        this.addCommand(cmdRect);
        this.addCommand(cmdBack);
                this.addCommand(cmdMenu);
        this.setCommandListener(this);
        w=getWidth();
         h=getHeight();
    }



   

    protected void paint(Graphics g)  {
       
        double vente=(double)nbVente/nbTotal;
 
        double location=(double)nbLocation/nbTotal;
             
        int ww=w/8;
       g.setColor(0,0,0);
       g.drawRect(0, 0, w, h);
        g.setColor(0,255,255);
         g.drawString("Vente", 40, 10,0);
         g.setColor(0,255,255);
        g.fillRect(10, 10, 20, 20);
      
     g.setColor(0,255,255);
       g.fillArc(w/2-90, h/2-75, 180,180, 0, (int) (360*vente));
     
       g.setColor(250,0,255);
        g.fillArc(w/2-90, h/2-75, 180,180, (int) (360*vente), (int) (360*location));

       
        
        
        
        
       
        
        String ventestr=Double.toString(vente*100);
        String locationstr=Double.toString(location*100);
        if(ventestr.length()>5)
        {  ventestr=ventestr.substring(0, 4);}
        if(locationstr.length()>5)
        {  locationstr=locationstr.substring(0, 4);}
        
        g.setColor(0,255,255);
        g.drawString(ventestr+" %", 10,30,0);
         g.setColor(250,0,255);
        
        g.drawString(locationstr+" %", 100,30,0);
    g.setColor(250,0,255);
        g.fillRect(100, 10, 110, 20);
           g.setColor(0,0,0);
            g.fillRect(120, 10,h, 20);
      g.setColor(250,0,255);
      g.drawString("Location", 130, 10,0);
        
    }

    public void commandAction(Command c, Displayable d) {

    if(c==cmdRect)
        
    {
    MidletStatistique ms=new MidletStatistique(disp);
    ms.rectangle();
    }
    
    if(c==cmdBack)       
    {
        
   new MidletStatistique(disp);
    }
      if(c==cmdMenu)
        {
        new MenuEventA(disp);
        }
    
    }

}
