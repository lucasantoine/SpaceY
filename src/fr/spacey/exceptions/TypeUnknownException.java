package fr.spacey.exceptions;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 26 oct. 2019
 * 
 *        Exception permettant d'avertir l'utilisateur qu'un type d'Entite
 *        inexistant a ete saisi.
 */
public class TypeUnknownException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de TypeUnknownException prenant en parametre un message
	 * d'erreur.
	 * 
	 * @param s Message d'erreur.
	 */
	public TypeUnknownException(String s) {
		super(s);
	}

	/**
	 * Constructeur derive de TypeUnknownException ayant un message d'erreur par
	 * defaut.
	 */
	public TypeUnknownException() {
		this("Ce type n'existe pas");
	}
}
