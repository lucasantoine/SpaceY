package fr.spacey.model;

import java.util.HashSet;
import java.util.List;
import java.util.Observable;
import java.util.Set;
import java.util.Vector;

import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.EntityType;
import fr.spacey.model.entity.Vaisseau;
import fr.spacey.model.integration.EulerExplicite;
import fr.spacey.model.integration.IntegrationStrategy;
import fr.spacey.model.integrator.Integrator;
import fr.spacey.utils.AstroParser;
import fr.spacey.utils.State;
import fr.spacey.utils.Vecteur;

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
	private Vector<Double> states;
	private Vaisseau vaisseau;
	private int selected;
	private Affichage affichage;
	private IntegrationStrategy integrator;

	public static double G; // constante gravitationelle
	private double dt; // nb d'update() qui sera reparti par le nombre d'image
		// par seconde
	private int time;

	private double fa; // facteur multiplicatif pour le nombre d'update()
	private double rayon; // taille de l'univers

	/**
	 * Constructeur de SpaceModel prenant en parametre la Liste des Entites de la
	 * simulation.
	 * 
	 * @param filepath Nom du fichier de configuration sans son extension.
	 * @throws Exception
	 */
	public SpaceModel(String filepath) throws Exception {
		this.entities = AstroParser.loadAstroFile(this, filepath);
		this.states = new Vector<Double>();
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
			states.add(e.getState().getPosition().getX());
			states.add(e.getState().getPosition().getY());
			states.add(e.getState().getVelocity().getX());
			states.add(e.getState().getVelocity().getY());
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

		this.time = 0;
		this.selected = -1;
		this.affichage = new Affichage();
		for (Entity e : entities) {
			if (e instanceof Vaisseau) {
				this.vaisseau = (Vaisseau) e;
			}
		}
		this.integrator = new EulerExplicite(new Integrator(this));
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
	 * Renvoie le num�ro de l'entite selectionnee.
	 * 
	 * @return le num�ro de l'entite selectionnee.
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
		if (hasEntitySelected()) {
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
		this.states = integrator.newStates(states, time, dt);
		for (int i = 0; i < entities.size(); i++) {
			State s = new State(new Vecteur(this.states.get(i * 4), this.states.get((i * 4) + 1)),
					new Vecteur(this.states.get((i * 4) + 2), this.states.get((i * 4) + 3)));
			entities.get(i).updateState(s);
		}

		Set<Entity> toRemove = new HashSet<Entity>();
		int idxfrom = 0, idxto = 0;

		for (Entity efrom : entities) {
			idxto = 0;
			for (Entity eto : entities) {
				if (!efrom.equals(eto) && !toRemove.contains(efrom) && !toRemove.contains(eto)) {
					Vecteur from = efrom.getPos();
					Vecteur to = eto.getPos();

					int distance = (int) Math.sqrt((to.getX() - from.getX()) * (to.getX() - from.getX())
							+ (to.getY() - from.getY()) * (to.getY() - from.getY()));
					distance -= efrom.getRadius() / 2;
					distance -= eto.getRadius() / 2;

					if (distance <= 0) {
						// System.out.println(idxfrom+" "+idxto+" COLLISION ! "+efrom.getName()+"->
						// "+eto.getName()+": "+distance);

						if (efrom.getMasse() < eto.getMasse()) {

							toRemove.add(efrom);
							eto.getVel().setVector(eto.getVel().add(efrom.getVel().getX() / efrom.getMasse(),
									efrom.getVel().getY() / efrom.getMasse()));

							if (hasEntitySelected() && getEntitySelectedId() == idxfrom) {
								setEntitySelected(-1);
							}
						} else {

							toRemove.add(eto);
							efrom.getVel().setVector(efrom.getVel().add(eto.getVel().getX() / eto.getMasse(),
									eto.getVel().getY() / eto.getMasse()));

							if (hasEntitySelected() && getEntitySelectedId() == idxto) {
								setEntitySelected(-1);
							}
						}
					}
				}
				idxto++;
			}
			idxfrom++;
		}

		if (toRemove.size() > 0) {
			for (Entity entity : toRemove) {
				if (entity.getType().equals(EntityType.VAISSEAU)) {
					this.vaisseau = null;
				}
				entities.remove(entity);
			}
		}
		render();
	}

	public void setIntegrationStrategy(IntegrationStrategy is) {
		this.integrator = is;
	}

	public IntegrationStrategy getIntegrationStrategy() {
		return this.integrator;
	}

	public Vector<Double> getStates() {
		return this.states;
	}

	/**
	 * Notifie les abonnes au modele que quelque chose a change.
	 */
	public void render() {
		setChanged();
		notifyObservers();
	}
	
	public void setTime(int time) {
		this.time = time;
	}
	
	public int getTime() {
		return time;
	}

}
