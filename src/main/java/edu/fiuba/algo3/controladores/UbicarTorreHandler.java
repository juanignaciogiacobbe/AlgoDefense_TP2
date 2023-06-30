package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.TorreBlanca;
import edu.fiuba.algo3.modelo.defensas.TorrePlateada;
import edu.fiuba.algo3.modelo.juego.CreditosInsuficientes;
import edu.fiuba.algo3.modelo.mapa.Coordenada;

import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;
import edu.fiuba.algo3.vista.AlertBox;
import edu.fiuba.algo3.vista.AlgoDefenseVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class UbicarTorreHandler implements EventHandler<ActionEvent> {

    private AlgoDefense juego;

    private AlgoDefenseVista vista;

    private Torre torre;

    public UbicarTorreHandler(AlgoDefense juego, AlgoDefenseVista algoDefenseVista, String inicial) {
        this.juego = juego;
        this.vista = algoDefenseVista;
        this.torre = crearTorre(inicial);
    }

    private Torre crearTorre(String inicial) {
        switch (inicial) {
            case "p":
                return new TorrePlateada();

            case "b":
                return new TorreBlanca();

            default:
                return null;
        }

    }

    @Override
    public void handle(ActionEvent actionEvent) {
        Parcela parcela = vista.getUltimaParcela();
        try {
            try {
                if (parcela == null) {
                    AlertBox.display("Atención", "Debe seleccionar una parcela para construir");
                    return;
                }
                this.juego.construir(this.torre, parcela);
                vista.setUltimaParcela(null);
                Media media = new Media(new File("src/resources/sonido-construccion.mp3").toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setCycleCount(1); // Repetir la música indefinidamente
                mediaPlayer.play(); // Reproducir la música
            } catch (CreditosInsuficientes e) {
                AlertBox.display("Atencion", "No tiene creditos suficientes para la construcción");
                throw new RuntimeException(e);
            }
        } catch (TerrenoNoAptoParaConstruir e) {
            AlertBox.display("Atención", "El terreno no es apto para construir esta defensa");
            throw new RuntimeException(e);
        }
        juego.notifyObservers();
    }


}
