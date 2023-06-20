package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.TorreBlanca;
import edu.fiuba.algo3.modelo.defensas.TorrePlateada;
import edu.fiuba.algo3.modelo.defensas.TrampaArenosa;
import edu.fiuba.algo3.modelo.mapa.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.PasarelaComun;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;
import edu.fiuba.algo3.vista.AlgoDefenseVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UbicarTrampaHandler implements EventHandler<ActionEvent> {

    private AlgoDefense juego;

    private AlgoDefenseVista vista;

    public UbicarTrampaHandler(AlgoDefense juego, AlgoDefenseVista algoDefenseVista) {
        this.juego = juego;
        this.vista = algoDefenseVista;
    }


    @Override
    public void handle(ActionEvent actionEvent) {
        Coordenada coordenada = vista.getUltimaCoordenada();
        TrampaArenosa trampaArenosa = new TrampaArenosa();
        Parcela parcela = this.juego.getMapa().obtenerParcelaConCoordenadas(coordenada.getAbscisa(),coordenada.getOrdenada());
        try {
            this.juego.ubicarTrampa(trampaArenosa, (PasarelaComun) parcela);
        } catch (TerrenoNoAptoParaConstruir e) {
            throw new RuntimeException(e);
        }
        juego.notifyObservers();
    }


}


