package fr.spacey.controller;

import java.util.Observer;

import fr.spacey.model.Affichage;
import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Entity;
import fr.spacey.utils.ShowState;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent; 

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 30 oct. 2019
 * 
 *        Controleur de la simulation. Permet de gerer les evenements lies a la
 *        simulation, de modifier le modele en fonction de ces derniers, et
 *        d'abonner la vue de la simulation a son modele.
 */
public class SpaceController {

	private final int FPS = 60; // nb d'image par seconde de la simulation, inexact car la machine peut mettre
								// plus ou moins de temps pour afficher une frame, ceci est donc une limite
								// approximative
	private final double LOOPSLOT = 1f / FPS;

	private boolean isRunning = false;
	private int time;

	private SpaceModel sm;

	/* VARIABLES DE CANVAS */
	private RenderTimer rendertimer;

	/**
	 * Constructeur de SpaceController prenant en parametre le modele de la
	 * simulation.
	 * 
	 * @param space Modele de la simulation.
	 */
	public SpaceController(SpaceModel space) {
		this.sm = space;
		this.time = 0;
		this.rendertimer = new RenderTimer();
		this.isRunning = true;
	}

	/**
	 * Initialise le rendu de la simulation.
	 */
	public void initRender() {
		Thread renderThread = new Thread(new Runnable() {

			float ellapsedTime;
			float accu = 0f;

			@Override
			public void run() {
				rendertimer.init();
				while (true) {
					ellapsedTime = rendertimer.getEllapsedTime();
					if (!isRunning) {
						ellapsedTime = 0;
					}

					accu += ellapsedTime;

					while (isRunning && accu >= sm.getDt() / sm.getFa()) {
						time++;
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								sm.updatePositions();
							}
						});

						accu -= (sm.getDt() / sm.getFa());
					}

					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							sm.render();
						}
					});

					sync();
				}

			}
		}, "SpaceController-RenderThread");
		renderThread.setDaemon(true);
		renderThread.start();
	}

	/**
	 * Methode permettant d'attendre le temps necessaire pour atteindre le nombre
	 * d'images par seconde desire.
	 */
	private void sync() {
		double endTime = rendertimer.getLastLoopTime() + LOOPSLOT;
		while (rendertimer.getTime() < endTime) {
			try {
				Thread.sleep(2);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	/**
	 * Permet de mettre en pause, ou de reprendre la simulation.
	 */
	public void toggleRunning() {
		this.isRunning = !this.isRunning;
	}

	/**
	 * Renvoie vrai si la simulation est en cours, renvoie faux sinon.
	 * 
	 * @return vrai si la simulation est en cours, renvoie faux sinon.
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * Evenement se declenchant lorsque l'utilisateur laisse sa souris appuye sur
	 * l'univers. Deplace la camera en fonction de la direction de la trainee.
	 * 
	 * @param e Evenement de souris.
	 */
	public void onMouseDrag(MouseEvent e) {
		Affichage aff = getModel().getAffichage();
		int centerX = (int) (aff.getxOffset() - aff.getWidth() / 2),
				centerY = (int) (aff.getyOffset() - aff.getHeight() / 2);
		// System.out.println("center " + centerY + " " + yOffset);
		aff.setxOffset(aff.getStartSceneX() + e.getSceneX() - aff.getStartDragX());
		aff.setyOffset(aff.getStartSceneY() + e.getSceneY() - aff.getStartDragY());

		double rayon = sm.getRayon();
		if (centerX > rayon) {
			aff.setxOffset(aff.getWidth() / 2 + rayon);
			aff.setStartDragX(e.getSceneX());
			aff.setStartSceneX(aff.getxOffset());
		} else if (centerX < -rayon) {
			aff.setxOffset(aff.getWidth() / 2 - rayon);
			aff.setStartDragX(e.getSceneX());
			aff.setStartSceneX(aff.getxOffset());
		}

		if (centerY > rayon) {
			aff.setyOffset(aff.getHeight() / 2 + rayon);
			aff.setStartDragY(e.getSceneY());
			aff.setStartSceneY(aff.getyOffset());
		} else if (centerY < -rayon) {
			aff.setyOffset(aff.getHeight() / 2 - rayon);
			aff.setStartDragY(e.getSceneY());
			aff.setStartSceneY(aff.getyOffset());
		}
	}

	/**
	 * Evenement se declenchant lorsque l'utilisateur deplace sa souris sur
	 * l'univers. Change le mode d'affichage des planetes en consequence.
	 * 
	 * @param e Evenement de souris.
	 */
	public void onMouseMoved(MouseEvent e) {
		Affichage aff = getModel().getAffichage();
		double mouseX = e.getSceneX(), mouseY = e.getSceneY();

		double mouseXTransformed = (mouseX) / aff.getZoom();
		double mouseYTransformed = (mouseY) / aff.getZoom();

		for (Entity en : getModel().getEntities()) {
			double entityX = en.getPos().getX() + aff.getxOffset();
			double entityY = en.getPos().getY() + aff.getyOffset();

			double minX = entityX - en.getRadius() / 2, maxX = entityX + en.getRadius() / 2;
			double minY = entityY - en.getRadius() / 2, maxY = entityY + en.getRadius() / 2;

			if (!en.getInfoMode().equals(ShowState.SHOWINFO) && mouseXTransformed > minX && mouseXTransformed < maxX
					&& mouseYTransformed > minY && mouseYTransformed < maxY) {
				en.setInfo(ShowState.HOVERING);
			} else if (en.getInfoMode().equals(ShowState.HOVERING)) {
				en.setInfo(ShowState.NOINFO);
			}
		}
	}

	/**
	 * Evenement se declenchant lorsque l'utilisateur relache le clic de sa souris.
	 * Change le mode d'affichage des planetes en consequence.
	 * 
	 * @param e Evenement de souris.
	 */
	public void onMouseReleased(MouseEvent e) {
		Affichage aff = getModel().getAffichage();
		double mouseX = e.getSceneX(), mouseY = e.getSceneY();
		boolean nouvelleSelection = false;
		int idx = 0;
		
		if(getModel().hasEntitySelected()) {
			//fleche gauche
			if(e.getX() >= 855 && e.getX() <= 890 && e.getY() >= 740 && e.getY() <= 820) {
				getModel().getEntitySelected().setInfo(ShowState.NOINFO);
				getModel().setEntitySelected((getModel().getEntitySelectedId()-1)<0?getModel().getEntities().size()-1:getModel().getEntitySelectedId()-1);
				getModel().getEntitySelected().setInfo(ShowState.SHOWINFO);
				return;
			}
			//fleche droite
			else if(e.getX() >= 1510 && e.getX() <= 1545 && e.getY() >= 740 && e.getY() <= 820) {
				getModel().getEntitySelected().setInfo(ShowState.NOINFO);
				getModel().setEntitySelected((getModel().getEntitySelectedId()+1)%getModel().getEntities().size());
				getModel().getEntitySelected().setInfo(ShowState.SHOWINFO);
				return;
			}
		}
		
		
		for (Entity en : getModel().getEntities()) {
			double entityX = en.getPos().getX() + aff.getxOffset();
			double entityY = en.getPos().getY() + aff.getyOffset();

			double minX = entityX - en.getRadius() / 2, maxX = entityX + en.getRadius() / 2;
			double minY = entityY - en.getRadius() / 2, maxY = entityY + en.getRadius() / 2;

			if (!en.getInfoMode().equals(ShowState.SHOWINFO) && mouseX > minX && mouseX < maxX && mouseY > minY
					&& mouseY < maxY) {
				getModel().setEntitySelected(idx);
				en.setInfo(ShowState.SHOWINFO);
				// centerCameraOnEntity(en);
				e.setDragDetect(false);
				nouvelleSelection = true;
			} else {
				en.setInfo(ShowState.NOINFO);
			}
			idx++;
		}
		if (!nouvelleSelection) {
			getModel().setEntitySelected(-1);
		}
	}

	/**
	 * Renvoie le temps de la simulation.
	 * 
	 * @return le temps de la simulation.
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Renvoie le temps du rendu de la simulation.
	 * 
	 * @return le temps du rendu de la simulation.
	 */
	public RenderTimer getRendertimer() {
		return rendertimer;
	}

	/**
	 * Evenement se declenchant lorsque l'utilisateur presse le clic de sa souris.
	 * Change le mode d'affichage des planetes en consequence. Change les variables
	 * de debut de Scene et de debut de trainee en consequence.
	 * 
	 * @param e Evenement de souris.
	 */
	public void onMousePressed(MouseEvent e) {
		Affichage aff = getModel().getAffichage();
		aff.setStartDragX(e.getSceneX());
		aff.setStartDragY(e.getSceneY());
		aff.setStartSceneX(aff.getxOffset());
		aff.setStartSceneY(aff.getyOffset());
	}

	/**
	 * Abonne la vue passee en parametre au SpaceModel.
	 * 
	 * @param view Nouvel abonne du SpaceModel.
	 */
	public void register(Observer view) {
		sm.addObserver(view);
	}

	/**
	 * Renvoie le modele de la simulation.
	 * 
	 * @return le modele de la simulation.
	 */
	public SpaceModel getModel() {
		return sm;
	}

	/**
	 * Evenement permettant de controler le vaisseau de la simulation (s'il y en a
	 * un) en fonction de la touche du clavier pressee.
	 * 
	 * @param e Touche du clavier pressee.
	 */
	public void onKeyPressed(KeyEvent e) {
		if (isRunning) {
			if (e.getCode().equals(KeyCode.ADD) && getModel().getFa() < 200) {
				getModel().setFa(getModel().getFa() + 1);
			} else if (e.getCode().equals(KeyCode.SUBTRACT) && getModel().getFa() > 1) {
				getModel().setFa(getModel().getFa() - 1);
			}

			/*if (sm.hasVaisseau()) {
				switch (e.getCode()) {
				case UP:
					sm.getVaisseau().up();
					break;
				case DOWN:
					sm.getVaisseau().down();
					break;
				default:
					break;
				}
			}*/
		}
	}

}
