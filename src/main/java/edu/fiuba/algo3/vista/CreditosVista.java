package edu.fiuba.algo3.vista;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.layout.VBox;

public class CreditosVista implements Vista {

    private Vista nextVista;

    @Override
    public void setNext(Vista nextVista) {
        this.nextVista = nextVista;
    }

    @Override
    public void show(Scene scene) {
        VBox vbox = new VBox();
        vbox.getStyleClass().add("container");

        Label titleLabel = new Label("Créditos");
        titleLabel.getStyleClass().add("texto-creditos");
        titleLabel.getStyleClass().add("titulo");

        Label creditsLabel = new Label("Desarrollado por:\n" +
                "Cusihuaman Altagracia, Luis Eduardo\n" +
                "Fernandez Boch, Valeria\n" +
                "Giacobbe, Juan Ignacio\n" +
                "Olaran, Sebastian\n" +
                "Grupo 2 - Algoritmos y Programación III");

        creditsLabel.getStyleClass().add("texto-creditos");

        Button backButton = new Button("Volver");
        backButton.getStyleClass().add("boton");
        backButton.getStyleClass().add("boton-volver");

        backButton.setOnAction(e -> {
            if (nextVista != null) {
                nextVista.show(scene);
            }
        });
        vbox.getChildren().addAll(titleLabel, creditsLabel, backButton);

        scene.setRoot(vbox);
    }

    @Override
    public void playMusic() {

    }

}

