
package fr.spacey.utils;

import javafx.scene.image.Image;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 26 oct. 2019
 * 
 *        Enumeration contenant les differentes images pouvant habiller chaque
 *        Entite.
 */
public enum Sprite {

	TERRE("file:res/images/Terre.png"), LUNE("file:res/images/Lune.png"), MARS("file:res/images/Mars.png"),
	MERCURE("file:res/images/Mercure.png"), SOLEIL("file:res/images/Soleil.png"), VENUS("file:res/images/Venus.png"),
	JUPITER("file:res/images/Jupiter.png"), VAISSEAU("file:res/images/Vaisseau.png"), LOGO("file:res/images/fusee.png");

	private Image img;

	/**
	 * Constructeur prive de Sprite prenant en parametre le chemin d'acces a son
	 * image.
	 * 
	 * @param url Chemin d'acces vers une image.
	 */
	private Sprite(String url) {
		this.img = new Image(url);
	}

	/**
	 * Renvoie l'image correspondant a ce Sprite.
	 * 
	 * @return l'image correspondant a ce Sprite.
	 */
	public Image getImage() {
		return this.img;
	}
	
	/**
	 * Retourne une Image en fonction de son indice. Lance une erreur si l'image
	 * n'existe pas.
	 * 
	 * @param id Indice de l'image.
	 * @return une Image en fonction de son indice si il existe. Lance une erreur
	 *         sinon.
	 */
	public static Image getImage(int id) {
		if (id < 0 || id >= values().length)
			return values()[0].img;
		return values()[id].img;
	}
}
