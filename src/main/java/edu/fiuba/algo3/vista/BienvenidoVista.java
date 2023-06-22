package edu.fiuba.algo3.vista;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
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

		// Create a StackPane to hold the logo image and input field
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(
				createLogoImageView(), // Add logo image view
				vbox
		);
		StackPane.setMargin(vbox, new Insets(250, 0, 0, 0)); // Adjust margin to position the input field

		loginButton.setOnAction(e -> validateAndHandleInput(scene));
		scene.setRoot(stackPane);
	}

	private ImageView createLogoImageView() {
		String imagePath = "file:src/resources/logo.png";
		Image image = new Image(imagePath);
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(500);
		return imageView;
	}
}
