package fr.spacey.model.integrator;

import java.util.List;
import java.util.Vector;

import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.Vaisseau;
import fr.spacey.utils.Vecteur;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 13 janv. 2020
 * 
 *        Integrateur permettant d'obtenir la derivee des etats de la
 *        simulation.
 */
public class Integrator {
	private SpaceModel model;

	/**
	 * Constructeur d'Integrator.
	 * 
	 * @param model Modele de la simulation.
	 */
	public Integrator(SpaceModel model) {
		this.model = model;
	}

	/**
	 * Renvoie la derivee des etats de la simulation.
	 * 
	 * @param states Etats de la simulation.
	 * @param t      Temps de la simulation.
	 * @return la derivee des etats de la simulation.
	 */
	public Vector<Double> getDerivative(Vector<Double> states, double t) {
		@SuppressWarnings("unchecked")
		Vector<Double> derivative = (Vector<Double>) states.clone();
		this.getVelocity(derivative);
		this.getAcceleration(derivative, model.getEntities());
		return derivative;
	}

	/**
	 * Integre les positions de la simulation.
	 * 
	 * @param states Etats de la simulation.
	 */
	private void getVelocity(Vector<Double> states) {
		for (int i = 2; i < states.size(); i += 3) {
			states.set(i - 2, states.get(i));
			i++;
			states.set(i - 2, states.get(i));
		}
	}

	/**
	 * Integre les vitesses de la simulation.
	 * 
	 * @param states   Etats de la simulation.
	 * @param entities Entites de la simulation.
	 */
	public void getAcceleration(Vector<Double> states, List<Entity> entities) {
		for (int i = 0; i < entities.size(); i++) {
			Vecteur force = new Vecteur(0, 0);
			Entity e = entities.get(i);
			for (Entity e2 : entities) {
				if (e2 != e) {
					force.setVector(force.add(this.getForce(e, e2)));
				} else if (e instanceof Vaisseau) {
					Vaisseau vaisseau = (Vaisseau) e2;
					if (vaisseau.getFuel() >= 0) {
						force.setVector(force.add(new Vecteur(vaisseau.getXForce(), vaisseau.getYForce())));
						vaisseau.consumeFuel();
					}
				}
			}
			states.set((i * 4) + 2, force.getX() / e.getMasse());
			states.set((i * 4) + 3, force.getY() / e.getMasse());
		}
	}

	/**
	 * Renvoie le vecteur force entre e et e2.
	 * 
	 * @param e  Premiere Entite.
	 * @param e2 Seconde Entite.
	 * @return le vecteur force entre e et e2.
	 */
	public Vecteur getForce(Entity e, Entity e2) {
		double cste = SpaceModel.G * e.getMasse() * e2.getMasse() / Math.pow(e.getPos().getDistanceTo(e2.getPos()), 3);
		return new Vecteur(cste * e2.getPos().minus(e.getPos()).getX(), cste * e2.getPos().minus(e.getPos()).getY());
	}
}
