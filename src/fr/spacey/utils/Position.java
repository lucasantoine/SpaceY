package fr.spacey.utils;

/**
 * Position dans l'espace avec deux coordonnées x et y
 * @author Benoit
 */
public class Position {
	
	private int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
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
		return sb.append('[').append(x).append(';')
				.append(y).append(']').toString();
	}
}
