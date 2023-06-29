package edu.fiuba.algo3.controladores;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.TorreBlanca;
import edu.fiuba.algo3.modelo.defensas.TorrePlateada;
import edu.fiuba.algo3.modelo.defensas.TrampaArenosa;
import edu.fiuba.algo3.modelo.juego.CreditosInsuficientes;
import edu.fiuba.algo3.modelo.mapa.Coordenada;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.PasarelaComun;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;
import edu.fiuba.algo3.vista.AlertBox;
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
        Parcela parcela = this.vista.getUltimaParcela();
        TrampaArenosa trampaArenosa = new TrampaArenosa();
        try {
            if (parcela == null) {
                AlertBox.display("Atención", "Debe seleccionar una parcela para construir");
                return;
            }
            this.juego.construir(trampaArenosa, parcela);
            vista.setUltimaParcela(null);
        } catch (TerrenoNoAptoParaConstruir e) {
            AlertBox.display("Atención", "El terreno no es apto para construir esta defensa");
            throw new RuntimeException(e);
        } catch (CreditosInsuficientes e) {
            AlertBox.display("Atencion", "No tiene creditos suficientes para la construcción");
            throw new RuntimeException(e);
        }
        juego.notifyObservers();
    }


}


