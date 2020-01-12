
package fr.spacey.model.entity;

import fr.spacey.SpaceY;
import fr.spacey.utils.State;
import fr.spacey.utils.Vecteur;

public class Vaisseau extends Simule {

	private final double TANKSIZE;
	
	private double angle;
	private double pretro; //used for ship rotation
	private double rocketActivity;
	private double maxForce;
	private double fuel;
	private int facteur = -1;
	
	public Vaisseau(String name, double masse, Vecteur pos, Vecteur vel, double pretro, double maxForce) {
		super(name, EntityType.VAISSEAU, masse, pos, vel);
		super.radius = masse * 10000;
		this.pretro = pretro;
		this.rocketActivity = 0;
		this.maxForce = maxForce;
		this.angle = 0;
		this.TANKSIZE = 10000;
		this.fuel = 10000;
	}
	
	public void fullThrottle() {
		this.rocketActivity=100;
	}
	
	public void noThrottle() {
		this.rocketActivity=0;
	}
	
	@Override
	public void updateState(State s) {
		rocketActivity += facteur;
		if(rocketActivity < 0) rocketActivity = 0;
		if(rocketActivity > 100) rocketActivity = 100;
		super.updateState(s);
	}
	
	public void upThrottle() {
		if((int)this.getFuel() != 0)
			this.facteur = 1;
	}
	public void downThrottle() {
		this.facteur = -1;
	}
	
	public void incAngle(double d) {
		this.angle+=d%360;
		if(this.angle<0)this.angle+=360;
		if(this.angle>=360)this.angle-=360;
	}
	
	public double getRocketActivity() {
		return this.rocketActivity;
	}

	public double getFuel() {
		return this.fuel;
	}

	public double getTankSize() {
		return this.TANKSIZE;
	}
	
	public double getForce() {
		return this.maxForce*rocketActivity/100.0;
	}
	
	public double getXForce() {
		return Math.cos(Math.toRadians(getAngle()))*this.getForce();
	}
	public double getYForce() {
		return Math.sin(Math.toRadians(getAngle()))*this.getForce();
	}

	public void consumeFuel() {
		this.fuel-=0.1*(this.rocketActivity/100);
		if((int)this.getFuel() == 0) { this.noThrottle(); }
	}
	
	public double getAngle() {
		double value = (Math.atan(getVel().getY() / getVel().getX())) * (180 / SpaceY.getInstance().PI);
		if(getVel().getX() <= 0 || (getVel().getY() <= 0 && getVel().getX() <= 0)) return value + 180;
		return value;
	}

	public void turn(boolean left) {

		Vecteur vel = this.getState().getVelocity();
		
		double fact = left ? -1 : 1;
		
		double x = (getVel().getY() > 0 ? pretro : -pretro) * fact;
		double y = (getVel().getY() > 0 ? pretro : -pretro) * fact;

		setVel(getVel().add(new Vecteur(x, y)));

		
	}

}
