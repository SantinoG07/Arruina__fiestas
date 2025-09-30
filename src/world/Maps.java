package world;

import java.awt.Color;
import java.awt.Graphics;

public class Maps {
	private int rows, cols;
	
	public Maps(int rows, int cols) {
		this.cols = cols;
		this.rows = rows;
	}
	
	public void dibujarM(Graphics g) {
		for(int row=0 ; row < rows ; row++) {
			for(int col=0; col<cols; col++) {
				int x = Isoutils.ToscreenX(row, col) + 300; // Centrado
				int y = Isoutils.ToscreenY(row, col) + 50; 
				
				g.setColor(Color.LIGHT_GRAY);
				g.drawPolygon(
						new int[] {x, x + Isoutils.Tilewidth/2, x , x - Isoutils.Tilewidth/2},  //De esta forma le pasamos los 4 vertices del poligono (posicion)
						new int[] {y, y, Isoutils.Tileheight/2 , y , y - Isoutils.Tileheight/2},
						4
						);
			}
		}
	}
	
}
