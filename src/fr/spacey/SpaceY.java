package fr.spacey;

import java.util.ArrayList;
import java.util.List;

import fr.spacey.controller.MainMenuController;
import fr.spacey.controller.SpaceController;
import fr.spacey.models.entities.EntityModel;
import fr.spacey.models.entities.types.EntityType;
import fr.spacey.models.entities.types.Fixe;
import fr.spacey.models.entities.types.Simule;
import fr.spacey.models.menu.Star;
import fr.spacey.utils.AstroParser;
import fr.spacey.utils.Position;
import fr.spacey.utils.Velocity;
import fr.spacey.view.MainMenuView;
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
//		/* ENTITIES */
//		
//		List<EntityModel> entities = AstroParser.loadAstroFile("res/exemple.astro");
//		
//		/* MODELES */
//
//		/* CONTROLEURS */
//		SpaceController sc = new SpaceController();
//		
//		/* VUES */
//		SpaceView sv = new SpaceView(sc);
//		sc.setView(sv);
//		
//		/* START */
//		//sc.addEntityModel(soleil, sv);
//		//sc.addEntityModel(asteroid, sv);
//		for (EntityModel entityModel : entities) {
//			sc.addEntityModel(entityModel, sv);
//		}
//
//		sv.start(stage);
//		isRunning = true;
//		dt = 1;
//
//		renderThread = new Thread(new Runnable() {
//			@Override
//			public void run() {
//				
//				int fps = 120;
//				double timePerTick = 1000 / fps;
//				double delta = 0;
//				long now = 0;
//				long lastTime = System.currentTimeMillis();
//				long timer = 0;
//				int ticks = 0;
//				
//				while(isRunning) {
//					//System.out.println(now+" "+delta+" "+timer+" "+lastTime+" "+ticks);
//					
//					now = System.currentTimeMillis();
//					delta += (now - lastTime) / timePerTick;
//					timer += now - lastTime;
//					lastTime = now;
//					
//					if(delta >= 1) {
//						Platform.runLater(sc);
//						ticks++;
//						delta--;
//					}
//					
//					if(timer >= dt*1000) {
//						System.out.println("Application gravitationelle (dt="+dt+")");
//						ticks = 0;
//						timer = 0;
//					}
//				}
//			}
//		});
//		renderThread.setDaemon(true);
//		renderThread.start();
		
		MainMenuController mmc = new MainMenuController();
		MainMenuView mmv = new MainMenuView(mmc);
		mmc.setView(mmv);
		for (int i = 0; i < 800; i++) {
			mmc.addStar(new Star(Math.random() * mmv.getWidth(), Math.random() * mmv.getHeight(), Math.random(), mmv.getWidth(), mmv.getCanvas()), mmv);
		}
		mmv.start(stage);
		
		isRunning = true;
		
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
				now = System.currentTimeMillis();
				delta += (now - lastTime) / timePerTick;
				timer += now - lastTime;
				lastTime = now;
				
				if(delta >= 1) {
					Platform.runLater(mmc);
					ticks++;
					delta--;
				}
				
				if(timer >= dt*1000) {
					
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
