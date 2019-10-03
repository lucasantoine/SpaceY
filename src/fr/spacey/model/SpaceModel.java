package fr.spacey.model;

import java.util.Observable;
import java.util.Set;

import fr.spacey.model.entity.Entity;

public class SpaceModel extends Observable {
	
	private Set<Entity> entities;
	
	public SpaceModel(Set<Entity> list) {
		this.entities = list;
	}
	
	public Set<Entity> getEntities() {
		return entities;
	}
	
	public void update() {
		for(Entity e : entities) {
			e.updatePosition(entities);
		}
		setChanged();
		notifyObservers();
	}
	
}
