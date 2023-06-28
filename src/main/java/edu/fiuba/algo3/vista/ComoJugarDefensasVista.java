package edu.fiuba.algo3.vista;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ComoJugarDefensasVista implements Vista{
    private final Vista anteriorVista;
    private final Vista menu;


    public ComoJugarDefensasVista(Vista anteriorVista, Vista menu) {
        this.anteriorVista = anteriorVista;
        this.menu = menu;
    }

    @Override
    public void setNext(Vista nextVista) {

    }

    @Override
    public void show(Scene scene) {
        VBox vbox = new VBox();
        vbox.getStyleClass().add("howToPlayCard");

        Button backButton = new Button("Volver al menu principal");

        String imagePath = "file:" + "src/resources/" + "messi" + ".jpg";
        Image image = new Image(imagePath, 500, 500, true, true);
        ImageView imageView = new ImageView(image);
        Label labelHowToPlay = new Label("\"Recuerda que la clave para alcanzar la \n" +
                                            "victoria está en la planificación táctica y \n" +
                                            "en el despliegue estratégico de tus defensas");
        Button backVistaButton = new Button("Anterior vista");
        backButton.getStyleClass().add("boton");
        backVistaButton.getStyleClass().add("boton");



        backVistaButton.setOnAction(e -> {
            if (anteriorVista != null) {
                anteriorVista.show(scene);
            }
        });

        backButton.setOnAction(e -> {
            if (menu!= null) {
                menu.show(scene);
            }
        });

        vbox.getChildren().addAll(backButton, imageView, labelHowToPlay,backVistaButton);

        scene.setRoot(vbox);
    }

    @Override
    public void playMusic() {

    }

}


