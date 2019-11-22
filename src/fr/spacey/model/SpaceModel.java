package fr.spacey.model;

import java.util.List;
import java.util.Observable;

import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.Vaisseau;
import fr.spacey.utils.AstroParser;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 26 oct. 2019
 * 
 *        Modele de la simulation contenant toutes les Entites presentes a
 *        l'interieur. Il recupere les informations presentes dans le fichier de
 *        configuration et cree la simulation. Permet egalement de modifier la
 *        Position et l'Affichage des Entites.
 */
public class SpaceModel extends Observable {

	private List<Entity> entities;
	private Vaisseau vaisseau;
	private int selected;
	private Affichage affichage;

	public static double G; // constante gravitationelle
	private double dt; // nb d'update() qui sera reparti par le nombre d'image par seconde
	private double fa; // facteur multiplicatif pour le nombre d'update()
	private double rayon; // taille de l'univers

	/**
	 * Constructeur de SpaceModel prenant en parametre la Liste des Entites de la
	 * simulation.
	 * 
	 * @param filepath Nom du fichier de configuration sans son extension.
	 */
	public SpaceModel(String filepath) {
		this.entities = AstroParser.loadAstroFile(this, "res/systemes/" + filepath + ".astro");
		for (Entity e : entities) {
			if (e.getName().equalsIgnoreCase("SOLEIL")) {
				e.setImgId(4);
			} else if (e.getName().equalsIgnoreCase("JUPITER")) {
				e.setImgId(6);
			} else if (e.getName().equalsIgnoreCase("TERRE")) {
				e.setImgId(0);
			} else if (e.getName().equalsIgnoreCase("VENUS")) {
				e.setImgId(5);
			} else if (e.getName().equalsIgnoreCase("LUNE")) {
				e.setImgId(1);
			} else if (e.getName().equalsIgnoreCase("MARS")) {
				e.setImgId(2);
			} else if (e.getName().equalsIgnoreCase("VAISSEAU")) {
				e.setImgId(7);
			} else {
				e.setImgId(3);
			}
		}
		if (this.rayon == 0)
			this.rayon = 500;

		if (this.dt == 0)
			this.dt = 0.025f;

		this.dt = 0.1f;

		if (this.fa == 0)
			this.fa = 1;

		if (G == 0)
			G = 0.005;

		this.selected = -1;
		this.affichage = new Affichage();
		for (Entity e : entities) {
			if (e instanceof Vaisseau) {
				this.vaisseau = (Vaisseau) e;
			}
		}
	}

	/**
	 * Renvoie la liste des entites presentes dans la simulation.
	 * 
	 * @return la liste des entites presentes dans la simulation.
	 */
	public List<Entity> getEntities() {
		return entities;
	}

	/**
	 * Renvoie le vaisseau present dans la simulation, null sinon.
	 * 
	 * @return le vaisseau present dans la simulation, null sinon.
	 */
	public Vaisseau getVaisseau() {
		return vaisseau;
	}

	/**
	 * Renvoie vrai si il y a un vaisseau dans la simulation, faux sinon.
	 * 
	 * @return vrai si il y a un vaisseau dans la simulation, faux sinon.
	 */
	public boolean hasVaisseau() {
		return vaisseau != null;
	}

	/**
	 * Renvoie vrai si une entite est selectionnee, faux sinon.
	 * 
	 * @return vrai si une entite est selectionnee, faux sinon.
	 */
	public boolean hasEntitySelected() {
		return selected > -1;
	}
	
	/**
	 * Renvoie le numéro de l'entite selectionnee.
	 * 
	 * @return le numéro de l'entite selectionnee.
	 */
	public int getEntitySelectedId() {
		return selected;
	}
	
	/**
	 * Renvoie l'entite selectionnee, null sinon.
	 * 
	 * @return l'entite selectionnee, null sinon.
	 */
	public Entity getEntitySelected() {
		if (this.hasEntitySelected()) {
			return entities.get(selected);
		}
		return null;
	}

	/**
	 * Revoie les proprietes de l'affichage associe au modele.
	 * 
	 * @return les proprietes de l'affichage associe au modele.
	 */
	public Affichage getAffichage() {
		return this.affichage;
	}

	/**
	 * Renvoie le pas de temps de la simulation.
	 * 
	 * @return le pas de temps de la simulation.
	 */
	public double getDt() {
		return dt;
	}

	/**
	 * Renvoie le facteur d'acceleration de la simulation.
	 * 
	 * @return le facteur d'acceleration de la simulation.
	 */
	public double getFa() {
		return fa;
	}

	/**
	 * Modifie le pas de temps de la simulation par celui passe en parametre.
	 * 
	 * @param dt Nouveau pas de temps de la simulation.
	 */
	public void setDt(double dt) {
		this.dt = dt;
	}

	/**
	 * Modifie le facteur d'acceleration de la simulation par celui passe en
	 * parametre.
	 * 
	 * @param fa Nouveau facteur d'acceleration de la simulation.
	 */
	public void setFa(double fa) {
		this.fa = fa;
	}

	/**
	 * Modifie la hauteur et la largeur de l'univers de la simulation avec la valeur
	 * passee en parametre.
	 * 
	 * @param rayon Nouvelle hauteur et largeur de l'univers de la simulation.
	 */
	public void setRayon(int rayon) {
		this.rayon = rayon;
	}

	/**
	 * Renvoie la hauteur et la largeur de l'univers de la simulation.
	 * 
	 * @return la hauteur et la largeur de l'univers de la simulation.
	 */
	public double getRayon() {
		return rayon;
	}

	/**
	 * Change l'entite selectionnee en celle positionnee en idx dans la List
	 * entities.
	 * 
	 * @param idx designe l'index de l'entite dans la liste entities.
	 */
	public void setEntitySelected(int idx) {
		if (idx >= 0 && idx < entities.size()) {
			this.selected = idx;
		} else {
			this.selected = -1;
		}
	}

	/**
	 * Modifie la position de chaque Entite presentes dans la simulation.
	 */
	public void updatePositions() {
		for (Entity e : entities) {
			e.updatePosition(entities);
		}
		render();
	}

	/**
	 * Notifie les abonnes au modele que quelque chose a change.
	 */
	public void render() {
		setChanged();
		notifyObservers();
	}

}
