package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.TorreBlanca;
import edu.fiuba.algo3.modelo.defensas.TorrePlateada;
import edu.fiuba.algo3.modelo.mapa.Coordenada;

import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;
import edu.fiuba.algo3.vista.AlgoDefenseVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

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
        Coordenada coordenada = vista.getUltimaCoordenada();
        try {
            this.juego.ubicarDefensa(this.torre, coordenada.getAbscisa(), coordenada.getOrdenada());
        } catch (TerrenoNoAptoParaConstruir e) {
            throw new RuntimeException(e);
        }
        juego.notifyObservers();
    }


}
