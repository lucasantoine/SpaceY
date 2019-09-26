package fr.spacey;

import fr.spacey.controller.SpaceController;
import fr.spacey.models.entities.types.EntityType;
import fr.spacey.models.entities.types.Fixe;
import fr.spacey.models.entities.types.Simule;
import fr.spacey.utils.Position;
import fr.spacey.view.SpaceView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class SpaceY extends Application {

	public static boolean isRunning;

	public static void main(String[] args) {
		Application.launch(args);
	}

	private Thread renderThread;

	@Override
	public void start(Stage stage) throws Exception {
		/* ENTITIES */
		// TODO LE LECTEUR DE FICHIER
		Fixe soleil = new Fixe("Soleil", EntityType.FIXE, 100, new Position(0,0));
		Simule asteroid = new Simule("Astéroïd", EntityType.SIMULE, 30, new Position(-80,-120), Fixe.VELOCITY_FIXE);

		/* MODELES */

		/* CONTROLEURS */
		SpaceController sc = new SpaceController();
		sc.addEntityModel(soleil);
		sc.addEntityModel(asteroid);

		/* VUES */
		SpaceView sv = new SpaceView(sc);

		/* START */
		sv.start(stage);
		SpaceY.isRunning = true;

		renderThread = new Thread(new Runnable() {
			@Override
			public void run() {
				while(SpaceY.isRunning) {
					Platform.runLater(sv);
					try {
						Thread.sleep(20);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		renderThread.setDaemon(true);
		renderThread.start();
	}
}
