package fr.spacey.model.entity;

import java.util.LinkedList;
import java.util.Set;

import fr.spacey.utils.Vector;

public class Simule extends Entity {

	private LinkedList<Vector> trail;
	private int timeForTrail;

	public Simule(String name, EntityType type, double masse, Vector pos, Vector vel) {
		super(name, type, masse, pos, vel);
		this.trail = new LinkedList<>();
		this.timeForTrail = 0;
	}

	public LinkedList<Vector> getTrail() {
		return trail;
	}

	@Override
	public void updatePosition(Set<Entity> entities) {
		Vector newPos = new Vector(getPos().getX(), getPos().getY());
		if(this.timeForTrail++ > 100) {
			this.trail.add(newPos);
			if(this.trail.size()>100) {
				this.trail.removeFirst();
			}
			this.timeForTrail = 0;
		}
		super.updateVelocity(entities);
		super.getPos().setPos(this.getPos().add(getVel()));
	}
}
