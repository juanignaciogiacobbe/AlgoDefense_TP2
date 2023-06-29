package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.Observer;
import edu.fiuba.algo3.modelo.convertidor.FormatoJSONInvalidoException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class FinJuegoVista implements Vista {

	private AlgoDefense algoDefense;
	private Vista nextVista;

	@Override
	public void setNext(Vista nextVista) {
		this.nextVista = nextVista;
	}

	@Override
	public void show(Scene scene) {
		Label celebratoryLabel = new Label("El ganador es: " + this.algoDefense.finDelJuego());
		celebratoryLabel.getStyleClass().add("titulo");

		Button finishButton = new Button();
		finishButton.setGraphic(celebratoryLabel);
		finishButton.setStyle("-fx-background-color: black;");

		Button playAgainButton = new Button("Jugar de nuevo");
		playAgainButton.setOnAction(e -> {
			try {
				this.algoDefense.reiniciarJuego();
			} catch (IOException | ParseException | FormatoJSONInvalidoException ex) {
				throw new RuntimeException(ex);
			}
			this.nextVista.playMusic();
			this.nextVista.show(scene);
		});

		StackPane stackPane = new StackPane();
		stackPane.setAlignment(Pos.CENTER);
		stackPane.setStyle("-fx-background-color: black;");
		// Add spacing between the buttons
		StackPane.setMargin(finishButton, new Insets(0, 0, 20, 0));
		StackPane.setMargin(playAgainButton, new Insets(60, 0, 0, 0));
		stackPane.getChildren().addAll(finishButton, playAgainButton);

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
