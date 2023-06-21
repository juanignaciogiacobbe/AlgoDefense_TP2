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
        Coordenada coordenada = vista.getUltimaCoordenada();
        try {
            try {
                this.juego.construir(this.torre, parcela);
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
