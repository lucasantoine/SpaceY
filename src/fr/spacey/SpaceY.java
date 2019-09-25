package fr.spacey;

import fr.spacey.controller.EntityController;
import fr.spacey.models.entities.EntityType;
import fr.spacey.models.entitites.Fixe;
import fr.spacey.models.entitites.Simule;
import fr.spacey.utils.Position;
import fr.spacey.view.SpaceView;
import javafx.application.Application;
import javafx.stage.Stage;

public class SpaceY extends Application {
	
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		/* MODELES */
		// TODO LE LECTEUR DE FICHIER
		Fixe soleil = new Fixe("Soleil", EntityType.FIXE, 30, new Position(0,0));
		Simule asteroid = new Simule("Astéroïd", EntityType.SIMULE, 30, new Position(5,5), Fixe.VELOCITY_FIXE);
		
		/* CONTROLEURS */
		EntityController ec = new EntityController();
		ec.getEntities().add(soleil);
		ec.getEntities().add(asteroid);
		
		/* VUES */
		SpaceView sv = new SpaceView(ec);
		
		/* START */
		
		ec.registerAll(sv);
		
		sv.start(stage);
		sv.render();
		//while(true) {
			//sv.render();
			//Thread.sleep(500);
		//}
	}
}
