package fr.spacey.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Observer;

import fr.spacey.model.Affichage;
import fr.spacey.model.SpaceModel;
import fr.spacey.model.entity.Entity;
import fr.spacey.model.entity.EntityType;
import fr.spacey.utils.RenderTimer;
import fr.spacey.utils.ShowState;
import fr.spacey.view.SpaceView;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage; 

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

	private static int spacecontrollernb = 1;
	
	private final int FPS = 60; // nb d'image par seconde de la simulation, inexact car la machine peut mettre
								// plus ou moins de temps pour afficher une frame, ceci est donc une limite
								// approximative
	private final double LOOPSLOT = 1f / FPS;

	private boolean isRunning = false;
	private boolean isStopped = false;
	

	private Thread renderThread;
	private RenderTimer rendertimer;
	private SpaceModel sm;
	private boolean isZPressed;
	
	private Affichage affichage;
	private int selected;
	private HashMap<Entity, ShowState> showstates;

	/**
	 * Constructeur de SpaceController prenant en parametre le modele de la
	 * simulation.
	 * 
	 * @param space Modele de la simulation.
	 */
	public SpaceController(SpaceModel space) {
		this.sm = space;
		this.rendertimer = new RenderTimer();
		this.isRunning = true;
		this.affichage = new Affichage();
		this.selected = -1;
		this.showstates = new HashMap<Entity, ShowState>();
		for (Entity e : space.getEntities()) {
			showstates.put(e, ShowState.NOINFO);
		}
	}

	public Affichage getAffichage() {
		return affichage;
	}

	/**
	 * Initialise le rendu de la simulation.
	 */
	public void initRender() {
		renderThread = new Thread(new Runnable() {

			float ellapsedTime;
			float accu = 0f;

			@Override
			public void run() {
				rendertimer.init();
				while(!isStopped) {
					ellapsedTime = rendertimer.getEllapsedTime();
					if (!isRunning) {
						ellapsedTime = 0;
					}

					accu += ellapsedTime;

					while (isRunning && accu >= sm.getDt() / sm.getFa()) {
						sm.setTime(sm.getTime() + 1);
						if(renderThread.getName().equalsIgnoreCase("SpaceController-RenderThread-1")) {
							Platform.runLater(new Runnable() {
								@Override
								public void run() {
									sm.updatePositions();
								}
							});
						}
						accu -= sm.getDt() / sm.getFa();
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
		}, "SpaceController-RenderThread-" + spacecontrollernb++);
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
	 * Permet de mettre en pause, ou de reprendre la simulation.
	 */
	public void stopRunning() {
		isStopped = true;
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
		Affichage aff = getAffichage();
		//int centerX = (int) (aff.getxOffset() - aff.getWidth() / 2),
		//		centerY = (int) (aff.getyOffset() - aff.getHeight() / 2);
		
		double mouseXTransformed = e.getSceneX() /*/ aff.getZoom()*/;
		double mouseYTransformed = e.getSceneY() /*/ aff.getZoom()*/;
		
		aff.setxOffset(aff.getStartSceneX() + mouseXTransformed - aff.getStartDragX());
		aff.setyOffset(aff.getStartSceneY() + mouseYTransformed - aff.getStartDragY());

		/*double rayon = sm.getRayon();
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
		}*/
	}

	/**
	 * Evenement se declenchant lorsque l'utilisateur deplace sa souris sur
	 * l'univers. Change le mode d'affichage des planetes en consequence.
	 * 
	 * @param e Evenement de souris.
	 */
	public void onMouseMoved(MouseEvent e) {
		Affichage aff = getAffichage();
		double mouseX = e.getSceneX(), mouseY = e.getSceneY();

		double mouseXTransformed = (mouseX) / aff.getZoom();
		double mouseYTransformed = (mouseY) / aff.getZoom();

		for (Entity en : getModel().getEntities()) {
			double entityX = en.getPos().getX() + aff.getxOffset();
			double entityY = en.getPos().getY() + aff.getyOffset();

			double minX = entityX - en.getRadius() / 2, maxX = entityX + en.getRadius() / 2;
			double minY = entityY - en.getRadius() / 2, maxY = entityY + en.getRadius() / 2;

//			if (!en.getInfoMode().equals(ShowState.SHOWINFO) && mouseXTransformed > minX && mouseXTransformed < maxX
//					&& mouseYTransformed > minY && mouseYTransformed < maxY) {
//				en.setInfo(ShowState.HOVERING);
//			} else if (en.getInfoMode().equals(ShowState.HOVERING)) {
//				en.setInfo(ShowState.NOINFO);
//			}
			
			if (!showstates.get(en).equals(ShowState.SHOWINFO) && mouseXTransformed > minX && mouseXTransformed < maxX
					&& mouseYTransformed > minY && mouseYTransformed < maxY) {
				showstates.put(en, ShowState.HOVERING);
			} else if (showstates.get(en).equals(ShowState.HOVERING)) {
				showstates.put(en, ShowState.NOINFO);
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
		Affichage aff = getAffichage();

		double mouseXTransformed = (e.getSceneX()) / aff.getZoom();
		double mouseYTransformed = (e.getSceneY()) / aff.getZoom();
		
		boolean nouvelleSelection = false;
		int idx = 0;
		if(hasEntitySelected()) {
			//fleche gauche
			if(mouseXTransformed >= 855 && mouseXTransformed <= 890 && mouseYTransformed >= 740 && mouseYTransformed <= 820) {
				showstates.put(getEntitySelected(), ShowState.NOINFO);
				setEntitySelected((getEntitySelectedId()-1)<0?getModel().getEntities().size()-1:getEntitySelectedId()-1);
				showstates.put(getEntitySelected(), ShowState.SHOWINFO);
				return;
			}
			//fleche droite
			else if(mouseXTransformed >= 1510 && mouseXTransformed <= 1545 && mouseYTransformed >= 740 && mouseYTransformed <= 820) {
				showstates.put(getEntitySelected(), ShowState.NOINFO);
				setEntitySelected((getEntitySelectedId()+1)%getModel().getEntities().size());
				showstates.put(getEntitySelected(), ShowState.SHOWINFO);
				return;
			}
		}
		
		if(sm.hasVaisseau()) {
			if(mouseXTransformed >= 60 && mouseXTransformed <= 150 && mouseYTransformed >= 730 && mouseYTransformed <= 815) {
				int k = 0;
				for (Entity en : getModel().getEntities()) {
					if(en.getType().equals(EntityType.VAISSEAU)) {
						setEntitySelected(k);
						showstates.put(en, ShowState.SHOWINFO);
						return;
					}
					k++;
				}
			}
		}
		
		for (Entity en : getModel().getEntities()) {
			double entityX = en.getPos().getX() + aff.getxOffset();
			double entityY = en.getPos().getY() + aff.getyOffset();

			double minX = entityX - en.getRadius() / 2, maxX = entityX + en.getRadius() / 2;
			double minY = entityY - en.getRadius() / 2, maxY = entityY + en.getRadius() / 2;

			if(mouseXTransformed > minX && mouseXTransformed < maxX && mouseYTransformed > minY
					&& mouseYTransformed < maxY) {
				if(e.getClickCount() >= 2) {
					System.out.println("DOUBLE CLICK");
					final int focus = idx;
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							System.out.println("RUN !");
							SpaceController newsc = new SpaceController(sm);
							newsc.setEntitySelected(focus);
							SpaceView newsp = new SpaceView(newsc);
							newsp.start(new Stage());
						}
					});
					return;
				}
			}
			
			if (!showstates.get(en).equals(ShowState.SHOWINFO) && mouseXTransformed > minX && mouseXTransformed < maxX && mouseYTransformed > minY
					&& mouseYTransformed < maxY) {
				if(e.getClickCount() < 2) {
					setEntitySelected(idx);
					showstates.put(en, ShowState.SHOWINFO);
					e.setDragDetect(false);
					nouvelleSelection = true;
				}
			} else {
				showstates.put(en, ShowState.NOINFO);
			}
			idx++;
		}
		if (!nouvelleSelection) {
			setEntitySelected(-1);
		}
	}

	/**
	 * Renvoie le temps de la simulation.
	 * 
	 * @return le temps de la simulation.
	 */
	public int getTime() {
		return sm.getTime();
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
		Affichage aff = getAffichage();
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

	public void onKeyReleased(KeyEvent e) {
		if(isRunning && getModel().hasVaisseau()) {
			if(e.getCode().equals(KeyCode.Z)) {
				this.isZPressed = false;
			}
			if (e.getCode().equals(KeyCode.Z)) {
				sm.getVaisseau().downThrottle();
			}
		}
	}

	/**
	 * Evenement permettant de controler le vaisseau de la simulation (s'il y en a
	 * un) en fonction de la touche du clavier pressee.
	 * 
	 * @param e Touche du clavier pressee.
	 */
	public void onKeyPressed(KeyEvent e) {
		if(e.getCode().equals(KeyCode.SPACE)) {
			toggleRunning();
		}
		if (isRunning) {
			if (e.getCode().equals(KeyCode.ADD)) {
				getModel().setFa(Math.min(getModel().getFa() + 1, 500));
			} else if (e.getCode().equals(KeyCode.SUBTRACT)) {
				getModel().setFa(Math.max(getModel().getFa() - 1, 1));
			} else if (e.getCode().equals(KeyCode.MULTIPLY)) {
				getModel().setFa(Math.min(getModel().getFa() + 10, 500));
			} else if (e.getCode().equals(KeyCode.DIVIDE)) {
				getModel().setFa(Math.max(getModel().getFa() - 10, 1));
			}
			if (sm.hasVaisseau()) {
			if (e.getCode().equals(KeyCode.Z)) {
				sm.getVaisseau().upThrottle();
			}

			if (e.getCode().equals(KeyCode.S)) {
				sm.getVaisseau().downThrottle();
			}

			if (e.getCode().equals(KeyCode.Q)) {
				if (e.isAltDown()) {
					sm.getVaisseau().incAngle(-45.0);
				} else {
					sm.getVaisseau().incAngle(-1.0);
				}
			}

			if (e.getCode().equals(KeyCode.D)) {
				if (e.isAltDown()) {
					sm.getVaisseau().incAngle(45.0);
				} else {
					sm.getVaisseau().incAngle(1.0);
				}
			}
			}
		}
	}

	public void saveSpace(File to) {
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		try(PrintWriter bf = new PrintWriter(to)) {

			bf.println("# "+formatter.format(LocalDateTime.now()));
			
			bf.println("PARAMS G="+SpaceModel.G+" dt="+getModel().getDt()+" fa="+getModel().getFa()+" rayon="+getModel().getRayon());
			
			for(Entity e : getModel().getEntities()) {
				
				StringBuilder sb = new StringBuilder();
				sb.append(e.getName()).append(": ").append(e.getType().NOM).append(" masse=").append(e.getMasse())
				.append(" posx=").append(e.getPos().getX()).append(" posy=").append(e.getPos().getY());
				
				if(!e.getType().equals(EntityType.FIXE)) {
					sb.append(" vitx=").append(e.getVel().getX()).append(" vity=").append(e.getVel().getY());
				}
				
				bf.println(sb.toString());
				
			}
			
			bf.flush();
			
		} catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	/**
	 * Renvoie vrai si une entite est selectionnee, faux sinon.
	 * 
	 * @return vrai si une entite est selectionnee, faux sinon.
	 */
	public boolean hasEntitySelected() {
		return selected > -1;
	}
	
	/**
	 * Renvoie le num�ro de l'entite selectionnee.
	 * 
	 * @return le num�ro de l'entite selectionnee.
	 */
	public int getEntitySelectedId() {
		return selected;
	}

	/**
	 * Renvoie l'entite selectionnee, null sinon.
	 * 
	 * @return l'entite selectionnee, null sinon.
	 */
	public Entity getEntitySelected() {
		if (hasEntitySelected()) {
			return getModel().getEntities().get(selected);
		}
		return null;
	}
	
	/**
	 * Change l'entite selectionnee en celle positionnee en idx dans la List
	 * entities.
	 * 
	 * @param idx designe l'index de l'entite dans la liste entities.
	 */
	public void setEntitySelected(int idx) {
		if (idx >= 0 && idx < getModel().getEntities().size()) {
			this.selected = idx;
		} else {
			this.selected = -1;
		}
	}
	
	public ShowState getShowStateOf(Entity e) {
		return showstates.get(e);
	}

}
