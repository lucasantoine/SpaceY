package fr.spacey.controller;


import java.util.Observer;

<<<<<<< HEAD
import fr.spacey.model.SpaceModel;
=======
import fr.spacey.models.entities.EntityModel;
import fr.spacey.models.entities.types.EntityType;
import fr.spacey.view.SpaceView;
>>>>>>> refs/heads/Lucas

public class SpaceController {

	private SpaceModel sm;
	
	public SpaceController(SpaceModel space) {
		this.sm = space;
	}

	public void register(Observer view) {
		sm.addObserver(view);
	}

<<<<<<< HEAD
	public SpaceModel getModel() {
		return sm;
=======
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
>>>>>>> refs/heads/Lucas
	}
}
