package fr.spacey.controller;

import java.util.Observer;

import fr.spacey.models.entitites.Entity;

public class EntityController {
	
	private Entity entity;
	
	public EntityController(Entity entity) {
		this.entity = entity;
	}
	
	public void register(Observer view) {
		entity.addObserver(view);
	}
}
