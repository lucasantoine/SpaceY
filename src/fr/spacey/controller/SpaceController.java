package fr.spacey.controller;

import java.util.Observer;

import fr.spacey.model.SpaceModel;

public class SpaceController {

	private SpaceModel sm;
	
	public SpaceController(SpaceModel space) {
		this.sm = space;
	}

	public void register(Observer view) {
		sm.addObserver(view);
	}

	public SpaceModel getModel() {
		return sm;
	}

}
