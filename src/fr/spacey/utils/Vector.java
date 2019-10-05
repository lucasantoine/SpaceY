package fr.spacey.utils;

/**
 * ProjetY - IUT A of Lille - 3rd Semester
 * 
 * @author ANTOINE Lucas Created on 5 oct. 2019
 * 
 *         Vector class containing two double variables that permit to
 *         instantiate positions, magnitudes and velocities used by the entities
 *         of the simulation.
 */
public class Vector {

	private double x, y;

	/**
	 * Constructs a Vector fully parameterized with the specified two double values.
	 * 
	 * @param x Vector's first parameter value.
	 * @param y Vector's second parameter value.
	 */
	public Vector(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns Vector's first parameter value.
	 * 
	 * @return Vector's first parameter value.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Returns Vector's second parameter value.
	 * 
	 * @return Vector's second parameter value.
	 */
	public double getY() {
		return y;
	}

	/**
	 * Sets this Vector's parameters with those from the specified Vector v.
	 * 
	 * @param v Vector used to set this Vector's parameters.
	 */
	public void setVector(Vector v) {
		this.setX(v.x);
		this.setY(v.y);
	}

	/**
	 * Sets this Vector's parameters with the specified double values.
	 * 
	 * @param x new value of Vector's first parameter.
	 * @param y new value of Vector's second parameter.
	 */
	public void setVector(double x, double y) {
		this.setX(x);
		this.setY(y);
	}

	/**
	 * Sets this Vector's first parameter with the specified double value.
	 * 
	 * @param x new value of Vector's first parameter.
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * Sets this Vector's second parameter with the specified double value.
	 * 
	 * @param y new value of Vector's second parameter.
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Compares the specified Vector with this Vector for equality. Returns true
	 * only if they are the same instance or if their parameters have the same
	 * double value.
	 * 
	 * @param v Compared Vector.
	 * @return true if they are the same instance or if their parameters have the
	 *         same double value, else return false.
	 */
	public boolean equals(Vector v) {
		if (this == v)
			return true;
		if (v == null)
			return false;
		if (getClass() != v.getClass())
			return false;
		if (x != v.x)
			return false;
		if (y != v.y)
			return false;
		return true;
	}

	/**
	 * Add specified Vector's parameters double values to this Vector's parameters
	 * double values.
	 * 
	 * @param v Vector used to add double values to this Vector.
	 * @return Vector with this Vector's parameters double values plus specified
	 *         Vector's parameters double values.
	 */
	public Vector add(Vector v) {
		return new Vector(this.x + v.x, this.y + v.y);
	}

	/**
	 * Remove specified Vector's parameters double values to this Vector's
	 * parameters double values.
	 * 
	 * @param v Vector used to remove double values to this Vector.
	 * @return Vector with this Vector's parameters double values minus specified
	 *         Vector's parameters double values.
	 */
	public Vector minus(Vector v) {
		return new Vector(this.x - v.x, this.y - v.y);
	}

	/**
	 * Returns the double distance between this Vector and the specified Vector.
	 * 
	 * @param v Vector used to calculate the distance.
	 * @return the double distance between this Vector and the specified Vector.
	 */
	public double getDistanceTo(Vector v) {
		return Math.sqrt(Math.pow(this.x - v.x, 2) + Math.pow(this.y - v.y, 2));
	}

	/**
	 * Returns the magnitude of this Vector.
	 * 
	 * @return the magnitude of this Vector.
	 */
	public double getMagnitude() {
		return Math.sqrt(x * x + y * y);
	}

	/**
	 * Returns the cosine of this Vector.
	 * 
	 * @return the cosine of this Vector.
	 */
	public double getCosine() {
		return x / getMagnitude();
	}

	/**
	 * Returns the sine of this Vector.
	 * 
	 * @return the sine of this Vector.
	 */
	public double getSine() {
		return y / getMagnitude();
	}

	/**
	 * Returns a string representation of this Vector, containing the String
	 * representation of each element.
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		return sb.append('[').append(x).append(';').append(y).append(']').toString();
	}

	/**
	 * Returns a string representation of this Vector, containing the String
	 * representation of each rounded element.
	 * 
	 * @return a string representation of this Vector, containing the String
	 *         representation of each rounded element.
	 */
	public String toStringRounded() {
		StringBuilder sb = new StringBuilder();
		return sb.append('[').append((int) x).append(';').append((int) y).append(']').toString();
	}
}
