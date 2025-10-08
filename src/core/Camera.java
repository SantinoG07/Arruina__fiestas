package core;

import entities.Player;

public class Camera {
    private float xOffset;
    private float yOffset;
    private final int VIRTUAL_WIDTH;
    private final int VIRTUAL_HEIGHT;
    private int mapWidth;
    private int mapHeight;
    
    // Factor de interpolación (0.1f = movimiento suave, 1.0f = sin interpolación)
    private static final float LERP_FACTOR = 0.1f;
    
    public Camera(int virtualWidth, int virtualHeight) {
        this.VIRTUAL_WIDTH = virtualWidth;
        this.VIRTUAL_HEIGHT = virtualHeight;
        this.xOffset = 0;
        this.yOffset = 0;
        // Valores por defecto del mapa, deberían actualizarse según el mapa real
        this.mapWidth = 2000;
        this.mapHeight = 1500;
    }
    
    public void setMapDimensions(int mapWidth, int mapHeight) {
        this.mapWidth = mapWidth;
        this.mapHeight = mapHeight;
    }
    
    private float lerp(float start, float end, float alpha) {
        return start + alpha * (end - start);
    }
    
    public void centerOnPlayer(Player player) {
        // Calcular la posición objetivo de la cámara
        float targetX = (float)(player.posX - (VIRTUAL_WIDTH / 2.0));
        float targetY = (float)(player.posY - (VIRTUAL_HEIGHT / 2.0));
        
        // Aplicar interpolación para movimiento suave
        xOffset = lerp(xOffset, targetX, LERP_FACTOR);
        yOffset = lerp(yOffset, targetY, LERP_FACTOR);
        
        // Limitar la cámara a los bordes del mapa
        xOffset = Math.max(0, Math.min(xOffset, mapWidth - VIRTUAL_WIDTH));
        yOffset = Math.max(0, Math.min(yOffset, mapHeight - VIRTUAL_HEIGHT));
    }
    
    public float getXOffset() {
        return xOffset;
    }
    
    public float getYOffset() {
        return yOffset;
    }
    
    // Convertir coordenadas del mundo a coordenadas de pantalla
    public int worldToScreenX(float worldX) {
        return (int)(worldX - xOffset);
    }
    
    public int worldToScreenY(float worldY) {
        return (int)(worldY - yOffset);
    }
    
    // Comprobar si un objeto está dentro del viewport de la cámara
    public boolean isVisible(float x, float y, int width, int height) {
        return (x + width >= xOffset && 
                x <= xOffset + VIRTUAL_WIDTH &&
                y + height >= yOffset && 
                y <= yOffset + VIRTUAL_HEIGHT);
    }
}