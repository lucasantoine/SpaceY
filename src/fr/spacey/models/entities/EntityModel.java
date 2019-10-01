package fr.spacey.models.entities;

import java.util.Observable;

import fr.spacey.SpaceY;
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

	public void updatePosition() {
		updateVelocity();
		pos.setY(pos.getY()+vel.getX());
		pos.setX(pos.getX()+vel.getY());
		setChanged();
		notifyObservers();
	}
	
	private void updateVelocity() {
		updateAcceleration();
		vel.setX(vel.getX()+acc.getX());
		vel.setY(vel.getY()+acc.getY());
	}
	
	private void updateAcceleration() {
		acc.setX(0.00002);
		acc.setY(-0.00005);
	}
	
	
	
	
}
