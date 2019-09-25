package fr.spacey.view;

import java.util.Observable;
import java.util.Observer;

import fr.spacey.controller.EntityController;
import fr.spacey.models.entitites.Entity;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class SpaceView implements Observer {

	private EntityController ec;
	private Canvas can;
	private GraphicsContext gc;
	
	public SpaceView(EntityController ec) {
		this.ec = ec;
		this.can = new Canvas(1080, 720);
		this.gc = can.getGraphicsContext2D();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		
	}
	
	public void render() {
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, can.getWidth(), can.getHeight());
		
		for(Entity e : ec.getEntities()) {
			
		}
	}
	
	public void start(Stage s) {
		Pane p = new Pane();
		Scene sc = new Scene(p);
		p.getChildren().add(can);
		s.setScene(sc);
		s.show();
	}
	
	
	
}
