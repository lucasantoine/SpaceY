package fr.spacey.model.integration;

import java.util.Vector;

import fr.spacey.model.integrator.Integrator;

public abstract class IntegrationStrategy {

	protected Integrator i;
	private String name;

	public IntegrationStrategy(Integrator i, String name) {
		this.i = i;
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public abstract Vector<Double> newStates(Vector<Double> states, double t, double dt);
}
