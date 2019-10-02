package fr.spacey.models.menu;

import java.util.Observable;
import java.util.Random;

import fr.spacey.utils.Position;

public class Star extends Observable{

	private Position pos;
	public double z;
	private int factorX;
	private int factorY;
	double inc;
	private double rayon;
	private double opacity;
	private int factorOpacity;
	
	public Star() {
		this(new Position(0, 0), 0.3, 10);
	}
	
	public Star(Position pos, double inc, double z) {
		this.pos = pos;
		this.factorX = Math.random() < 0.5 ? 1 : -1;
		this.factorY = Math.random() > 0.5 ? 1 : -1;
		this.inc = inc / 100;
		this.rayon = Math.random() * 5;
		this.opacity = Math.random();
		this.factorOpacity = -1;
		this.z = new Random().nextInt((int)z);
	}
	
	public Star(double x, double y, double inc, double z) {
		this(new Position(x, y), inc, z);
	}
	
	public Position getPosition() {
		return pos;
	}
	
	public void addPosition(double x, double y) {
		setChanged();
		notifyObservers();
		pos.setX(pos.getX() + x);
		pos.setY(pos.getY() + y);
		if(Math.random() < 0.2) {
			factorOpacity = factorOpacity * -1;
			opacity += (Math.random() / 100) * factorOpacity;
		}
	}
	
	public void removePosition(double x, double y) {
		setChanged();
		notifyObservers();
		pos.setX(pos.getX() - x);
		pos.setY(pos.getY() - y);
	}
	
	public void incPosition() {
		addPosition(inc * factorX, inc * factorY);
	}
	
	public void deincPosition() {
		removePosition(inc * factorX, inc * factorY);
	}
	
	public void setFactorX(int f) {
		this.factorX = f;
	}
	
	public void setFactorY(int f) {
		this.factorY = f;
	}
	
	public int getFactorX() {
		return factorX;
	}
	
	public int getFactorY() {
		return factorY;
	}
	
	public double getRayon() {
		return rayon;
	}
	
	public double getOpacity() {
		return opacity;
	}
	
	public void setOpacity(double opacity) {
		this.opacity = opacity;
	}
	
	public double getZ() {
		return z;
	}
	
	public void setZ(double z) {
		this.z = z;
	}
	
}
