package fr.spacey.utils;

public class State {
	private Vecteur position;
	private Vecteur velocity;

	public State(Vecteur position, Vecteur velocity) {
		this.position = position;
		this.velocity = velocity;
	}

	public State(double posX, double posY, double velX, double velY) {
		this(new Vecteur(posX, posY), new Vecteur(velX, velY));
	}

	public Vecteur getPosition() {
		return position;
	}

	public Vecteur getVelocity() {
		return velocity;
	}
	
	public void add(State s) {
		this.position.add(s.getPosition());
		this.velocity.add(s.getVelocity());
	}

	public State multiply(double d) {
		return new State((this.getPosition().getX() * d), (this.getPosition().getY() * d), (this.getVelocity().getX() * d),
				(this.getVelocity().getY() * d));
	}

	@Override
	public String toString() {
		return "State [position=" + position + ", velocity=" + velocity + "]";
	}
	
	
}
