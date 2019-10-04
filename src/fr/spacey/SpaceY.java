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

public class SpaceY extends Application {
	private static SpaceY instance = new SpaceY();
	
	public boolean isRunning = false;
	public double dt;
	public double gravite;
	public double rayon;
	public double fa;

	private Thread renderThread;

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
	
	public static SpaceY getInstance() {
		return instance;
	}
	
	public static void main(String[] args) {
		Application.launch(args);
	}
}
