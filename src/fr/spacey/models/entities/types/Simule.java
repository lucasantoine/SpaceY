package fr.spacey.models.entities.types;

import fr.spacey.models.entities.EntityModel;
import fr.spacey.utils.Vector;

public class Simule extends EntityModel {
	
	public Simule(String name, EntityType type, double masse, Vector pos, Vector vel) {
		super(name, type, masse, pos, vel);
	}

	@Override
	public void updatePosition(EntityModel entity) {
		super.updateVelocity(entity);
		super.getPos().setPos(this.getPos().add(getVel()));
		setChanged();
		notifyObservers();
	}
	
	
}
