package fr.spacey.models.entities;

import java.util.Observable;
import java.util.Set;

import fr.spacey.SpaceY;
import fr.spacey.controller.EntityController;
import fr.spacey.models.entities.types.EntityType;
import fr.spacey.utils.Vector;

public abstract class EntityModel extends Observable {
	
	/* NE PAS OUBLIER setChanged() et notifyObservers() */
	
	private final String NAME;
	private final EntityType TYPE;
	private double masse;
	private Vector pos;
	private Vector vel;
	private Vector acc;
	
	public EntityModel(String name, EntityType type, double masse, Vector pos, Vector vel) {
		this.TYPE = type;
		this.NAME = name;
		this.masse = masse;
		this.pos = pos;
		this.vel = vel;
		this.acc = new Vector(0, 0);
	}
	
	public String getName() {
		return NAME;
	}

	public Vector getPos() {
		return pos;
	}
 
	public void setPos(Vector pos) {
		this.pos = pos;
	}

	public Vector getVel() {
		return vel;
	}

	public void setVel(Vector vel) {
		this.vel = vel;
	}

	public EntityType getType() {
		return TYPE;
	}

	public double getMasse() {
		return masse;
	}
	
	public void setMasse(double masse) {
		this.masse = masse;
	}

	public abstract void updatePosition(EntityModel model);
	
	protected void updateVelocity(EntityModel entity) {
		updateAcceleration(entity);
		vel.setX(vel.getX()+acc.getX());
		vel.setY(vel.getY()+acc.getY());
	}
	
	private void updateAcceleration(Set<EntityController> entities) {
		Vector force = new Vector(0,0);
		for(EntityController ec : entities) {
			if(ec.getEntityModel() != this) {
				force.add(getForce(ec.getEntityModel()));
			}
		}
		
		
	}
	
	private Vector getForce(EntityModel entity) {
		Vector distance = entity.getPos().minus(this.getPos());
		double forceMagnitude = getForceMagnitude(entity);
		return new Vector(forceMagnitude * distance.getCosine(), forceMagnitude * distance.getSine());
	}
	
	private double getForceMagnitude(EntityModel entity) {
		return SpaceY.gravite*this.masse*entity.getMasse()/Math.pow(this.pos.getDistanceTo(entity.getPos()), 2);
	}
	
	
	
	
}
