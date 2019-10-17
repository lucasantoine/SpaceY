package fr.spacey.view;

import javafx.scene.image.Image;

public enum Sprite {

	TERRE("file:res/images/Terre.png"),
	LUNE("file:res/images/Lune.png"),
	MARS("file:res/images/Mars.png"),
	MERCURE ("file:res/images/Mercure.png"),
	SOLEIL("file:res/images/Soleil.png"),
	VENUS("file:res/images/Venus.png"),
	JUPITER("file:res/images/Jupiter.png");
	
	private Image img;
	
	private Sprite(String url) {
		this.img = new Image(url);
	}
	
	public static Image getImage(int id) {
		if(id < 0 || id >= values().length)
			throw new Error("L'image "+id+" n'existe pas.");
		return values()[id].img;
	}
}
