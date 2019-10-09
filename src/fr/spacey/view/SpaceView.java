package fr.spacey.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import fr.spacey.SpaceY;
import fr.spacey.controller.SpaceController;
import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.Simule;
import fr.spacey.utils.Vector;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
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
	public Pane pane;
	private GraphicsContext gc;
	
	/* VARIABLES DE CANVAS */
	private double width;
	private double height;
	
	private double xOffset;
	private double yOffset;
	
	private double startDragX = 0;
	private double startDragY = 0;
	protected double startSceneX;
	protected double startSceneY;
	private double zoom;
	
	public SpaceView(SpaceController sc) {
		this.sc = sc;
		this.pane = new Pane();
		this.width = 1600;
		this.height = 900;
		this.can = new Canvas(width, height);
		this.gc = can.getGraphicsContext2D();
		this.xOffset = width/2;
		this.yOffset = height/2;
		this.zoom = 1;
		this.can.setFocusTraversable(true);
		this.sc.register(this);
		this.pane.getChildren().add(can);
		try {
			Font.loadFont(new FileInputStream(new File("res/fonts/pixelmix.ttf")), 10);
			Font.loadFont(new FileInputStream(new File("res/fonts/pixelmix_bold.ttf")), 10);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		
		pane.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.SPACE)) {
				SpaceY.getInstance().toggleRunning();
			}
		});
		
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

        can.widthProperty().bind(pane.widthProperty());
        can.heightProperty().bind(pane.heightProperty());
        
        
	}
	
	@Override
	public void update(Observable obs, Object obj) {
		
		// FOND DECRAN
		gc.setTransform(1, 0, 0, 1, 0, 0);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, pane.getWidth(), pane.getHeight());
		
		// ZOOM
		gc.setTransform(zoom, 0, 0, zoom, (width - width * zoom) / 2.0, (height - height * zoom) / 2.0);
		
		//ENTITES
		for(Entity e : sc.getModel().getEntities()) {
			double planetX = e.getPos().getX()+xOffset-e.getRadius()/2;
			double planetY = e.getPos().getY()+yOffset-e.getRadius()/2;

			// TRAINEE
			if (e instanceof Simule) {
				try {
					@SuppressWarnings("unchecked")
					LinkedList<Vector> ll = (LinkedList<Vector>) ((Simule) e).getTrail().clone();
					for (Vector v : ll) {
						gc.setFill(Color.GREY);
						gc.fillOval(v.getX() + xOffset, v.getY() + yOffset, 2, 2);
					}
				} catch (NullPointerException exc) {}
			}

			gc.drawImage(e.getImage(), planetX, planetY, e.getRadius(), e.getRadius());

			//INFOS SUR ENTITE
			if(e.isShowInfo()) {
		         double startDescX = planetX+e.getRadius()+25, 
		        	   startDescY = planetY+e.getRadius()+25;
		        
		        gc.setFill(new Color(.4,.4,.4,0.7));
				gc.fillRoundRect(startDescX, startDescY, 130, 100, 10, 10);

				gc.setFill(Color.RED);
		        gc.setFont(new Font("pixelmix regular", 16));
				gc.fillText(e.getName(), startDescX+5, startDescY+17);
				
				gc.setStroke(Color.MEDIUMAQUAMARINE);
				gc.setLineWidth(5);
				gc.strokeOval(planetX, planetY, e.getRadius(), e.getRadius());
				gc.setLineWidth(1);
				gc.strokeLine(planetX+e.getRadius()+3, planetY+e.getRadius()+3,
						startDescX-5, startDescY-5);
				
				gc.setFill(Color.LIGHTGRAY);
				gc.setFont(new Font("pixelmix regular", 10));
				gc.fillText("Type: "+e.getType().NOM, startDescX+15, startDescY+30);
				gc.fillText("Masse: "+(int)e.getMasse(), startDescX+15, startDescY+42);
				gc.fillText("Rayon: "+(int)e.getRadius(), startDescX+15, startDescY+54);
				gc.fillText("Pos: "+e.getPos().toStringRounded(), startDescX+15, startDescY+66);
				gc.fillText("Vel: "+e.getVel().toStringRounded(2), startDescX+15, startDescY+78);
				gc.fillText("Acc: "+e.getAcc().toStringRounded(3), startDescX+15, startDescY+90);
				
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
		gc.setFont(new Font("pixelmix regular", 10));
		gc.fillText("Pos: ["+(int)(xOffset-width/2)+","+(int)(yOffset-height/2)+']', 
				relatX, 25 - relatY);

		gc.fillText("G: "+inst.getG(), 
				relatX, 43 - relatY);
		
		gc.fillText("dt: "+inst.getDt(), 
				relatX, 62 - relatY);
		
		gc.fillText("fa: "+inst.getFa(), 
				relatX, 80 - relatY);
		
		gc.fillText("rayon: "+inst.getRayon(), 
				relatX, 97 - relatY);
		
		if(sc.getModel().hasVaisseau()) {
			// HUD ROND VAISSEAU
			gc.setFill(new Color(.3,.3,.3,1));
			gc.fillOval(relatX+20, height-200-relatY, 160, 160);
			
			gc.setLineWidth(1);
			gc.setStroke(new Color(0.6,0.6,0.6,1));
			gc.strokeOval(relatX+27.5, height-192.5-relatY, 145, 145);
			
			
			// HUD BARRES VAISSEAU ACCELERATION
			gc.fillRoundRect(relatX+150, height-135-relatY, 200, 35, 20, 20);
			gc.strokeRoundRect(relatX+155, height-130-relatY, 190, 25, 20, 20);

			Stop[] stops = new Stop[] { new Stop(0, Color.YELLOW), new Stop(1, Color.RED)};
			gc.setFill(new LinearGradient(relatX+158, height-127-relatY, relatX+342, height-108-relatY, false, CycleMethod.NO_CYCLE, stops));
			gc.fillRoundRect(relatX+158, height-127-relatY, sc.getModel().getVaisseau().getPropPrincipal()*184, 19, 15, 15);

			// HUD BARRES VAISSEAU FUEL
			gc.setFill(new Color(.3,.3,.3,1));
			gc.fillRoundRect(relatX+130, height-90-relatY, 200, 35, 20, 20);
			gc.strokeRoundRect(relatX+135, height-85-relatY, 190, 25, 20, 20);
			
			double val = (sc.getModel().getVaisseau().getFuel()*100/sc.getModel().getVaisseau().getTankSize())/100;
			Color col = new Color(1,1,0,1);
			if(val > 0.75) {
				col = new Color(0,1,0,1);
			} else if(val < 0.25) {
				col = new Color(1,0,0,1);
			}
			gc.setFill(col);
			gc.fillRoundRect(relatX+138, height-82-relatY, 
					val*184, 19, 15, 15);
		}
	}

	public void start(Stage s) {
		s.setTitle("SpaceY");
		s.setScene(new Scene(pane, width, height));
		s.setResizable(true);
		//s.setFullScreen(true);
		//s.setMaximized(true);
		s.show();
	}
}
