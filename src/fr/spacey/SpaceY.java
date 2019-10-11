package fr.spacey;

import java.util.List;
import java.util.Set;

import fr.spacey.controller.SpaceController;
import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Entity;
import fr.spacey.utils.AstroParser;
import fr.spacey.utils.Sprite;
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
	private int rayon;
	private double fa;

	/**
	 * Fonction de démarrage de l'Application
	 * 
	 * @return Le premier stage initialisé par JavaFX
	 */
	@Override
	public void start(Stage stage) throws Exception {
		
		// ENTITIES
		List<Entity> entities = AstroParser.loadAstroFile("res/systemes/test.astro");
		for(Entity e : entities) {
			if(e.getName().equalsIgnoreCase("SOLEIL")) {
				e.setImg(Sprite.SOLEIL.getImage());
			} else if(e.getName().equalsIgnoreCase("JUPITER")) {
				e.setImg(Sprite.JUPITER.getImage());
			} else if(e.getName().equalsIgnoreCase("TERRE")) {
				e.setImg(Sprite.TERRE.getImage());
			} else if(e.getName().equalsIgnoreCase("VENUS")) {
				e.setImg(Sprite.VENUS.getImage());
			} else if(e.getName().equalsIgnoreCase("LUNE")) {
				e.setImg(Sprite.LUNE.getImage());
			} else {
				e.setImg(Sprite.MERCURE.getImage());
			}
		}

		instance.isRunning = true;
		instance.dt = 0.00001;
		instance.rayon = 500;
		instance.gravite = 0.01;
		
		// MODELES
		SpaceModel sm = new SpaceModel(entities);

		// CONTROLEURS
		SpaceController sc = new SpaceController(sm);
		
		// VUES
		SpaceView sv = new SpaceView(sc);
		sv.start(stage);
		
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
	

	public void setDt(double dt) {
		this.dt = dt;
	}

	public void setRayon(int rayon) {
		this.rayon = rayon;
	}

	public void setFa(double fa) {
		this.fa = fa;
	}

	/**
	 * getter to get the current gravity constant
	 * 
	 * @author Benoit
	 */
	public double getG() {
		return gravite;
	}

	public void setG(Double v) {
		this.gravite = v;
	}

	/**
	 * getter to get the length of the simulation
	 * 
	 * @author Benoit
	 */
	public int getRayon() {
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

	public void toggleRunning() {
		this.isRunning = !this.isRunning;
	}
}
