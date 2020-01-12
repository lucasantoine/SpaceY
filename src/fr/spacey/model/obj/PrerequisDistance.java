package fr.spacey.model.obj;

import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.EntityType;
import fr.spacey.model.entity.Vaisseau;
import fr.spacey.utils.Vecteur;

public class PrerequisDistance implements Prerequis {
	
	private double distance;
	private Vecteur from = new Vecteur(0,0);

	public PrerequisDistance(double dist) {
		this.distance = dist;
	}
	
	public boolean isComplete(Vaisseau e) {
		return getDistance(e) >= this.distance;
	}
	
	private double getDistance(Vaisseau e) {
		Vecteur to = e.getPos();
		int distanceParcourue = (int) Math.sqrt((to.getX() - from.getX()) * (to.getX() - from.getX())
				+ (to.getY() - from.getY()) * (to.getY() - from.getY()));
		return distanceParcourue;
	}

	@Override
	public void init(SpaceModel ref) {
		for(Entity e : ref.getEntities())
			if(e.getType().equals(EntityType.FIXE))
				from = e.getPos().clone();
	}
	
	public String toString(SpaceModel sm) {
		return "Distance: "+(distance-getDistance(sm.getVaisseau()));
	}
}
