package core;

import javax.swing.JPanel;

import entities.NPC;
import entities.Player;
import input.Inputhandler;
import levels.Mission;
import levels.Mission1;
import ui.Dialoguemanager;
import ui.Drawmenu;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;

public class Gamepanel extends JPanel {

	private Dialoguemanager npc1Dialogues;
	public Gamestate gameState = Gamestate.MENU;

	
	public final static int Virtualwidth = 800;
	public final static int Virtualheight = 600;
	private Mission activeMission = null;
	private List<NPC> npcs;
	
	private Player player;
	private Inputhandler input;
	
	private boolean dialogoActivo = false;
	private NPC npcDialogando = null;
	private int lineaDialogo = 0;
	private List<List<String>> npcDialogos;

	Drawmenu menu = new Drawmenu();

	public Gamepanel() throws IOException {
	    input = new Inputhandler(this);
	    this.addKeyListener(input);
	    this.addMouseListener(input);
	    this.addMouseMotionListener(input);
	    this.setFocusable(true);
	    this.requestFocusInWindow();
	    setPreferredSize(new Dimension(Virtualwidth, Virtualheight));

	    player = new Player(400, 300, input);
	    npcs = new ArrayList<>();
	    npcDialogos = Dialoguemanager.cargarDialogos();
	    npcs.add(new NPC(200, 200, 1, npcDialogos.get(0)));
	}



	public void update() {

	    if (input.esc) {
	        if (gameState == Gamestate.PLAYING) {
	            gameState = Gamestate.PAUSE;
	        } else if (gameState == Gamestate.PAUSE) {
	            gameState = Gamestate.PLAYING;
	        }
	        input.esc = false;
	    }

	    switch (gameState) {
	    case MENU: {
	        if (input.enter) {
	            gameState = Gamestate.PLAYING;
	            input.enter = false;
	        }
	        break;
	    }
	    case PLAYING: {
	        if (!dialogoActivo) player.actualizar();
	        
	        if (input.e) {
	            manejarDialogo();
	            input.e = false;
	        }
	        break;
	    }
	    case PAUSE: {
	        if (!dialogoActivo) player.actualizar();

	        if (input.e) {
	            manejarDialogo();
	            input.e = false;
	        }
	        break;
	    }
	    }
	}
    
	private void manejarDialogo() {
	    if (!dialogoActivo) {
	        for (NPC npc : npcs) {
	            if (npc.Estacerca(player.posX, player.posY)) {
	                dialogoActivo = true;
	                npcDialogando = npc;
	                lineaDialogo = 0;
	                System.out.println("Diálogo iniciado con NPC " + npc.getid());
	                break;
	            }
	        }
	    } else {
	        if (npcDialogando != null) {
	            List<String> dialogos = npcDialogando.getdialogos();
	            if (lineaDialogo < dialogos.size() - 1) {
	                lineaDialogo++;
	            } else {
	                dialogoActivo = false;
	                System.out.println("Diálogo terminado con NPC " + npcDialogando.getid());

	                if (player != null) {
	                    player.resetPosition();
	                    System.out.println("Jugador reiniciado a posición inicial: (" 
	                        + player.posX + ", " + player.posY + ")");
	                }

	                npcDialogando = null;
	                lineaDialogo = 0;
	                repaint(); 
	            }
	        }
	    }
	}



    
    @Override	//Indica que esta sobreescribiendo un metodo de la libreria
    protected void paintComponent(Graphics g) { //El protected indica que solo se puede usar en clases del mismo paquete
        super.paintComponent(g); //PREGUNTAR
        Graphics2D g2d = Scalation.escalado(g, this, Virtualwidth, Virtualheight);
        if (gameState == Gamestate.MENU) {
            menu.dibujarMenuInicio(g2d, Virtualwidth, Virtualheight);
            g2d.dispose();
            return;
        }
        if (gameState == Gamestate.PAUSE) {
			menu.dibujarPauseMenu(g2d, Virtualwidth, Virtualheight);
            g2d.dispose();
            return;
        }
        player.dibujarJ(g2d);
        
        //Recorremos todos los npcs
        for(NPC npc: npcs) {
            npc.dibujarNPC(g2d);
            if(npc.Estacerca(player.posX, player.posY) && !dialogoActivo) {
                Dialoguemanager.dibujarE(g2d, npc);
            }
        }
        if (dialogoActivo && npcDialogando != null) {
        	Dialoguemanager.dibujarCartel(g2d, npcDialogando.getdialogos().get(lineaDialogo), Virtualwidth, Virtualheight);
        }
        g2d.dispose();
    }
    
    public int obtaltura() {
    	return getHeight();
    }
    
    public int obtancho() {
    	return getWidth();
    	
    }



    public void cambiarhab(String string) {
        if(string.equals("Mision 1")) {
            // Mueve al jugador al inicio de la misión
            player.posX = 400;
            player.posY = 150;
        } else if(string.equals("Inicio")) {
            player.posX = 400;
            player.posY = 300;
        }
    }

    public void reemplazarNPC(String string) {
        if(string.equals("Mision 1")) {
            npcs.clear();
            npcs.add(new NPC(300, 300, 2, npcDialogos.get(1))); // NPC principal de la misión
            // Agrego otro NPC opcional de la misión
            if(npcDialogos.size() > 2)
                npcs.add(new NPC(500, 200, 3, npcDialogos.get(2)));
        }
    }

    public void reiniciarNPCs() {
        npcs.clear();
        npcs.add(new NPC(200, 200, 1, npcDialogos.get(0)));        
    }




}