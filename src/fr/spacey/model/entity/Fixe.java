package fr.spacey.model.entity;

import fr.spacey.utils.Position;
import fr.spacey.utils.Velocity;

public class Fixe extends Entity {
	
	public final static Velocity VELOCITY_FIXE = new Velocity(0, 0);
	
	public Fixe(String name, EntityType type, double masse, Position pos) {
		super(name, type, masse, pos, VELOCITY_FIXE);
	}
	
	
}
