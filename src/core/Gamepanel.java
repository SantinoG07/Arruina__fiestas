package core;

import javax.swing.JPanel;

import core.Gamestate;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;

public class Gamepanel extends JPanel {

	private Dialoguemanager npc1Dialogues;
	public Gamestate gameState = Gamestate.MENU;

	
	private final int Virtualwidth = 800;
	private final int Virtualheight = 600;
	private List<NPC> npcs;
	
	private Player player;
	private Inputhandler input;
	
	private boolean dialogoActivo = false;
	private NPC npcDialogando = null;
	private int lineaDialogo = 0;
	private List<List<String>> npcDialogos;
	private Rectangle btnJugar, btnSalir;
	private boolean hoverJugar = false, hoverSalir = false;
	private Rectangle btnContinuar, btnSalirMenu;
	private boolean hoverContinuar = false, hoverSalirMenu = false;

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
		
		int btnWidth = 200, btnHeight = 60;
        btnJugar = new Rectangle((Virtualwidth - btnWidth) / 2, Virtualheight / 2 - 40, btnWidth, btnHeight);
        btnSalir = new Rectangle((Virtualwidth - btnWidth) / 2, Virtualheight / 2 + 40, btnWidth, btnHeight);
        btnContinuar = new Rectangle((Virtualwidth - btnWidth) / 2, Virtualheight / 2 - 40, btnWidth, btnHeight);
        btnSalirMenu = new Rectangle((Virtualwidth - btnWidth) / 2, Virtualheight / 2 + 40, btnWidth, btnHeight);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameState == Gamestate.MENU) {
                    if (btnJugar.contains(e.getPoint())) {
                        gameState = Gamestate.PLAYING;
                        repaint();
                    } else if (btnSalir.contains(e.getPoint())) {
                        System.exit(0);
                    }
                } else if (gameState == Gamestate.PAUSE) {
                    if (btnContinuar.contains(e.getPoint())) {
                        gameState = Gamestate.PLAYING;
                        repaint();
                    } else if (btnSalirMenu.contains(e.getPoint())) {
                        gameState = Gamestate.MENU;
                        repaint();
                    }
                }
            }
            @Override
            public void mouseMoved(MouseEvent e) {
                if (gameState == Gamestate.MENU) {
                    hoverJugar = btnJugar.contains(e.getPoint());
                    hoverSalir = btnSalir.contains(e.getPoint());
                    repaint();
                } else if (gameState == Gamestate.PAUSE) {
                    hoverContinuar = btnContinuar.contains(e.getPoint());
                    hoverSalirMenu = btnSalirMenu.contains(e.getPoint());
                    repaint();
                }
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (gameState == Gamestate.MENU) {
                    hoverJugar = btnJugar.contains(e.getPoint());
                    hoverSalir = btnSalir.contains(e.getPoint());
                    repaint();
                } else if (gameState == Gamestate.PAUSE) {
                    hoverContinuar = btnContinuar.contains(e.getPoint());
                    hoverSalirMenu = btnSalirMenu.contains(e.getPoint());
                    repaint();
                }
            }
        });
        // Key listener for ESC to pause/resume
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (gameState == Gamestate.PLAYING && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    gameState = Gamestate.PAUSE;
                    repaint();
                } else if (gameState == Gamestate.PAUSE && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    gameState = Gamestate.PLAYING;
                    repaint();
                }
            }
        });
	}


    public void update() {
    	switch (gameState){
    	case MENU:{
    		if(input.enter) {
    			gameState = gameState.PLAYING;
    			input.enter = false;
    		}
    	}
    	case PLAYING:{
    		if(!dialogoActivo) player.actualizar();
    	}
    	case PAUSE:{
            if (!dialogoActivo) player.actualizar();
            if (input.e) {
                manejarDialogo();
                input.e = false; // evita repetir
            }
            break;
    	}
    		
    	}
    }
    
    private void manejarDialogo() {
        if (dialogoActivo) {
            if (npcDialogando != null && lineaDialogo < npcDialogando.getdialogos().size() - 1) {
                lineaDialogo++;
            } else {
                dialogoActivo = false;
                npcDialogando = null;
                lineaDialogo = 0;
            }
        } else {
            for (NPC npc : npcs) {
                if (npc.Estacerca(player.posX, player.posY)) {
                    dialogoActivo = true;
                    npcDialogando = npc;
                    lineaDialogo = 0;
                    break;
                }
            }
        }
    }

    
    @Override	//Indica que esta sobreescribiendo un metodo de la libreria
    protected void paintComponent(Graphics g) { //El protected indica que solo se puede usar en clases del mismo paquete
        super.paintComponent(g); //PREGUNTAR
        Graphics2D g2d = escalado(g);
        if (gameState == Gamestate.MENU) {
            dibujarMenuInicio(g2d);
            g2d.dispose();
            return;
        }
        if (gameState == Gamestate.PAUSE) {
            dibujarPauseMenu(g2d);
            g2d.dispose();
            return;
        }
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

    private void dibujarMenu(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Virtualwidth, Virtualheight);

        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(36f));
        g2d.drawString("TUKITUX", Virtualwidth / 2 - 100, Virtualheight / 3);

        g2d.setFont(g2d.getFont().deriveFont(24f));
        g2d.drawString("Presiona ENTER para jugar", Virtualwidth / 2 - 150, Virtualheight / 2);
        g2d.drawString("Presiona ESC para salir", Virtualwidth / 2 - 140, Virtualheight / 2 + 50);
    }

    private void dibujarMenuInicio(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Virtualwidth, Virtualheight);
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(36f));
        g2d.drawString("TUKITUX", Virtualwidth / 2 - 100, Virtualheight / 3);
        g2d.setFont(g2d.getFont().deriveFont(24f));
        // Botón Jugar
        g2d.setColor(hoverJugar ? Color.GREEN : Color.DARK_GRAY);
        g2d.fillRoundRect(btnJugar.x, btnJugar.y, btnJugar.width, btnJugar.height, 20, 20);
        g2d.setColor(Color.WHITE);
        g2d.drawRoundRect(btnJugar.x, btnJugar.y, btnJugar.width, btnJugar.height, 20, 20);
        g2d.drawString("Jugar", btnJugar.x + 60, btnJugar.y + 38);
        // Botón Salir
        g2d.setColor(hoverSalir ? Color.RED : Color.DARK_GRAY);
        g2d.fillRoundRect(btnSalir.x, btnSalir.y, btnSalir.width, btnSalir.height, 20, 20);
        g2d.setColor(Color.WHITE);
        g2d.drawRoundRect(btnSalir.x, btnSalir.y, btnSalir.width, btnSalir.height, 20, 20);
        g2d.drawString("Salir", btnSalir.x + 65, btnSalir.y + 38);
    }

    private void dibujarPauseMenu(Graphics2D g2d) {
        g2d.setColor(new Color(0,0,0,180));
        g2d.fillRect(0, 0, Virtualwidth, Virtualheight);
        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(36f));
        g2d.drawString("PAUSA", Virtualwidth / 2 - 70, Virtualheight / 3);
        g2d.setFont(g2d.getFont().deriveFont(24f));
        // Botón Continuar
        g2d.setColor(hoverContinuar ? Color.GREEN : Color.DARK_GRAY);
        g2d.fillRoundRect(btnContinuar.x, btnContinuar.y, btnContinuar.width, btnContinuar.height, 20, 20);
        g2d.setColor(Color.WHITE);
        g2d.drawRoundRect(btnContinuar.x, btnContinuar.y, btnContinuar.width, btnContinuar.height, 20, 20);
        g2d.drawString("Continuar", btnContinuar.x + 40, btnContinuar.y + 38);
        // Botón Salir al menú
        g2d.setColor(hoverSalirMenu ? Color.RED : Color.DARK_GRAY);
        g2d.fillRoundRect(btnSalirMenu.x, btnSalirMenu.y, btnSalirMenu.width, btnSalirMenu.height, 20, 20);
        g2d.setColor(Color.WHITE);
        g2d.drawRoundRect(btnSalirMenu.x, btnSalirMenu.y, btnSalirMenu.width, btnSalirMenu.height, 20, 20);
        g2d.drawString("Salir al menú", btnSalirMenu.x + 25, btnSalirMenu.y + 38);
    }

    private void dibujarE(Graphics2D g2d, NPC npc) {
        g2d.setColor(Color.YELLOW);
        g2d.setFont(g2d.getFont().deriveFont(24f));
        g2d.drawString("E", (int)npc.getPosX() + 12, (int)npc.getPosY() - 10);
    }


}