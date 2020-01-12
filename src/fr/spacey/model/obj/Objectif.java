package fr.spacey.model.obj;

import java.util.ArrayList;
import java.util.List;

import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Vaisseau;

public abstract class Objectif {
	
	public String name;
	public List<Prerequis> prerequis;
	
	public Objectif(String name) {
		this.name = name;
		this.prerequis = new ArrayList<Prerequis>();
	}
	
	public boolean isComplete(Vaisseau vaisseau) {
		for(Prerequis pr : prerequis)
			if(!pr.isComplete(vaisseau)) 
				return false;
		return true;
	}

	public void init(SpaceModel sm) {
		for(Prerequis pr : prerequis)
			pr.init(sm);
	}
	
}
