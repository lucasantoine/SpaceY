package fr.spacey.model.integration;

import java.util.Vector;

import fr.spacey.model.integrator.Integrator;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 12 janv. 2020
 * 
 *        Classe implementant la methode d'integration Runge Kutta 4.
 */
public class Rk4 extends IntegrationStrategy {

	/**
	 * Constructeur de Rk4.
	 * 
	 * @param i Integrateur.
	 */
	public Rk4(Integrator i) {
		super(i, "RK4");
	}

	@Override
	public Vector<Double> newStates(Vector<Double> states, double t, double dt) {
		Vector<Double> k1 = i.getDerivative(states, dt);
		Vector<Double> k2 = i.getDerivative(this.muladd(dt / 2, k1, states), t + (dt / 2));
		Vector<Double> k3 = i.getDerivative(this.muladd(dt / 2, k2, states), t + (dt / 2));
		Vector<Double> k4 = i.getDerivative(this.muladd(dt, k3, states), t + dt);
		Vector<Double> k = this.add(k1, this.add(this.multiply(k2, 2), this.add(this.multiply(k3, 2), k4)));
		return this.muladd(dt / 6, k, states);
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

	/**
	 * Ajoute second a first.
	 * 
	 * @param first  Liste de double.
	 * @param second Liste de double.
	 * @return l'addition de first et second.
	 */
	private Vector<Double> add(Vector<Double> first, Vector<Double> second) {
		Vector<Double> res = new Vector<Double>();
		for (int i = 0; i < first.size(); i++) {
			res.add(first.get(i) + second.get(i));
		}
		return res;
	}

	/**
	 * Multplie la liste par value.
	 * 
	 * @param states Liste de double.
	 * @param value  Entier a multiplier.
	 * @return la multiplication entre states et value.
	 */
	private Vector<Double> multiply(Vector<Double> states, int value) {
		Vector<Double> res = new Vector<Double>();
		for (int i = 0; i < states.size(); i++) {
			res.add(states.get(i) * value);
		}
		return res;
	}

}
