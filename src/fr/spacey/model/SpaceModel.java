package fr.spacey.model;

import java.util.List;
import java.util.Observable;

import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.Vaisseau;

public class SpaceModel extends Observable {
	
	private List<Entity> entities;
	private Vaisseau vaisseau;
	private int selected;
	
	public SpaceModel(List<Entity> list) {
		this.entities = list;
		this.selected = -1;
		for(Entity e : entities)
			if(e instanceof Vaisseau) {
				this.vaisseau = (Vaisseau)e;
				this.vaisseau.setPropPrincipal(0.5);
			}
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
	
	public Vaisseau getVaisseau() {
		return vaisseau;
	}

	public boolean hasVaisseau() {
		return vaisseau != null;
	}
	
	public boolean hasEntitySelected() {
		return selected > -1;
	}

	public Entity getEntitySelected() {
		return entities.get(selected);
	}
	
	public void setEntitySelected(int idx) {
		this.selected = idx;
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
