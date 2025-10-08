package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class TimerBar {
    private int x, y, width, height;
    private float progress;
    private boolean active;
    private long startTime;
    private int duration;  // duración en milisegundos

    public TimerBar(int x, int y, int width, int height, int durationInSeconds) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.duration = durationInSeconds * 1000;
        this.active = false;
        this.progress = 1.0f;
    }

    public void start() {
        this.active = true;
        this.progress = 1.0f;
        this.startTime = System.currentTimeMillis();
        System.out.println("Barra de tiempo iniciada");
    }

    public void update() {
        if (!active) return;

        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - startTime;
        
        progress = 1.0f - ((float)elapsedTime / duration);
        
        if (progress <= 0) {
            progress = 0;
            active = false;
        }
    }

    public void draw(Graphics g) {
        if (!active) return;

        Graphics2D g2d = (Graphics2D) g;
        
        // Dibuja el fondo de la barra con un color más oscuro
        g2d.setColor(new Color(30, 30, 30));  // Gris muy oscuro
        g2d.fillRect(x, y, width, height);
        
        // Dibuja el progreso con un degradado
        int progressWidth = (int)(width * progress);
        g2d.setColor(new Color(0, 255, 0));
        g2d.fillRect(x, y, progressWidth, height);
        
        // Dibuja el borde
        g2d.setColor(Color.WHITE);  // Borde blanco para mayor contraste
        g2d.drawRect(x, y, width, height);
        
        // Dibuja un efecto de brillo
        g2d.setColor(new Color(255, 255, 255, 50));  // Blanco semi-transparente
        g2d.fillRect(x, y, progressWidth, height/2);
    }

    public boolean isActive() {
        return active;
    }

    public boolean isFinished() {
        return !active && progress <= 0;
    }

    public float getProgress() {
        return progress;
    }
}