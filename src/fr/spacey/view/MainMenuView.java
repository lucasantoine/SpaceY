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
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.FileChooser;
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
	public Label chooseFile;
	public Text quit;
	public Text errorMessage;

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

		scene.getStylesheets()
				.add(getClass().getClassLoader().getResource("styles/MainMenuStyle.css").toExternalForm());

		title = this.createTitle();

		play = this.createText("D�marrer", -300);

		chooseFile = this.createLabel("Choisir un fichier ...", -700);

		quit = this.createText("Quitter", -900);

		chooseFile.setOnMouseClicked(e -> {
			FileChooser filechooser = new FileChooser();
			filechooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Aastro File", "*.astro"));
			File file = filechooser.showOpenDialog((Stage) play.getScene().getWindow());
			if (file != null) {
				chooseFile.setText(file.getName());
				mmc.chooseFile(file);
			}
		});

		play.setOnMouseClicked(e -> {
			mmc.start((Stage) play.getScene().getWindow());
		});

		quit.setOnMouseClicked(e -> {
			mmc.leaveApplication((Stage) play.getScene().getWindow());
		});

		errorMessage = this.createError("", -2000);

		scene.setCamera(new PerspectiveCamera());
		this.pane.getChildren().addAll(canvas, title, play, chooseFile, quit, errorMessage);
	}

	/**
	 * Cree le titre du menu.
	 * 
	 * @return le titre du menu.
	 */
	private Text createTitle() {
		Text title = new Text("S p a c e Y");
		title.setFont(Font.font(100));
		title.setStroke(Color.YELLOW);
		title.setStrokeWidth(2);
		title.setStrokeType(StrokeType.OUTSIDE);
		title.getTransforms().add(new Rotate(-50, 300, 200, 20, Rotate.X_AXIS));
		title.setX(canvas.getWidth() / 2);
		title.setY(canvas.getHeight() / 2);
		return title;
	}

	/**
	 * Cree le message d'erreur du menu.
	 * 
	 * @param text   Texte d'erreur par defaut.
	 * @param offset Decalage pour bien placer le message d'erreur
	 * @return le message d'erreur du menu.
	 */
	private Text createError(String text, int offset) {
		Text error = new Text("");
		error.setFont(Font.font(30));
		error.setFill(Color.INDIANRED);
		error.getTransforms().add(new Rotate(-50, 300, 100, 20, Rotate.X_AXIS));
		error.setX(canvas.getWidth() / 2);
		error.setY(canvas.getHeight() / 2 + offset);
		return error;
	}

	/**
	 * Cree une partie du menu.
	 * 
	 * @param text   Texte de la partie.
	 * @param offset Decalage pour bien placer la partie.
	 * @return une partie du menu.
	 */
	private Text createText(String text, int offset) {
		Text res = new Text(text);
		res.setFont(Font.font(60));
		res.setFill(Color.YELLOW);
		res.getTransforms().add(new Rotate(-50, 300, 200, 20, Rotate.X_AXIS));
		res.setX(canvas.getWidth() / 2);
		res.setY(canvas.getHeight() / 2 + offset);
		res.setId("handCursor");
		res.setOnMouseEntered(e -> {
			res.setText("> " + res.getText() + " <");
		});
		res.setOnMouseExited(e -> {
			res.setText(res.getText().replaceAll("[<> ]", ""));
		});
		return res;
	}

	/**
	 * Cree le selecteur de fichier.
	 * 
	 * @param text   Texte du selecteur de fichier.
	 * @param offset Decalage pour bien placer le selecteur de fichier.
	 * @return le selecteur de fichier.
	 */
	private Label createLabel(String text, int offset) {
		Label label = new Label(text);
		label.setFont(Font.font(40));
		label.setTextFill(Color.YELLOW);
		label.getTransforms().add(new Rotate(-50, 300, 400, 20, Rotate.X_AXIS));
		label.setLayoutX(canvas.getWidth() / 2);
		label.setLayoutY(canvas.getHeight() / 2 + offset);
		label.setId("chooseFile");
		return label;

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
			errorMessage.setText(mmc.getModel().getErrorMessage());
		}
		for (Star s : mmc.getModel().getStars()) {
			gc.setFill(s.getColor());
			gc.setGlobalAlpha(s.getOpacity());
			double sx;
			double sy;

			sx = map((s.getPosition().getX() / s.getZ()), 0, 1, s.getOstartx(), s.getOstopx());
			sy = map((s.getPosition().getY() / s.getZ()), 0, 1, s.getOstarty(), s.getOstopy());

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
	 * @param value  TODO
	 * @param istart TODO
	 * @param istop  TODO
	 * @param ostart TODO
	 * @param ostop  TODO
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
