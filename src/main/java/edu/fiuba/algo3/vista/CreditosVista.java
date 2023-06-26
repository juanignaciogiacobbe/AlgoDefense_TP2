package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.modelo.AlgoDefense;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.scene.layout.VBox;

public class CreditosVista implements Vista {

    private Vista nextVista;

    @Override
    public void setNextVista(Vista nextVista) {
        this.nextVista = nextVista;
    }

    @Override
    public void mostrar(Scene scene) {
        VBox vbox = new VBox();
        vbox.getStyleClass().add("container");

        Label titleLabel = new Label("Créditos");
        titleLabel.getStyleClass().add("titulo");

        Label creditsLabel = new Label("Desarrollado por:\n" +
                "- Giacobbe, Juan Ignacio\n" +
                "- Fernandez Boch, Valeria\n" +
                "- Olaran, Sebastian\n" +
                "- Cusihuaman Altagracia, Luis Eduardo\n" +
                "Algoritmos y Programación III");

        creditsLabel.getStyleClass().add("texto-creditos");

        Button backButton = new Button("Volver");
        backButton.getStyleClass().add("boton-volver");

        backButton.setOnAction(e -> {
            if (nextVista != null) {
                nextVista.mostrar(scene);
            }
        });
        vbox.getChildren().addAll(titleLabel, creditsLabel, backButton);

        scene.setRoot(vbox);


    }

}

