package input;
import java.awt.event.KeyListener;


import java.awt.event.KeyEvent;

public class Inputhandler implements KeyListener{
	public boolean w,s,a,d;
	
	
	@Override 
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W: w=true;break;
        case KeyEvent.VK_S: s = true; break;
        case KeyEvent.VK_A: a = true; break;
        case KeyEvent.VK_D: d = true; break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	
	@Override
	public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()) {
        case KeyEvent.VK_W: w = false; break;
        case KeyEvent.VK_S: s = false; break;
        case KeyEvent.VK_A: a = false; break;
        case KeyEvent.VK_D: d = false; break;
    }
		
	}

}
