package fr.spacey.utils;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 12 janv. 2020
 * 
 *        Etat comportant le Vecteur position et le Vecteur vitesse d'une
 *        Entite.
 */
public class State {
	private Vecteur position;
	private Vecteur velocity;

	/**
	 * Constructeur de State.
	 * 
	 * @param position Position de l'etat.
	 * @param velocity Vitesse de l'etat.
	 */
	public State(Vecteur position, Vecteur velocity) {
		this.position = position;
		this.velocity = velocity;
	}

	/**
	 * Constructeur derive de State.
	 * 
	 * @param posX Position en X de l'etat.
	 * @param posY Position en Y de l'etat.
	 * @param velX Vitesse en X de l'etat.
	 * @param velY Vitesse en Y de l'etat.
	 */
	public State(double posX, double posY, double velX, double velY) {
		this(new Vecteur(posX, posY), new Vecteur(velX, velY));
	}

	/**
	 * Renvoie la position de l'etat.
	 * 
	 * @return la position de l'etat.
	 */
	public Vecteur getPosition() {
		return position;
	}

	/**
	 * Renvoie la vitesse de l'etat.
	 * 
	 * @return la vitesse de l'etat.
	 */
	public Vecteur getVelocity() {
		return velocity;
	}

	/**
	 * Ajoute cet etat a celui passe en parametre.
	 * 
	 * @param s Etat a ajouter a celui-ci.
	 */
	public void add(State s) {
		this.position.add(s.getPosition());
		this.velocity.add(s.getVelocity());
	}

	/**
	 * Multiplie les parametres de cet etat par le facteur passe en parametre.
	 * 
	 * @param d Facteur de multiplication.
	 * @return cet etat multiplie par le facteur passe en parametre.
	 */
	public State multiply(double d) {
		return new State((this.getPosition().getX() * d), (this.getPosition().getY() * d),
				(this.getVelocity().getX() * d), (this.getVelocity().getY() * d));
	}

	@Override
	public String toString() {
		return "State [position=" + position + ", velocity=" + velocity + "]";
	}

}
