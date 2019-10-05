package fr.spacey.model.entity;

import java.util.Set;

import fr.spacey.SpaceY;
import fr.spacey.utils.ImageLoader;
import fr.spacey.utils.Vector;
import javafx.scene.image.Image;

public abstract class Entity {
	
	private final String NAME;
	private final EntityType TYPE;
	private double masse;
	private Vector pos;
	private Vector vel;
	private Vector acc;
	private boolean showInfo;
	protected double radius;
	private Image img;
	
	public Entity(String name, EntityType type, double masse, Vector pos, Vector vel) {
		this.TYPE = type;
		this.NAME = name;
		this.masse = masse;
		this.pos = pos;
		this.vel = vel;
		this.showInfo = false;
		this.acc = new Vector(0, 0);
		this.radius = masse * 10;
		this.img = ImageLoader.MERCURE;
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

	public abstract void updatePosition(Set<Entity> entities);

	public void toggleInfo() {
		this.showInfo = !this.showInfo;
	}
	public boolean isShowInfo() {
		return this.showInfo;
	}

	public Image getImage() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

	protected void updateVelocity(Set<Entity> entities) {
		updateAcceleration(entities);
		vel.setVector(vel.add(acc));
	}

	private void updateAcceleration(Set<Entity> entities) {
		Vector force = new Vector(0, 0);
		for (Entity e : entities) {
			if(e != this) {
				Vector v = getForce(e);
				force.setVector(force.add(v));
			}
			
		}
		acc.setVector(force.getX() / this.masse, force.getY() / this.masse);
	}

	private Vector getForce(Entity entity) {
		Vector distance = entity.getPos().minus(this.getPos());
		double forceMagnitude = getForceMagnitude(entity);
		return new Vector(forceMagnitude * distance.getCosine(), forceMagnitude * distance.getSine());
	}

	private double getForceMagnitude(Entity entity) {
		return SpaceY.getInstance().gravite * this.masse * entity.getMasse() / Math.pow(this.pos.getDistanceTo(entity.getPos()), 2);
	}
}
