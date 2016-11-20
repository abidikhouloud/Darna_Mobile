package sprintMobile;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.List;
import javax.microedition.midlet.MIDlet;

abstract public class GoogleMapsTestCanvas extends Canvas implements CommandListener
{
	Command back= new Command("Back", Command.BACK, 1);
	Display disp;
	Displayable testListScreen;
	MIDlet midlet;
	
	public GoogleMapsTestCanvas(MIDlet m, Displayable testListScreen,Display disp)
	{
		this.midlet = m;
		this.testListScreen = testListScreen;
		
		addCommand(back );
		this.disp=disp;
		setCommandListener(this);
	}
	
	public void commandAction(Command c, Displayable d)
	{
		if(c == back)
		{
                    new MidletMenu(disp);
			//Display.getDisplay(midlet).setCurrent(testListScreen);
		}
	}
	void showError(String message)
	{
		Alert error = new Alert("Error", message, null, AlertType.ERROR);
		
		Display.getDisplay(midlet).setCurrent(error, this);
	}
}
