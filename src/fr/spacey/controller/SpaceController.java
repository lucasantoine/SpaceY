package fr.spacey.controller;

import java.util.HashSet;
import java.util.Observer;
import java.util.Set;

import fr.spacey.models.entities.EntityModel;
import fr.spacey.models.entities.types.EntityType;
import fr.spacey.view.SpaceView;

public class SpaceController implements Runnable {
	
	private Set<EntityController> entities;
	private SpaceView sv;
	
	public SpaceController() {
		this.entities = new HashSet<EntityController>();
	}

	public Set<EntityController> getEntities() {
		return entities;
	}

	public boolean addEntityModel(EntityModel em, Observer observer) {
		EntityController ec = new EntityController(em, observer);
		return addEntityController(ec);
	}
	
	public boolean addEntityController(EntityController ec) {
		return entities.add(ec);
	}
	
	public void setView(SpaceView sv) {
		this.sv = sv;
	}

	@Override
	public void run() {
		sv.printBackground();
		for(EntityController ec : entities) {
			EntityModel em = ec.getEntityModel();
			em.updatePosition(entities);
		}
	}
}
