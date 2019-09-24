package fr.spacey.models.entities;

public enum EntityType {

	FIXE("Fixe"),
	SIMULE("Simulé" ),
	//ELLIPSE("Ellipse"),
	//CERCLE("Cercle"),
	VAISEAU("Vaisseau");
	
	public final String NOM;

	private EntityType(String nom) {
		this.NOM = nom;
	}
	
	public static EntityType getByName(String name) {
		try {
	}
	
}
