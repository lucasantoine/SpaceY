package fr.spacey.model.integration;

import java.util.Vector;

import fr.spacey.model.integrator.Integrator;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 12 janv. 2020
 * 
 *        Classe abstraite permettant d'implementer differentes methodes
 *        d'integration.
 */
public abstract class IntegrationStrategy {

	protected Integrator i;
	private String name;

	/**
	 * Constructeur d'IntegrationStrategy.
	 * 
	 * @param i    Integrateur.
	 * @param name Nom de la methode d'integration.
	 */
	public IntegrationStrategy(Integrator i, String name) {
		this.i = i;
		this.name = name;
	}

	/**
	 * Renvoie le nom de la methode d'integration.
	 * 
	 * @return le nom de la methode d'integration.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Methode permettant d'appliquer la bonne methode d'integration aux etats de la
	 * simulation.
	 * 
	 * @param states Etats de la simulation.
	 * @param t      Temps de la simulation.
	 * @param dt     dt de la simulation.
	 * @return les nouveaux etats de la simulation.
	 */
	public abstract Vector<Double> newStates(Vector<Double> states, double t, double dt);
}
