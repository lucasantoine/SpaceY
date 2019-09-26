package fr.spacey.controller;

import java.util.Observer;

import fr.spacey.models.entities.EntityModel;

public class EntityController {
	
	private EntityModel em;
	
	public EntityController(EntityModel em) {
		this.em = em;
	}
	
	public void register(Observer view) {
		em.addObserver(view);
	}
	
	public EntityModel getEntity() {
		return this.em;
	}
}
