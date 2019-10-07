package fr.spacey;

import java.util.Set;

import fr.spacey.controller.SpaceController;
import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Entity;
import fr.spacey.utils.AstroParser;
import fr.spacey.utils.ImageLoader;
import fr.spacey.view.SpaceView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * SpaceY - IUT A of Lille - 3rd Semester
 * 
 * Classe main qui génère le modèle MVC et
 * démarre la simulation.
 * 
 * @author BARBIER Benoit
 * @Since 24 sept 2019
 */
public class SpaceY extends Application {
	private static SpaceY instance = new SpaceY();
	
	private boolean isRunning = false;
	private double dt;
	private double gravite;
	private double rayon;
	private double fa;

	/**
	 * Fonction de démarrage de l'Application
	 * 
	 * @return Le premier stage initialisé par JavaFX
	 */
	@Override
	public void start(Stage stage) throws Exception {
		
		// ENTITIES
		Set<Entity> entities = AstroParser.loadAstroFile("res/systemes/test.astro");
		for(Entity e : entities) {
			e.toggleInfo();
			if(e.getName().equalsIgnoreCase("SOLEIL")) {
				e.setImg(ImageLoader.SOLEIL);
			} else if(e.getName().equalsIgnoreCase("JUPITER")) {
				e.setImg(ImageLoader.JUPITER);
			} else if(e.getName().equalsIgnoreCase("TERRE")) {
				e.setImg(ImageLoader.TERRE);
			} else if(e.getName().equalsIgnoreCase("VENUS")) {
				e.setImg(ImageLoader.VENUS);
			} else if(e.getName().equalsIgnoreCase("LUNE")) {
				e.setImg(ImageLoader.LUNE);
			} else {
				e.setImg(ImageLoader.MERCURE);
			}
		}
		
		// MODELES
		SpaceModel sm = new SpaceModel(entities);

		// CONTROLEURS
		SpaceController sc = new SpaceController(sm);
		
		// VUES
		SpaceView sv = new SpaceView(sc);
		sv.start(stage);

		instance.isRunning = true;
		instance.dt = 0.00001;
		instance.rayon = 1000;
		instance.gravite = 0.01;
		
		sc.initRender();
		
	}
	
	/**
	 * getter to know is the simulation is currently running
	 * 
	 * @author Benoit
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * getter to get the current time value of the simulation
	 * 
	 * @author Benoit
	 */
	public double getDt() {
		return dt;
	}

	/**
	 * getter to get the current gravity constant
	 * 
	 * @author Benoit
	 */
	public double getG() {
		return gravite;
	}

	/**
	 * getter to get the length of the simulation
	 * 
	 * @author Benoit
	 */
	public double getRayon() {
		return rayon;
	}


	/**
	 * getter to get the acceleration value
	 * 
	 * @author Benoit
	 */
	public double getFa() {
		return fa;
	}

	/**
	 * Singleton of the simulation
	 * 
	 * @author Benoit
	 */
	public static SpaceY getInstance() {
		return instance;
	}
	

	/**
	 * main function to start the java application
	 * 
	 * @param array of arguments while starting the java app
	 * @author Benoit
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}
}
