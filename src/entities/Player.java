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
		this.speed = 5;
		this.input = input;
	}
	
	public void actualizar() {
		double moveX = 0;
		double moveY = 0;
		
		if(input.w) moveY -= 1;
		if(input.s) moveY += 1;
		if(input.a) moveX -= 1;
		if(input.d) moveX += 1;
		
		// Normalizar el movimiento diagonal
		if(moveX != 0 && moveY != 0) {
			double length = Math.sqrt(moveX * moveX + moveY * moveY);
			moveX = moveX / length * speed;
			moveY = moveY / length * speed;
		} else {
			moveX *= speed;
			moveY *= speed;
		}
		
		posX += moveX;
		posY += moveY;
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
