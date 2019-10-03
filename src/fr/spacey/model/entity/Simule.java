package fr.spacey.model.entity;

import java.util.Set;

import fr.spacey.utils.Vector;

public class Simule extends Entity {
	
	public Simule(String name, EntityType type, double masse, Vector pos, Vector vel) {
		super(name, type, masse, pos, vel);
	}

	@Override
	public void updatePosition(Set<Entity> entities) {
		super.updateVelocity(entities);
		super.getPos().setPos(this.getPos().add(getVel()));
	}
}
