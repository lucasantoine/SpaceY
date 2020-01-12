package fr.spacey.model.obj;

import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Vaisseau;

public class PrerequisTemps implements Prerequis {

	private long start;
	private long time;

	public PrerequisTemps(int time) {
		this.time = time;
	}
	
	public boolean isComplete(Vaisseau e) {
		int tempsEcoule = (int) ((System.currentTimeMillis() - start)/1000);
		return tempsEcoule < time;
	}

	@Override
	public void init(SpaceModel ref) {
		start = System.currentTimeMillis();
	}
	
	public String toString(SpaceModel sm) {
		int tempsEcoule = (int) ((System.currentTimeMillis() - start)/1000);
		return "Temps Restant: "+(time - tempsEcoule)+"s";
	}
}
