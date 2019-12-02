
package fr.spacey.utils;

import java.io.InputStream;

import fr.spacey.SpaceY;
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

	TERRE(SpaceY.class.getClassLoader().getResourceAsStream("images/Terre.png")), 
	LUNE(SpaceY.class.getClassLoader().getResourceAsStream("images/Lune.png")), 
	MARS(SpaceY.class.getClassLoader().getResourceAsStream("images/Mars.png")),
	MERCURE(SpaceY.class.getClassLoader().getResourceAsStream("images/Mercure.png")), 
	SOLEIL(SpaceY.class.getClassLoader().getResourceAsStream("images/Soleil.png")), 
	VENUS(SpaceY.class.getClassLoader().getResourceAsStream("images/Venus.png")),
	JUPITER(SpaceY.class.getClassLoader().getResourceAsStream("images/Jupiter.png")), 
	VAISSEAU(SpaceY.class.getClassLoader().getResourceAsStream("images/Vaisseau.png")), 
	LOGO(SpaceY.class.getClassLoader().getResourceAsStream("images/fusee.png"));
	
	private Image img;

	/**
	 * Constructeur prive de Sprite prenant en parametre le chemin d'acces a son
	 * image.
	 * 
	 * @param url Chemin d'acces vers une image.
	 */
	private Sprite(InputStream url) {
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
			throw new Error("L'image " + id + " n'existe pas.");
		return values()[id].img;
	}
}
