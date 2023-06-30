package edu.fiuba.algo3.vista;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ColocarDefensaVista implements Vista{
        private final Vista anteriorVista;

        private final Vista menu;
        private Vista nextVista;

        public ColocarDefensaVista(Vista anteriorVista, Vista menu) {
            this.anteriorVista = anteriorVista;
            this.setNext(new EjecutarTurnoVista(this,menu));
            this.menu = menu;
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

            String imagePath = "file:" + "src/resources/" + "ColocarDefensa" + ".png";
            Image image = new Image(imagePath, 900, 500, true, true);
            ImageView imageView = new ImageView(image);
            Label labelHowToPlay = new Label("\"Pero..... ¿Como hago para activar mis defensas? \n");
            Button nextButton = new Button("Siguiente vista");
            Button backVistaButton = new Button("Anterior vista");
            nextButton.getStyleClass().add("boton");
            backButton.getStyleClass().add("boton");
            backVistaButton.getStyleClass().add("boton");

            nextButton.setOnAction(e -> {
                if (nextVista != null) {
                    nextVista.show(scene);
                }
            });

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

            vbox.getChildren().addAll(backButton, imageView, labelHowToPlay, nextButton,backVistaButton);

            scene.setRoot(vbox);
        }

        @Override
        public void playMusic() {

        }


    }




