package edu.fiuba.algo3.vista;

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
			nameField.getStyleClass().add("input-invalido");
			return;
		}
			System.out.println(playerName + ", bienvenido a AlgoDefense");
			if (nextVista != null) {
				nextVista.mostrar(scene);
		}
	}

	private boolean validateInput(String input) {
		return input.length() >= 6;
	}

	@Override
	public void mostrar(Scene scene) {
		Label nameLabel = new Label("Ingresa tu nombre:");
		nameField = new TextField();
		nameField.getStyleClass().add("input");
		Button loginButton = new Button("Comenzar juego");
		loginButton.getStyleClass().add("boton");

		VBox vbox = new VBox(nameLabel, nameField, loginButton);
		vbox.getStyleClass().add("container");
		loginButton.setOnAction(e -> validateAndHandleInput(scene));
		scene.setRoot(vbox);
	}
}
