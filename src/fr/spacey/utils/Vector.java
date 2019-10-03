package fr.spacey.utils;

/**
 * Position dans l'espace avec deux coordonnées x et y
 * 
 * @author Benoit
 */
public class Vector {

	private double x, y;

	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public void setPos(Vector v) {
		this.setX(v.x);
		this.setY(v.y);
	}
	
	public void setPos(double x, double y) {
		this.setX(x);
		this.setY(y);
	}

	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean equals(Vector obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		if (x != obj.x)
			return false;
		if (y != obj.y)
			return false;
		return true;
	}

	public Vector add(Vector v) {
		return new Vector(this.x + v.x, this.y + v.y);	 
	}

	public Vector minus(Vector v) {
		return new Vector(this.x - v.x, this.y - v.y);
	}

	public double getDistanceTo(Vector v) {
		return Math.sqrt(Math.pow(this.x - v.x, 2) + Math.pow(this.y - v.y, 2));
	}

	public double getMagnitude() {
		return Math.sqrt(x * x + y * y);
	}

	public double getCosine() {
		return x / getMagnitude();
	}

	public double getSine() {
		return y / getMagnitude();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.append('[').append(x).append(';').append(y).append(']').toString();
	}
	
	public String toStringRounded() {
		StringBuilder sb = new StringBuilder();
		return sb.append('[').append((int)x).append(';')
				.append((int)y).append(']').toString();
	}
}
