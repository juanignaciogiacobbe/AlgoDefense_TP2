package edu.fiuba.algo3.controladores;


import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.convertidor.FormatoJSONInvalidoException;
import edu.fiuba.algo3.modelo.juego.NombreInvalido;
import edu.fiuba.algo3.vista.Vista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaPlayer;
import org.json.simple.parser.ParseException;


import java.io.IOException;


public class LoginBotonControlador implements EventHandler<ActionEvent> {


    private final Scene scene;
    private final TextField nameField;
    private final Vista nextVista;
    private final AlgoDefense juego;
    private MediaPlayer mediaPlayer;

    public LoginBotonControlador(TextField namefield, Vista nextVista, AlgoDefense juego, Scene scene, MediaPlayer media) {
        this.nameField = namefield;
        this.nextVista = nextVista;
        this.juego = juego;
        this.scene = scene;
        this.mediaPlayer = media;

    }

    @Override
    public void handle(ActionEvent event) {
        String playerName = nameField.getText();
        if (!validateInput(playerName)) {
            nameField.getStyleClass().add("input-invalido");
            return;
        }
        try {
            juego.agregarJugador(playerName);
        } catch (NombreInvalido | FormatoJSONInvalidoException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        System.out.println(playerName + ", bienvenido a AlgoDefense");
        if (nextVista != null) {
            stopMusic();
            nextVista.playMusic();
            nextVista.show(scene);
        }
    }


    private boolean validateInput(String input) {
        return input.length() >= 6;
    }



    private void stopMusic() {
        mediaPlayer.stop();
        mediaPlayer.dispose();
        mediaPlayer = null;

    }
}
