package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.convertidor.FormatoJSONInvalidoException;
import edu.fiuba.algo3.modelo.juego.NombreInvalido;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class BienvenidoVista implements Vista {
	private Vista nextVista;
	private TextField nameField;
	private AlgoDefense juego;

	@Override
	public void setNextVista(Vista nextVista) {
		this.nextVista = nextVista;
	}

	private void validateAndHandleInput(Scene scene) throws FormatoJSONInvalidoException, IOException, ParseException, NombreInvalido {
		String playerName = nameField.getText();
		if (!validateInput(playerName)) {
			nameField.getStyleClass().add("input-invalido");
			return;
		}
		this.juego.agregarJugador(playerName);
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
		nameLabel.getStyleClass().add("label");
		nameField = new TextField();
		nameField.getStyleClass().add("input");
		Button loginButton = new Button("Comenzar juego");
		loginButton.getStyleClass().add("boton");

		VBox vbox = new VBox(createLogoImageView(), nameLabel, nameField, loginButton);
		vbox.getStyleClass().add("container");

		// Create a StackPane to hold the logo image and input field
		StackPane stackPane = new StackPane();
		stackPane.getChildren().addAll(vbox);

		loginButton.setOnAction(e -> {
			try {
				validateAndHandleInput(scene);
			} catch (FormatoJSONInvalidoException ex) {
				throw new RuntimeException(ex);
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			} catch (ParseException ex) {
				throw new RuntimeException(ex);
			} catch (NombreInvalido ex) {
				throw new RuntimeException(ex);
			}
		});
		scene.setRoot(stackPane);
	}

	private ImageView createLogoImageView() {
		String imagePath = "file:src/resources/logo.png";
		Image image = new Image(imagePath);
		ImageView imageView = new ImageView(image);
		imageView.setPreserveRatio(true);
		imageView.setFitWidth(1000);
		return imageView;
	}

	public void setAlgoDefense(AlgoDefense algoDefense) {

		this.juego = algoDefense;

		}
	}

