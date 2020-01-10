package fr.spacey.model.obj;

import java.util.ArrayList;
import java.util.List;

import fr.spacey.model.entity.Vaisseau;

public abstract class Objectif {
	
	public List<Prerequis> prerequis;
	
	public Objectif() {
		this.prerequis = new ArrayList<Prerequis>();
	}
	
	public boolean isComplete(Vaisseau vaisseau) {
		for(Prerequis pr : prerequis)
			if(!pr.isComplete(vaisseau)) 
				return false;
		return true;
	}
	
}
