package core;

import entities.Player;

public class Gameloop extends Thread{
	private Gamepanel panel;
	private boolean running = true;
	
	public Gameloop(Gamepanel panel) {
		this.panel = panel;
	}
	
	@Override 
	public void run() {
		while(running) {
			panel.update();
			panel.repaint();//Refresheamos todo lo que tenemos en pantalla, lo dibujamos
			try {
				Thread.sleep(16); //Setteamos a 60FPS el thread
			}catch(InterruptedException e) { //En caso de ser interrumpido, arroja un error por consola
				e.printStackTrace();
			}
		}
	}

}
