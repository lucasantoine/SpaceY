package fr.spacey.model.integration;

import java.util.Vector;

import fr.spacey.model.integrator.Integrator;

public abstract class IntegrationStrategy {

	protected Integrator i;

	public IntegrationStrategy(Integrator i) {
		this.i = i;
	}
	
	public abstract Vector<Double> newStates(Vector<Double> e, double t, double dt);
}
