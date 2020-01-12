package fr.spacey.model.entity;

import fr.spacey.utils.ShowState;
import fr.spacey.utils.State;
import fr.spacey.utils.Vecteur;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 26 oct. 2019
 * 
 *        Classe abstraite permettant de decrire une Entite. Une Entite
 *        peut-etre Fixe, Simule. Une Entite est principalement determinee par
 *        son nom, sa masse, son type, sa position et sa vitesse. Ces parametres
 *        peuvent etres modifies au cours de la simulation par le controleur lie
 *        a la vue abonnee au modele.
 */
public abstract class Entity {

	private final String NAME;
	private final EntityType TYPE;
	private double masse;
	private State state;
	private ShowState infomode;
	protected double radius;
	private int imgId;

	/**
	 * Constructeur d'une Entity avec son nom, son type, sa masse, sa position et sa
	 * vitesse passes en parametres.
	 * 
	 * @param name  nom de l'Entite
	 * @param type  type de l'Entite
	 * @param masse masse de l'Entite
	 * @param pos   Vecteur position de l'Entite
	 * @param vel   Vecteur vitesse de l'Entite
	 */
	public Entity(String name, EntityType type, double masse, Vecteur pos, Vecteur vel) {
		this.TYPE = type;
		this.NAME = name;
		this.masse = masse;
		this.state = new State(pos, vel);
		this.infomode = ShowState.NOINFO;
		this.radius = masse * 2;
		this.imgId = 3;
	}

	/**
	 * Renvoie le rayon de l'Entite.
	 * 
	 * @return le rayon de l'Entite.
	 */
	public double getRadius() {
		return this.radius;
	}

	/**
	 * Renvoie le nom de l'Entite.
	 * 
	 * @return le nom de l'Entite.
	 */
	public String getName() {
		return NAME;
	}

	/**
	 * Renvoie le Vecteur position de l'Entite.
	 * 
	 * @return le Vecteur position de l'Entite.
	 */
	public Vecteur getPos() {
		return this.state.getPosition();
	}

	/**
	 * Renvoie le Vecteur vitesse de l'Entite.
	 * 
	 * @return le Vecteur vitesse de l'Entite.
	 */
	public Vecteur getVel() {
		return this.state.getVelocity();
	}

	/**
	 * Renvoie le type de l'Entite.
	 * 
	 * @return le type de l'Entite.
	 */
	public EntityType getType() {
		return TYPE;
	}

	/**
	 * Renvoie la masse de l'Entite.
	 * 
	 * @return la masse de l'Entite.
	 */
	public double getMasse() {
		return masse;
	}

	/**
	 * Change la masse de l'Entite en celle passee en parametre.
	 * 
	 * @param masse Nouvelle masse de l'Entite.
	 */
	public void setMasse(double masse) {
		this.masse = masse;
	}

	/**
	 * Renvoie l'etat de l'affichage des informations de l'Entite.
	 * 
	 * @return l'etat de l'affichage des informations de l'Entite.
	 */
	public ShowState getInfoMode() {
		return this.infomode;
	}

	/**
	 * Renvoie l'indice de l'image de l'Entite.
	 * 
	 * @return l'indice de l'image de l'Entite.
	 */
	public int getImgId() {
		return imgId;
	}

	/**
	 * Modifie l'etat de l'affichage des informations de cette Entite avec celui
	 * passe en parametres.
	 * 
	 * @param b Nouvel etat d'affichage des informations pour cette Entite.
	 */
	public void setInfo(ShowState b) {
		this.infomode = b;
	}

	/**
	 * Modifie l'indice de l'image de cette Entite avec celui passe en parametres.
	 * 
	 * @param i Nouvel indice d'image pour cette Entite.
	 */
	public void setImgId(int i) {
		this.imgId = i;
	}

	/**
	 * Compare cette Entitee avec celle passee en parametre. Renvoie vrai seulement
	 * si il s'agit de la meme instance, ou si la valeur de leurs parametres est
	 * identique. Renvoie faux sinon.
	 * 
	 * @param e Entitee comparee
	 * @return vrai seulement si il s'agit de la meme instance, ou si la valeur de
	 *         leurs parametres est identique. Renvoie faux sinon.
	 */
	public boolean equals(Entity e) {
		if (this == e)
			return true;
		if (e == null)
			return false;
		if (getClass() != e.getClass())
			return false;
		if (!this.getName().equals(e.getName()))
			return false;
		if (this.getType() != e.getType())
			return false;
		if (!this.getPos().equals(e.getPos()))
			return false;
		if (this.getMasse() != e.getMasse())
			return false;
		if (!this.getVel().equals(e.getVel()))
			return false;
		return true;
	}

	/**
	 * Renvoie l'etat de l'entite.
	 * 
	 * @return l'etat de l'entite.
	 */
	public State getState() {
		return this.state;
	}

	/**
	 * Modifie l'etat de l'entite.
	 * 
	 * @param s Nouvel etat de l'entite.
	 */
	public void setState(State s) {
		this.state = s;
	}

	/**
	 * Methode abstraite permettant de modifier l'etat de chaque Entite.
	 * 
	 * @param s Nouvel etat de l'entite
	 */
	public abstract void updateState(State s);
}
