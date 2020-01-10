package fr.spacey.model.integration;

import java.util.Vector;

import fr.spacey.model.integrator.Integrator;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 6 déc. 2019
 */
public class EulerExplicite extends IntegrationStrategy {

	public EulerExplicite(Integrator i) {
		super(i);
	}

	public Vector<Double> newStates(Vector<Double> states, double t, double dt) {
		Vector<Double> newStates = i.getDerivative(states, t);
		return this.muladd(dt, newStates, states);
	}
	

	private Vector<Double> muladd(double dt, Vector<Double> newStates, Vector<Double> oldStates) {
		Vector<Double> res = new Vector<Double>();
		for (int i = 0; i < oldStates.size(); i++) {
			res.add(oldStates.get(i) + (dt * newStates.get(i)));
		}
		return res;
	}

}