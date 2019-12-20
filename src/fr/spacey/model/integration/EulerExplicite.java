package fr.spacey.model.integration;

import java.util.Vector;

import fr.spacey.model.SpaceModel;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 6 déc. 2019
 */
public class EulerExplicite implements IntegrationStrategy {

	private Integrator integrator;
	private SpaceModel model;

	public EulerExplicite(SpaceModel model) {
		this.integrator = new Integrator();
		this.model = model;
	}

	public void setModel(SpaceModel model) {
		this.model = model;
	}
	
	public Vector<Double> f(Vector<Double> e, double t) {
		Vector<Double> newStates = integrator.getDerivative(e, model.getEntities());
		newStates = this.muladd(model.getDt(), newStates, e);
		return newStates;
	}

	private Vector<Double> muladd(double dt, Vector<Double> newStates, Vector<Double> oldStates) {
		Vector<Double> res = new Vector<Double>();
		for (int i = 0; i < oldStates.size(); i++) {
			res.add(oldStates.get(i) + (dt * newStates.get(i)));
		}
		return res;
	}


}