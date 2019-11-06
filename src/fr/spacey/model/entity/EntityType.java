package fr.spacey.model.entity;

import fr.spacey.exceptions.TypeUnknownException;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas - Cree le 25 oct. 2019
 * 
 *         Enumeration des differents types d'Entites celestes. Une Entite peut
 *         etre Fixe, Simulee, Elliptique, Circulaire ou un Vaisseau.
 */
public enum EntityType {

	FIXE("Fixe"), SIMULE("Simul\u00E9"), ELLIPSE("Ellipse"), CERCLE("Cercle"), VAISSEAU("Vaisseau");

	public final String NOM;

	/**
	 * Constructeur prive d'EntityType prenant en parametre son nom.
	 * 
	 * @param nom Nom de l'EntityType.
	 */
	private EntityType(String nom) {
		this.NOM = nom;
	}

	/**
	 * Renvoie l'EntityType correspondant au nom passe en parametre. Envoie une
	 * exception TypeUnknownException si le nom ne correspond a aucun EntityType.
	 * 
	 * @param name Nom de l'EntityType.
	 * @return EntityType correspondant au nom passe en parametre.
	 * @throws TypeUnknownException Exception jetee si le nom ne correspond a aucun
	 *                              EntityType.
	 */
	public static EntityType getByName(String name) throws TypeUnknownException {
		if (name.equals(FIXE.NOM)) {
			return FIXE;
		} else if (name.equals(SIMULE.NOM)) {
			return SIMULE;
		} else if (name.equals(ELLIPSE.NOM)) {
			return ELLIPSE;
		} else if (name.equals(CERCLE.NOM)) {
			return CERCLE;
		} else if (name.equals(VAISSEAU.NOM)) {
			return VAISSEAU;
		}
		throw new TypeUnknownException();
	}

}
