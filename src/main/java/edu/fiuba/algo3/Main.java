package edu.fiuba.algo3;


import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.convertidor.FormatoJSONInvalidoException;
import edu.fiuba.algo3.vista.AlgoDefenseVista;
import edu.fiuba.algo3.vista.BienvenidoVista;
import edu.fiuba.algo3.vista.FinJuegoVista;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch();
	}

	@Override
	public void start(Stage stage) {
		try {
			BienvenidoVista bienvenidoVista = new BienvenidoVista();
			AlgoDefenseVista algoDefenseVista = new AlgoDefenseVista();
			FinJuegoVista finJuegoVista = new FinJuegoVista();
			bienvenidoVista.setNextVista(algoDefenseVista);
			algoDefenseVista.setNextVista(finJuegoVista);

			AlgoDefense algoDefense = new AlgoDefense();
			algoDefense.addObserver(algoDefenseVista);

			bienvenidoVista.mostrar(stage);
		} catch (Exception | FormatoJSONInvalidoException e) {
			e.printStackTrace();
		}
	}
}
