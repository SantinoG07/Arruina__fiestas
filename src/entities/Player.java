package entities;

import java.awt.Color;
import java.awt.Graphics;

import input.Inputhandler;
import world.Isoutils;

public class Player {
	//public double posX, posY, height, width, speed;
	public int row, col;
	
	private Inputhandler input;
	
	public Player(int comienzox, int comienzoy, Inputhandler input) {
		/*this.posX = comienzox;
		this.posY = comienzoy;
		this.width = 400;
		this.height = 40;
		this.speed = 5;
		this.input = input;*/
        this.row = row;
        this.col = col;
	}
	
	/*public void actualizar() {
		if(input.w) posY -=speed;
		if(input.s) posY +=speed;
		if(input.a) posX -=speed;
		if(input.d) posX +=speed;
	}
	
	
	
	public void dibujarJ(Graphics g) {
        // Ejemplo: dibujar jugador
        g.setColor(Color.GREEN);
        g.fillRect((int)posX, (int)posY, (int)width, (int)height);
	}*/
	
	public void movimiento(int dr, int dc) {
		row+=dr;
		col+=dc;
	}
	
	public void dibujarJ(Graphics g) {
		int x = Isoutils.ToscreenX(row, col) + 300;
		int y = Isoutils.ToscreenX(row, col) + 50;
        g.setColor(Color.GREEN);
        g.fillOval(x - 10, y - 20, 20, 20); 
	}
	
	

}
