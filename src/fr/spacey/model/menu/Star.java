package fr.spacey.model.menu;

import java.util.Random;

import fr.spacey.utils.Vecteur;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 26 oct. 2019
 * 
 *        Classe Star permettant de creer les etoiles du fond d'ecran du menu
 *        principal.
 */
public class Star {

	private Vecteur pos;
	public double z;
	private int factorX;
	private int factorY;
	double inc;
	private double rayon;
	private double opacity;
	private Color color;
	private double ostartx;
	private double ostopx;
	private double ostarty;
	private double ostopy;

	/**
	 * Constructeur de Star.
	 * 
	 * @param pos    Vecteur Position de l'etoile.
	 * @param inc    Inc de l'etoile.
	 * @param z      Position en Z de l'etoile.
	 * @param width  Largeur de l'etoile.
	 * @param height Hauteur de l'etoile.
	 */
	public Star(Vecteur pos, double inc, double z, double width, double height) {
		this.pos = pos;
		this.factorX = Math.random() < 0.5 ? 1 : -1;
		this.factorY = Math.random() > 0.5 ? 1 : -1;
		this.inc = inc / 100;
		this.rayon = Math.random() * 5;
		this.opacity = Math.random();
		this.z = Math.random() * z;
		this.color = Math.random() < 0.5 ? Color.LIGHTSKYBLUE : Color.WHITE;
		this.ostartx = width / 2.0;
		this.ostarty = height / 2.0;
		switch (new Random().nextInt(4)) {
		case 0:
			this.ostopx = width;
			this.ostopy = 0;
			break;
		case 1:
			this.ostopx = width;
			this.ostopy = height;
			break;
		case 2:
			this.ostopx = 0;
			this.ostopy = height;
			break;
		case 3:
			this.ostopx = 0;
			this.ostopy = -height;
			break;
		default:
			break;
		}
	}

	/**
	 * Constructeur derive de Star.
	 * 
	 * @param x      Position en X de l'etoile.
	 * @param y      Position en Y de l'etoile.
	 * @param inc    Inc de l'etoile.
	 * @param z      Position en Z de l'etoile.
	 * @param width  Largeur de l'etoile.
	 * @param height Hauteur de l'etoile.
	 */
	public Star(double x, double y, double inc, double z, double width, double height) {
		this(new Vecteur(x, y), inc, z, width, height);
	}

	/**
	 * Renvoie le Vecteur Position de l'etoile.
	 * 
	 * @return le Vecteur Position de l'etoile.
	 */
	public Vecteur getPosition() {
		return pos;
	}

	/**
	 * Modifie la position de l'etoile si la simulation va demarrer.
	 * 
	 * @param isstart Vrai si la simulation va demarrer, Faux sinon.
	 */
	public void update(boolean isstart) {
		z -= 1;
		if (z < 1 && !isstart) {
			z = 1280;
			pos.setX(Math.random() * 1280);
			pos.setY(Math.random() * 720);
		} else if (isstart && z <= 0) {
			z = 0;
		}
	}

	/**
	 * Modifie le facteur en X de l'etoile par la valeur passee en parametre.
	 * 
	 * @param f Nouveau facteur en X de l'etoile.
	 */
	public void setFactorX(int f) {
		this.factorX = f;
	}

	/**
	 * Modifie le facteur en Y de l'etoile par la valeur passee en parametre.
	 * 
	 * @param f Nouveau facteur en Y de l'etoile.
	 */
	public void setFactorY(int f) {
		this.factorY = f;
	}

	/**
	 * Renvoie le facteur en X de l'etoile.
	 * 
	 * @return le facteur en X de l'etoile.
	 */
	public int getFactorX() {
		return factorX;
	}

	/**
	 * Renvoie le facteur en Y de l'etoile.
	 * 
	 * @return le facteur en Y de l'etoile.
	 */
	public int getFactorY() {
		return factorY;
	}

	/**
	 * Renvoie le rayon de l'etoile.
	 * 
	 * @return le rayon de l'etoile.
	 */
	public double getRayon() {
		return rayon;
	}

	/**
	 * Renvoie l'opacite de l'etoile.
	 * 
	 * @return l'opacite de l'etoile.
	 */
	public double getOpacity() {
		return opacity;
	}

	/**
	 * Modifie l'opacite de l'etoile par la valeur passee en parametre.
	 * 
	 * @param opacity Nouvelle opacite de l'etoile.
	 */
	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}

	/**
	 * Renvoie la Position en Z de l'etoile.
	 * 
	 * @return la Position en Z de l'etoile.
	 */
	public double getZ() {
		return z;
	}

	/**
	 * Renvoie la couleur de l'etoile.
	 * 
	 * @return la couleur de l'etoile.
	 */
	public Paint getColor() {
		return color;
	}

	/**
	 * Renvoie le debut en X de l'etoile.
	 * 
	 * @return le debut en X de l'etoile.
	 */
	public double getOstartx() {
		return ostartx;
	}

	/**
	 * Renvoie la fin en X de l'etoile.
	 * 
	 * @return la fin en X de l'etoile.
	 */
	public double getOstopx() {
		return ostopx;
	}

	/**
	 * Renvoie le debut en Y de l'etoile.
	 * 
	 * @return le debut en Y de l'etoile.
	 */
	public double getOstarty() {
		return ostarty;
	}

	/**
	 * Renvoie la fin en Y de l'etoile.
	 * 
	 * @return la fin en Y de l'etoile.
	 */
	public double getOstopy() {
		return ostopy;
	}

}
