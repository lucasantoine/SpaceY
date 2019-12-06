package fr.spacey.utils;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 26 oct. 2019
 * 
 *        Classe permettant de calculer le temps ecoule dans la simulation en
 *        fonction de son pas de temps et de son facteur d'acceleration.
 */
public class RenderTimer {

	private double lastLoopTime;

	/**
	 * Initialise le rendu du temps.
	 */
	public void init() {
		lastLoopTime = getTime();
	}

	/**
	 * Renvoie le temps de la simulation.
	 * 
	 * @return le temps de la simulation.
	 */
	public double getTime() {
		return System.nanoTime() / 1000_000_000.0;
	}

	/**
	 * Renvoie le temps ecoule depuis le dernier appel a cette methode.
	 * 
	 * @return le temps ecoule depuis le dernier appel a cette methode.
	 */
	public float getEllapsedTime() {
		double time = getTime();
		float ellapsedTime = (float) (time - lastLoopTime);
		lastLoopTime = time;
		return ellapsedTime;
	}

	/**
	 * Renvoie le temps du dernier calcul du temps.
	 * 
	 * @return le temps du dernier calcul du temps.
	 */
	public double getLastLoopTime() {
		return lastLoopTime;
	}
}
