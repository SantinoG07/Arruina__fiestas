package entities;

import java.util.List;

import core.Gamepanel;
import levels.Mission;

public class MissionNPC extends NPC{
	private Mission mission;
    private boolean misioncomen = false;
	public MissionNPC(int x, int y, int id, List<String> dialogos, Mission mission) {
		super(x,y,id,dialogos);
		this.mission = mission;
	}
	
	public void interact(Gamepanel game) {
		if(!misioncomen) {
			misioncomen=true;
			mission.empezar(game);
		}
	}

	

}
