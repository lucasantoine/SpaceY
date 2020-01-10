package fr.spacey.model.entity;

import java.util.ArrayList;
import java.util.List;

import fr.spacey.utils.Vecteur;

public class Ellipse {
	private ArrayList<Entity> centers;

	public Ellipse(String name, double masse, Vecteur pos, Fixe f1, Fixe f2) {
		this.centers = new ArrayList<>();
		this.centers.add(f1);
		this.centers.add(f2);
	}
	
	

}

