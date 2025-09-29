package core;

import javax.swing.JPanel;

import entities.Player;
import input.Inputhandler;

import java.awt.Graphics;
import java.awt.Color;

public class Gamepanel extends JPanel {
	
	private Player player;
	private Inputhandler input;
	
	public Gamepanel() {
		input = new Inputhandler();
		this.addKeyListener(input);
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		
		player = new Player(100, 100, input);
	}
	

    public void update() {
        player.actualizar(); // Actualizamos jugador
    }
    @Override	//Indica que esta sobreescribiendo un metodo de la libreria
    protected void paintComponent(Graphics g) { //El protected indica que solo se puede usar en clases del mismo paquete
        super.paintComponent(g); //PREGUNTAR
        
        
        player.dibujar(g);
    }
    
}
