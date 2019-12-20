package fr.spacey.model.entity;

import fr.spacey.utils.State;
import fr.spacey.utils.Vecteur;

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

	public final static Vecteur VELOCITY_FIXE = new Vecteur(0, 0);

	/**
	 * Constructeur de Fixe prenant en parametre son nom, sa masse et sa position.
	 * 
	 * @param name  Nom de l'Entite.
	 * @param masse Masse de l'Entite.
	 * @param pos   Vecteur Position de l'Entite.
	 */
	public Fixe(String name, double masse, Vecteur pos) {
		super(name, EntityType.FIXE, masse, pos, VELOCITY_FIXE);
	}

	/**
	 * Ne modifie pas la Position de l'Entite.
	 */
	@Override
	public void updateState(State s) {
		return;
	}

	/**
	 * Renvoie une copie de cette Entite Fixe.
	 * 
	 * @return une copie de cette Entite Fixe.
	 */
	public Entity clone() {
		return new Fixe(new String(getName()), this.getMasse(), new Vecteur(getPos().getX(), getPos().getY()));
	}
}
