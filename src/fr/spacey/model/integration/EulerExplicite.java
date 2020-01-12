package fr.spacey.model.integration;

import java.util.Vector;

import fr.spacey.model.integrator.Integrator;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 6 dec. 2019
 * 
 *        Classe implementant la methode d'integration Euler Explicite.
 */
public class EulerExplicite extends IntegrationStrategy {

	/**
	 * Constructeur d'EulerExcplicite.
	 * 
	 * @param i Integrateur.
	 */
	public EulerExplicite(Integrator i) {
		super(i, "EulerExplicite");
	}

	public Vector<Double> newStates(Vector<Double> states, double t, double dt) {
		Vector<Double> newStates = i.getDerivative(states, t);
		return this.muladd(dt, newStates, states);
	}

	/**
	 * Methode permettant d'ajouter dt * newStates a oldStates.
	 * 
	 * @param dt        dt de la simulation.
	 * @param newStates Derives des etats de la simulation.
	 * @param oldStates Etats de la simulation.
	 * @return les nouveaux etats de la simulation.
	 */
	private Vector<Double> muladd(double dt, Vector<Double> newStates, Vector<Double> oldStates) {
		Vector<Double> res = new Vector<Double>();
		for (int i = 0; i < oldStates.size(); i++) {
			res.add(oldStates.get(i) + (dt * newStates.get(i)));
		}
		return res;
	}

}