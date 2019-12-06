package fr.spacey.model.entity;

import java.util.LinkedList;
import java.util.List;

import fr.spacey.utils.Vector;

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

	private LinkedList<Vector> trail;
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
	protected Simule(String name, EntityType type, double masse, Vector pos, Vector vel) {
		super(name, type, masse, pos, vel);
		this.trail = new LinkedList<>();
		this.timeForTrail = 0;
	}
	
	/**
	 * Constructeur de Simule prenant en parametre son nom, son type, sa masse et sa
	 * position.
	 * 
	 * @param name  Nom de l'Entite.
	 * @param type  Type de l'Entite.
	 * @param masse Masse de l'Entite.
	 * @param pos   Vecteur Position de l'Entite.
	 */
	protected Simule(String name, EntityType type, double masse, Vector pos) {
		this(name, type, masse, pos, null);
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
	public Simule(String name, double masse, Vector pos, Vector vel) {
		this(name, EntityType.SIMULE, masse, pos, vel);
		super.radius = masse * 10;
	}

	/**
	 * Change le Vecteur vitesse de l'Entite en celui passe en parametre.
	 * 
	 * @param vel Nouvelle vitesse de l'Entite.
	 */
	public void setVel(Vector vel) {
		this.getVel().setVector(vel);
	}

	/**
	 * Change le Vecteur position de l'Entite en celui passe en parametre.
	 * 
	 * @param pos Nouvelle position de l'Entite.
	 */
	public void setPos(Vector pos) {
		this.getPos().setVector(pos);
	}

	/**
	 * Renvoie la liste des Positions precedentes de l'Entite.
	 * 
	 * @return la liste des Positions precedentes de l'Entite.
	 */
	public LinkedList<Vector> getTrail() {
		return trail;
	}

	@Override
	public void updatePosition(List<Entity> entities) {
		previousPosition();
		super.updateVelocity(entities);
		super.getPos().setVector(this.getPos().add(getVel()));
	}

	/**
	 * Ajoute une nouvelle Position à la liste des Positions precedentes et, s'il y
	 * en a trop, supprime la plus ancienne.
	 */
	protected void previousPosition() {
		Vector newPos = new Vector(getPos().getX(), getPos().getY());
		if (this.timeForTrail++ > 100) {
			this.trail.add(newPos);
			if (this.trail.size() > 100) {
				this.trail.removeFirst();
			}
			this.timeForTrail = 0;
		}
	}
}
