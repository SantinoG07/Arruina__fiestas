package core;
import java.awt.Panel;
import java.io.IOException;

import javax.swing.JFrame;

public class Game {
    public static void main(String[] args) throws IOException {
        // Crear ventana principal
        JFrame window = new JFrame("Arruina Fiestas");
        window.setSize(800, 600);      // Tamaño de la ventana
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Cierra al salir
        window.setResizable(true);     // Permite cambiar tamaño
        window.setLocationRelativeTo(null); // Centrar ventana
        
        //Crear panel del juego
        Gamepanel gamePanel = new Gamepanel(); // Crear instancia de GamePanel
        window.add(gamePanel); // Agregar GamePanel a la ventana
        window.setVisible(true); // Hacer visible la ventana
        
        //Iniciamos el loop
        Gameloop loop = new Gameloop(gamePanel);
        loop.start();
    }
}