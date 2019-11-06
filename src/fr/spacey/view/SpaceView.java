package fr.spacey.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import fr.spacey.controller.SpaceController;
import fr.spacey.model.Affichage;
import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.EntityType;
import fr.spacey.model.entity.Simule;
import fr.spacey.utils.ShowState;
import fr.spacey.utils.Sprite;
import fr.spacey.utils.Vector;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 26 oct. 2019
 * 
 *        Vue de la simulation contenant tous les parametres et les methodes
 *        utiles au bon affichage de la simulation.
 */
public class SpaceView implements Observer {

	private Stage stage;
	private SpaceController sc;

	/* VARIABLES GRAPHIQUES */
	private Canvas can;
	public Pane pane;
	private GraphicsContext gc;

	/**
	 * Constructeur de la SpaceView prenant en parametre son controleur.
	 * 
	 * @param sc Controleur de cette Vue.
	 */
	public SpaceView(SpaceController sc) {
		this.sc = sc;
		this.pane = new Pane();
		this.can = new Canvas(1, 1);
		this.gc = can.getGraphicsContext2D();
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
			if (e.getCode().equals(KeyCode.Z)) {
				sc.getModel().getVaisseau().upThrottle();
			}

			if (e.getCode().equals(KeyCode.S)) {
				sc.getModel().getVaisseau().downThrottle();
			}

			if (e.getCode().equals(KeyCode.Q)) {
				if (e.isAltDown())
					sc.getModel().getVaisseau().incAngle(-45.0);
				else
					sc.getModel().getVaisseau().incAngle(-1.0);
				;
			}

			if (e.getCode().equals(KeyCode.D)) {
				if (e.isAltDown())
					sc.getModel().getVaisseau().incAngle(45.0);
				else
					sc.getModel().getVaisseau().incAngle(1.0);
				;
			}

			if (e.getCode().equals(KeyCode.SHIFT)) {
				sc.getModel().getVaisseau().fullThrottle();
				;
			}

			if (e.getCode().equals(KeyCode.CONTROL)) {
				sc.getModel().getVaisseau().noThrottle();
				;
			}
		
			if (e.getCode().equals(KeyCode.SPACE)) {
				sc.toggleRunning();
			} else {
				sc.onKeyPressed(e);
			}
		});

		pane.setOnMouseReleased(e -> {
			sc.onMouseReleased(e);
		});

		/*
		 * pane.setOnMouseClicked(e -> { sc.onMouseClicked(e); });
		 */

		pane.setOnMousePressed(e -> {
			sc.onMousePressed(e);
		});

		pane.setOnMouseMoved(e -> {
			sc.onMouseMoved(e);
		});

		pane.setOnMouseDragged(e -> {
			sc.onMouseDrag(e);
		});

		/*
		 * pane.setOnScroll(e -> { double valeur = e.getDeltaY() > 0 ? 0.1 : -0.1; if
		 * (this.zoom + valeur > 0.5 && this.zoom + valeur < 2) this.zoom += valeur; });
		 */

		can.widthProperty().bind(pane.widthProperty());
		can.heightProperty().bind(pane.heightProperty());
	}

	/**
	 * Modifie l'affichage en fonction des modifications apportees au modele de la
	 * simulation.
	 */
	@Override
	public void update(Observable obs, Object obj) {
		Affichage aff = sc.getModel().getAffichage();

		// FOND DECRAN
		gc.setTransform(1, 0, 0, 1, 0, 0);
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, pane.getWidth(), pane.getHeight());

		// ZOOM
		// gc.setTransform(zoom, 0, 0, zoom, (width - width * zoom) / 2.0,
		// (height - height * zoom) / 2.0);
		gc.setTransform(new Affine(new Scale(aff.getZoom(), aff.getZoom())));
		gc.setFill(Color.WHITE);

		gc.setFill(Color.LIGHTGREY);
		for (int i = 0; i < aff.getStars().length; i++) {
			gc.fillOval(aff.getStars()[i].getX() + aff.getxOffset(), aff.getStars()[i].getY() + aff.getyOffset(), 3, 3);
		}

		// ENTITES
		for (Entity e : sc.getModel().getEntities()) {
			double planetX = e.getPos().getX() + aff.getxOffset() - e.getRadius() / 2;
			double planetY = e.getPos().getY() + aff.getyOffset() - e.getRadius() / 2;

			// TRAINEE
			if (e instanceof Simule) {
				try {
					@SuppressWarnings("unchecked")
					LinkedList<Vector> ll = (LinkedList<Vector>) ((Simule) e).getTrail().clone();
					for (Vector v : ll) {
						gc.setFill(Color.GREY);
						gc.fillOval(v.getX() + aff.getxOffset(), v.getY() + aff.getyOffset(), 2, 2);
					}

				} catch (NullPointerException exc) {
				}
			}
			
			Image img = Sprite.getImage(e.getImgId());
			
			if(e.getType().equals(EntityType.VAISSEAU)) {
				planetX += e.getRadius()/2;
				planetY += e.getRadius()/2;
		        gc.save();
		        gc.transform(new Affine(new Rotate(sc.getModel().getVaisseau().getAngle(), planetX, planetY)));
		        gc.translate(-e.getRadius()/2, -e.getRadius()/2);
		        gc.drawImage(img, planetX, planetY, e.getRadius(), e.getRadius());
		        gc.restore();
				planetX -= e.getRadius()/2;
				planetY -= e.getRadius()/2;
			} else {
				gc.drawImage(img, planetX, planetY, e.getRadius(), e.getRadius());
			}
			
			// INFOS SUR ENTITE
			if (e.getInfoMode().equals(ShowState.HOVERING) || e.getInfoMode().equals(ShowState.SHOWINFO)) {
				double startDescX = planetX + e.getRadius() + 25, startDescY = planetY + e.getRadius() + 25;

				gc.setFill(new Color(.4, .4, .4, 0.7));
				gc.fillRoundRect(startDescX, startDescY, 130, 100, 10, 10);

				gc.setFill(Color.RED);
				gc.setFont(new Font("pixelmix regular", 15));
				gc.fillText(e.getName(), startDescX + 5, startDescY + 17);

				gc.setStroke(Color.MEDIUMAQUAMARINE);
				gc.setLineWidth(5);
				gc.strokeOval(planetX, planetY, e.getRadius(), e.getRadius());
				gc.setLineWidth(1);
				gc.strokeLine(planetX + e.getRadius() + 3, planetY + e.getRadius() + 3, startDescX - 5, startDescY - 5);

				gc.setFill(Color.LIGHTGRAY);
				gc.setFont(new Font("pixelmix regular", 10));
				gc.fillText("Type: " + e.getType().NOM, startDescX + 15, startDescY + 30);
				gc.fillText("Masse: " + (int) e.getMasse(), startDescX + 15, startDescY + 42);
				gc.fillText("Rayon: " + (int) e.getRadius(), startDescX + 15, startDescY + 54);

			}
		}

		// COORD EN HAUT A GAUCHE
		gc.setTransform(1, 0, 0, 1, 0, 0);
		gc.setFont(new Font(17));
		double relatX = 10 - gc.getTransform().getTx(), relatY = gc.getTransform().getTy();

		gc.setFill(new Color(.2, .2, .2, 0.9));
		gc.fillRoundRect(relatX - 5, 5 - relatY, 140, 125, 10, 10);

		gc.setFill(Color.WHITE);
		gc.setFont(new Font("pixelmix regular", 10));
		
		gc.fillText("Pos: [" + (int) (aff.getxOffset() - aff.getWidth() / 2)+","+ (int)(aff.getyOffset() - aff.getHeight() / 2) + ']', relatX, 25 - relatY);
		gc.fillText("G: " + String.format("%4.2e", SpaceModel.G), relatX, 43 - relatY);
		gc.fillText("DT: " + String.format("%4.2e", sc.getModel().getDt()), relatX, 62 - relatY);
		gc.fillText("FA: " + sc.getModel().getFa(), relatX, 80 - relatY);
		gc.fillText("RAYON: " + sc.getModel().getRayon(), relatX, 97 - relatY);
		gc.fillText("TEMPS: "+formatTimeFromSec(sc.getTime()), relatX, 115 - relatY);

		if (sc.getModel().hasEntitySelected()) {
			drawSelectedEntity(gc);
		}

		if (sc.getModel().hasVaisseau()) {
			drawSpaceshipHUD(gc);
		}

		if (sc.getModel().hasEntitySelected()) {
			centerCameraOnEntity(sc.getModel().getEntitySelected());
		}

		stage.setTitle("SpaceY  -  DT=" + sc.getModel().getDt() + ", FA=" + sc.getModel().getFa() + ", G=" + SpaceModel.G
				+ ", TIME=" + formatTimeFromSec(sc.getTime()));
	}

	/**
	 * Change le format de l'entier passe en parametre pour qu'il corresponde a
	 * HH:MM:SS.
	 * 
	 * @param seconds Secondes ecoulees dans la simulation.
	 * @return le temps ecoule dans la simulation sous la forme HH:MM:SS.
	 */
	private String formatTimeFromSec(int seconds) {
		if (seconds <= 0)
			return "00:00:00";

		String date = "";

		int[] unitValues = { 604800, 86400, 3600, 60, 1 }; // semaine, jour, heure, minute, seconde

		for (int i = 0; i < unitValues.length; i++) {
			int quot = seconds / unitValues[i];
			if (quot > 0) {
				if (i > 1) {
					date += (quot < 10 ? "0" : "") + quot + ":";
				} else if (i == 1) {
					date += quot + "j ";
				} else {
					date += quot + "s ";
				}
				seconds -= (quot * unitValues[i]);
			} else if (i > 1) {
				date += "00:";
			}
		}
		return date.substring(0, date.length() - 1);
	}

	/**
	 * Methode permettant de dessiner le HUD du Vaisseau.
	 * 
	 * @param gc Contexte Graphique de la simulation.
	 */
	private void drawSpaceshipHUD(GraphicsContext gc) {
		Affichage aff = sc.getModel().getAffichage();
		double relatX = 10 - gc.getTransform().getTx(), relatY = gc.getTransform().getTy();

		// HUD ROND VAISSEAU
		gc.setFill(new Color(.3, .3, .3, 1));
		gc.fillOval(relatX+20, aff.getHeight() - 200 - relatY, 160, 160);

		gc.setLineWidth(1);
		gc.setStroke(new Color(0.6, 0.6, 0.6, 1));
		gc.strokeOval(relatX + 27.5, aff.getHeight() - 192.5 - relatY, 145, 145);

		// HUD BARRES VAISSEAU ACCELERATION
		gc.fillRoundRect(relatX + 150, aff.getHeight() - 135 - relatY, 200, 35, 20, 20);
		gc.strokeRoundRect(relatX + 155, aff.getHeight() - 130 - relatY, 190, 25, 20, 20);

		Stop[] stops = new Stop[] { new Stop(0, Color.YELLOW), new Stop(1, Color.RED) };
		gc.setFill(new LinearGradient(relatX + 158, aff.getHeight() - 127 - relatY, relatX + 342,
				aff.getHeight() - 108 - relatY, false, CycleMethod.NO_CYCLE, stops));
		gc.fillRoundRect(relatX + 158, aff.getHeight() - 127 - relatY,
				sc.getModel().getVaisseau().getRocketActivity()*0.01 * 184, 19, 15, 15);

		// HUD BARRES VAISSEAU FUEL
		gc.setFill(new Color(.3, .3, .3, 1));
		gc.fillRoundRect(relatX + 130, aff.getHeight() - 90 - relatY, 200, 35, 20, 20);
		gc.strokeRoundRect(relatX + 135, aff.getHeight() - 85 - relatY, 190, 25, 20, 20);

		double val = (sc.getModel().getVaisseau().getFuel() * 100 / sc.getModel().getVaisseau().getTankSize()) / 100;
		Color col = new Color(1, 1, 0, 1);
		if (val > 0.75) {
			col = new Color(0, 1, 0, 1);
		} else if (val < 0.25) {
			col = new Color(1, 0, 0, 1);
		}
		gc.setFill(col);
		gc.fillRoundRect(relatX + 138, aff.getHeight() - 82 - relatY, val * 184, 19, 15, 15);

		if (!sc.isRunning()) {
			gc.setFill(Color.GREY);
			gc.fillRect(aff.getWidth() - 50, 20, 10, 40);
			gc.fillRect(aff.getWidth() - 30, 20, 10, 40);
		}
	}

	private final static double SELECTX = 850, SELECTY = 900 - 190;
	private final static double SELECTWIDTH = 700, SELECTHEIGHT = 140;
	private final static int TAILLEIMG = 100;
	private final static double TEXTX = SELECTX + SELECTWIDTH / 6;

	/**
	 * Methode permettant de dessiner l'affichage d'une Entite selectionnee.
	 * 
	 * @param gc2 Contexte Graphique de la simulation.
	 */
	private void drawSelectedEntity(GraphicsContext gc) {
		Entity e = sc.getModel().getEntitySelected();

		gc.setFill(new Color(.3, .3, .3, 1));
		gc.fillRoundRect(SELECTX, SELECTY, SELECTWIDTH, SELECTHEIGHT, 20, 20);
		gc.setLineWidth(1);
		gc.setStroke(new Color(0.6, 0.6, 0.6, 1));
		gc.strokeRoundRect(SELECTX + 5, SELECTY + 5, SELECTWIDTH - 10, SELECTHEIGHT - 10, 20, 20);

		gc.setFill(new Color(0.6, 0.6, 0.6, 1));
		gc.fillPolygon(new double[] { SELECTX + 15, SELECTX + 30, SELECTX + 30 }, 
				new double[] { 780, 805, 755 }, 3);
		gc.fillPolygon(new double[] { SELECTX + 685, SELECTX + 670, SELECTX + 670 }, 
				new double[] { 780, 805, 755 }, 3);

		gc.drawImage(Sprite.getImage(e.getImgId()), SELECTX + SELECTWIDTH / 1.5 - 50, SELECTY + SELECTHEIGHT / 2 - 50,
				TAILLEIMG, TAILLEIMG);

		gc.setFill(Color.LIGHTBLUE);
		gc.setFont(new Font("pixelmix regular", 17));
		gc.fillText(e.getName(), TEXTX + 8, SELECTY + 28);
		gc.setFill(Color.LIGHTGRAY);
		gc.setFont(new Font("pixelmix regular", 14));
		gc.fillText("Type: " + e.getType().NOM, TEXTX, SELECTY + 46);
		gc.fillText("Masse: " + String.format("%4.2e", e.getMasse()) + " kg", TEXTX, SELECTY + 62);
		gc.fillText("Rayon: " + String.format("%4.2e", e.getRadius()) + " m", TEXTX, SELECTY + 78);
		gc.fillText("Pos: " + e.getPos().toStringScientific(), TEXTX, SELECTY + 94);
		gc.fillText("Vel: " + e.getVel().toStringScientific() + " m/s", TEXTX, SELECTY + 110);
		gc.fillText("Acc: " + e.getAcc().toStringScientific() + " m/s", TEXTX, SELECTY + 126);
	}

	/**
	 * Methode permettant de centrer la camera sur l'Entite selectionnee.
	 * 
	 * @param e Entite selectionnee.
	 */
	private void centerCameraOnEntity(Entity e) {
		Affichage aff = sc.getModel().getAffichage();
		aff.setxOffset(aff.getWidth() / 2 - e.getPos().getX());
		aff.setyOffset(aff.getHeight() / 2 - e.getPos().getY());
	}

	/**
	 * Methode permettant de demarrer la simulation.
	 * 
	 * @param s Scene de la simulation.
	 */
	public void start(Stage s) {
		this.stage = s;
		Affichage aff = sc.getModel().getAffichage();
		s.setTitle("SpaceY");
		s.setScene(new Scene(pane, aff.getWidth(), aff.getHeight()));
		s.setResizable(true);
		// s.setFullScreen(true);
		// s.setMaximized(true);
		// s.show();
	}
}
