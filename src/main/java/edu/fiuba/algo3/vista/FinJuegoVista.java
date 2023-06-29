package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.PlayAgainControlador;
import edu.fiuba.algo3.modelo.AlgoDefense;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class FinJuegoVista implements Vista {

	private AlgoDefense algoDefense;
	private Vista nextVista;

	@Override
	public void setNext(Vista nextVista) {
		this.nextVista = nextVista;
	}

	@Override
	public void show(Scene scene) {
		String ganador = this.algoDefense.finDelJuego();

		Label celebratoryLabel = new Label("El ganador es: " + ganador);
		celebratoryLabel.getStyleClass().add("titulo");
		celebratoryLabel.setFont(Font.font("Arial", FontWeight.BOLD, 36));
		celebratoryLabel.setTextFill(Color.WHITE);

		Button finishButton = new Button("Finish Game");
		finishButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold;");
		finishButton.setOnAction(e -> {
			Stage stage = (Stage) scene.getWindow();
			stage.close();
		});

		Button playAgainButton = new Button("Jugar de nuevo");
		PlayAgainControlador playAgainController = new PlayAgainControlador(algoDefense, nextVista, scene);
		playAgainButton.setOnAction(playAgainController);


		StackPane stackPane = new StackPane();
		stackPane.setAlignment(Pos.CENTER);
		stackPane.setStyle("-fx-background-color: black;");
		StackPane.setMargin(finishButton, new Insets(0, 0, 90, 0));
		StackPane.setMargin(playAgainButton, new Insets(220, 0, 0, 0));
		stackPane.getChildren().addAll(finishButton, playAgainButton, celebratoryLabel);

		scene.setRoot(stackPane);
	}

	@Override
	public void playMusic() {

	}

	public void setAlgoDefense(AlgoDefense algoDefense) {
		this.algoDefense = algoDefense;
	}
}
