package edu.fiuba.algo3.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class BienvenidoVista implements Vista {

	private Vista nextVista;

	@Override
	public void setNextVista(Vista nextVista) {
		this.nextVista = nextVista;
	}

	@Override
	public void mostrar(Scene scene) {
		var label = new Label("Hola que hace");
		var button = new Button("Next");

		VBox.setMargin(button, new Insets(20, 0, 0, 0)); // Add top margin to move the button down

		button.setOnAction(e -> {
			if (nextVista != null) {
				nextVista.mostrar(scene);
			}
		});

		var vbox = new VBox(label, button);
		vbox.setAlignment(Pos.CENTER); // Align the VBox to the center
		scene.setRoot(vbox);
	}
}
