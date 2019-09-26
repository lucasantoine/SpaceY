package fr.spacey.view;

import java.util.Observable;
import java.util.Observer;

import fr.spacey.controller.EntityController;
import fr.spacey.controller.SpaceController;
import fr.spacey.models.entities.EntityModel;
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
public class SpaceView implements Observer {

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
	
	void setwall() {
		gc.setTransform(1, 0, 0, 1, 0, 0);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, can.getWidth(), can.getHeight());
		
	}
	
	@Override
	public void update(Observable obs, Object obj) {
		gc.setTransform(zoom, 0, 0, zoom, (width - width * zoom) / 2.0, (height - height * zoom) / 2.0);
		{
			EntityModel e = (EntityModel) obs;
			
			gc.setFill(Color.AQUA);
			gc.fillOval(e.getPos().getX()+xOffset-e.getMasse()/2, e.getPos().getY()+yOffset-e.getMasse()/2, 
					e.getMasse(), e.getMasse());
			
			gc.setFill(Color.ALICEBLUE);
			gc.setLineWidth(3.0);
			gc.strokeRect(e.getPos().getX()+xOffset, e.getPos().getY()+yOffset, 1, 1);
		}
		gc.setFont(new Font(20));
		gc.setFill(Color.WHITE);
		gc.fillText("x: "+(xOffset-width/2), 0, 15);
		gc.fillText("y: "+(yOffset-height/2), 0, 35);
	}
	
	public void move(float x, float y) {
		xOffset += x;
		yOffset += y;
		checkOffScreen();
	}
	
	private void checkOffScreen() {}
	
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
}
