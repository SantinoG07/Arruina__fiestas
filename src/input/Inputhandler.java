package input;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import core.Gamepanel;
import core.Gamestate;
import ui.Drawmenu;

import java.awt.event.KeyEvent;

public class Inputhandler implements KeyListener, MouseListener, MouseMotionListener{
	public boolean w,s,a,d,e,enter,esc;
	

    private Gamepanel game;

    public Inputhandler(Gamepanel game) {
        this.game = game;
    }
	
	
	@Override 
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W: w=true;break;
        case KeyEvent.VK_S: s = true; break;
        case KeyEvent.VK_A: a = true; break;
        case KeyEvent.VK_D: d = true; break;
        case KeyEvent.VK_E: this.e = true; break;
        case KeyEvent.VK_ENTER: enter = true; break;
        case KeyEvent.VK_ESCAPE: esc = true; break;
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
	
	
	// --- MOUSE ---
    public void mouseClicked(MouseEvent e) {
        if (game.gameState == Gamestate.MENU) {
            if (Drawmenu.btnJugar.contains(e.getPoint())) game.gameState = Gamestate.PLAYING;
            else if (Drawmenu.btnSalir.contains(e.getPoint())) System.exit(0);
        } else if (game.gameState == Gamestate.PAUSE) {
            if (Drawmenu.btnContinuar.contains(e.getPoint())) game.gameState = Gamestate.PLAYING;
            else if (Drawmenu.btnSalirMenu.contains(e.getPoint())) game.gameState = Gamestate.MENU;
        }
        game.repaint();
    }

    public void mouseMoved(MouseEvent e) {
        if (game.gameState == Gamestate.MENU) {
            Drawmenu.hoverJugar = Drawmenu.btnJugar.contains(e.getPoint());
            Drawmenu.hoverSalir = Drawmenu.btnSalir.contains(e.getPoint());
        } else if (game.gameState == Gamestate.PAUSE) {
            Drawmenu.hoverContinuar = Drawmenu.btnContinuar.contains(e.getPoint());
            Drawmenu.hoverSalirMenu = Drawmenu.btnSalirMenu.contains(e.getPoint());
        }
        game.repaint();
    }

    // Métodos vacíos obligatorios
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {}

}
