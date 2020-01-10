package fr.spacey.model.obj;

import fr.spacey.model.entity.Vaisseau;

public interface Prerequis {
	
	public default boolean isComplete(Vaisseau e) {
		return false;
	}
	
}
