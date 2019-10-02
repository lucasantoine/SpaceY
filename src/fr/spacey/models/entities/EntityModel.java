package fr.spacey.models.entities;

import java.util.Observable;

import fr.spacey.models.entities.types.EntityType;
import fr.spacey.utils.Position;
import fr.spacey.utils.Velocity;

public abstract class EntityModel extends Observable {
	
	/* NE PAS OUBLIER setChanged() et notifyObservers() */
	
	private final String NAME;
	private final EntityType TYPE;
	private double masse;
	private Position pos;
	private Velocity vel;
	private boolean showInfo;
	
	public EntityModel(String name, EntityType type, double masse, Position pos, Velocity vel) {
		this.TYPE = type;
		this.NAME = name;
		this.masse = masse;
		this.pos = pos;
		this.vel = vel;
		this.showInfo = false;
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
	
	public void toggleInfo() {
		this.showInfo = !this.showInfo;
	}
	
	public void setMasse(double masse) {
		this.masse = masse;
	}

	public void updatePosition() {
		pos.setY(pos.getY()+vel.getX());
		pos.setX(pos.getX()+vel.getY());
		setChanged();
		notifyObservers();
	}

	public boolean isShowInfo() {
		return this.showInfo;
	}
}
