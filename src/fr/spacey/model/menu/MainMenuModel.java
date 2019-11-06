package fr.spacey.model.menu;

import java.util.HashSet;
import java.util.Observable;
import java.util.Set;

import javafx.stage.Stage;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 26 oct. 2019
 * 
 *        Modele du menu principal de la simulation. Permet modifier le fichier
 *        de parametrage de la simulation et de placer et d'animer les etoiles
 *        en fond d'ecran.
 */
public class MainMenuModel extends Observable {

	private Set<Star> stars;
	private boolean isStart;
	private Stage stage;
	private String filepath;

	/**
	 * Constructeur de MainMenuModel.
	 */
	public MainMenuModel() {
		this.stars = new HashSet<Star>();
		for (int i = 0; i < 800; i++) {
			stars.add(new Star(Math.random() * 1600, Math.random() * 900, Math.random(), 1600, 1600, 900));
		}
		this.isStart = false;
	}

	/**
	 * Renvoie la liste d'etoiles du Menu.
	 * 
	 * @return la liste d'etoiles du Menu.
	 */
	public Set<Star> getStars() {
		return stars;
	}

	/**
	 * Demarre la simulation.
	 */
	public void setStart() {
		isStart = true;
	}

	/**
	 * Renvoie vrai si la simulation est demarree, renvoie faux sinon.
	 * 
	 * @return vrai si la simulation est demarree, renvoie faux sinon.
	 */
	public boolean isStart() {
		return isStart;
	}

	/**
	 * Modifie la position des etoiles du Menu.
	 */
	public void updatePositions() {
		for (Star s : stars) {
			s.update(isStart);
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Notifie les abonnes au modele que quelque chose a change.
	 */
	public void askForRender() {
		setChanged();
		notifyObservers();
	}

	/**
	 * Change la Scene du Menu par celle passee en parametre.
	 * 
	 * @param stage Nouvelle scene du Menu.
	 */
	public void setStage(Stage stage) {
		this.stage = stage;
	}

	/**
	 * Renvoie la Scene du Menu.
	 * 
	 * @return la Scene du Menu.
	 */
	public Stage getStage() {
		return stage;
	}

	/**
	 * Modifie le chemin d'acces vers le fichier de configuration de la simulation
	 * par celui passe en parametre.
	 * 
	 * @param filepath Nouveau chemin d'acces vers le fichier de configuration de la
	 *                 simulation.
	 */
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	/**
	 * Renvoie le chemin d'acces vers le fichier de configuration de la simulation.
	 * 
	 * @return le chemin d'acces vers le fichier de configuration de la simulation.
	 */
	public String getFilepath() {
		return filepath;
	}

}
