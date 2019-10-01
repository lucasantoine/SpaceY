package fr.spacey.models.menu;

import java.util.Observable;

import fr.spacey.utils.Position;

public class Star extends Observable{

	private Position pos;
	private int factorX;
	private int factorY;
	double inc;
	private double rayon;
	
	public Star() {
		this(new Position(0, 0), 0.3);
	}
	
	public Star(Position pos, double inc) {
		this.pos = pos;
		this.factorX = Math.random() < 0.5 ? 1 : -1;
		this.factorY = Math.random() > 0.5 ? 1 : -1;
		this.inc = inc;
		this.rayon = Math.random();
	}
	
	public Star(double x, double y, double inc) {
		this(new Position(x, y), inc);
	}
	
	public Position getPosition() {
		return pos;
	}
	
	public void addPosition(double x, double y) {
		setChanged();
		notifyObservers();
		pos.setX(pos.getX() + x);
		pos.setY(pos.getY() + y);
	}
	
	public void removePosition(double x, double y) {
		pos.setX(pos.getX() - x);
		pos.setY(pos.getY() - y);
	}
	
	public void incPosition() {
		addPosition(inc * factorX, inc * factorY);
	}
	
	public void deincPosition() {
		removePosition(inc * factorX, inc * factorY);
	}
	
	public void reverseFactorX() {
		this.factorX *= -1;
	}
	
	public void reverseFactorY() {
		this.factorY *= -1;
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
	
}
