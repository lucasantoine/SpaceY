package fr.spacey.model;

import java.util.Random;

import fr.spacey.utils.Vector;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 26 oct. 2019
 * 
 *        Classe contenant toutes les variables permettant le bon affichage de
 *        la simulation au cours du temps et des evenements.
 */
public class Affichage {

	private double width;
	private double height;
	private double xOffset;
	private double yOffset;
	private double startDragX;
	private double startDragY;
	private double startSceneX;
	private double startSceneY;
	private double zoom;
	


	private Vector[] stars;

	/**
	 * Constructeur d'Affichage initialisant chaque variable avec une valeur par
	 * defaut.
	 */
	public Affichage() {
		this.width = 1600;
		this.height = 900;
		this.startDragX = 0;
		this.startDragY = 0;
		this.xOffset = width / 2;
		this.yOffset = height / 2;
		this.zoom = 1;

		this.stars = new Vector[500];
		Random r = new Random();
		for (int i = 0; i < stars.length; i++) {
			this.stars[i] = new Vector(r.nextInt((int) (width * 4)) - width*2, r.nextInt((int) (height * 4)) - height*2);
		}
		
	}

	/**
	 * Renvoie la largeur de l'Affichage.
	 * 
	 * @return la largeur de l'Affichage.
	 */
	public double getWidth() {
		return width / zoom;
	}
	
	public double getAbsoluteWidth() {
		return width;
	}

	/**
	 * Modifie la largeur de l'Affichage par la valeur passee en parametre.
	 * 
	 * @param width Nouvelle largeur de l'Affichage.
	 */
	public void setWidth(double width) {
		this.width = width;
	}

	/**
	 * Renvoie la hauteur de l'Affichage.
	 * 
	 * @return la hauteur de l'Affichage.
	 */
	public double getHeight() {
		return height / zoom;
	}
	
	public double getAbsoluteHeight() {
		return height;
	}

	/**
	 * Modifie la hauteur de l'Affichage par la valeur passee en parametre.
	 * 
	 * @param height Nouvelle hauteur de l'Affichage.
	 */
	public void setHeight(double height) {
		this.height = height;
	}

	/**
	 * Renvoie la compensation en X de l'Affichage.
	 * 
	 * @return la compensation en X de l'Affichage.
	 */
	public double getxOffset() {
		return xOffset;
	}

	/**
	 * Modifie la compensation en X de l'Affichage par la valeur passee en
	 * parametre.
	 * 
	 * @param xOffset Nouvelle compensation en X de l'Affichage.
	 */
	public void setxOffset(double xOffset) {
		this.xOffset = xOffset;
	}

	/**
	 * Renvoie la compensation en X de l'Affichage.
	 * 
	 * @return la compensation en X de l'Affichage.
	 */
	public double getyOffset() {
		return yOffset;
	}

	/**
	 * Modifie la compensation en Y de l'Affichage par la valeur passee en
	 * parametre.
	 * 
	 * @param yOffset Nouvelle compensation en Y de l'Affichage.
	 */
	public void setyOffset(double yOffset) {
		this.yOffset = yOffset;
	}

	/**
	 * Renvoie la valeur du debut du deplacement en X de l'Affichage.
	 * 
	 * @return la valeur du debut du deplacement en X de l'Affichage.
	 */
	public double getStartDragX() {
		return startDragX;
	}

	/**
	 * Modifie la valeur du debut du deplacement en X de l'Affichage par la valeur
	 * passee en parametre.
	 * 
	 * @param startDragX Nouvelle valeur du debut du deplacement en X de
	 *                   l'Affichage.
	 */
	public void setStartDragX(double startDragX) {
		this.startDragX = startDragX;
	}

	/**
	 * Renvoie la valeur du debut du deplacement en Y de l'Affichage.
	 * 
	 * @return la valeur du debut du deplacement en Y de l'Affichage.
	 */
	public double getStartDragY() {
		return startDragY;
	}

	/**
	 * Modifie la valeur du debut du deplacement en Y de l'Affichage par la valeur
	 * passee en parametre.
	 * 
	 * @param startDragY Nouvelle valeur du debut du deplacement en Y de
	 *                   l'Affichage.
	 */
	public void setStartDragY(double startDragY) {
		this.startDragY = startDragY;
	}

	/**	
	 * Renvoie la position en X du debut de la Scene.
	 * 
	 * @return la position en X du debut de la Scene.
	 */
	public double getStartSceneX() {
		return startSceneX;
	}

	/**
	 * Modifie la valeur de la position en X du debut de la Scene par la valeur
	 * passee en parametre.
	 * 
	 * @param startSceneX Nouvelle valeur de la position en X du debut de la Scene.
	 */
	public void setStartSceneX(double startSceneX) {
		this.startSceneX = startSceneX;
	}

	/**
	 * Renvoie la position en Y du debut de la Scene.
	 * 
	 * @return la position en Y du debut de la Scene.
	 */
	public double getStartSceneY() {
		return startSceneY;
	}

	/**
	 * Modifie la valeur de la position en Y du debut de la Scene par la valeur
	 * passee en parametre.
	 * 
	 * @param startSceneY Nouvelle valeur de la position en Y du debut de la Scene.
	 */
	public void setStartSceneY(double startSceneY) {
		this.startSceneY = startSceneY;
	}

	/**
	 * Renvoie la valeur du zoom sur l'Affichage.
	 * 
	 * @return la valeur du zoom sur l'Affichage.
	 */
	public double getZoom() {
		return zoom;
	}

	/**
	 * Modifie la valeur du zoom sur l'Affichage par la valeur passee en parametre.
	 * 
	 * @param zoom Nouvelle valeur du zoom sur l'Affichage.
	 */
	public void setZoom(double zoom) {
		this.zoom = zoom;
		//System.out.println(width+" "+height+" "+zoom);
	}

	/**
	 * Renvoie un Array contenant la position des etoiles sur le fond de
	 * l'Affichage.
	 * 
	 * @return un Array contenant la position des etoiles sur le fond de
	 *         l'Affichage.
	 */
	public Vector[] getStars() {
		return stars;
	}
	

}
