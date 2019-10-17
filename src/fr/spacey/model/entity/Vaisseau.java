package fr.spacey.model.entity;

import fr.spacey.utils.Vector;

public class Vaisseau extends Simule {

	private final double TANKSIZE;
	
	private double pprincipal;
	private double pretro;
	private double fuel;
	
	public Vaisseau(String name, EntityType type, double masse, Vector pos, Vector vel, double pprincipal, double pretro) {
		super(name, type, masse, pos, vel);
		super.radius = masse;
		this.pprincipal = pprincipal;
		this.pretro = pretro;
		this.TANKSIZE = 200;
		this.fuel = 37;
	}
	
	public double getPropPrincipal() {
		return pprincipal;
	}

	public void setPropPrincipal(double d) {
		this.pprincipal = d;
	}

	public double getFuel() {
		return this.fuel;
	}

	public double getTankSize() {
		return this.TANKSIZE;
	}
}
