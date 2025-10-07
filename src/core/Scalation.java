package core;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class Scalation {

	

    public static Graphics2D escalado(Graphics g, Gamepanel panel, int Virtualwidth, int Virtualheight) {
    	
    	//Manejo de resoluciones temporal
    	//Obtenemos las medidas de la pantalla actualmente
        int Screenwidth = panel.obtancho(); 
        int Screenheight = panel.obtaltura();
        
        
        //Las virtual son las res que nosotros setteamos, las Screen son las que el user settea, se hace como un promedio por asi decirlo para obtener el centro x y el centro y
        double ScaleX = (double) Screenwidth / Virtualwidth;
        double ScaleY = (double) Screenheight / Virtualheight;
        
        //Creamos la nueva resolucion virtual
        Graphics2D g2d = (Graphics2D) g.create();
        
        //Setteamos la neva res
        g2d.scale(ScaleX, ScaleY);
        
        return g2d;
    	
    }

}
