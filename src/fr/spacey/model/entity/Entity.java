package fr.spacey.model.entity;

import java.util.Set;

import fr.spacey.utils.Position;
import fr.spacey.utils.Velocity;

public abstract class Entity {
	
	private final String NAME;
	private final EntityType TYPE;
	private double masse;
	private Position pos;
	private Velocity vel;
	private boolean showInfo;
	
	public Entity(String name, EntityType type, double masse, Position pos, Velocity vel) {
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
	
	public void setMasse(double masse) {
		this.masse = masse;
	}

	public void updatePosition() {
		pos.setY(pos.getY()+vel.getX());
		pos.setX(pos.getX()+vel.getY());
	}

	public void applyMotion(Set<Entity> entities) {
		
	}

	public void toggleInfo() {
		this.showInfo = !this.showInfo;
	}
	
	public boolean isShowInfo() {
		return this.showInfo;
	}
}
