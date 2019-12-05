package fr.spacey.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Cryostase {
	private static final Image SPRITE=new Image("file:res/images/stase.png");
	private static final double FREEZESPEED=1.0/(60.0*3.0);
	private boolean active=false;
	private double frostLevel=0.0;
	
	protected boolean isActive() {
		return active;
	}
	protected double getFrostLevel() {
		return frostLevel;
	}
	protected void switchActive() {
		this.active=!active;
	}
	protected void freeze() {
		if(this.frostLevel<1.0)this.frostLevel+=FREEZESPEED;
		if(this.frostLevel>1.0)this.frostLevel=1.0;
	}
	protected void unfreeze() {
		if(this.frostLevel>0.0)this.frostLevel-=FREEZESPEED;
		if(this.frostLevel<0.0)this.frostLevel=0.0;	
	}
	
	protected void drawStase(GraphicsContext gc) {
		if(active)this.freeze();
		else this.unfreeze();
			double alpha=gc.getGlobalAlpha();
			gc.setGlobalAlpha(frostLevel);
			gc.drawImage(SPRITE, 0, 0);
			gc.setGlobalAlpha(alpha);
			System.out.println(this.frostLevel);

	}
	
}
