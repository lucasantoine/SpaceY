package fr.spacey.controller;

import java.util.Observer;

import fr.spacey.SpaceY;
import fr.spacey.model.SpaceModel;
import javafx.application.Platform;

public class SpaceController {

	private SpaceY instanceY;
	private SpaceModel sm;
	private Thread renderThread;
	
	public SpaceController(SpaceModel space) {
		this.sm = space;
		this.instanceY = SpaceY.getInstance();
	}
	
	public void initRender() {
		this.renderThread = new Thread(new Runnable() {
			@Override
			public void run() {
				
				int fps = 60;
				double timePerTick = 1000 / fps;
				double delta = 0;
				long now = 0;
				long lastTime = System.currentTimeMillis();
				long timer = 0;
				int ticks = 0;
				
				while(true) {
					//System.out.println(now+" "+delta+" "+timer+" "+lastTime+" "+ticks);
					
					now = System.currentTimeMillis();
					delta += (now - lastTime) / timePerTick;
					timer += now - lastTime;
					lastTime = now;
					
					if(delta >= 1) {
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								sm.askForRender();
							}
							
						});
						ticks++;
						delta--;
					}
					 
					if(instanceY.isRunning() && timer >= instanceY.getDt()*1000) {
						Platform.runLater(new Runnable() {

							@Override
							public void run() {
								sm.updatePositions();
							}
							
						});
						ticks = 0;
						timer = 0;
					}
				}
			}
		});
		renderThread.setDaemon(true);
		renderThread.start();

	}

	public void register(Observer view) {
		sm.addObserver(view);
	}

	public SpaceModel getModel() {
		return sm;
	}

}
