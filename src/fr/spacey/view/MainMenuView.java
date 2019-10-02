package fr.spacey.view;

import java.util.Observable;
import java.util.Observer;

import fr.spacey.SpaceY;
import fr.spacey.controller.MainMenuController;
import fr.spacey.models.menu.Star;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

public class MainMenuView implements Observer{

	private MainMenuController mmc;
	private Scene scene;
	private StackPane pane;
	private Canvas canvas;
	private int width;
	private int height;
	private GraphicsContext gc;
	
	public MainMenuView(MainMenuController mmc) {
		this.mmc = mmc;
		this.pane = new StackPane();
		this.scene = new Scene(pane);
		this.width = 1280;
		this.height = 720;
		this.canvas = new Canvas(width, height);
		this.gc = canvas.getGraphicsContext2D();
		
		Text title = new Text("S p a c e Y");
		title.setFont(Font.font(100));
		title.setStroke(Color.YELLOW);
		title.setStrokeWidth(2);
		title.setStrokeType(StrokeType.OUTSIDE);
        title.getTransforms().add(new Rotate(-50, 300, 200, 20, Rotate.X_AXIS));
        title.setX(canvas.getWidth()/2);
        title.setY(canvas.getHeight()/2);
        
		Text play = new Text("Create your world");
		play.setFont(Font.font(60));
		play.setFill(Color.YELLOW);
		play.getTransforms().add(new Rotate(-50, 300, 200, 20, Rotate.X_AXIS));
		play.setX(canvas.getWidth()/2);
		play.setY(canvas.getHeight()/2 - 300);
		play.setOnMouseEntered(e -> { play.setText("> Create your world <"); });
		play.setOnMouseExited(e -> { play.setText("Create your world"); });
        
		play.setOnMouseClicked(e -> {
			mmc.isStart = true;
		});
		
		Text quit = new Text("Quitter");
		quit.setFont(Font.font(60));
		quit.setFill(Color.YELLOW);
		quit.getTransforms().add(new Rotate(-50, 300, 200, 20, Rotate.X_AXIS));
		quit.setX(canvas.getWidth()/2);
		quit.setY(canvas.getHeight()/2 - 500);
		quit.setOnMouseEntered(e -> { quit.setText("> Quitter <"); });
		quit.setOnMouseExited(e -> { quit.setText("Quitter"); });
		
        
        scene.setCamera(new PerspectiveCamera());
		this.pane.getChildren().addAll(canvas, title, play, quit);
	}
	
	
	public void start(Stage s) {
		s.setTitle("SpaceY | Menu principale");
		s.setScene(scene);
		s.setResizable(true);
		s.setFullScreen(false);
		s.show();
	}


	@Override
	public void update(Observable obs, Object arg) {
		Star e = (Star) obs;
		
		Color color = (mmc.isStart ? Color.LIGHTSKYBLUE : Color.WHITE);
		
		gc.setFill(color);
		gc.setGlobalAlpha(e.getOpacity());
		double sx = map((e.getPosition().getX() / e.getZ()), width/2, 1, width/2, width);
		double sy = map((e.getPosition().getY() / e.getZ()), height/2, 1, height/2, height);
		gc.fillOval(sx, sy, e.getRayon(), e.getRayon());
		gc.setGlobalAlpha(1);
	}
	
	public void printBackground() {
		this.gc.setFill(Color.BLACK);
		this.gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public double map(double value, double istart, double istop, double ostart, double ostop) {
		 return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
	}
	
}
