package fr.spacey.model;

import fr.spacey.SpaceY;
import fr.spacey.controller.SpaceController;
import javafx.scene.image.Image;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 12 janv. 2020
 * 
 *        Classe permmettant a la simulation d'avancer dans le temps grace a la
 *        cryostase.
 */
public class Cryostase {
	public static final Image SPRITE = new Image(SpaceY.class.getResourceAsStream("/images/stase.png"));
	private static final double FREEZESPEED = 1.0 / (60.0 * 3.0);
	private boolean active = false;
	private double frostLevel = 0.0;
	private SpaceController sc;

	/**
	 * Constructeur de Cryostase.
	 * 
	 * @param sc Controller de la simulation.
	 */
	public Cryostase(SpaceController sc) {
		this.sc = sc;
	}

	/**
	 * Renvoie vrai si la cryostase est active, faux sinon.
	 * 
	 * @return vrai si la cryostase est active, faux sinon.
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Renvoie le niveau de cryostase.
	 * 
	 * @return le niveau de cryostase.
	 */
	public double getFrostLevel() {
		return frostLevel;
	}

	/**
	 * Active ou desactive la cryostase.
	 */
	public void switchActive() {
		this.active = !active;
	}

	/**
	 * Lance la cryostase.
	 */
	public void freeze() {
		if (this.frostLevel < 1.0)
			this.frostLevel += FREEZESPEED;
		if (this.frostLevel > 1.0)
			this.frostLevel = 1.0;
		if (this.frostLevel == 1.0 && !sc.isFrozen())
			sc.toggleFreezing();
	}

	/**
	 * Stoppe la cryostase.
	 */
	public void unfreeze() {
		if (this.frostLevel > 0.0)
			this.frostLevel -= FREEZESPEED;
		if (this.frostLevel < 0.0)
			this.frostLevel = 0.0;
		if (sc.isFrozen())
			sc.toggleFreezing();
	}

}
