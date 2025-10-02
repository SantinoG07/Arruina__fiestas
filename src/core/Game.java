package core;
import java.awt.Panel;
import java.io.IOException;

import javax.swing.JFrame;

import input.Inputhandler;

public class Game {
    public static void main(String[] args) throws IOException {


        // Crear ventana principal
        JFrame window = new JFrame("Arruina Fiestas");	//Definir ventana
        window.setSize(800, 600);      // Tamaño de la ventana
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra al salir
        window.setResizable(true);     // Permite cambiar tamaño
        window.setLocationRelativeTo(null); // Centrar ventana
        window.setVisible(true);       // Mostrar ventana
        
        //Crear panel del juego
        Gamepanel gamePanel = new Gamepanel(); // Crear instancia de GamePanel
        window.add(gamePanel); // Agregar GamePanel a la ventana
        window.setVisible(true); // Hacer visible la ventana
        
        
        //Configuramos el input
        Inputhandler input = new Inputhandler();
        gamePanel.addKeyListener(input);
        gamePanel.setFocusable(true);
        gamePanel.requestFocusInWindow();
        
        //Iniciamos el loop
        Gameloop loop = new Gameloop(gamePanel);
    	loop.start();


    }
}
