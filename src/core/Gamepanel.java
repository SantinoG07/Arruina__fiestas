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
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Gamepanel extends JPanel {

	private Dialoguemanager npc1Dialogues;
	
	private final int Virtualwidth = 800;
	private final int Virtualheight = 600;
	private List<NPC> npcs;
	
	private Player player;
	private Inputhandler input;
	
	private boolean dialogoActivo = false;
	private NPC npcDialogando = null;
	private int lineaDialogo = 0;
	private List<List<String>> npcDialogos;

	public Gamepanel() throws IOException {
		input = new Inputhandler();
		this.addKeyListener(input);
		this.setFocusable(true);
		this.requestFocusInWindow();
		setPreferredSize(new Dimension(Virtualwidth, Virtualheight));
		player = new Player(400, 300, input);
		npcs = new ArrayList<>();
		npcDialogos = Dialoguemanager.cargarDialogos();
		
		
		// Crear un NPC de ejemplo con diálogos
		npcs.add(new NPC(200, 200, 1, npcDialogos.get(0)));
		
		// Key listener para interacción con E
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//Si el jugador presiona E
				if (e.getKeyCode() == KeyEvent.VK_E) {
					//Y el dialogo esta activo
					if (dialogoActivo) {
						//Se carga la siguiente linea de dialogo
						if (npcDialogando != null && lineaDialogo < npcDialogando.getdialogos().size() - 1) {
							lineaDialogo++;
						} 
						//Se desactiva el dialogo
						else {
							dialogoActivo = false;
							npcDialogando = null;
							lineaDialogo = 0;
						}
						repaint();
					} 
					//Si el jugador no presiona la E
					else {
						for (NPC npc : npcs) {
							//Se revisa si esta cerca
							if (npc.Estacerca(player.posX, player.posY)) {
								dialogoActivo = true;
								npcDialogando = npc;
								lineaDialogo = 0;
								repaint();
								break;
							}
						}
					}
				}
			}
		});
	}


    public void update() {
        if (!dialogoActivo) {
            player.actualizar();
        }
    }
    
    @Override	//Indica que esta sobreescribiendo un metodo de la libreria
    protected void paintComponent(Graphics g) { //El protected indica que solo se puede usar en clases del mismo paquete
        super.paintComponent(g); //PREGUNTAR
        Graphics2D g2d = escalado(g);
        player.dibujarJ(g2d);
        
        //Recorremos todos los npcs
        for(NPC npc: npcs) {
            npc.dibujarNPC(g2d);
            if(npc.Estacerca(player.posX, player.posY) && !dialogoActivo) {
                dibujarE(g2d, npc);
            }
        }
        if (dialogoActivo && npcDialogando != null) {
        	Dialoguemanager.dibujarCartel(g2d, npcDialogando.getdialogos().get(lineaDialogo), Virtualwidth, Virtualheight);
        }
        g2d.dispose();
    }
    
    
    private Graphics2D escalado(Graphics g) {
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


    private void dibujarE(Graphics2D g2d, NPC npc) {
        g2d.setColor(Color.YELLOW);
        g2d.setFont(g2d.getFont().deriveFont(24f));
        g2d.drawString("E", (int)npc.getPosX() + 12, (int)npc.getPosY() - 10);
    }


}