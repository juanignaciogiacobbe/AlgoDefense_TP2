package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.Observer;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class AlgoDefenseVista implements Observer, Vista {
	private Vista nextVista;
	private Scene scene;

	@Override
	public void update() {
		if (scene != null) {
			mostrar(scene);
		}
	}

	@Override
	public void setNextVista(Vista nextVista) {
		this.nextVista = nextVista;
		if (scene != null) {
			mostrar(scene);
		}
	}

	@Override
	public void mostrar(Scene scene) {
		this.scene = scene;

		Label mapaLabel = new Label("Mapa");

		Button finishButton = new Button("Finish Game");
		finishButton.setOnAction(e -> {
			// Handle logic to finish the game
			// ...

			if (nextVista != null) {
				nextVista.mostrar(scene);
			}
		});

		VBox vbox = new VBox(mapaLabel, finishButton);
		vbox.setAlignment(Pos.CENTER);
		scene.setRoot(vbox);
	}
}
