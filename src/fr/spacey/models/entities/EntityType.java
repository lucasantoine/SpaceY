package fr.spacey.models.entities;

import fr.spacey.exceptions.TypeUnknownException;

public enum EntityType {

	FIXE("Fixe"),
	SIMULE("Simulé" ),
	ELLIPSE("Ellipse"),
	CERCLE("Cercle"),
	VAISEAU("Vaisseau");
	
	public final String NOM;

	private EntityType(String nom) {
		this.NOM = nom;
	}
	
	public static EntityType getByName(String name) throws TypeUnknownException {
		if(name.equals(FIXE.NOM)) {
			return FIXE;
		} else if(name.equals(SIMULE.NOM)) {
			return SIMULE;
		} else if(name.equals(ELLIPSE.NOM)) {
			return ELLIPSE;
		} else if(name.equals(CERCLE.NOM)) {
			return CERCLE;
		} else if(name.equals(VAISEAU.NOM)) {
			return VAISEAU;
		}
		throw new TypeUnknownException();
	}
	
}
