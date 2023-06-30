package edu.fiuba.algo3.vista;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ComoJugarVistaPrincipal implements Vista {

    private Vista menu;
    private Vista nextVista;


    public ComoJugarVistaPrincipal(Vista menu) {
        this.menu = menu;
        this.nextVista= new ComoJugarEnemigosVista(this,menu);

    }
    @Override
    public void setNext(Vista nextVista) {
        this.nextVista = nextVista;
    }

    @Override
    public void show(Scene scene) {


        VBox vbox = new VBox();
        vbox.getStyleClass().add("howToPlayCard");
        Button backButton = new Button("Volver al menu principal");

        String imagePath = "file:" + "src/resources/" + "howtoplay1" + ".png";
        Image image = new Image(imagePath, 500, 500, true, true);
        ImageView imageView = new ImageView(image);
        Label labelHowToPlay = new Label("\"¡Bienvenido Algo Defense! Tu misión es defenderte  \n" +
                                                "de las hordas de enemigos que intentan atacarte. Debes\n" +
                                                " construir y ubicar estratégicamente torres defensivas \n" +
                                                "a lo largo del camino enemigo para detener su avance");
        Button nextButton = new Button("Siguiente vista");
        nextButton.getStyleClass().add("boton");
        backButton.getStyleClass().add("boton");

        nextButton.setOnAction(e -> {
            if (nextVista != null) {
                nextVista.show(scene);
            }
        });

        backButton.setOnAction(e -> {
            if (nextVista != null) {
                menu.show(scene);
            }
        });



        vbox.getChildren().addAll(backButton, imageView, labelHowToPlay, nextButton);

        scene.setRoot(vbox);
    }

    @Override
    public void playMusic() {

    }


}

