package fr.spacey.models.menu;

import java.util.Observable;
import java.util.Random;

import fr.spacey.utils.Position;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Star extends Observable{

	private Position pos;
	public double z;
	private int factorX;
	private int factorY;
	double inc;
	private double rayon;
	private double opacity;
	private int factorOpacity;
	private Color color;
	private double ostartx;
	private double ostopx;
	private double ostarty;
	private double ostopy;
	
	public Star(Position pos, double inc, double z, Canvas canvas) {
		this.pos = pos;
		this.factorX = Math.random() < 0.5 ? 1 : -1;
		this.factorY = Math.random() > 0.5 ? 1 : -1;
		this.inc = inc / 100;
		this.rayon = Math.random() * 5;
		this.opacity = Math.random();
		this.factorOpacity = -1;
		this.z = Math.random() * z;
		this.color = (Math.random() < 0.5 ? Color.LIGHTSKYBLUE : Color.WHITE);
		switch (new Random().nextInt(4)) {
		case 0:
			this.ostartx = canvas.getWidth()/2.0;
			this.ostopx = canvas.getWidth();
			this.ostarty = canvas.getHeight()/2.0;
			this.ostopy = 0;
			break;
		case 1:
			this.ostartx = canvas.getWidth()/2.0;
			this.ostopx = canvas.getWidth();
			this.ostarty = canvas.getHeight()/2.0;
			this.ostopy = canvas.getHeight();
			break;
		case 2:
			this.ostartx = canvas.getWidth()/2.0;
			this.ostopx = 0;
			this.ostarty = canvas.getHeight()/2.0;
			this.ostopy = canvas.getHeight();
			break;
		case 3:
			this.ostartx = canvas.getWidth()/2.0;
			this.ostopx = 0;
			this.ostarty = canvas.getHeight()/2.0;
			this.ostopy = -canvas.getHeight();
			break;
		}
	}

	public Star(double x, double y, double inc, double z, Canvas canvas) {
		this(new Position(x, y), inc, z, canvas);
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
	
	public void update(boolean isstart) {
		z-=1;
		if(z < 1 && !isstart) {
			z = 1280;
			pos.setX(Math.random() * 1280);
			pos.setY(Math.random() * 720);
		}else if(isstart && z <= 0) {
			z = 0;
		}
		setChanged();
		notifyObservers();
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
		setChanged();
		notifyObservers();
		this.z = z;
	}

	public Paint getColor() {
		return color;
	}
	
	public double getOstartx() {
		return ostartx;
	}

	public double getOstopx() {
		return ostopx;
	}

	public double getOstarty() {
		return ostarty;
	}

	public double getOstopy() {
		return ostopy;
	}
	
}
