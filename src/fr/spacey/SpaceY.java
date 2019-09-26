package fr.spacey;

import fr.spacey.controller.SpaceController;
import fr.spacey.models.entities.types.EntityType;
import fr.spacey.models.entities.types.Fixe;
import fr.spacey.models.entities.types.Simule;
import fr.spacey.utils.Position;
import fr.spacey.utils.Velocity;
import fr.spacey.view.SpaceView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class SpaceY extends Application {

	private static SpaceY instance;
	public boolean isRunning;
	public double dt;

	private Thread renderThread;

	@Override
	public void start(Stage stage) throws Exception {
		/* ENTITIES */
		// TODO LE LECTEUR DE FICHIER
		Fixe soleil = new Fixe("Soleil", EntityType.FIXE, 100, new Position(0,0));
		Simule asteroid = new Simule("Astéroïd", EntityType.SIMULE, 30, new Position(-80,-120), new Velocity(0.2,0.1));

		/* MODELES */

		/* CONTROLEURS */
		SpaceController sc = new SpaceController();
		
		/* VUES */
		SpaceView sv = new SpaceView(sc);
		sc.setView(sv);
		
		/* START */
		sc.addEntityModel(soleil, sv);
		sc.addEntityModel(asteroid, sv);

		sv.start(stage);
		isRunning = true;
		dt = 1;

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
						Platform.runLater(sc);
						ticks++;
						delta--;
					}
					
					if(timer >= dt*1000) {
						System.out.println("Application gravitationelle (dt="+dt+")");
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
