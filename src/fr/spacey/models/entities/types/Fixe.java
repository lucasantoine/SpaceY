package fr.spacey.models.entities.types;

import fr.spacey.models.entities.EntityModel;
import fr.spacey.utils.Vector;

public class Fixe extends EntityModel {
	
	public final static Vector VELOCITY_FIXE = new Vector(0, 0);
	
	public Fixe(String name, EntityType type, double masse, Vector pos) {
		super(name, type, masse, pos, VELOCITY_FIXE);
	}

	@Override
	public void updatePosition(EntityModel model) {
		setChanged();
		notifyObservers();
	}	
}
