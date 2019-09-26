package fr.spacey.controller;

import java.util.HashSet;
import java.util.Set;

import fr.spacey.models.entities.EntityModel;

public class SpaceController {
	
	private Set<EntityController> entities;
	
	public SpaceController() {
		this(new HashSet<EntityController>());
	}
	
	public SpaceController(Set<EntityController> entities) {
		this.entities = entities;
	}

	public Set<EntityController> getEntities() {
		return entities;
	}

	public boolean addEntityModel(EntityModel em) {
		return addEntityController(new EntityController(em));
	}
	
	public boolean addEntityController(EntityController ec) {
		return entities.add(ec);
	}
}
