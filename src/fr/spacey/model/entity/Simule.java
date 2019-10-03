package fr.spacey.model.entity;

import java.util.Set;

import fr.spacey.utils.Position;
import fr.spacey.utils.Velocity;

public class Simule extends Entity {
	
	public Simule(String name, EntityType type, double masse, Position pos, Velocity vel) {
		super(name, type, masse, pos, vel);
	}
	
	@Override
	public void applyMotion(Set<Entity> entities) {
		getPos().setX(getPos().getX()+1);
	}
}
