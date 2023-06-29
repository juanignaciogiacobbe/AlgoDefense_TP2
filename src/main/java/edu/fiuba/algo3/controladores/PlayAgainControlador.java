package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.convertidor.FormatoJSONInvalidoException;
import edu.fiuba.algo3.vista.Vista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;

import java.io.IOException;

public class PlayAgainControlador implements EventHandler<ActionEvent> {
    private AlgoDefense algoDefense;
    private Vista nextVista;
    private Scene scene;

    public PlayAgainControlador(AlgoDefense algoDefense, Vista nextVista, Scene scene) {
        this.algoDefense = algoDefense;
        this.nextVista = nextVista;
        this.scene = scene;
    }

    @Override
    public void handle(ActionEvent event) {
        try {
            this.algoDefense.reiniciarJuego();
        } catch (IOException | FormatoJSONInvalidoException | org.json.simple.parser.ParseException ex) {
            throw new RuntimeException(ex);
        }
        this.nextVista.playMusic();
        this.nextVista.show(scene);
    }
}

