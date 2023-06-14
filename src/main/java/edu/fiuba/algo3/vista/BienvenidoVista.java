package edu.fiuba.algo3.vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class BienvenidoVista implements Vista {
	private Vista nextVista;

	@Override
	public void setNextVista(Vista nextVista) {
		this.nextVista = nextVista;
	}

	@Override
	public void mostrar(Scene scene) {
		Label nameLabel = new Label("Nombre del jugador:");
		TextField nameField = new TextField();
		Button loginButton = new Button("Jugar ▶️");

		VBox vbox = new VBox(nameLabel, nameField, loginButton);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(20));

		loginButton.setOnAction(e -> {
			String playerName = nameField.getText();
			System.out.println("Que onda perro, " + playerName + "!");
			if (nextVista != null) {
				nextVista.mostrar(scene);
			}
		});

		scene.setRoot(vbox);
	}
}
