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
	private TextField nameField;

	@Override
	public void setNextVista(Vista nextVista) {
		this.nextVista = nextVista;
	}

	private void validateAndHandleInput(Scene scene) {
		String playerName = nameField.getText();
		if (!validateInput(playerName)) {
			nameField.setStyle("-fx-border-color: red");
			return;
		}
			System.out.println("que onda, " + playerName + "!");
			if (nextVista != null) {
				nextVista.mostrar(scene);
		}
	}

	private boolean validateInput(String input) {
		return input.length() >= 6;
	}

	@Override
	public void mostrar(Scene scene) {
		Label nameLabel = new Label("Nombre del jugador:");
		nameField = new TextField();
		Button loginButton = new Button("Jugar ▶️");

		VBox vbox = new VBox(nameLabel, nameField, loginButton);
		vbox.setAlignment(Pos.CENTER);
		vbox.setSpacing(10);
		vbox.setPadding(new Insets(20));

		loginButton.setOnAction(e -> validateAndHandleInput(scene));
		scene.setRoot(vbox);
	}


}
