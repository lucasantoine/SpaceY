package fr.spacey.models.entitites;

import fr.spacey.models.entities.EntityType;
import fr.spacey.utils.Position;
import fr.spacey.utils.Velocity;

public class Simule extends Entity {
	
	public Simule(String name, EntityType type, double masse, Position pos, Velocity vel) {
		super(name, type, masse, pos, vel);
	}
	
	
}
