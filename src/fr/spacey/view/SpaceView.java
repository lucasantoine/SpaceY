package fr.spacey.view;

import java.util.Observable;
import java.util.Observer;

import fr.spacey.SpaceY;
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
	public Pane pane;
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
		this.sc = sc;
		this.pane = new Pane();
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

			gc.setFill(Color.RED);
			gc.fillOval(planetX, planetY, 
					e.getMasse(), e.getMasse());

			//INFOS SUR ENTITE
			if(e.isShowInfo()) {
		        double startDescX = planetX+e.getMasse()+25, 
		        	   startDescY = planetY+e.getMasse()+25;
				
		        gc.setFill(new Color(.4,.4,.4,0.7));
				gc.fillRoundRect(startDescX, startDescY, 130, 100, 10, 10);

				gc.setFill(Color.RED);
		        gc.setFont(new Font(16));
				gc.fillText(e.getName(), startDescX+5, startDescY+17);
				
				gc.setStroke(Color.WHITE);
				gc.strokeOval(planetX, planetY, e.getMasse(), e.getMasse());
		        gc.setLineWidth(1);
				gc.strokeLine(planetX+e.getMasse()+3, planetY+e.getMasse()+3,
						startDescX-5, startDescY-5);
				
				gc.setFill(Color.LIGHTGRAY);
				gc.setFont(new Font(10));
				gc.fillText("Masse: "+e.getMasse(), startDescX+5, startDescY+30);
				gc.fillText("Pos: "+e.getPos().toStringRounded(), startDescX+5, startDescY+40);
			}

		}

		//COORD EN HAUT DE GAUCHE
		gc.setTransform(1, 0, 0, 1, 0, 0);
		gc.setFont(new Font(17));
		SpaceY inst = SpaceY.getInstance();
		double relatX = 10 - gc.getTransform().getTx(), relatY = gc.getTransform().getTy();
		
		gc.setFill(new Color(.1,.1,.1,0.9));
		gc.fillRoundRect(relatX-5, 5-relatY, 130, 100, 10, 10);
		
		gc.setFill(Color.WHITE);
		gc.fillText("Pos: ["+(int)(xOffset-width/2)+","+(int)(yOffset-height/2)+']', 
				relatX, 25 - relatY);

		gc.fillText("G: "+inst.gravite, 
				relatX, 43 - relatY);
		
		gc.fillText("dt: "+inst.dt, 
				relatX, 62 - relatY);
		
		gc.fillText("fa: "+inst.fa, 
				relatX, 80 - relatY);
		
		gc.fillText("rayon: "+inst.rayon, 
				relatX, 97 - relatY);
	}


	public void start(Stage s) {
		s.setTitle("SpaceY");
		s.setScene(new Scene(pane, width, height));
		s.setResizable(true);
		s.setFullScreen(false);
		s.show();
	}
}
