package fr.spacey.view;

import java.io.File;
import java.util.Observable;
import java.util.Observer;

import fr.spacey.controller.MainMenuController;
import fr.spacey.model.menu.Star;
import fr.spacey.utils.Sprite;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 26 oct. 2019
 * 
 *        Vue du menu principal de la simulation contenant tous les parametres
 *        et les methodes lies a l'affichage du menu.
 */
public class MainMenuView implements Observer {

	private MainMenuController mmc;
	private Scene scene;
	private StackPane pane;
	private Canvas canvas;
	private int width;
	private int height;
	public GraphicsContext gc;
	public Text title;
	public Text play;
	public Text quit;

	/**
	 * Constructeur de MainMenuView prenant en parametre son controleur.
	 * 
	 * @param mmc Controleur de cette Vue.
	 */
	public MainMenuView(MainMenuController mmc) {
		this.mmc = mmc;
		this.pane = new StackPane();
		this.scene = new Scene(pane);
		this.width = 1600;
		this.height = 900;
		this.canvas = new Canvas(width, height);
		this.gc = canvas.getGraphicsContext2D();
		this.mmc.register(this);

		scene.getStylesheets().add("file:res/styles/MainMenuStyle.css");

		title = new Text("S p a c e Y");
		title.setFont(Font.font(100));
		title.setStroke(Color.YELLOW);
		title.setStrokeWidth(2);
		title.setStrokeType(StrokeType.OUTSIDE);
		title.getTransforms().add(new Rotate(-50, 300, 200, 20, Rotate.X_AXIS));
		title.setX(canvas.getWidth() / 2);
		title.setY(canvas.getHeight() / 2);

		play = new Text("Demarrer");
		play.setFont(Font.font(60));
		play.setFill(Color.YELLOW);
		play.getTransforms().add(new Rotate(-50, 300, 200, 20, Rotate.X_AXIS));
		play.setX(canvas.getWidth() / 2);
		play.setY(canvas.getHeight() / 2 - 300);
		play.setOnMouseEntered(e -> {
			play.setText("> " + play.getText() + " <");
		});
		play.setOnMouseExited(e -> {
			play.setText(play.getText().replaceAll("[<> ]", ""));
		});

		ChoiceBox<String> choicebox = new ChoiceBox<String>();
		choicebox.getTransforms().add(new Rotate(-50, 200, 100, 20, Rotate.X_AXIS));
		choicebox.setManaged(true);
		choicebox.relocate(canvas.getWidth() / 2, canvas.getHeight() / 2 - 800);

		File systemesfolder = new File("res/systemes");

		for (File systemesfile : systemesfolder.listFiles()) {
			choicebox.getItems().add(systemesfile.getName().replaceAll(".astro", ""));
		}
		choicebox.getSelectionModel().select(0);

		play.setOnMouseClicked(e -> {
			mmc.start("res/systemes/"+choicebox.getSelectionModel().getSelectedItem()+".astro", (Stage) play.getScene().getWindow());
		});

		quit = new Text("Quitter");
		quit.setFont(Font.font(60));
		quit.setFill(Color.YELLOW);
		quit.getTransforms().add(new Rotate(-50, 300, 200, 20, Rotate.X_AXIS));
		quit.setX(canvas.getWidth() / 2);
		quit.setY(canvas.getHeight() / 2 - 700);
		quit.setOnMouseEntered(e -> {
			quit.setText("> " + quit.getText() + " <");
		});
		quit.setOnMouseExited(e -> {
			quit.setText(quit.getText().replaceAll("[<> ]", ""));
		});
		quit.setOnMouseClicked(e -> {
			mmc.leaveApplication((Stage) play.getScene().getWindow());
		});

		scene.setCamera(new PerspectiveCamera());
		this.pane.getChildren().addAll(canvas, title, play, choicebox, quit);
	}

	/**
	 * Demarre la Vue du Menu.
	 * 
	 * @param s Scene du Menu.
	 */
	public void start(Stage s) {
		s.setTitle("SpaceY | Menu principal");
		s.setScene(scene);
		s.setResizable(true);
//		s.setMaximized(true);
		s.getIcons().add(Sprite.LOGO.getImage());
		s.show();
	}

	/**
	 * Modifie la position des etoiles en fond d'ecran.
	 */
	@Override
	public void update(Observable obs, Object arg) {
		if (!mmc.getModel().isStart()) {
			printBackground();
		}
		for (Star s : mmc.getModel().getStars()) {
			gc.setFill(s.getColor());
			gc.setGlobalAlpha(s.getOpacity());
			double sx;
			double sy;

			sx = map(s.getPosition().getX() / s.getZ(), 0, 1, s.getOstartx(), s.getOstopx());
			sy = map(s.getPosition().getY() / s.getZ(), 0, 1, s.getOstarty(), s.getOstopy());

			gc.fillOval(sx, sy, s.getRayon(), s.getRayon());
			gc.setGlobalAlpha(1);
		}
	}

	/**
	 * Dessine le fond du Menu.
	 */
	public void printBackground() {
		this.gc.setFill(Color.BLACK);
		this.gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}

	/**
	 * Renvoie la largeur de la Vue.
	 * 
	 * @return la largeur de la Vue.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Renvoie la hauteur de la Vue.
	 * 
	 * @return la hauteur de la Vue.
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * TODO
	 * 
	 * @param value TODO
	 * @param istart TODO
	 * @param istop TODO
	 * @param ostart TODO
	 * @param ostop TODO
	 * @return TODO
	 */
	public double map(double value, double istart, double istop, double ostart, double ostop) {
		return ostart + (ostop - ostart) * ((value - istart) / (istop - istart));
	}

	/**
	 * Renvoie le Canvas sur lequel est dessine le Menu.
	 * 
	 * @return le Canvas sur lequel est dessine le Menu.
	 */
	public Canvas getCanvas() {
		return canvas;
	}

}