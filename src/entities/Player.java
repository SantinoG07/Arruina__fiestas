package entities;

import java.awt.Color;
import java.awt.Graphics;

import input.Inputhandler;

public class Player {
	public double posX, posY, height, width, speed;
	private Inputhandler input;
	
	public Player(int comienzox, int comienzoy, Inputhandler input) {
		this.posX = comienzox;
		this.posY = comienzoy;
		this.width = 40;
		this.height = 40;
		this.speed = 7;
		this.input = input;
	}
	
	public void actualizar() {
		if(input.w) posY -=speed;
		if(input.s) posY +=speed;
		if(input.a) posX -=speed;
		if(input.d) posX +=speed;
	}
	
	
	
	public void dibujarJ(Graphics g) {
        // Ejemplo: dibujar jugador
        g.setColor(Color.GREEN);
        g.fillRect((int)posX, (int)posY, (int)width, (int)height);
	}
	
	
	
	

}
