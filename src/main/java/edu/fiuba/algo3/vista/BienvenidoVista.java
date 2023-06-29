package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.controladores.LoginBotonControlador;
import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.convertidor.FormatoJSONInvalidoException;
import edu.fiuba.algo3.modelo.juego.NombreInvalido;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;

public class BienvenidoVista implements Vista {
    private Vista nextVista;

    private TextField nameField;

    private AlgoDefense juego;

    private Vista creditos;

    private Vista comoJugarPrincipal;
    private MediaPlayer mediaPlayer;

    @Override
    public void setNext(Vista nextVista) {
        this.nextVista = nextVista;
    }

    public void setCreditos(Vista creditosVista) {
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
            nextVista.playMusic();
            nextVista.show(scene);
        }
    }

    private boolean validateInput(String input) {
        return input.length() >= 6;
    }

    @Override
    public void show(Scene scene) {
        Label nameLabel = new Label("Ingresa tu nombre:");
        nameLabel.getStyleClass().add("label");
        nameField = new TextField();
        nameField.getStyleClass().add("input");
        Button loginButton = new Button("Comenzar juego");
        loginButton.getStyleClass().add("boton");

        VBox vbox = new VBox(createLogoImageView(), nameLabel, nameField, loginButton);
        vbox.getStyleClass().add("container");

        Button creditosBoton = new Button("Créditos");
        Button howToPlayButton = new Button("Cómo jugar");
        Button salirBoton = new Button("Salir");


        creditosBoton.getStyleClass().add("boton");
        howToPlayButton.getStyleClass().add("boton");
        salirBoton.setStyle("-fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        salirBoton.setOnAction(e -> {
            Stage stage = (Stage) salirBoton.getScene().getWindow();
            stage.close();
        });


        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setHgap(20); // Espacio horizontal entre columnas

        // Establecer márgenes para los botones en el GridPane
        GridPane.setMargin(creditosBoton, new Insets(25, 25, 0, 0)); // Margen derecho para "Créditos"
        GridPane.setMargin(howToPlayButton, new Insets(25, 12.5, 0, 12.5)); // Margen izquierdo para "Cómo jugar"
        GridPane.setMargin(salirBoton, new Insets(25, 0, 0, 25)); // Margen izquierdo para "Cómo jugar"

        // Añadir los botones al gridPane
        gridPane.add(creditosBoton, 0, 0);
        gridPane.add(howToPlayButton, 1, 0);
        gridPane.add(salirBoton, 2, 0);


        VBox mainContainer = new VBox(gridPane, vbox);
        mainContainer.setAlignment(Pos.TOP_CENTER);
        mainContainer.setSpacing(50); // Espacio vertical entre elementos
        mainContainer.getStyleClass().add("game-background"); // Agregar clase CSS para el fondo

        creditosBoton.setOnAction(e -> {
            if (nextVista != null) {
                creditos.show(scene);
            }
        });

        howToPlayButton.setOnAction(e -> {
            if (nextVista != null) {
                comoJugarPrincipal.show(scene);
            }
        });

        LoginBotonControlador controller = new LoginBotonControlador(nameField,nextVista,juego,scene,mediaPlayer);
        nameField.setOnAction(controller);
        loginButton.setOnAction(controller);

        scene.setRoot(mainContainer);
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

    @Override
    public void playMusic() {
        Media media = new Media(new File("src/resources/musica-inicio.mp3").toURI().toString());
        this.mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE); // Repetir la música indefinidamente
        mediaPlayer.setVolume(0.5);
        mediaPlayer.play(); // Reproducir la música
    }


}
