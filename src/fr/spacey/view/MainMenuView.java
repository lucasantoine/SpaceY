package fr.spacey.view;

import java.util.Observable;
import java.util.Observer;

import fr.spacey.controller.MainMenuController;
import fr.spacey.models.menu.Star;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
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
		
		this.pane.getChildren().add(canvas);
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
		
		gc.setFill(Color.WHITE);
		gc.fillOval(e.getPosition().getX(), e.getPosition().getY(), e.getRayon(), e.getRayon());
	}


	public void printBackground() {
		gc.setTransform(1, 0, 0, 1, 0, 0);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
}
