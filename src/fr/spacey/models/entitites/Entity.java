package fr.spacey.models.entitites;

import java.util.Observable;

import fr.spacey.models.entities.EntityType;
import fr.spacey.utils.Position;
import fr.spacey.utils.Velocity;

public abstract class Entity extends Observable {
	
	/* NE PAS OUBLIER setChanged() et notifyObservers() */
	
	private final String NAME;
	private final EntityType TYPE;
	private double masse;
	private Position pos;
	private Velocity vel;
	
	public Entity(String name, EntityType type, double masse, Position pos, Velocity vel) {
		this.TYPE = type;
		this.NAME = name;
		this.masse = masse;
		this.pos = pos;
		this.vel = vel;
	}
	
	public String getName() {
		return NAME;
	}

	public Position getPos() {
		return pos;
	}

	public void setPos(Position pos) {
		this.pos = pos;
	}

	public Velocity getVel() {
		return vel;
	}

	public void setVel(Velocity vel) {
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
}
