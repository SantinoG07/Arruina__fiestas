package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dialoguemanager {
		
		private List dialogues;
		private int lineaactual;

		public static List<List<String>> cargarDialogos() throws IOException {
			List<List<String>> dialogos = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader("src/utils/NPCdialogs.txt"));
			String linea;
			
			List<String> actual = null;
			
			while ((linea = br.readLine()) != null) {
					if (linea.startsWith("#")) {
						if (actual != null) dialogos.add(actual);
						actual = new ArrayList<>();
					} else if (!linea.trim().isEmpty()) {
						if (actual != null) actual.add(linea);
					}
				}
				
				//Cargamos los dialogos al array
				if (actual != null && !actual.isEmpty()) {
					dialogos.add(actual);
				}
			
			return dialogos;
		}
		

	    public static void dibujarCartel(Graphics g, String texto, int Virtualwidth, int Virtualheight) {
	        Graphics2D g2d = (Graphics2D) g;
	        g2d.setColor(Color.BLACK);
	        g2d.fillRect(Virtualwidth/2 - 150, Virtualheight - 120, 300, 60);
	        g2d.setColor(Color.WHITE);
	        g2d.drawString(texto, Virtualwidth/2 - 140, Virtualheight - 80);
	    }
		
		public String getlineaactual(){
			if(lineaactual >=0 && lineaactual < dialogues.size()) {
				return (String) dialogues.get(lineaactual);
			}
			return null;
		}
		
		public String lineasiguiente() {
			if(lineaactual < dialogues.size()-1) {
				lineaactual ++;
			}
			return null;
		}
		
		public boolean maslineas() {
			return lineaactual<dialogues.size()-1;
		}
		
		public void reset() {
			lineaactual = 0;
		}
		
		
		
		
}
