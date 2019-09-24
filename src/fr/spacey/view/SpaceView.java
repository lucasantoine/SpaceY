package fr.spacey.view;

import java.util.Observable;
import java.util.Observer;

import fr.spacey.controller.EntityController;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SpaceView implements Observer {

	private EntityController ec;
	private Canvas can;
	
	public SpaceView(EntityController ec) {
		this.ec = ec;
		this.can = new Canvas(1080, 720);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}
	
	public void start(Stage s) {
		Pane p = new Pane();
		Scene sc = new Scene(p);
		p.getChildren().add(can);
		s.setScene(sc);
		s.show();
	}

}
