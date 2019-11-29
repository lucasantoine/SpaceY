package fr.spacey.model.entity;

import fr.spacey.utils.Vector;

public class Vaisseau extends Simule {

	private final double TANKSIZE;
	
	private double angle;
	private double pretro; //used for ship rotation
	private double rocketActivity;
	private double maxForce;
	private double fuel;
	
	public Vaisseau(String name, double masse, Vector pos, Vector vel, double pretro, double maxForce) {
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
	
	public void upThrottle() {
		this.rocketActivity=rocketActivity+1.0;
		if(this.rocketActivity>100)this.rocketActivity=100;
	}
	public void downThrottle() {
		this.rocketActivity=rocketActivity-1.0;
		if(this.rocketActivity<0)this.rocketActivity=0;
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

	public double getMaxForce() {
		return maxForce;
	}

	public double getPretro() {
		return pretro;
	}

	public void setPretro(double pretro) {
		this.pretro = pretro;
	}

	public double getTankSize() {
		return this.TANKSIZE;
	}
	
	public double getForce() {
		return this.maxForce*rocketActivity/100.0;
	}
	
	public double getXForce() {
		return Math.cos(Math.toRadians(angle))*this.getForce();
	}
	public double getYForce() {
		return Math.sin(Math.toRadians(angle))*this.getForce();
	}

	public void consumeFuel() {
		this.fuel-=0.1*(this.rocketActivity/100);
	}
	
	public double getAngle() {
		return this.angle;
	}
}
