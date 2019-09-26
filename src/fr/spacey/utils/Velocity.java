package fr.spacey.utils;

/**
 * Position dans l'espace avec deux coordonnées x et y
 * @author Benoit
 */
public class Velocity {
	
	private double x, y;

	public Velocity(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public boolean equals(Velocity obj) {
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.append('[').append(x).append(';')
				.append(y).append(']').toString();
	}
}
