package fr.spacey.model.entity;

import java.util.ArrayList;
import java.util.List;

import fr.spacey.utils.Vector;

public class Ellipse extends Simule {
	private ArrayList<Entity> centers;

	public Ellipse(String name, double masse, Vector pos, Fixe f1, Fixe f2) {
		super(name, EntityType.CERCLE, masse, pos);
		this.centers = new ArrayList<>();
		this.centers.add(f1);
		this.centers.add(f2);
	}
	
	public void updatePosition(List<Entity> entities) {
		super.previousPosition();
		super.updateVelocity(centers);
		super.getPos().setVector(this.getPos().add(getVel()));
	}

}

