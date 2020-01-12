package fr.spacey.utils;

/**
 * ProjetY - IUT A of Lille - 3rd Semester
 * 
 * @author ANTOINE Lucas Created on 5 oct. 2019
 * 
 *         Classe Vecteur contenant deux variables double permettant
 *         d'instancier les positions, les normes, les vitesses et les
 *         accelerations utilises par les Entites de la simulation.
 */
public class Vecteur {

	private double x, y;

	/**
	 * Constructeur de Vecteur prenant en parametre deux valeurs double.
	 * 
	 * @param x Valeur du premier parametre du Vecteur
	 * @param y Valeur du second parametre du Vecteur.
	 */
	public Vecteur(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vecteur(Vecteur v) {
		this(v.getX(), v.getY());
	}

	/**
	 * Renvoie la valeur du premier parametre du Vecteur.
	 * 
	 * @return la valeur du premier parametre du Vecteur.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Renvoie la valeur du second parametre du Vecteur.
	 * 
	 * @return la valeur du second parametre du Vecteur.
	 */
	public double getY() {
		return y;
	}

	/**
	 * Modifie les parametres de ce Vecteur avec ceux du Vecteur passe en parametre.
	 * 
	 * @param v Vecteur utilise pour modifier les parametres de ce Vecteur.
	 */
	public void setVector(Vecteur v) {
		this.setX(v.x);
		this.setY(v.y);
	}

	/**
	 * Modifie les parametres de ce Vecteur avec les valeurs passees en parametre.
	 * 
	 * @param x Nouvelle valeur du premier parametre de ce Vecteur.
	 * @param y Nouvelle valeur du second parametre de ce Vecteur.
	 */
	public void setVector(double x, double y) {
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Modifie le premier parametre de ce Vecteur avec la valeur passee en
	 * parametre.
	 * 
	 * @param x Nouvelle valeur du premier parametre de ce Vecteur.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Modifie le second parametre de ce Vecteur avec la valeur passee en parametre.
	 * 
	 * @param y Nouvelle valeur du second parametre de ce Vecteur.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Compare ce Vecteur avec celui passe en parametre. Renvoie vrai seulement si
	 * il s'agit de la meme instance, ou si la valeur de leurs parametres est
	 * identique. Renvoie faux sinon.
	 * 
	 * @param v Vecteur compare.
	 * @return vrai si il s'agit de la meme instance, ou si la valeur de leurs
	 *         parametres est identique, renvoie faux sinon.
	 */
	public boolean equals(Vecteur v) {
		if (this == v)
			return true;
		if (v == null)
			return false;
		if (getClass() != v.getClass())
			return false;
		if (x != v.x)
			return false;
		if (y != v.y)
			return false;
		return true;
	}

	/**
	 * Additionne la valeur des parametres du Vecteur v passe en parametre a ceux de
	 * ce Vecteur.
	  * * @param v Vecteur ajoute a ce Vecteur.
	 * @return l'addition des deux Vecteurs.
	 */
	public Vecteur add(double x, double y) {
		return new Vecteur(this.x + x, this.y + y);
	}
	
	/**
	 * Additionne la valeur des parametres du Vecteur v passe en parametre a ceux de
	 * ce Vecteur.
	 * 
	 * @param v Vecteur ajoute a ce Vecteur.
	 * @return l'addition des deux Vecteurs.
	 */
	public Vecteur add(Vecteur v) {
		return new Vecteur(this.x + v.x, this.y + v.y);
	}

	/**
	 * Soustrait la valeur des parametres du Vecteur v passe en parametre a ceux de
	 * ce Vecteur.
	 * 
	 * @param v Vecteur soustrait a ce Vecteur.
	 * @return la soustraction des deux Vecteurs.
	 */
	public Vecteur minus(Vecteur v) {
		return new Vecteur(this.x - v.x, this.y - v.y);
	}

	/*
	 * public Vector scalarproduct(Vector v) { return null; // TODO }
	 */

	/**
	 * Renvoie la distance entre ce Vecteur et le Vecteur passe en parametre.
	 * 
	 * @param v Vecteur utilise pour calculer la distance.
	 * @return la distance entre ce Vecteur et le Vecteur passe en parametre.
	 */
	public double getDistanceTo(Vecteur v) {
		return Math.sqrt(Math.pow(this.x - v.x, 2) + Math.pow(this.y - v.y, 2));
	}

	/**
	 * Renvoie la norme de ce Vecteur.
	 * 
	 * @return la norme de ce Vecteur.
	 */
	public double getMagnitude() {
		return Math.sqrt(x * x + y * y);
	}

	/**
	 * Renvoie une copie de ce Vecteur.
	 * 
	 * @return une copie de ce Vecteur.
	 */
	public Vecteur clone() {
		return new Vecteur(this.getX(), this.getY());
	}

	/**
	 * Renvoie la representation d'un Vecteur sous forme de texte.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.append('[').append(x).append(';').append(y).append(']').toString();
	}

	/**
	 * Renvoie la representation d'un Vecteur sous forme de texte en notation
	 * scientifique.
	 * 
	 * @return la representation d'un Vecteur sous forme de texte en notation
	 *         scientifique.
	 */
	public String toStringScientific() {
		StringBuilder sb = new StringBuilder();
		return sb.append('[').append(String.format("%4.2e", x)).append(';').append(String.format("%4.2e", y))
				.append(']').toString();
	}
}
