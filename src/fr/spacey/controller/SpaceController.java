package fr.spacey.controller;

import java.util.Observer;

import fr.spacey.model.SpaceModel;
import fr.spacey.view.CreateEntityView;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SpaceController {

	private static final int FPS = 30; // nb d'image par seconde de la simulation, inexact car la machine peut mettre plus ou moins de temps pour afficher une frame, ceci est donc une limite approximative
	private static final double LOOPSLOT = 1f / FPS;
	public static float dt; // nb d'update() qui sera réparti par le nombre d'image par seconde
	public static double G; // constante gravitationelle
	public static double fa; // facteur multiplicatif pour le nombre d'update()

	private SpaceModel sm;
	private Thread renderThread;

	private boolean isRunning = false;
	private int rayon;
	private long time;

	private RenderTimer rendertimer;

	public SpaceController(SpaceModel space) {
		this.sm = space;
		this.rendertimer = new RenderTimer();

		//TODO A SUPPRIMER
		isRunning = true;
		rayon = 500;
		time = 0;
		SpaceController.dt = 0.005f;
		SpaceController.fa = 10;
		SpaceController.G = 0.005;
	}

	public SpaceModel getModel() {
		return sm;
	}

	public void register(Observer view) {
		sm.addObserver(view);
	}

	public void initRender() {
		this.renderThread = new Thread(new Runnable() {
			
			int timercounter = 0;
			float ellapsedTime;
			float accumulator = 0f;
			
			@Override
			public void run() {
				rendertimer.init();
				while(true) {
					ellapsedTime = rendertimer.getEllapsedTime();
					accumulator += ellapsedTime;

					//int nbUpdate = 0;
					while (isRunning && accumulator >= dt) {
						
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								sm.updatePositions();
							}
						});
						
						accumulator -= (dt/fa);
						//nbUpdate++;
					}
					//System.out.println("updated "+nbUpdate+" times in 1 frame ("+FPS+" FPS, "+dt+" DT, "+fa+" FA)");

					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							sm.render();
						}
					});

					sync();

					if(timercounter++ % (FPS / fa) < 1) {
						time++;
					}
				}

			}
		}, "SpaceController-RenderThread");
		renderThread.setDaemon(true);
		renderThread.start();
	}

	private void sync() {
		double endTime = rendertimer.getLastLoopTime() + LOOPSLOT;
		while (rendertimer.getTime() < endTime) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	public void onMouseClicked(MouseEvent e, Stage stage, double xOffset, double yOffset) {
		if(e.isControlDown()) {
			CreateEntityController controller = new CreateEntityController(sm);
			CreateEntityView cev = new CreateEntityView(controller, xOffset, yOffset, e);
			cev.start(stage);

		}
	}

	/**
	 * permet d'activer ou non l'état de pause de la simulation
	 * 
	 * @author Benoit
	 */
	public void toggleRunning() {
		this.isRunning = !this.isRunning;
	}

	/**
	 * permet de savoir si la simulation est en pause ou non
	 * 
	 * @author Benoit
	 */
	public boolean isRunning() {
		return isRunning;
	}

	/**
	 * setter de la taille d'univers
	 * 
	 * @author Benoit
	 */
	public void setRayon(int rayon) {
		this.rayon = rayon;
	}


	/**
	 * getter de la taille de l'univers
	 * 
	 * @author Benoit
	 */
	public int getRayon() {
		return rayon;
	}



}
