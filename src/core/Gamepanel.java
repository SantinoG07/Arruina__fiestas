package core;

import javax.swing.JPanel;

import entities.Player;
import input.Inputhandler;
import world.Maps;

import java.awt.Graphics;
import java.awt.Color;

public class Gamepanel extends JPanel {
	
	private Player player;
    private Maps map;
	private Inputhandler input;
	
	public Gamepanel() {
		input = new Inputhandler();
		this.addKeyListener(input);
		this.setFocusable(true);
		this.requestFocusInWindow();
		

        map = new Maps(10, 10);
        player = new Player(5, 5, input);
	}
	

    public void update() {
        if (input.s) player.movimiento(-1, 0);   // arriba-izquierda
        if (input.d) player.movimiento(0, 1);    // arriba-derecha
        if (input.w) player.movimiento(1, 0);    // abajo-derecha
        if (input.a) player.movimiento(0, -1);  
    }
    @Override	//Indica que esta sobreescribiendo un metodo de la libreria
    protected void paintComponent(Graphics g) { //El protected indica que solo se puede usar en clases del mismo paquete
        super.paintComponent(g); //PREGUNTAR
        

        map.dibujarM(g);
        player.dibujarJ(g);
    }
    
}
