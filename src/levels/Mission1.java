package levels;

import core.Gamepanel;

public class Mission1 extends Mission {

    @Override
    public void empezar(Gamepanel game) {
        // Cambia la posición del jugador
        game.cambiarhab("Mision 1");
        // Reemplaza los NPCs de la misión
        game.reemplazarNPC("Mision 1");
        // Marca la misión como activa/comenzada
        completed = false;
    }

    @Override
    public void terminado(Gamepanel game) {
        completed = true;
        game.cambiarhab("Inicio");
        game.reiniciarNPCs();
    }
}
