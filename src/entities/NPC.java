package entities;

import java.awt.Color;

import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NPC {
	private int id;
	private double posX, posY;
	private List<String> dialogo;
	
	public NPC(double posX, double posY, int id, List<String> dialogo){
		this.posX = posX;
		this.posY = posY;
		this.dialogo = dialogo;
	}
	
	public void dibujarNPC(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect((int)posX, (int)posY, 40, 40);
	}
	
	public boolean Estacerca(double posXp, double posYp) {
		double dx = posX - posXp;
		double dy = posY - posYp;
		double dist = Math.sqrt(dx*dx + dy*dy); // PREGUNTAR
		return dist<80; //Rango de proximidad
	}
	
	public List<String> getdialogos() {
		return dialogo;
	}
	
	public int getid() {
		return id;
	}
	public double getPosX() {
		return posX;
	}
	public double getPosY() {
		return posY;
	}

}