package ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import core.Gamepanel;

public class Drawmenu {

	public static Rectangle btnJugar;
	public static Rectangle btnSalir;
	public static boolean hoverJugar = false;
	public static boolean hoverSalir = false;
	public static Rectangle btnContinuar, btnSalirMenu;
	public static boolean hoverContinuar = false, hoverSalirMenu = false;
	
	public Drawmenu() {

		int btnWidth = 200, btnHeight = 60;
        btnJugar = new Rectangle((Gamepanel.VIRTUAL_WIDTH - btnWidth) / 2, Gamepanel.VIRTUAL_HEIGHT / 2 - 40, btnWidth, btnHeight);
        btnSalir = new Rectangle((Gamepanel.VIRTUAL_WIDTH - btnWidth) / 2, Gamepanel.VIRTUAL_HEIGHT / 2 + 40, btnWidth, btnHeight);
        btnContinuar = new Rectangle((Gamepanel.VIRTUAL_WIDTH - btnWidth) / 2, Gamepanel.VIRTUAL_HEIGHT / 2 - 40, btnWidth, btnHeight);
        btnSalirMenu = new Rectangle((Gamepanel.VIRTUAL_WIDTH - btnWidth) / 2, Gamepanel.VIRTUAL_HEIGHT / 2 + 40, btnWidth, btnHeight);
	}

	

    public void dibujarPauseMenu(Graphics2D g2d, int Virtualwidth, int Virtualheight) {
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
    


    private void dibujarMenu(Graphics2D g2d, int Virtualwidth, int Virtualheight) {
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Virtualwidth, Virtualheight);

        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(36f));
        g2d.drawString("TUKITUX", Virtualwidth / 2 - 100, Virtualheight / 3);

        g2d.setFont(g2d.getFont().deriveFont(24f));
        g2d.drawString("Presiona ENTER para jugar", Virtualwidth / 2 - 150, Virtualheight / 2);
        g2d.drawString("Presiona ESC para salir", Virtualwidth / 2 - 140, Virtualheight / 2 + 50);
    }

    public void dibujarMenuInicio(Graphics2D g2d, int Virtualwidth, int Virtualheight) {
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


}
