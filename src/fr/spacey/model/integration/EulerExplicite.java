package fr.spacey.model.integration;

import java.util.List;

import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.Vaisseau;
import fr.spacey.utils.Vector;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 6 déc. 2019
 */
public class EulerExplicite implements IntegrationStrategy {

	@Override
	public void updatePosition(Entity e, List<Entity> entities) {
		updateVelocity(e, entities);
		e.getPos().setVector(e.getPos().add(e.getVel()));

	}

	/**
	 * Modifie le Vecteur vitesse de l'Entite en fonction de son Vecteur
	 * acceleration.
	 * 
	 * @param entities Array contenant l'ensemble des Entites de la simulation.
	 */
	private void updateVelocity(Entity e, List<Entity> entities) {
		updateAcceleration(e, entities);
		e.getVel().setVector(e.getVel().add(e.getAcc()));
	}

	/**
	 * Modifie le Vecteur acceleration de l'Entite en fonction de la force exercee
	 * par les autres Entites de la simulation sur elle.
	 * 
	 * @param entities Array contenant l'ensemble des Entites de la simulation.
	 */
	private void updateAcceleration(Entity e, List<Entity> entities) {
		Vector force = new Vector(0, 0);
		for (Entity e2 : entities) {
			if (e2 != e) {
				force.setVector(force.add(new Vector(this.getForceX(e, e2), this.getForceY(e, e2))));
			} else if (e instanceof Vaisseau) {
				Vaisseau vaisseau = (Vaisseau) e2;
				if (vaisseau.getFuel() >= 0) {
					force.setVector(force.add(new Vector(vaisseau.getXForce(), vaisseau.getYForce())));
					vaisseau.consumeFuel();
				}
			}
		}
		e.getAcc().setVector(force.getX() / e.getMasse(), force.getY() / e.getMasse());
	}

	/**
	 * Renvoie la force exercee en X par l'Entite passee en parametre sur cette
	 * Entite.
	 * 
	 * @param entity Entite exercant une force sur cette Entite.
	 * @return la force exercee en X par l'Entite passee en parametre sur cette
	 *         Entite.
	 */
	private double getForceX(Entity e, Entity entity) {
		return (SpaceModel.G * e.getMasse() * entity.getMasse() / Math.pow(e.getPos().getDistanceTo(entity.getPos()), 3))
				* entity.getPos().minus(e.getPos()).getX();
	}

	/**
	 * Renvoie la force exercee en Y par l'Entite passee en parametre sur cette
	 * Entite.
	 * 
	 * @param entity Entite exercant une force sur cette Entite.
	 * @return la force exercee en Y par l'Entite passee en parametre sur cette
	 *         Entite.
	 */
	private double getForceY(Entity e, Entity entity) {
		return (SpaceModel.G * e.getMasse() * entity.getMasse() / Math.pow(e.getPos().getDistanceTo(entity.getPos()), 3))
				* entity.getPos().minus(e.getPos()).getY();
	}

}
