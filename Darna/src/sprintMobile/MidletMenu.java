
   /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sprintMobile;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;
/**
 * @author ASUS
 */
public class MidletMenu  implements CommandListener{
    Display disp;
    
    String[] tab={"Gestion Agences","Gestion Rendez vous"};
List lst = new List("Menu", List.IMPLICIT,tab,null);
Command cmdSelect = new Command("Select", Command.SCREEN, 0);

    public MidletMenu(Display disp) {
        this.disp = disp;
    }




    public void startApp() {
        lst.addCommand(cmdSelect);
        lst.setCommandListener(this);
        disp.setCurrent(lst);
    }
    
    
    
    
    public void pauseApp() {
    }
    
    public void destroyApp(boolean unconditional) {
    }

    public void commandAction(Command c, Displayable d) {
        
        
 if (c == List.SELECT_COMMAND) {
     
     
     if(lst.getSelectedIndex()==0)
     {
     new ParsingXmlAgence(disp);
     }
     if(lst.getSelectedIndex()==1)
     {
     new ParsingXmlRendezVous(disp);
     }
     
     
 }
 }
    
    }

