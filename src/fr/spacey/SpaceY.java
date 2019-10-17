package fr.spacey;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import fr.spacey.controller.SpaceController;
import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Entity;
import fr.spacey.utils.AstroParser;
import fr.spacey.view.SpaceView;
import javafx.application.Application;
import javafx.scene.text.Font;
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
	
	public SpaceModel sm;
	public SpaceController sc;
	public SpaceView sv;

	/**
	 * Fonction de démarrage de l'Application
	 * 
	 * @return Le premier stage initialisé par JavaFX
	 */
	@Override
	public void start(Stage stage) throws Exception {
	
		// FONT
		try {
			Font.loadFont(new FileInputStream(new File("res/fonts/pixelmix.ttf")), 10);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		// ENTITIES
		List<Entity> entities = AstroParser.loadAstroFile("res/systemes/test.astro");
		for(Entity e : entities) {
			if(e.getName().equalsIgnoreCase("SOLEIL")) {
				e.setImgId(4);
			} else if(e.getName().equalsIgnoreCase("JUPITER")) {
				e.setImgId(6);
			} else if(e.getName().equalsIgnoreCase("TERRE")) {
				e.setImgId(0);
			} else if(e.getName().equalsIgnoreCase("VENUS")) {
				e.setImgId(5);
			} else if(e.getName().equalsIgnoreCase("LUNE")) {
				e.setImgId(1);
			} else if(e.getName().equalsIgnoreCase("MARS")) {
				e.setImgId(2);
			} else {
				e.setImgId(3);
			}
		}
		
		// MODELES
		this.sm = new SpaceModel(entities);

		// CONTROLEURS
		this.sc = new SpaceController(sm);
		
		// VUES
		this.sv = new SpaceView(sc);
		
		this.sv.start(stage);
		
		this.sc.initRender();
		
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

	public SpaceController getSC() {
		return this.sc;
	}
}
