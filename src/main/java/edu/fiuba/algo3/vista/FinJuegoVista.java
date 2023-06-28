package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.AlgoDefense;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FinJuegoVista implements Vista {

	private AlgoDefense algoDefense;
	@Override
	public void setNext(Vista nextVista) {
	}

	@Override
	public void show(Scene scene) {
		Label celebratoryLabel = new Label("El ganador es: " + this.algoDefense.finDelJuego());
		celebratoryLabel.getStyleClass().add("titulo");

		Button finishButton = new Button();
		finishButton.setGraphic(celebratoryLabel);
		finishButton.setStyle("-fx-background-color: black;");

		StackPane stackPane = new StackPane(finishButton);
		stackPane.setAlignment(Pos.CENTER);
		stackPane.setStyle("-fx-background-color: black;");

		scene.setRoot(stackPane);

		finishButton.setOnAction(e -> {
			Stage stage = (Stage) scene.getWindow();
			stage.close();
		});
	}


	@Override
	public void playMusic() {

	}

	public void setAlgoDefense(AlgoDefense algoDefense) {
		this.algoDefense = algoDefense;
	}
}
