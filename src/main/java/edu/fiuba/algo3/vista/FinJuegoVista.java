package edu.fiuba.algo3.vista;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FinJuegoVista implements Vista {
	@Override
	public void setNextVista(Vista nextVista) {
	}

	@Override
	public void mostrar(Scene scene) {
		Label celebratoryLabel = new Label("clickme  ;)");
		celebratoryLabel.setStyle("-fx-font-size: 60px;");

		Button finishButton = new Button();
		finishButton.setGraphic(celebratoryLabel);
		finishButton.setStyle("-fx-background-color: transparent;");

		StackPane stackPane = new StackPane(finishButton);
		stackPane.setAlignment(Pos.CENTER);

		scene.setRoot(stackPane);

		finishButton.setOnAction(e -> {
			Stage stage = (Stage) scene.getWindow();
			stage.close();
		});
	}
}
