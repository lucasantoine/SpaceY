package fr.spacey.models.entitites;

import fr.spacey.models.entities.EntityType;
import fr.spacey.utils.Position;
import fr.spacey.utils.Velocity;

public class Vaisseau extends Entity {

	private double pprincipal;
	private double pretro;
	
	public Vaisseau(String name, EntityType type, double masse, Position pos, Velocity vel, double pprincipal, double pretro) {
		super(name, type, masse, pos, vel);
		this.pprincipal = pprincipal;
		this.pretro = pretro;
	}
	
	
}
