package fr.spacey.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import fr.spacey.models.entitites.Entity;

public class EntityController {
	
	private List<Entity> entities;
	
	public EntityController(Entity entity) {
		this.entities = new ArrayList<Entity>();
	}
	
	public void registerAll(Observer view) {
		for(Entity entity : entities)
			entity.addObserver(view);
	}
}
