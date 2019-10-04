package fr.spacey;

import java.util.Set;

import fr.spacey.controller.SpaceController;
import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Entity;
import fr.spacey.utils.AstroParser;
import fr.spacey.view.SpaceView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SpaceY extends Application {
	private static SpaceY instance;
	
	public Stage stage;
	public Scene menu, simulation;
	
	public boolean isRunning = false;
	public double dt;
	public double gravite;
	public double rayon;
	public double fa;

	private Thread renderThread;

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		this.isRunning = true;
		
		// ENTITIES
		Set<Entity> entities = AstroParser.loadAstroFile("res/exemple.astro");
		//for(Entity e : entities) e.toggleInfo();
		
		// MODELES
		SpaceModel sm = new SpaceModel(entities);

		// CONTROLEURS
		SpaceController sc = new SpaceController(sm);
		
		// VUES
		SpaceView sv = new SpaceView(sc);
		sv.start(stage);
		
		this.dt = 0.00001;
		this.rayon = 1000;

		renderThread = new Thread(new Runnable() {
			@Override
			public void run() {
				
				int fps = 120;
				double timePerTick = 1000 / fps;
				double delta = 0;
				long now = 0;
				long lastTime = System.currentTimeMillis();
				long timer = 0;
				int ticks = 0;
				
				while(isRunning) {
					//System.out.println(now+" "+delta+" "+timer+" "+lastTime+" "+ticks);
					
					now = System.currentTimeMillis();
					delta += (now - lastTime) / timePerTick;
					timer += now - lastTime;
					lastTime = now;
					
					if(delta >= 1) {
						Platform.runLater(sv);
						ticks++;
						delta--;
					}
					 
					if(timer >= dt*1000) {
						//System.out.println("Application gravitationelle (dt="+dt+")");
						sm.update();
						ticks = 0;
						timer = 0;
					}
				}
			}
		});
		renderThread.setDaemon(true);
		renderThread.start();
	}
	
	public static SpaceY getInstance() {
		if(instance == null) {
			instance = new SpaceY();
		}
		return instance;
	}
	
	public static void main(String[] args) {
		Application.launch(args);
		getInstance();
	}
}
