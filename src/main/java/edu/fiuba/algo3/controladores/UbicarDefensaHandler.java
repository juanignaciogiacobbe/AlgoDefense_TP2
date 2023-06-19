package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defensas.TorrePlateada;
import edu.fiuba.algo3.vista.AlgoDefenseVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class UbicarDefensaHandler implements EventHandler<ActionEvent> {

    private AlgoDefense juego;

    private AlgoDefenseVista vista;

    public UbicarDefensaHandler(AlgoDefense juego, AlgoDefenseVista algoDefenseVista) {
        this.juego = juego;
        this.vista = algoDefenseVista;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        TorrePlateada torrePlateada = new TorrePlateada();
        this.juego.ubicarDefensa(torrePlateada,1,0);
        juego.notifyObservers();
    }



}
