package fr.spacey.utils;

/**
 * Position dans l'espace avec deux coordonnées x et y
 * @author Benoit
 */
public class Position {
	
	private double x, y;

	public Position(double x, double y) {
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

	public boolean equals(Position obj) {
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
		return sb.append('[').append(Math.round(x * 100.0) / 100.0).append(';')
				.append(Math.round(y * 100.0) / 100.0).append(']').toString();
	}
	
	public String toStringRounded() {
		StringBuilder sb = new StringBuilder();
		return sb.append('[').append((int)x).append(';')
				.append((int)y).append(']').toString();
<<<<<<< HEAD
=======

>>>>>>> refs/heads/Benoit
	}
}
