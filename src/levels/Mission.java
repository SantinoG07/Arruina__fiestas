package levels;

import core.Gamepanel;

public abstract class Mission {
    protected boolean completed = false;

    public abstract void empezar(Gamepanel game);
    public abstract void terminado(Gamepanel game);

    public boolean isCompleted() {
        return completed;
    }
}
