package fr.spacey.model;

import java.util.Observable;
import java.util.Set;

import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.Vaisseau;

public class SpaceModel extends Observable {
	
	private Set<Entity> entities;
	private Vaisseau vaisseau;
	private Entity selected;
	
	public SpaceModel(Set<Entity> list) {
		this.entities = list;
		this.selected = null;
		for(Entity e : entities)
			if(e instanceof Vaisseau) {
				this.vaisseau = (Vaisseau)e;
				this.vaisseau.setPropPrincipal(0.5);
			}
	}
	
	public Set<Entity> getEntities() {
		return entities;
	}
	
	public Vaisseau getVaisseau() {
		return vaisseau;
	}

	public boolean hasVaisseau() {
		return vaisseau != null;
	}
	
	public boolean hasEntitySelected() {
		return selected != null;
	}

	public Entity getEntitySelected() {
		return selected;
	}
	
	public void setEntitySelected(Entity e) {
		this.selected = e;
	}
	
	public void updatePositions() {
		for(Entity e : entities) {
			e.updatePosition(entities);
		}
		setChanged();
		notifyObservers();
	}

	public void askForRender() {
		setChanged();
		notifyObservers();
	}
	
}
