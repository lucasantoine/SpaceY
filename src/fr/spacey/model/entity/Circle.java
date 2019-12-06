package fr.spacey.model.entity;

import java.util.ArrayList;
import java.util.List;

import fr.spacey.utils.Vector;

public class Circle extends Simule {

	private ArrayList<Entity> center;

	public Circle(String name, double masse, Vector pos, Entity center) {
		super(name, EntityType.CERCLE, masse, pos);
		this.center = new ArrayList<Entity>();
		this.center.add(center);
	}

	public void updatePosition(List<Entity> entities) {
		super.previousPosition();
		super.updateVelocity(center);
		super.getPos().setVector(this.getPos().add(getVel()));
	}

}
