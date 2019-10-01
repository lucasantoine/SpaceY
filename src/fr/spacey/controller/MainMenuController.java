package fr.spacey.controller;

import java.util.ArrayList;
import java.util.Observer;

import fr.spacey.models.menu.Star;
import fr.spacey.view.MainMenuView;

public class MainMenuController implements Runnable{

	private ArrayList<Star> stars;
	private MainMenuView mmv;
	
	public MainMenuController() {
		this.stars = new ArrayList<Star>();
	}
	
	public ArrayList<Star> getStars() {
		return stars;
	}
	
	public boolean addStar(Star s, Observer observer) {
		s.addObserver(mmv);
		return stars.add(s);
	}
	
	public void setView(MainMenuView mmv) {
		this.mmv = mmv;
	}
	
	@Override
	public void run() {
		mmv.printBackground();
		for(Star s : stars) {
			if(s.getPosition().getX() < 0) s.setFactorX(1);
			if(s.getPosition().getX() > mmv.getWidth()) s.setFactorX(-1);
			if(s.getPosition().getY() < 0) s.setFactorY(1);
			if(s.getPosition().getY() > mmv.getHeight()) s.setFactorY(-1);
			s.incPosition(); 
		}
	}

}
