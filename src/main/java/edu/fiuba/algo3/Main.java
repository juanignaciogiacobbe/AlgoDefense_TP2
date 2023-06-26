package edu.fiuba.algo3;


import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.convertidor.FormatoJSONInvalidoException;
import edu.fiuba.algo3.vista.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) {
		String cssFile = new File("src/resources/styles.css").toURI().toString();
		String mainCss = new File("src/resources/main.css").toURI().toString();

		Scene scene = new Scene(new StackPane(), 640, 480);
		scene.getStylesheets().add(cssFile);
		scene.getStylesheets().add(mainCss);

		primaryStage.setMaximized(true);
		primaryStage.setScene(scene);
		primaryStage.setTitle("AlgoDefense");



		try {
			BienvenidoVista bienvenidoVista = new BienvenidoVista();
			AlgoDefenseVista algoDefenseVista = new AlgoDefenseVista();
            CreditosVista creditosVista =  new CreditosVista();
			ComoJugarVistaPrincipal comoJugarVistaPrincipal = new ComoJugarVistaPrincipal();
			FinJuegoVista finJuegoVista = new FinJuegoVista();
			bienvenidoVista.setNextVista(algoDefenseVista);
            bienvenidoVista.setCreditos(creditosVista);
			bienvenidoVista.setComoJugarPrincipal(comoJugarVistaPrincipal);
			creditosVista.setNextVista(bienvenidoVista);
			comoJugarVistaPrincipal.setNextVista(bienvenidoVista);
			algoDefenseVista.setNextVista(finJuegoVista);

			AlgoDefense algoDefense = new AlgoDefense();
			algoDefense.addObserver(algoDefenseVista);
			bienvenidoVista.setAlgoDefense(algoDefense);
			algoDefenseVista.setAlgoDefense(algoDefense);
			finJuegoVista.setAlgoDefense(algoDefense);

			bienvenidoVista.mostrar(scene);
			primaryStage.show();
		} catch (Exception | FormatoJSONInvalidoException e) {
			e.printStackTrace();
			Platform.exit();
		}
	}
}
