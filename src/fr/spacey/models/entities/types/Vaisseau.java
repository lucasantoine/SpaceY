package fr.spacey.models.entities.types;

import fr.spacey.models.entities.EntityModel;
import fr.spacey.utils.Vector;

public class Vaisseau extends EntityModel {

	private double pprincipal;
	private double pretro;
	
	public Vaisseau(String name, EntityType type, double masse, Vector pos, Vector vel, double pprincipal, double pretro) {
		super(name, type, masse, pos, vel);
		this.pprincipal = pprincipal;
		this.pretro = pretro;
	}
	
	
}
