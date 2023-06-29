package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.PlayAgainControlador;
import edu.fiuba.algo3.modelo.AlgoDefense;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;

public class FinJuegoVista implements Vista {

	private AlgoDefense algoDefense;
	private Vista nextVista;
	private MediaPlayer mediaPlayer;

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

		Button finishButton = new Button("Salir");
		finishButton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
		finishButton.setOnAction(e -> {
			Stage stage = (Stage) scene.getWindow();
			stage.close();
		});

		Button playAgainButton = new Button("Jugar de nuevo");
		PlayAgainControlador playAgainController = new PlayAgainControlador(algoDefense, nextVista, scene, mediaPlayer);
		playAgainButton.setOnAction(playAgainController);
		playAgainButton.setStyle("-fx-text-fill: black; -fx-font-weight: bold; -fx-cursor: hand;");

		StackPane stackPane = new StackPane();
		stackPane.setAlignment(Pos.CENTER);
		stackPane.setStyle("-fx-background-color: black;");
		StackPane.setMargin(playAgainButton, new Insets(120, 0, 0, 0));
		StackPane.setMargin(finishButton, new Insets(280, 0, 0, 0));
		stackPane.getChildren().addAll(playAgainButton,finishButton, celebratoryLabel);

		scene.setRoot(stackPane);
	}

	@Override
	public void playMusic() {
		Media media = new Media(new File("src/resources/gameover.mp3").toURI().toString());
		this.mediaPlayer = new MediaPlayer(media);
		mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaPlayer.play();
	}
	public void stopMusic() {
		mediaPlayer.stop();
		mediaPlayer.dispose();
		mediaPlayer = null;
	}

	public void setAlgoDefense(AlgoDefense algoDefense) {
		this.algoDefense = algoDefense;
	}
}
