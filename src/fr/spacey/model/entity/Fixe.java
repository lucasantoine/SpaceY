package fr.spacey.model.entity;

import java.util.List;

import fr.spacey.utils.Vector;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 26 oct. 2019
 * 
 *        Classe heritant d'Entity permettant d'instancier des Entites Fixes,
 *        qui ont donc une Position definie et non-modifiable, ainsi qu'une
 *        Vitesse et une Acceleration nulle.
 */
public class Fixe extends Entity {

	public final static Vector VELOCITY_FIXE = new Vector(0, 0);

	/**
	 * Constructeur de Fixe prenant en parametre son nom, sa masse et sa position.
	 * 
	 * @param name  Nom de l'Entite.
	 * @param masse Masse de l'Entite.
	 * @param pos   Vecteur Position de l'Entite.
	 */
	public Fixe(String name, double masse, Vector pos) {
		super(name, EntityType.FIXE, masse, pos, VELOCITY_FIXE);
	}

	/**
	 * Ne modifie pas la Position de l'Entite.
	 */
	@Override
	public void updatePosition(Entity e, List<Entity> entities) {
		return;
	}

	/**
	 * Renvoie une copie de cette Entite Fixe.
	 * 
	 * @return une copie de cette Entite Fixe.
	 */
	public Entity clone() {
		return new Fixe(new String(getName()), this.getMasse(), new Vector(getPos().getX(), getPos().getY()));
	}
}
