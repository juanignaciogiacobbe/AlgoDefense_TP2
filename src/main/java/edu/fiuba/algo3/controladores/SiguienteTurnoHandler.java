package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.defensas.TorreNoDesplegada;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;
import edu.fiuba.algo3.vista.AlgoDefenseVista;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SiguienteTurnoHandler implements EventHandler<ActionEvent> {

    private AlgoDefense juego;

    private AlgoDefenseVista vista;

    public SiguienteTurnoHandler(AlgoDefense juego, AlgoDefenseVista algoDefenseVista) {
            this.juego = juego;
            this.vista = algoDefenseVista;
    }

    @Override
    public void handle(ActionEvent actionEvent) {
        try {
            juego.ejecutarTurno();
        } catch (TerrenoNoAptoParaCaminar | TerrenoNoAptoParaConstruir | DefensasVacias | TorreNoDesplegada e) {

        }
        juego.notifyObservers();
    }
}
