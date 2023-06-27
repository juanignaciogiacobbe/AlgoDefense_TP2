

package edu.fiuba.algo3.vista;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ComoJugarEnemigosVista implements Vista {


    private  Vista anteriorVista;
    private  Vista menu;
    private Vista nextVista;

    public ComoJugarEnemigosVista(Vista anteriorVista, Vista menu) {
        this.anteriorVista = anteriorVista;
        this.setNextVista(new ComoJugarParcelaVista(this,menu));
        this.menu = menu;
    }

    @Override
    public void setNextVista(Vista nextVista) {
        this.nextVista = nextVista;
    }

    @Override
    public void mostrar(Scene scene) {
        VBox vbox = new VBox();
        vbox.getStyleClass().add("howToPlayCard");

        Button backButton = new Button("Volver al menu principal");

        String imagePath = "file:" + "src/resources/" + "H" + ".png";
        Image image = new Image(imagePath, 500, 500, true, true);
        ImageView imageView = new ImageView(image);
        Label labelHowToPlay = new Label("\"Te enfrentarás a una variedad de enemigos con habilidades únicas y letales, cada uno con su propio estilo de ataque y movimiento distintivo. Prepárate para poner a prueba tus habilidades mientras te enfrentas a estos enemigos despiadados. ");
        Button nextButton = new Button("Siguiente vista");
        Button backVistaButton = new Button("Anterior vista");
        nextButton.getStyleClass().add("boton");
        backButton.getStyleClass().add("boton");
        backVistaButton.getStyleClass().add("boton");

        nextButton.setOnAction(e -> {
            if (nextVista != null) {
                nextVista.mostrar(scene);
            }
        });

        backVistaButton.setOnAction(e -> {
            if (anteriorVista != null) {
                anteriorVista.mostrar(scene);
            }
        });

        backButton.setOnAction(e -> {
            if (menu!= null) {
                menu.mostrar(scene);
            }
        });

        vbox.getChildren().addAll(backButton, imageView, labelHowToPlay, nextButton,backVistaButton);

        scene.setRoot(vbox);
    }



}


