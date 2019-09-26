package fr.spacey.models.entities.types;

import fr.spacey.models.entities.EntityModel;
import fr.spacey.utils.Position;
import fr.spacey.utils.Velocity;

public class Simule extends EntityModel {
	
	public Simule(String name, EntityType type, double masse, Position pos, Velocity vel) {
		super(name, type, masse, pos, vel);
	}
	
	
}
