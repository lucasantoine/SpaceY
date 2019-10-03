package fr.spacey.view;

import java.util.Observable;
import java.util.Observer;

import fr.spacey.controller.SpaceController;
import fr.spacey.model.entity.Entity;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
/**
 * 
 * @author ItsPower
 *
 */
public class SpaceView implements Observer, Runnable {

	private SpaceController sc;
	
	/* VARIABLES GRAPHIQUES */
	private Canvas can;
	private Pane pane;
	private Scene scene;
	private GraphicsContext gc;
	
	/* VARIABLES DE CANVAS */
	private double xOffset;
	private double yOffset;
	private final int width, height;
	private double startDragX = 0;
	private double startDragY = 0;
	protected double startSceneX;
	protected double startSceneY;
	private double zoom;
	
	public SpaceView(SpaceController sc) {
		super();
		this.sc = sc;
		this.pane = new Pane();
		this.scene = new Scene(pane);
		this.width = 1280;
		this.height = 720;
		this.can = new Canvas(width, height);
		this.gc = can.getGraphicsContext2D();
		this.xOffset = width/2;
		this.yOffset = height/2;
		this.zoom = 1;
		this.pane.getChildren().add(can);
		
		pane.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				startDragX = e.getSceneX();
				startDragY = e.getSceneY();
				startSceneX = xOffset;
				startSceneY = yOffset;
			}
		});

		pane.setOnMouseDragged(e -> {
			xOffset = startSceneX + e.getSceneX() - startDragX;
			yOffset = startSceneY + e.getSceneY() - startDragY;
		});
		
		pane.setOnScroll(e -> {
			double valeur = e.getDeltaY()>0?0.1:-0.1;
			if(this.zoom+valeur > 0.5 && this.zoom+valeur < 2) 
				this.zoom += valeur;
		});
	}

	@Override
	public void update(Observable obs, Object obj) {

	}
	
	public void start(Stage s) {
		s.setTitle("SpaceY");
		s.setScene(scene);
		s.setResizable(true);
		s.setFullScreen(false);
		s.show();
	}

	public void printBackground() {
		gc.setTransform(1, 0, 0, 1, 0, 0);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, can.getWidth(), can.getHeight());
	}
		
	@Override
	public void run() {

		// FOND DECRAN
		gc.setTransform(1, 0, 0, 1, 0, 0);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, can.getWidth(), can.getHeight());
		
		// ZOOM
		gc.setTransform(zoom, 0, 0, zoom, (width - width * zoom) / 2.0, (height - height * zoom) / 2.0);
		
		//ENTITES
		for(Entity e : sc.getModel().getEntities()) {
			double planetX = e.getPos().getX()+xOffset-e.getMasse()/2;
			double planetY = e.getPos().getY()+yOffset-e.getMasse()/2;

			gc.setFill(Color.AQUA);
			gc.fillOval(planetX, planetY, 
					e.getMasse(), e.getMasse());

			//INFOS SUR ENTITE
			if(e.isShowInfo()) {
				gc.setStroke(Color.WHITE);
				gc.strokeOval(planetX, planetY, e.getMasse(), e.getMasse());
		        gc.setLineWidth(2);
		        double startDescX = planetX+e.getMasse()+25, 
		        	   startDescY = planetY+e.getMasse()+25;
		        
				gc.strokeLine(planetX+e.getMasse()+3, planetY+e.getMasse()+3,
						startDescX-5, startDescY-5);
				gc.setFont(new Font(16));
				gc.fillText(e.getName()+':', startDescX, startDescY);
				gc.setFill(Color.LIGHTGRAY);
				gc.setFont(new Font(10));
				gc.fillText("Masse: "+e.getMasse(), startDescX, startDescY+15);
				gc.fillText("Pos: "+e.getPos().toStringRounded(), startDescX, startDescY+30);
			}

		}

		//COORD EN HAUT DE GAUCHE
		gc.setTransform(1, 0, 0, 1, 0, 0);
		gc.setFont(new Font(20));
		gc.setFill(Color.WHITE);
		
		gc.fillText("x: "+(xOffset-width/2), 0 - gc.getTransform().getTx(), 15 -  gc.getTransform().getTy());
		gc.fillText("y: "+(yOffset-height/2), 0 - gc.getTransform().getTx(), 35 -  gc.getTransform().getTy());
	}
}
