package fr.spacey.model;

import fr.spacey.controller.SpaceController;
import javafx.scene.image.Image;
/**
 * 
 *  @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 *  
 *  Classe contenant les outils nécessaires à la gestion de la cryostase
 *
 */
public class Cryostase {
	private static final int DEFAULTSTASEDURATION=120;// la stase dure de base ce nombre d'update
	public static final Image SPRITE=new Image("file:res/images/stase.png");
	private static final double FREEZESPEED=1.0/(60.0*3.0); //expression régulant la vitesse de la cryostase
	private boolean active=false;
	private double frostLevel=0.0;
	private SpaceController sc;
	
	/**
	 * Constructeur de cryostase nécessitant un SpaceController
	 * @param sc le SpaceController
	 */
	public Cryostase(SpaceController sc) {
		this.sc=sc;
	}
	
	/**
	 *Méthode indiquant si la cryostase est active 
	 * @return la cryostase est active
	 */
	public boolean isActive() {
		return active;
	}
	
	/**
	 * Méthode indiquant le niveau de gel de la stase, celle ci ne commence que lorsqu'elle est égale à 1.0
	 * @return le niveau de gel
	 */
	public double getFrostLevel() {
		return frostLevel;
	}
	
	/**
	 * Permet d'activer/désactiver la stase en augmentant/diminuant le niveau de gel
	 */
	public void switchActive() {
		this.active=!active;
	}
	
	/**
	 * Méthode responsable de l'augmentation du niveau de gel et gérant la pause lorsque le niveau de gel est maximum
	 */
	public void freeze() {		
		if(this.frostLevel<1.0)this.frostLevel+=FREEZESPEED;
		if(this.frostLevel>1.0)this.frostLevel=1.0;
		if(this.frostLevel==1.0 && SpaceController.isRunning()) {
			sc.toggleRunning();
			for(int i=0;i<DEFAULTSTASEDURATION;i++)sc.updatePosition();
			this.switchActive();
		}
	}
	
	/**
	 * Méthode responsable du retour à 0.0 progressif du niveau de gel
	 */
	public void unfreeze() {
		if(this.frostLevel>0.0)this.frostLevel-=FREEZESPEED;
		if(this.frostLevel<0.0)this.frostLevel=0.0;
		if(frostLevel!=1.0 && !SpaceController.isRunning())sc.toggleRunning();
	}
	
	
}
