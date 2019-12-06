package fr.spacey.exceptions;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 26 oct. 2019
 * 
 *        Exception permettant d'avertir l'utilisateur qu'une erreur est survenu lors de la lecture d'un fichier au format astro
 */
public class AstroParserException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructeur de AstroParserException prenant en parametre un message
	 * d'erreur.
	 * 
	 * @param s Message d'erreur.
	 */
	public AstroParserException(String s) {
		super(s);
	}
	
}
