package fr.spacey.view;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Observer;

import fr.spacey.SpaceY;
import fr.spacey.controller.SpaceController;
import fr.spacey.model.Affichage;
import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.EntityType;
import fr.spacey.model.entity.Simule;
import fr.spacey.model.entity.Vaisseau;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
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
	private Vector lastMousePos;
	private boolean isMenu;

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
		this.lastMousePos = new Vector(0, 0);
		this.can.setFocusTraversable(true);
		this.sc.register(this);
		this.pane.getChildren().add(can);
		this.isMenu = false;

		pane.setOnKeyPressed(e -> {
			if(e.getCode().equals(KeyCode.ESCAPE)) {
				if(!this.isMenu && sc.isRunning())
					sc.toggleRunning();
				this.isMenu = !this.isMenu;
			}
			sc.onKeyPressed(e);
		});
		
		pane.setOnKeyReleased(e -> {
			if(isMenu) return;
			sc.onKeyReleased(e);
		});

		pane.setOnMouseReleased(e -> {
			if(isMenu) {
				if(lastMousePos.getX() >= 550 && lastMousePos.getX() <= 1050 && lastMousePos.getY() >= 270 && lastMousePos.getY() <= 350) {
					this.isMenu = false;
				}
				
				if(lastMousePos.getX() >= 550 && lastMousePos.getX() <= 800 && lastMousePos.getY() >= 380 && lastMousePos.getY() <= 460) {
					
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH'h'mm");
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Sauvegardez votre fichier ASTRO");
					fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
					fileChooser.setInitialFileName(formatter.format(LocalDateTime.now())+".astro");
					fileChooser.getExtensionFilters().addAll(new ExtensionFilter("ASTRO Files", "*.astro"));
					File savedFile = fileChooser.showSaveDialog(stage);
					 
					if (savedFile != null) {
					    sc.saveSpace(savedFile);
					}
				}
				
				if(lastMousePos.getX() >= 810 && lastMousePos.getX() <= 1050 && lastMousePos.getY() >= 380 && lastMousePos.getY() <= 460) {
					FileChooser fileChooser = new FileChooser();
					fileChooser.setTitle("Selectionnez votre fichier ASTRO");
					fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
					fileChooser.getExtensionFilters().addAll(new ExtensionFilter("ASTRO Files", "*.astro"));
					File opennedFile = fileChooser.showOpenDialog(stage);
					 
					if (opennedFile != null) {
						sc.stopRunning();
						SpaceY.getInstance().startSimulation(opennedFile.getPath(), this.stage);
					}
				}
				
				if(lastMousePos.getX() >= 550 && lastMousePos.getX() <= 1050 && lastMousePos.getY() >= 490 && lastMousePos.getY() <= 570) {
					//TODO PARAMETRES
				}
				
				if(lastMousePos.getX() >= 550 && lastMousePos.getX() <= 1050 && lastMousePos.getY() >= 600 && lastMousePos.getY() <= 680) {
					sc.stopRunning();
					SpaceY.getInstance().start(this.stage);
				}
				return;
			}
			sc.onMouseReleased(e);
		});

		/*
		 * pane.setOnMouseClicked(e -> { sc.onMouseClicked(e); });
		 */

		pane.setOnMousePressed(e -> {
			sc.onMousePressed(e);
		});

		pane.setOnMouseMoved(e -> {
			lastMousePos = new Vector(e.getSceneX(), e.getSceneY());
			if(isMenu) return;
			sc.onMouseMoved(e);
		});

		pane.setOnMouseDragged(e -> {
			if(isMenu) return;
			sc.onMouseDrag(e);
		});

		pane.setOnScroll(e -> { 
			Affichage aff = sc.getModel().getAffichage();
			if(e.getDeltaY() > 0) {
				aff.setZoom(Math.min(10, aff.getZoom()+0.1)); 
			} else {
				aff.setZoom(Math.max(0.5, aff.getZoom()-0.1)); 
			}
		});
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
		gc.setTransform(new Affine(new Scale(aff.getZoom(), aff.getZoom())));
		gc.setFill(Color.WHITE);

		gc.setFill(Color.LIGHTGREY);
		for (int i = 0; i < aff.getStars().length; i++) {
			gc.fillOval(aff.getStars()[i].getX() + aff.getxOffset(), aff.getStars()[i].getY() + aff.getyOffset(), 3, 3);
		}
		



		// ENTITES TRAINEES
		for (Entity e : sc.getModel().getEntities()) {
			// TRAINEE
			if (e.getType().equals(EntityType.SIMULE)) {
				@SuppressWarnings("unchecked")
				LinkedList<Vector> ll = (LinkedList<Vector>) ((Simule) e).getTrail().clone();
				for (Vector v : ll) {
					gc.setFill(Color.GREY);
					gc.fillOval(v.getX() + aff.getxOffset(), v.getY() + aff.getyOffset(), 2, 2);
				}
			}
		}


		// ENTITES
		for (Entity e : sc.getModel().getEntities()) {
			double planetX = e.getPos().getX() + aff.getxOffset() - e.getRadius() / 2;
			double planetY = e.getPos().getY() + aff.getyOffset() - e.getRadius() / 2;

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
		}

		// ENTITES INFOS
		for (Entity e : sc.getModel().getEntities()) {
			// INFOS SUR ENTITE
			if (e.getInfoMode().equals(ShowState.HOVERING) || e.getInfoMode().equals(ShowState.SHOWINFO)) {

				double planetX = e.getPos().getX() + aff.getxOffset() - e.getRadius() / 2;
				double planetY = e.getPos().getY() + aff.getyOffset() - e.getRadius() / 2;
				
				double startDescX = planetX + e.getRadius() + 25, startDescY = planetY + e.getRadius() + 25;

				gc.setFill(new Color(.4, .4, .4, 0.7));
				gc.fillRoundRect(startDescX, startDescY, 130, 100, 20, 20);
				gc.setStroke(new Color(0.6, 0.6, 0.6, 1));
				gc.strokeRoundRect(startDescX+5, startDescY+5, 120, 90, 20, 20);

				gc.setStroke(Color.MEDIUMAQUAMARINE);
				gc.setLineWidth(5);
				gc.strokeOval(planetX, planetY, e.getRadius(), e.getRadius());
				gc.setLineWidth(1);
				gc.strokeLine(planetX + e.getRadius() + 3, planetY + e.getRadius() + 3, startDescX - 5, startDescY - 5);

				
				
				gc.setFill(Color.RED);
				gc.setFont(new Font("Minecraftia", 18));
				gc.fillText(e.getName(), startDescX + 15, startDescY + 40);

				startDescX += 25;
				gc.setFill(Color.LIGHTGRAY);
				gc.setFont(new Font("Minecraftia", 12));
				gc.fillText("Type: " + e.getType().NOM, startDescX, startDescY + 55);
				gc.fillText("Masse: " + (int) e.getMasse(), startDescX, startDescY + 70);
				gc.fillText("Rayon: " + (int) e.getRadius(), startDescX, startDescY + 85);

			}
		}

		// COORD EN HAUT A GAUCHE
		gc.setTransform(1, 0, 0, 1, 0, 0);
		gc.setFont(new Font(17));
		double relatX = 10 - gc.getTransform().getTx(), relatY = gc.getTransform().getTy();

		gc.setFill(new Color(.2, .2, .2, 0.9));
		gc.fillRoundRect(relatX - 5, 5 - relatY, 140, 115, 10, 10);

		gc.setFill(Color.WHITE);
		gc.setFont(new Font("Minecraftia", 10));

		gc.fillText("Pos: [" + (int) (aff.getxOffset() - aff.getWidth() / 2)+","+ (int)(aff.getyOffset() - aff.getHeight() / 2) + ']', relatX, 25 - relatY);
		gc.fillText("G: " + String.format("%4.2e", SpaceModel.G), relatX, 43 - relatY);
		gc.fillText("DT: " + String.format("%4.2e", sc.getModel().getDt()), relatX, 62 - relatY);
		gc.fillText("FA: " + sc.getModel().getFa(), relatX, 80 - relatY);
		gc.fillText("Taille: " + sc.getModel().getRayon(), relatX, 97 - relatY);
		gc.fillText("Temps: "+formatTimeFromSec(sc.getTime()), relatX, 115 - relatY);
		gc.fillText("Zoom: "+aff.getZoom(), relatX, 132 - relatY);

		if (sc.getModel().hasEntitySelected()) {
			drawSelectedEntity(gc);
			centerCameraOnEntity(sc.getModel().getEntitySelected());
		}

		if (sc.getModel().hasVaisseau()) {
			drawSpaceshipHUD(gc);
		}
		
		if(isMenu) {
			gc.setTransform(1, 0, 0, 1, 0, 0);
			gc.setFill(new Color(0,0,0, 0.8));
			//GaussianBlur gaussianBlur = new GaussianBlur(); 
		    //gaussianBlur.setRadius(10); 
			//gc.setEffect(gaussianBlur);
			gc.fillRect(0, 0, pane.getWidth(), pane.getHeight());
			//gc.setEffect(null);
			
			gc.setFill(new Color(.3, .3, .3, 1));
			gc.fillRoundRect(aff.getWidth()/2-250, aff.getHeight()*0.3, 500, 80, 20, 20);
			gc.fillRoundRect(aff.getWidth()/2-250, aff.getHeight()*0.42, 240, 80, 20, 20);
			gc.fillRoundRect(aff.getWidth()/2+10, aff.getHeight()*0.42, 240, 80, 20, 20);
			gc.fillRoundRect(aff.getWidth()/2-250, aff.getHeight()*0.54, 500, 80, 20, 20);
			gc.fillRoundRect(aff.getWidth()/2-250, aff.getHeight()*0.66, 500, 80, 20, 20);

			gc.setFill(Color.YELLOW);
			gc.setFont(new Font("Minecraftia", 80));
			gc.fillText("SpaceY", aff.getWidth()/2-160, aff.getHeight()*0.25);
			gc.setFill(Color.WHITE);
			gc.setFont(new Font("Minecraftia", 26));
			gc.fillText("Reprendre", aff.getWidth()/2-80, aff.getHeight()*0.3+65);
			gc.fillText("Sauvegarder", aff.getWidth()/2-235, aff.getHeight()*0.42+65);
			gc.fillText("Charger", aff.getWidth()/2+65, aff.getHeight()*0.42+65);
			gc.fillText("Paramètres", aff.getWidth()/2-90, aff.getHeight()*0.54+65);
			gc.fillText("Menu Principal", aff.getWidth()/2-110, aff.getHeight()*0.66+65);

			if(lastMousePos.getX() >= 550 && lastMousePos.getX() <= 1050 
					&& lastMousePos.getY() >= 270 && lastMousePos.getY() <= 350) {
				gc.setStroke(Color.WHITE);
			} else {
				gc.setStroke(new Color(0.6, 0.6, 0.6, 1));
			}
			gc.strokeRoundRect(aff.getWidth()/2-245, aff.getHeight()*0.3+5, 490, 70, 20, 20);
			
			if(lastMousePos.getX() >= 550 && lastMousePos.getX() <= 800 
					&& lastMousePos.getY() >= 380 && lastMousePos.getY() <= 460) {
				gc.setStroke(Color.WHITE);
			} else {
				gc.setStroke(new Color(0.6, 0.6, 0.6, 1));
			}
			gc.strokeRoundRect(aff.getWidth()/2-245, aff.getHeight()*0.42+5, 230, 70, 20, 20);
			
			if(lastMousePos.getX() >= 810 && lastMousePos.getX() <= 1050 
					&& lastMousePos.getY() >= 380 && lastMousePos.getY() <= 460) {
				gc.setStroke(Color.WHITE);
			} else {
				gc.setStroke(new Color(0.6, 0.6, 0.6, 1));
			}
			gc.strokeRoundRect(aff.getWidth()/2+15, aff.getHeight()*0.42+5, 230, 70, 20, 20);
			
			if(lastMousePos.getX() >= 550 && lastMousePos.getX() <= 1050 
					&& lastMousePos.getY() >= 490 && lastMousePos.getY() <= 570) {
				gc.setStroke(Color.WHITE);
			} else {
				gc.setStroke(new Color(0.6, 0.6, 0.6, 1));
			}
			gc.strokeRoundRect(aff.getWidth()/2-245, aff.getHeight()*0.54+5, 490, 70, 20, 20);
			
			if(lastMousePos.getX() >= 550 && lastMousePos.getX() <= 1050 
					&& lastMousePos.getY() >= 600 && lastMousePos.getY() <= 680) {
				gc.setStroke(Color.WHITE);
			} else {
				gc.setStroke(new Color(0.6, 0.6, 0.6, 1));
			}
			gc.strokeRoundRect(aff.getWidth()/2-245, aff.getHeight()*0.66+5, 490, 70, 20, 20);
			
		}

		//stage.setTitle("SpaceY  -  DT=" + sc.getModel().getDt() + ", FA=" + sc.getModel().getFa() + ", G=" + SpaceModel.G + ", TIME=" + formatTimeFromSec(sc.getTime()));
	}

	/**
	 * Change le format de l'entier passe en parametre pour qu'il corresponde a
	 * HH:MM:SS.
	 * 
	 * @param seconds Secondes ecoulees dans la simulation.
	 * @return le temps ecoule dans la simulation sous la forme HH:MM:SS.
	 */
	private String formatTimeFromSec(int seconds) {
		int secs = seconds;
		if (secs <= 0)
			return "00:00:00";

		String date = "";

		int[] unitValues = { 604800, 86400, 3600, 60, 1 }; // semaine, jour, heure, minute, seconde

		for (int i = 0; i < unitValues.length; i++) {
			int quot = secs / unitValues[i];
			if (quot > 0) {
				if (i > 1) {
					date += (quot < 10 ? "0" : "") + quot + ":";
				} else if (i == 1) {
					date += quot + "j ";
				} else {
					date += quot + "s ";
				}
				secs -= quot * unitValues[i];
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

		// HUD IMAGE VAISSEAU
		Vaisseau e = sc.getModel().getVaisseau();
		double vaissX = relatX + 55 + 45;
		double vaissY = aff.getHeight() - 165 - relatY + 45;
		Image img = Sprite.getImage(e.getImgId());

		gc.save();
		gc.transform(new Affine(new Rotate(sc.getModel().getVaisseau().getAngle(), vaissX, vaissY)));
		gc.translate(-45, -45);
		gc.drawImage(img, vaissX, vaissY, 90, 90);
		gc.restore();

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

		//fleche gauche
		if(lastMousePos.getX() >= 855 && lastMousePos.getX() <= 890 
				&& lastMousePos.getY() >= 740 && lastMousePos.getY() <= 820) {

			gc.setFill(new Color(0.95, 0.95, 0.95, 1));
			gc.fillPolygon(new double[] { SELECTX + 15, SELECTX + 30, SELECTX + 30 }, 
					new double[] { 780, 805, 755 }, 3);

		} else {

			gc.setFill(new Color(0.6, 0.6, 0.6, 1));
			gc.fillPolygon(new double[] { SELECTX + 15, SELECTX + 30, SELECTX + 30 }, 
					new double[] { 780, 805, 755 }, 3);
		}

		//fleche droite
		if(lastMousePos.getX() >= 1510 && lastMousePos.getX() <= 1545 
				&& lastMousePos.getY() >= 740 && lastMousePos.getY() <= 820) {

			gc.setFill(new Color(0.95, 0.95, 0.95, 1));
			gc.fillPolygon(new double[] { SELECTX + 685, SELECTX + 670, SELECTX + 670 }, 
					new double[] { 780, 805, 755 }, 3);

		} else {

			gc.setFill(new Color(0.6, 0.6, 0.6, 1));
			gc.fillPolygon(new double[] { SELECTX + 685, SELECTX + 670, SELECTX + 670 }, 
					new double[] { 780, 805, 755 }, 3);
		}


		gc.drawImage(Sprite.getImage(e.getImgId()), SELECTX + SELECTWIDTH / 1.5 - 50, SELECTY + SELECTHEIGHT / 2 - 50, TAILLEIMG, TAILLEIMG);

		gc.setFill(Color.LIGHTBLUE);
		gc.setFont(new Font("Minecraftia", 17));
		gc.fillText(e.getName(), TEXTX + 8, SELECTY + 40);
		gc.setFill(Color.LIGHTGRAY);
		gc.setFont(new Font("Minecraftia", 14));
		gc.fillText("Type: " + e.getType().NOM, TEXTX, SELECTY + 55);
		gc.fillText("Masse: " + String.format("%4.2e", e.getMasse()) + " kg", TEXTX, SELECTY + 70);
		gc.fillText("Rayon: " + String.format("%4.2e", e.getRadius()) + " m", TEXTX, SELECTY + 85);
		gc.fillText("Pos: " + e.getPos().toStringScientific(), TEXTX, SELECTY + 100);
		gc.fillText("Vel: " + e.getVel().toStringScientific() + " m/s", TEXTX, SELECTY + 115);
		gc.fillText("Acc: " + e.getAcc().toStringScientific() + " m/s", TEXTX, SELECTY + 130);
	}
 
	/**
	 * Methode permettant de centrer la camera sur l'Entite selectionnee.
	 * 
	 * @param e Entite selectionnee.
	 */
	private void centerCameraOnEntity(Entity e) {
		Affichage aff = sc.getModel().getAffichage();
		//aff.setxOffset((aff.getWidth() / aff.getZoom()) / 2 - e.getPos().getX() / aff.getZoom());
		//aff.setyOffset((aff.getHeight() / aff.getZoom()) / 2 - e.getPos().getY() / aff.getZoom());
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
