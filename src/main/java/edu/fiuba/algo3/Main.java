package edu.fiuba.algo3;


import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.convertidor.FormatoJSONInvalidoException;
import edu.fiuba.algo3.vista.AlgoDefenseVista;
import edu.fiuba.algo3.vista.BienvenidoVista;
import edu.fiuba.algo3.vista.FinJuegoVista;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) {
		String cssFile = Objects.requireNonNull(getClass().getClassLoader().getResource("styles.css")).toExternalForm();
		Scene scene = new Scene(new StackPane(), 640, 480);
		scene.getStylesheets().add(cssFile);
		primaryStage.setScene(scene);
		primaryStage.setTitle("AlgoDefense");

		try {
			BienvenidoVista bienvenidoVista = new BienvenidoVista();
			AlgoDefenseVista algoDefenseVista = new AlgoDefenseVista();
			FinJuegoVista finJuegoVista = new FinJuegoVista();
			bienvenidoVista.setNextVista(algoDefenseVista);
			algoDefenseVista.setNextVista(finJuegoVista);

			AlgoDefense algoDefense = new AlgoDefense();
			algoDefense.addObserver(algoDefenseVista);

			bienvenidoVista.mostrar(scene);
			primaryStage.show();
		} catch (Exception | FormatoJSONInvalidoException e) {
			e.printStackTrace();
			Platform.exit();
		}
	}
}
