package fr.spacey.model;

import fr.spacey.controller.SpaceController;
import javafx.scene.image.Image;

public class Cryostase {
	public static final Image SPRITE=new Image("file:res/images/stase.png");
	private static final double FREEZESPEED=1.0/(60.0*3.0);
	private boolean active=false;
	private double frostLevel=0.0;
	private SpaceController sc;
	
	public Cryostase(SpaceController sc) {
		this.sc=sc;
	}
	
	public boolean isActive() {
		return active;
	}
	public double getFrostLevel() {
		return frostLevel;
	}
	public void switchActive() {
		this.active=!active;
	}
	public void freeze() {		
		if(this.frostLevel<1.0)this.frostLevel+=FREEZESPEED;
		if(this.frostLevel>1.0)this.frostLevel=1.0;
		if(this.frostLevel==1.0 && !sc.isFrozen())sc.toggleFreezing();
	}
	public void unfreeze() {
		if(this.frostLevel>0.0)this.frostLevel-=FREEZESPEED;
		if(this.frostLevel<0.0)this.frostLevel=0.0;
		if(sc.isFrozen())sc.toggleFreezing();
	}
	
	
}
