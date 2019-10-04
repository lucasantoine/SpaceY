package fr.spacey.model.entity;

import java.util.Set;

import fr.spacey.SpaceY;
import fr.spacey.utils.Vector;

public abstract class Entity {
	
	private final String NAME;
	private final EntityType TYPE;
	private double masse;
	private Vector pos;
	private Vector vel;
	private Vector acc;
	private boolean showInfo;
	
	public Entity(String name, EntityType type, double masse, Vector pos, Vector vel) {
		this.TYPE = type;
		this.NAME = name;
		this.masse = masse;
		this.pos = pos;
		this.vel = vel;
		this.showInfo = false;
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

	public abstract void updatePosition(Set<Entity> entities);

	public void toggleInfo() {
		this.showInfo = !this.showInfo;
	}
	
	public boolean isShowInfo() {
		return this.showInfo;
	}
	
	protected void updateVelocity(Set<Entity> entities) {
		updateAcceleration(entities);
		vel.setX(vel.getX()+acc.getX());
		vel.setY(vel.getY()+acc.getY());
	}
	
	private void updateAcceleration(Set<Entity> entities) {
		Vector force = new Vector(0,0);
		for(Entity e : entities) {
			if(e != this) {
				force.add(getForce(e));
			}
		}
		
		
	}
	
	private Vector getForce(Entity entity) {
		Vector distance = entity.getPos().minus(this.getPos());
		double forceMagnitude = getForceMagnitude(entity);
		return new Vector(forceMagnitude * distance.getCosine(), forceMagnitude * distance.getSine());
	}
	
	private double getForceMagnitude(Entity entity) {
		return SpaceY.getInstance().gravite*this.masse*entity.getMasse()/Math.pow(this.pos.getDistanceTo(entity.getPos()), 2);
	}
}
