package fr.spacey.model.entity;

import fr.spacey.SpaceY;
import fr.spacey.utils.State;
import fr.spacey.utils.Vecteur;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 12 janv. 2020
 * 
 *        Classe heritant de Simule permettant d'instantier un Vaisseau. Le
 *        Vaisseau possede les memes caracteristiques qu'un objet Simule avec un
 *        propulseur en plus lui permettant ainsi de se deplacer dans la
 *        simulation.
 */
public class Vaisseau extends Simule {

	private final double TANKSIZE;

	public double angle;
	@SuppressWarnings("unused")
	private double pretro; // used for ship rotation
	private double rocketActivity;
	private double maxForce;
	private double fuel;
	private int facteur = -1;

	/**
	 * Constructeur de Vaisseau.
	 * 
	 * @param name     Nom du Vaisseau.
	 * @param masse    Masse du Vaisseau.
	 * @param pos      Position du Vaisseau.
	 * @param vel      Vitesse du Vaisseau.
	 * @param maxForce Propulsion avant du Vaisseau.
	 * @param pretro   Propulsion arriere du Vaisseau.
	 */
	public Vaisseau(String name, double masse, Vecteur pos, Vecteur vel, double maxForce, double pretro) {
		super(name, EntityType.VAISSEAU, masse, pos, vel);
		super.radius = masse * 10000;
		this.pretro = pretro;
		this.rocketActivity = 0;
		this.maxForce = maxForce;
		this.angle = 0;
		this.TANKSIZE = 10000;
		this.fuel = 10000;
	}

	/**
	 * Modifie la propulsion en la passant a son maximum.
	 */
	public void fullThrottle() {
		this.rocketActivity = 100;
	}

	/**
	 * Modifie la propulsion en la passant a son minimum.
	 */
	public void noThrottle() {
		this.rocketActivity = 0;
	}

	@Override
	public void updateState(State s) {
		rocketActivity += facteur;
		if (rocketActivity < 0)
			rocketActivity = 0;
		if (rocketActivity > 100)
			rocketActivity = 100;
		super.updateState(s);
	}

	/**
	 * Augmente la propulsion.
	 */
	public void upThrottle() {
		if ((int) this.getFuel() != 0)
			this.facteur = 1;
	}

	/**
	 * Baisse la propulsion.
	 */
	public void downThrottle() {
		this.facteur = -1;
	}

	/**
	 * Modifie l'angle en l'incrementant de d.
	 * 
	 * @param d Nombre ajoute a l'angle.
	 */
	public void incAngle(double d) {
		this.angle += d % 360;
		if (this.angle < 0)
			this.angle += 360;
		if (this.angle >= 360)
			this.angle -= 360;
	}

	/**
	 * Renvoie la valeur de propulsion du Vaisseau.
	 * 
	 * @return la valeur de propulsion du Vaisseau.
	 */
	public double getRocketActivity() {
		return this.rocketActivity;
	}

	/**
	 * Renvoie le nombre de fuel restant dans le vaisseau.
	 * 
	 * @return le nombre de fuel restant dans le vaisseau.
	 */
	public double getFuel() {
		return this.fuel;
	}

	/**
	 * Renvoie le facteur de propulsion du vaisseau.
	 * 
	 * @return le facteur de propulsion du vaisseau.
	 */
	public int getFacteur() {
		return facteur;
	}

	/**
	 * Renvoie la capacite du reservoir du vaisseau.
	 * 
	 * @return la capacite du reservoir du vaisseau.
	 */
	public double getTankSize() {
		return this.TANKSIZE;
	}

	/**
	 * Renvoie la force du Vaisseau.
	 * 
	 * @return la force du Vaisseau.
	 */
	public double getForce() {
		return this.maxForce * rocketActivity / 100.0;
	}

	/**
	 * Renvoie la force en X du Vaisseau.
	 * 
	 * @return la force en X du Vaisseau.
	 */
	public double getXForce() {
		return Math.cos(Math.toRadians(angle)) * this.getForce();
	}

	/**
	 * Renvoie la force en Y du Vaisseau.
	 * 
	 * @return la force en Y du Vaisseau.
	 */
	public double getYForce() {
		return Math.sin(Math.toRadians(angle)) * this.getForce();
	}

	/**
	 * Consume le fuel du vaisseau en fonction de la propulsion du vaisseau.
	 */
	public void consumeFuel() {
		this.fuel -= 0.1 * (this.rocketActivity / 100);
		if ((int) this.getFuel() == 0) {
			this.noThrottle();
		}
	}

	/**
	 * Renvoie l'angle du vaisseau.
	 * 
	 * @return l'angle du vaisseau.
	 */
	public double getAngle() {
		double value = (Math.atan(getVel().getY() / getVel().getX())) * (180 / SpaceY.getInstance().PI);
		if (getVel().getX() <= 0 || (getVel().getY() <= 0 && getVel().getX() <= 0))
			return value + 180;
		return value;
	}
}
