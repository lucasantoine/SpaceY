package fr.spacey.controller;

import java.util.ArrayList;
import java.util.Observer;

import fr.spacey.SpaceY;
import fr.spacey.models.menu.Star;
import fr.spacey.view.MainMenuView;
import javafx.scene.text.Font;

public class MainMenuController implements Runnable{

	private ArrayList<Star> stars;
	private MainMenuView mmv;
	public boolean isStart;
	
	public MainMenuController() {
		this.stars = new ArrayList<Star>();
		this.isStart = false;
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
		if(!isStart) {
			mmv.printBackground();
			for(Star s : stars) {
				s.update(isStart);
			}
		}else {
			for(Star s : stars) {
				s.update(isStart);
			}
			mmv.title.setVisible(false);
			mmv.play.setVisible(false);
			mmv.quit.setVisible(false);
			
			
		}
	}
	

}
