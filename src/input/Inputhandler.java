package input;
import java.awt.event.KeyListener;


import java.awt.event.KeyEvent;

public class Inputhandler implements KeyListener{
	public boolean w,s,a,d,e,enter,esc;
	
	
	@Override 
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W: w=true;break;
        case KeyEvent.VK_S: s = true; break;
        case KeyEvent.VK_A: a = true; break;
        case KeyEvent.VK_D: d = true; break;
        case KeyEvent.VK_E: this.e = true;
        case KeyEvent.VK_ENTER: enter = true;
        case KeyEvent.VK_ESCAPE: esc = true;
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
        case KeyEvent.VK_E: this.e = false; break;
        case KeyEvent.VK_ENTER: enter = false; break;
        case KeyEvent.VK_ESCAPE: esc = false; break;
    }
		
	}

}
