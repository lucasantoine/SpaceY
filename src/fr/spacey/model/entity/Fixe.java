package fr.spacey.model.entity;

import java.util.Set;

import fr.spacey.utils.Vector;

public class Fixe extends Entity {
	
	public final static Vector VELOCITY_FIXE = new Vector(0, 0);
	
	public Fixe(String name, EntityType type, double masse, Vector pos) {
		super(name, type, masse, pos, VELOCITY_FIXE);
		super.radius = masse * 5;
	}

	@Override
	public void updatePosition(Set<Entity> entities) {
		return;
	}
	
	
}
