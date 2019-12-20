package fr.spacey.model.entity;

import java.util.LinkedList;

import fr.spacey.utils.State;
import fr.spacey.utils.Vecteur;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 26 oct. 2019
 * 
 *        Classe heritant d'Entity permettant d'instancier des Entites Simulees.
 *        Sa Position, sa Vitesse et son Acceleration sont donc modifiables par
 *        les forces appliquees a cette Entite. Il existe une Entite Simulee
 *        speciale, le Vaisseau.
 * 
 */
public class Simule extends Entity {

	private LinkedList<Vecteur> trail;
	private int timeForTrail;

	/**
	 * Constructeur de Simule prenant en parametre son nom, son type, sa masse, sa
	 * position et sa vitesse.
	 * 
	 * @param name  Nom de l'Entite.
	 * @param type  Type de l'Entite.
	 * @param masse Masse de l'Entite.
	 * @param pos   Vecteur Position de l'Entite.
	 * @param vel   Vecteur Vitesse de l'Entite.
	 */
	protected Simule(String name, EntityType type, double masse, Vecteur pos, Vecteur vel) {
		super(name, type, masse, pos, vel);
		this.trail = new LinkedList<>();
		this.timeForTrail = 0;
	}

	/**
	 * Constructeur derive de Simule prenant en parametre son nom, sa masse, sa
	 * position et sa vitesse.
	 * 
	 * @param name  Nom de l'Entite.
	 * @param masse Masse de l'Entite.
	 * @param pos   Vecteur Position de l'Entite.
	 * @param vel   Vecteur Vitesse de l'Entite.
	 */
	public Simule(String name, double masse, Vecteur pos, Vecteur vel) {
		this(name, EntityType.SIMULE, masse, pos, vel);
		super.radius = masse * 10;
	}

	/**
	 * Change le Vecteur vitesse de l'Entite en celui passe en parametre.
	 * 
	 * @param vel Nouvelle vitesse de l'Entite.
	 */
	public void setVel(Vecteur vel) {
		this.getVel().setVector(vel);
	}

	/**
	 * Change le Vecteur position de l'Entite en celui passe en parametre.
	 * 
	 * @param pos Nouvelle position de l'Entite.
	 */
	public void setPos(Vecteur pos) {
		this.getPos().setVector(pos);
	}

	/**
	 * Renvoie la liste des Positions precedentes de l'Entite.
	 * 
	 * @return la liste des Positions precedentes de l'Entite.
	 */
	public LinkedList<Vecteur> getTrail() {
		return trail;
	}

	@Override
	public void updateState(State s) {
		super.setState(s);
	}

	/**
	 * Ajoute une nouvelle Position à la liste des Positions precedentes et, s'il y
	 * en a trop, supprime la plus ancienne.
	 */
	protected void previousPosition() {
		Vecteur newPos = new Vecteur(getPos().getX(), getPos().getY());
		if (this.timeForTrail++ > 200) {
			this.trail.add(newPos);
			if(this.getTrail().size() > 1000) {
				this.getTrail().removeFirst();
			}
			this.timeForTrail = 0;
		}
	}
}
