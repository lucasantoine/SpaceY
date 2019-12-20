package fr.spacey.model.integration;

import java.util.Vector;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 6 déc. 2019S
 */
public interface IntegrationStrategy {
	
	/**
	 * Methode permettant de modifier la position de chaque Entite en
	 * fonction des autres Entites presentes dans la simulation.
	 * 
	 * @param entities Liste des entites presentes dans la simulation.
	 */
	public Vector<Double> f(Vector<Double> e, double t);
}
