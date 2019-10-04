package fr.spacey.model.entity;

import java.util.Set;

import fr.spacey.utils.Vector;

public class Vaisseau extends Entity {

	private double pprincipal;
	private double pretro;
	
	public Vaisseau(String name, EntityType type, double masse, Vector pos, Vector vel, double pprincipal, double pretro) {
		super(name, type, masse, pos, vel);
		super.radius = 1;
		this.pprincipal = pprincipal;
		this.pretro = pretro;
	}

	@Override
	public void updatePosition(Set<Entity> entities) {
		super.updateVelocity(entities);
		super.getPos().setPos(this.getPos().add(getVel()));
	}
	
	
}
