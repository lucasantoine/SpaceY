package fr.spacey.controller;

import java.io.File;
import java.util.Observer;

import fr.spacey.SpaceY;
import fr.spacey.model.menu.MainMenuModel;
import javafx.application.Platform;
import javafx.stage.Stage;

/**
 * SpaceY - IUT A de Lille - 3e Semestre
 * 
 * @author ANTOINE Lucas, BARBIER Benoit, POMIER Mathys, RYCKEBUSH Corentin
 * @since 26 oct. 2019
 * 
 *        Controleur du menu principal. Permet de gerer les evenements lies au
 *        menu principal et d'abonner la vue du menu a son modele.
 */
public class MainMenuController {

	private SpaceY instanceY;
	private MainMenuModel mmm;
	private Thread renderThread;
	private int wait;
	boolean isRunning;

	/**
	 * Constructeur de MainMenuController prenant le modele du Menu en parametre.
	 * 
	 * @param mmm Modele du Menu.
	 */
	public MainMenuController(MainMenuModel mmm) {
		this.mmm = mmm;
		this.instanceY = SpaceY.getInstance();
	}

	/**
	 * Initialise le rendu.
	 */
	public void initRender() {
		this.isRunning = true;
		this.renderThread = new Thread(new Runnable() {
			@Override
			public void run() {

				int fps = 100;
				double timePerTick = 1000 / fps;
				double delta = 0;
				long now = 0;
				long lastTime = System.currentTimeMillis();
				long timer = 0;
				// int ticks = 0;

				while (isRunning) {
					now = System.currentTimeMillis();
					delta += (now - lastTime) / timePerTick;
					timer += now - lastTime;
					lastTime = now;

					if (delta >= 1) {
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								mmm.askForRender();
							}

						});
						// ticks++;
						delta--;
					}
					if (isRunning && timer >= 10) {
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								mmm.updatePositions();
								if (mmm.isStart() && isRunning) {
									if (wait >= 100) {
										try {
											instanceY.startSimulation(mmm.getFilepath(), mmm.getStage());
											isRunning = false;
										} catch (Exception e) {
											mmm.toggleStart();
											mmm.setErrorMessage(e.getMessage());
											wait = 0;
										}
									} else {
										wait++;
									}
								}
							}

						});
						// ticks = 0;
						timer = 0;
					}
				}
			}
		});
		renderThread.setDaemon(true);
		renderThread.start();

	}

	/**
	 * Permet de demarrer le menu principal.
	 * 
	 * @param stage Scene de la simulation.
	 */
	public void start(Stage stage) {
		mmm.toggleStart();
		mmm.setStage(stage);
	}

	/**
	 * Abonne la vue passee en parametre au modele du Menu.
	 * 
	 * @param view Nouvel abonne au modele du Menu.
	 */
	public void register(Observer view) {
		mmm.addObserver(view);
	}

	/**
	 * Renvoie le modele du Menu.
	 * 
	 * @return le modele du Menu.
	 */
	public MainMenuModel getModel() {
		return mmm;
	}

	/**
	 * Methode permettant de quitter l'application.
	 * 
	 * @param stage Scene de l'application.
	 */
	public void leaveApplication(Stage stage) {
		stage.close();
	}

	/**
	 * Modifie le fichier de configuration de la simulation.
	 * 
	 * @param file Fichier de configuration de la simulation.
	 */
	public void chooseFile(File file) {
		mmm.setFilepath(file.getAbsolutePath());
	}

}
