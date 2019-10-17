package fr.spacey.controller;

import java.util.Observer;

import fr.spacey.model.SpaceModel;
import fr.spacey.view.CreateEntityView;
import javafx.application.Platform;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SpaceController {

	public static final int FPS = 59;
	public static float dt;
	public static double G;
	public static double fa;

	private SpaceModel sm;
	private Thread renderThread;

	private boolean isRunning = false;
	private int rayon;

	private RenderTimer timer;

	public SpaceController(SpaceModel space) {
		this.sm = space;
		this.timer = new RenderTimer();

		//TODO A SUPPRIMER
		isRunning = true;
		rayon = 500;
		
		SpaceController.dt = 0.001f;
		SpaceController.fa = 1;
		SpaceController.G = 0.01;
	}

	public SpaceModel getModel() {
		return sm;
	}

	public void register(Observer view) {
		sm.addObserver(view);
	}

	public void initRender() {
		//final int UPS = 100; // nb d'update() qui sera réparti par le nombre d'image par seconde
		
		
		this.renderThread = new Thread(new Runnable() {

			float ellapsedTime;
			float accumulator = 0f;
			
			

			@Override
			public void run() {
				timer.init();
				while(true) {
					ellapsedTime = timer.getEllapsedTime();
					accumulator += ellapsedTime;

					int nbUpdate = 0;
					while (accumulator >= dt) {
						
						//System.out.println(accumulator+">="+interval+"; ");
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								sm.updatePositions();
							}

						});
						
						accumulator -= dt;
						nbUpdate++;
					}
					System.out.println("updated "+nbUpdate+" times in 1 frame ("+FPS+" FPS)");

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

	private void sync() {
		float loopSlot = 1f / FPS;
		double endTime = timer.getLastLoopTime() + loopSlot;
		while (timer.getTime() < endTime) {
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
