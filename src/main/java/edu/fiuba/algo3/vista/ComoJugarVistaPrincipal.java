package edu.fiuba.algo3.vista;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ComoJugarVistaPrincipal implements Vista {

    private Vista nextVista;

    @Override
    public void setNextVista(Vista nextVista) {
        this.nextVista = nextVista;
    }

    @Override
    public void mostrar(Scene scene) {
        VBox vbox = new VBox();
        vbox.getStyleClass().add("howToPlayCard");

        Button backButton = new Button("Volver al menu principal");

        String imagePath = "file:" + "src/resources/" + "howtoplay1" + ".png";
        Image image = new Image(imagePath, 500, 500, true, true);
        ImageView imageView = new ImageView(image);
        Label labelHowToPlay = new Label("asdjkasdjaljsdl;ajsdl;jasd");
        Button nextButton = new Button("Siguiente vista");
        nextButton.getStyleClass().add("boton");
        backButton.getStyleClass().add("boton");

        backButton.setOnAction(e -> {
            if (nextVista != null) {
                nextVista.mostrar(scene);
            }
        });

        vbox.getChildren().addAll(backButton, imageView, labelHowToPlay, nextButton);

        scene.setRoot(vbox);
    }



}

