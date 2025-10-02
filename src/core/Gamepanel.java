package core;

import javax.swing.JPanel;

import entities.NPC;
import entities.Player;
import input.Inputhandler;
import ui.Dialoguemanager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;

public class Gamepanel extends JPanel {

	private Dialoguemanager npc1Dialogues;
	
	private final int Virtualwidth = 800;
	private final int Virtualheight = 600;
	private List<NPC> npcs;
	
	private Player player;
	private Inputhandler input;
	
	public Gamepanel() throws IOException {

		input = new Inputhandler();
		this.addKeyListener(input);
		this.setFocusable(true);
		this.requestFocusInWindow();
		
		setPreferredSize(new Dimension(Virtualwidth, Virtualheight));
		

        player = new Player(400, 300, input);
	}
	

    public void update() {
        player.actualizar();
    }
    @Override	//Indica que esta sobreescribiendo un metodo de la libreria
    protected void paintComponent(Graphics g) { //El protected indica que solo se puede usar en clases del mismo paquete
        super.paintComponent(g); //PREGUNTAR
       
        player.dibujarJ(escalado(g));
        escalado(g).dispose();
    }
    
    
    private Graphics2D escalado(Graphics g) {
    	
    	//Dialogos de NPC
    	for(NPC npc: npcs) {
    		if(npc.Estacerca(player.posX, player.posY)) {
    			dibujarCartel(g, "Pulsa E para hablar");
    		}
    		npc.dibujarNPC(g);
    	}
    	
    	
    	
    	
    	//Manejo de resoluciones temporal
    	//Obtenemos las medidas de la pantalla actualmente
        int Screenwidth = getWidth(); 
        int Screenheight = getHeight();
        
        
        //Las virtual son las res que nosotros setteamos, las Screen son las que el user settea, se hace como un promedio por asi decirlo para obtener el centro x y el centro y
        double ScaleX = (double) Screenwidth / Virtualwidth;
        double ScaleY = (double) Screenheight / Virtualheight;
        
        //Creamos la nueva resolucion virtual
        Graphics2D g2d = (Graphics2D) g.create();
        
        //Setteamos la neva res
        g2d.scale(ScaleX, ScaleY);
        
        return g2d;
    	
    }
    
    

    private void dibujarCartel(Graphics g, String texto) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(java.awt.Color.BLACK);
        g2d.fillRect(getWidth()/2 - 150, getHeight() - 100, 300, 60);
        g2d.setColor(java.awt.Color.WHITE);
        g2d.drawString(texto, getWidth()/2 - 60, getHeight() - 65);
    }
    
}
