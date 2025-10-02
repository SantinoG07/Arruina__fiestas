package ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dialoguemanager {
		
		private List dialogues;
		private int lineaactual;
		
		public Dialoguemanager(String ruta) throws IOException { //Para no tener que hacer un try catch, usamos la exception
			dialogues = cargardialogos(ruta);
			lineaactual =0;
		}

		private List cargardialogos(String ruta) throws IOException {//Para no tener que hacer un try catch, usamos la exception (Lo agrega eclipse solo)
			List<String> lineas = new ArrayList<>();
			BufferedReader br = new BufferedReader(new FileReader(ruta));
			String linea;
			while((linea = br.readLine()) !=null) {
				if(!linea.trim().isEmpty()) {
					lineas.add(linea);
				}
			}
			
			return lineas;
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
