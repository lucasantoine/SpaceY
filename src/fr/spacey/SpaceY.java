package fr.spacey;

import fr.spacey.controller.MainMenuController;
import fr.spacey.controller.SpaceController;
import fr.spacey.model.SpaceModel;
import fr.spacey.model.menu.MainMenuModel;
import fr.spacey.view.MainMenuView;
import fr.spacey.view.SpaceView;
import javafx.application.Application;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 26 oct. 2019
 * 
 *        Classe principale heritant d'Application qui genere la structure
 *        Modele-Vue-Controleur et demarre la simulation.
 */
public class SpaceY extends Application {

	public final double PI = 3.14159265359;
	
	private static SpaceY instance = new SpaceY();
	
	private transient SpaceModel sm;
	private transient SpaceController sc;
	private transient SpaceView sv;
	
	/**
	 * Constructeur vide de l'application
	 */
	public SpaceY() {}

	/**
	 * Methode de demarrage de l'Application.
	 */
	@Override
	public void start(Stage stage) {
		// FONT
		Font f = Font.loadFont(getClass().getClassLoader().getResourceAsStream("fonts/Minecraftia.ttf"), 10);
		System.out.println(f);
		
		// MODELES
		MainMenuModel mmd = new MainMenuModel(); 

		// CONTROLEURS
		MainMenuController mmc = new MainMenuController(mmd);

		// VUES
		MainMenuView mmv = new MainMenuView(mmc);

		mmv.start(stage);
		mmc.initRender();
	}

	/**
	 * Methode de demarrage de la simulation prenant en parametre le chemin d'acces
	 * vers le fichier de configuration et la scene de la simulation.
	 * 
	 * @param filepath Chemin d'acces vers le fichier de configuration de la
	 *                 simulation.
	 * @param stage    Scene de la simulation.
	 * @throws Exception s
	 */
	public void startSimulation(String filepath, Stage stage) throws Exception {
		
		this.sm = new SpaceModel(filepath);
		this.sc = new SpaceController(sm);
		this.sv = new SpaceView(sc);

		this.sv.start(stage);
		this.sc.initRender();
	}

	/**
	 * Renvoie une instance de l'Application.
	 * 
	 * @return une instance de l'Application.
	 */
	public static SpaceY getInstance() {
		return instance;
	}

	/**
	 * Methode principale lancant l'Application.
	 * 
	 * @param args Arguments de la methode principale (inutile dans ce cas).
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
}
