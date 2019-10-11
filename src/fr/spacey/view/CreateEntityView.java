package fr.spacey.view;

import fr.spacey.SpaceY;
import fr.spacey.controller.CreateEntityController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CreateEntityView extends Application {

	CreateEntityController controller;

	double xOffset;
	double yOffset;

	MouseEvent e;

	public CreateEntityView(CreateEntityController controller, double xOffset, double yOffset, MouseEvent e) {
		this.controller = controller;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.e = e;
	}

	@Override
	public void start(Stage stage) {
		if (SpaceY.getInstance().isRunning()) {
			SpaceY.getInstance().toggleRunning();
		}

		Stage modale = new Stage();
		modale.initOwner(stage);
		modale.initModality(Modality.WINDOW_MODAL);
		modale.setAlwaysOnTop(true);
		
		
		Label name = new Label("Nom : ");
		TextField tfName = new TextField();
		
		HBox hName = new HBox(name, tfName);
		
		
		VBox left = new VBox(hName);
		
		
		
		
		
		HBox hMain = new HBox(left);
		hMain.setPrefSize(1500, 900);
		hMain.setStyle("-fx-border-insets: 7.5;"+"-fx-border-width: 1;" + "-fx-background-color: rgb(76,76,76);" + "-fx-border-color: rgb(153,153,153)");
		
		
		Pane main = new Pane(hMain);

		modale.setOnCloseRequest(e -> {
			if (!SpaceY.getInstance().isRunning()) {
				SpaceY.getInstance().toggleRunning();
			}
		});
		
		Scene scene = new Scene(main);
		
		modale.setScene(scene);
		modale.show();
	}

}
