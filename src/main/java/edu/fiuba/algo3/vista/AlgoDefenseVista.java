package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Observer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlgoDefenseVista implements Observer, Vista {
	private Vista nextVista;
	private Stage ventana;

	@Override
	public void update() {
		if (ventana != null) {
			mostrar(ventana);
		}
	}

	@Override
	public void setNextVista(Vista nextVista) {
		this.nextVista = nextVista;
		if (ventana != null) {
			mostrar(ventana);
		}
	}

	@Override
	public void mostrar(Stage stage) {
		this.ventana = stage;

		Label mapaLabel = new Label("Mapa");

		Button finishButton = new Button("Finish Game");
		finishButton.setOnAction(e -> {
			// Handle logic to finish the game
			// ...

			if (nextVista != null) {
				nextVista.mostrar(stage);
			}
		});

		VBox vbox = new VBox(mapaLabel, finishButton);
		vbox.setAlignment(Pos.CENTER);
		var scene = new Scene(vbox, 640, 480);
		stage.setScene(scene);
		stage.show();
	}
}
