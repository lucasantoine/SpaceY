package fr.spacey.controller;

import java.util.Observer;

import fr.spacey.models.entities.EntityModel;

public class EntityController {
	
	private EntityModel em;
	
	public EntityController(EntityModel em, Observer observer) {
		this.em = em;
		register(observer);
	}
	
	public void register(Observer view) {
		em.addObserver(view);
	}
	
	public EntityModel getEntityModel() {
		return this.em;
	}
}
