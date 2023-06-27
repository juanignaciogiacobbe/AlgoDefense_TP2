package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.convertidor.FormatoJSONInvalidoException;
import edu.fiuba.algo3.modelo.juego.NombreInvalido;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class BienvenidoVista implements Vista {
    private Vista nextVista;

    private TextField nameField;

    private AlgoDefense juego;

    private Vista creditos;

    private Vista comoJugarPrincipal;




    @Override
    public void setNextVista(Vista nextVista) {
        this.nextVista = nextVista;
    }

    public void setCreditos(Vista creditosVista){
        this.creditos = creditosVista;
    }

    public void setComoJugarPrincipal(Vista comoJugarPrincipal) {
        this.comoJugarPrincipal = comoJugarPrincipal;
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

        Button creditosBoton = new Button("Creditos");
        Button howToPlayButton = new Button("Como jugar");

        creditosBoton.getStyleClass().add("boton");
        howToPlayButton.getStyleClass().add("boton");

        StackPane.setAlignment(creditosBoton, Pos.TOP_CENTER);
        StackPane.setAlignment(howToPlayButton, Pos.TOP_CENTER);

        StackPane.setMargin(creditosBoton, new Insets(50));

          creditosBoton.setOnAction(e -> {
            if (nextVista != null) {
                creditos.mostrar(scene);
            }
        });

        howToPlayButton.setOnAction(e -> {
            if (nextVista != null) {
                comoJugarPrincipal.mostrar(scene);
            }
        });


        // Create a StackPane to hold the logo image and input field
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(vbox);
        stackPane.getChildren().addAll(creditosBoton, howToPlayButton);

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

