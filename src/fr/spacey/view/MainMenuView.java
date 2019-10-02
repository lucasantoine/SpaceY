package fr.spacey.view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Observable;
import java.util.Observer;

import fr.spacey.controller.MainMenuController;
import fr.spacey.models.menu.Star;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	public Text title;
	public Text play;
	public Text quit;
	
	public MainMenuView(MainMenuController mmc) {
		this.mmc = mmc;
		this.pane = new StackPane();
		this.scene = new Scene(pane);
		this.width = 1280;
		this.height = 720;
		this.canvas = new Canvas(width, height);
		this.gc = canvas.getGraphicsContext2D();
		
		 title = new Text("S p a c e Y");
		title.setFont(Font.font(100));
		title.setStroke(Color.YELLOW);
		title.setStrokeWidth(2);
		title.setStrokeType(StrokeType.OUTSIDE);
        title.getTransforms().add(new Rotate(-50, 300, 200, 20, Rotate.X_AXIS));
        title.setX(canvas.getWidth()/2);
        title.setY(canvas.getHeight()/2);
        
		FileInputStream input;
		Image image = null;
		try {
			input = new FileInputStream("res/fusee.png");
			image = new Image(input, 40, 40, true, true);
		
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		ImageView fusee1 = new ImageView(image);
		fusee1.setVisible(false);
		ImageView fusee2 = new ImageView(image);
		fusee2.setVisible(false);
		
		 play = new Text("Démarrer");
		play.setFont(Font.font(60));
		play.setFill(Color.YELLOW);
		play.getTransforms().add(new Rotate(-50, 300, 200, 20, Rotate.X_AXIS));
		play.setX(canvas.getWidth()/2);
		play.setY(canvas.getHeight()/2 - 300);
		play.setOnMouseEntered(e -> { 
			fusee1.setVisible(true);
			fusee2.setVisible(true);
		});
		play.setOnMouseExited(e -> { 
			fusee1.setVisible(false);
			fusee2.setVisible(false);
		});

		fusee1.relocate(canvas.getWidth() / 2 - 165, canvas.getHeight()/2 + 25);
		fusee1.setManaged(false);
		fusee1.setRotate(-45);
		fusee2.relocate(canvas.getWidth() / 2 + 125, canvas.getHeight()/2 + 25);
		fusee2.setManaged(false);
		fusee2.setRotate(135);
		
		play.setOnMouseClicked(e -> {
			mmc.isStart = true;
		});
		
		 quit = new Text("Quitter");
		quit.setFont(Font.font(60));
		quit.setFill(Color.YELLOW);
		quit.getTransforms().add(new Rotate(-50, 300, 200, 20, Rotate.X_AXIS));
		quit.setX(canvas.getWidth()/2);
		quit.setY(canvas.getHeight()/2 - 500);
		quit.setOnMouseEntered(e -> { quit.setText("> Quitter <"); });
		quit.setOnMouseExited(e -> { quit.setText("Quitter"); });
		
        
        scene.setCamera(new PerspectiveCamera());
		this.pane.getChildren().addAll(canvas, title, play, quit, fusee1, fusee2);
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
	
		gc.setFill(e.getColor());
		gc.setGlobalAlpha(e.getOpacity());
		double sx;
		double sy;

		sx = map((e.getPosition().getX() / e.getZ()), 0, 1, e.getOstartx(), e.getOstopx());
		sy = map((e.getPosition().getY() / e.getZ()), 0, 1, e.getOstarty(), e.getOstopy());
		
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
	
	public Canvas getCanvas() {
		return canvas;
	}
	
}
