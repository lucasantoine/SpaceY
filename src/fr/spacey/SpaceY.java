package fr.spacey;

import java.util.List;

import fr.spacey.controller.SpaceController;
import fr.spacey.models.entities.EntityModel;
import fr.spacey.utils.AstroParser;
import fr.spacey.view.SpaceView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class SpaceY extends Application {

	private static SpaceY instance;
	public boolean isRunning;
	public double dt;
	public double gravite;
	public double rayon;
	public double fa;

	private Thread renderThread;

	@Override
	public void start(Stage stage) throws Exception {
		/* MODELES */
		List<EntityModel> entities = AstroParser.loadAstroFile("res/exemple.astro");

		/* CONTROLEURS */
		SpaceController sc = new SpaceController();
		
		/* VUES */
		SpaceView sv = new SpaceView(sc);
		sc.setView(sv);
		
		/* START */
		for (EntityModel entityModel : entities) {
			entityModel.toggleInfo();
			sc.addEntityModel(entityModel, sv);
		}

		sv.start(stage);
		isRunning = true;
		dt = 0.5;
		fa = 2;

		renderThread = new Thread(new Runnable() {
			@Override
			public void run() {
				
				double timePerTick = 1000 / 30;
				double delta = 0;
				long now = 0;
				long lastTime = System.currentTimeMillis();
				long timer = 0;
				int fps = 0;
				long spaceTick = 0;
				
				while(isRunning) {
					now = System.currentTimeMillis();
					delta += (now - lastTime) / timePerTick;
					timer += now - lastTime;
					spaceTick += now - lastTime;
					lastTime = now;
					
					if(delta >= 1) {
						Platform.runLater(sc);
						fps++;
						delta--;
					}
					
					if(timer >= 1000) {
						System.out.println("fps="+fps);
						fps = 0;
						timer = 0;
					}
					if(spaceTick >= dt*1000) {
						System.out.println("SpaceTick");
						spaceTick = 0;
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
