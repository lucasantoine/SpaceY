package fr.spacey.model.entity;

import java.util.List;

import fr.spacey.SpaceY;
import fr.spacey.controller.SpaceController;
import fr.spacey.utils.ShowState;
import fr.spacey.utils.Vector;

public abstract class Entity {
	
	private final String NAME;
	private final EntityType TYPE;
	private double masse;
	private Vector pos;
	private Vector vel;
	private Vector acc;
	private ShowState infomode;
	protected double radius;
	private int imgId;
	
	public Entity(String name, EntityType type, double masse, Vector pos, Vector vel) {
		this.TYPE = type;
		this.NAME = name;
		this.masse = masse;
		this.pos = pos;
		this.vel = vel;
		this.infomode = ShowState.NOINFO;
		this.acc = new Vector(0, 0);
		this.radius = masse * 2;
		this.imgId = 3;
	}
	
	public double getRadius() {
		return this.radius;
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

	public abstract void updatePosition(List<Entity> entities);
	
	public ShowState getInfoMode() {
		return this.infomode;
	}

	public int getImgId() {
		return imgId;
	}

	protected void updateVelocity(List<Entity> entities) {
		updateAcceleration(entities);
		vel.setVector(vel.add(acc));
	}

	private void updateAcceleration(List<Entity> entities) {
		Vector force = new Vector(0, 0);
		for (Entity e : entities) {
			if(e != this) {
				Vector v = getForce(e);
				force.setVector(force.add(v));
			}
			
		}
		acc.setVector(force.getX() / this.masse, force.getY() / this.masse);
	}
	
	public Vector getAcc() {
		return acc;
	}

	private Vector getForce(Entity entity) {
		Vector distance = entity.getPos().minus(this.getPos());
		double forceMagnitude = getForceMagnitude(entity);
		return new Vector(forceMagnitude * distance.getCosine(), forceMagnitude * distance.getSine());
	}

	private double getForceMagnitude(Entity entity) {
		return SpaceController.G * this.masse * entity.getMasse()
				/ Math.pow(this.pos.getDistanceTo(entity.getPos()), 2);
	}

	public void setInfo(ShowState b) {
		this.infomode = b;
	}

	public void setImgId(int i) {
		this.imgId = i;
	}
}
