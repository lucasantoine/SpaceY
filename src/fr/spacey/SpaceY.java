package fr.spacey;

import fr.spacey.controller.EntityController;
import fr.spacey.models.entities.EntityType;
import fr.spacey.models.entitites.Fixe;
import fr.spacey.models.entitites.Simule;
import fr.spacey.utils.Position;
import fr.spacey.view.SpaceView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class SpaceY extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		/* MODELES */
		// TODO LE LECTEUR DE FICHIER
		Fixe soleil = new Fixe("Soleil", EntityType.FIXE, 100, new Position(0,0));
		Simule asteroid = new Simule("Astéroïd", EntityType.SIMULE, 30, new Position(-80,-120), Fixe.VELOCITY_FIXE);
		
		/* CONTROLEURS */
		EntityController ec = new EntityController();
		ec.getEntities().add(soleil);
		ec.getEntities().add(asteroid);
		
		/* VUES */
		SpaceView sv = new SpaceView(ec);
		
		/* START */
		
		ec.registerAll(sv);
		
		sv.start(stage);
		
		Runnable updater = new Runnable() {
            @Override
            public void run() {
                sv.render();
            }
        };
		
		Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                	asteroid.getPos().setX(asteroid.getPos().getX()+1);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                    	ex.printStackTrace();
                    }
                    Platform.runLater(updater);
                }
            }

        });
		
        thread.setDaemon(true);
        thread.start();
	}
}
