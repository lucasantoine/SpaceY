package fr.spacey.model.entity;

import java.util.LinkedList;
import java.util.Set;

import fr.spacey.utils.Vector;

public class Simule extends Entity {
	
	private LinkedList<Vector> trail;
	
	public Simule(String name, EntityType type, double masse, Vector pos, Vector vel) {
		super(name, type, masse, pos, vel);
		trail = new LinkedList<>();
	}
	
	

	public LinkedList<Vector> getTrail() {
		return trail;
	}

	@Override
	public void updatePosition(Set<Entity> entities) {
		Vector newPos = new Vector(getPos().getX(), getPos().getY());
		trail.add(newPos);
		if(trail.size()>10000) {
			trail.removeFirst();
		}
		super.updateVelocity(entities);
		super.getPos().setPos(this.getPos().add(getVel()));
	}
}
