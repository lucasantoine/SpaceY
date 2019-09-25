package fr.spacey.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import fr.spacey.models.entitites.Entity;

public class EntityController {
	
	private List<Entity> entities;
	
	public EntityController() {
		this.entities = new ArrayList<Entity>();
	}
	
	public EntityController(List<Entity> ents) {
		this.entities = ents;
	}
	
	public void registerAll(Observer view) {
		for(Entity entity : entities)
			entity.addObserver(view);
	}
	
	public List<Entity> getEntities() {
		return entities;
	}
}
