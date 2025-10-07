package entities;

import java.awt.Color;
import java.awt.Graphics;

import input.Inputhandler;

public class Player {
	public double posX, posY, height, width, speed, initialX, initialY;;
	private Inputhandler input;
	
	public Player(int comienzox, int comienzoy, Inputhandler input) {
		this.posX = comienzox;
		this.posY = comienzoy;
		this.initialX = comienzox;  // 🔹 guardamos posición inicial
        this.initialY = comienzoy;
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
        g.setColor(Color.GREEN);
        g.fillRect((int)posX, (int)posY, (int)width, (int)height);
	}
	
	public void resetPosition() {
        this.posX = initialX;
        this.posY = initialY;
    }

}
