package core;

import javax.swing.JPanel;

import entities.NPC;
import entities.Player;
import input.Inputhandler;
import levels.Mission;
import levels.Mission1;
import ui.Dialoguemanager;
import ui.Drawmenu;
import ui.TimerBar;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.Dimension;

public class Gamepanel extends JPanel {

	private Dialoguemanager npc1Dialogues;
	public Gamestate gameState = Gamestate.MENU;

	
	public static final int VIRTUAL_WIDTH = 800;
	public static final int VIRTUAL_HEIGHT = 600;
	private Mission activeMission = null;
	private List<NPC> npcs;
	private Camera camera;
	
	private Player player;
	private Inputhandler input;
	
	private boolean dialogoActivo = false;
	private NPC npcDialogando = null;
	private int lineaDialogo = 0;
	private List<List<String>> npcDialogos;
	private TimerBar timerBar;
	private boolean needsReset = false;

	Drawmenu menu = new Drawmenu();

	public Gamepanel() throws IOException {
		// Posicionamos la barra en el centro superior de la pantalla virtual
		timerBar = new TimerBar(VIRTUAL_WIDTH / 2 - 100, 20, 200, 30, 5); // 5 segundos de duración
	    input = new Inputhandler(this);
	    this.addKeyListener(input);
	    this.addMouseListener(input);
	    this.addMouseMotionListener(input);
	    this.setFocusable(true);
	    this.requestFocusInWindow();
	    setPreferredSize(new Dimension(VIRTUAL_WIDTH, VIRTUAL_HEIGHT));

	    camera = new Camera(VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
	    player = new Player(400, 300, input);
	    npcs = new ArrayList<>();
	    npcDialogos = Dialoguemanager.cargarDialogos();
	    npcs.add(new NPC(200, 200, 1, npcDialogos.get(0)));
	    System.out.println("NPC creado con ID: " + npcs.get(0).getid());
	}



    @Override
public void setBounds(int x, int y, int width, int height) {
    super.setBounds(x, y, width, height);
}public void update() {

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
	        if (!dialogoActivo) {
	            player.actualizar();
	            camera.centerOnPlayer(player);
	        }
	        
	        if (input.e) {
	            manejarDialogo();
	            input.e = false;
	        }

	        // Actualizar la barra de tiempo
	        if (timerBar.isActive()) {
	            timerBar.update();
	            if (timerBar.isFinished() && !needsReset) {
	                player.resetPosition();
	                needsReset = true;  // Evita múltiples resets
	            }
	            repaint(); // Forzar el redibujado cuando la barra está activa
	        } else {
	            needsReset = false;  // Resetea el flag cuando el timer no está activo
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

	                // Si es el NPC 1, iniciamos el temporizador
	                if (npcDialogando.getid() == 1) {
	                    timerBar.start();
	                } else {
	                    // Para otros NPCs, resetear posición inmediatamente
	                    if (player != null) {
	                        player.resetPosition();
	                    }
	                }

	                npcDialogando = null;
	                lineaDialogo = 0;
	                repaint();
	            }
	        }
	    }
	}



    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Aplicar el escalado basado en el tamaño de la ventana
        double scaleX = getWidth() / (double) VIRTUAL_WIDTH;
        double scaleY = getHeight() / (double) VIRTUAL_HEIGHT;
        g2d.scale(scaleX, scaleY);

        if (gameState == Gamestate.MENU) {
            menu.dibujarMenuInicio(g2d, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
            g2d.dispose();
            return;
        }
        if (gameState == Gamestate.PAUSE) {
                        menu.dibujarPauseMenu(g2d, VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
            g2d.dispose();
            return;
        }

        // Actualizar la posición de la cámara
        camera.centerOnPlayer(player);

        // Guardar la transformación actual para la UI
        java.awt.geom.AffineTransform originalTransform = g2d.getTransform();
        
        // Aplicar la transformación de la cámara
        g2d.translate(-camera.getXOffset(), -camera.getYOffset());
        
        // Dibujar el mundo (aquí deberías dibujar el fondo primero si lo tienes)
        
        // Dibujar NPCs solo si están en el viewport de la cámara
        for(NPC npc: npcs) {
            if(camera.isVisible((float)npc.getPosX(), (float)npc.getPosY(), 32, 32)) { // Usando tamaño fijo para NPC
                npc.dibujarNPC(g2d);
                if(npc.Estacerca(player.posX, player.posY) && !dialogoActivo) {
                    Dialoguemanager.dibujarE(g2d, npc);
                }
            }
        }

        // Dibujar jugador (siempre visible ya que la cámara lo sigue)
        player.dibujarJ(g2d);

        // Restaurar transformación original para UI
        g2d.setTransform(originalTransform);
        
        // Dibujar elementos de UI
        if (dialogoActivo && npcDialogando != null) {
            Dialoguemanager.dibujarCartel(g2d, npcDialogando.getdialogos().get(lineaDialogo), VIRTUAL_WIDTH, VIRTUAL_HEIGHT);
        }

        // Dibujar timer si está activo
        if (timerBar != null && timerBar.isActive()) {
            timerBar.draw(g2d);
        }

        // Dibujar la barra de tiempo si está activa
        if (timerBar != null && timerBar.isActive()) {
            timerBar.draw(g2d);
            System.out.println("Dibujando barra de tiempo - Progreso: " + timerBar.getProgress());
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