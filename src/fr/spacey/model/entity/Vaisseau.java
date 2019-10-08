package fr.spacey.model.entity;

import fr.spacey.utils.Vector;

public class Vaisseau extends Simule {

	private double pprincipal;
	private double pretro;
	
	public Vaisseau(String name, EntityType type, double masse, Vector pos, Vector vel, double pprincipal, double pretro) {
		super(name, type, masse, pos, vel);
		super.radius = masse * 10000;
		this.pprincipal = pprincipal;
		this.pretro = pretro;
	}
	
	public double getPropPrincipal() {
		return pprincipal;
	}

	public void setPropPrincipal(double d) {
		this.pprincipal = d;
	}
}
