package edu.fiuba.algo3.controladores;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class InformacionTrampaArenosaHandler implements EventHandler<MouseEvent> {

    private Label nombreDefensa;
    private Label creditosDefensa;
    private Label rangoDefensa;
    private Label danioDefensa;
    private Label tiempoDespliegue;

    public InformacionTrampaArenosaHandler(Label nombreDefensa, Label creditosDefensa, Label rangoDefensa, Label danioDefensa, Label tiempoDespliegue) {
        this.nombreDefensa = nombreDefensa;
        this.creditosDefensa = creditosDefensa;
        this.rangoDefensa = rangoDefensa;
        this.danioDefensa = danioDefensa;
        this.tiempoDespliegue = tiempoDespliegue;
    }

    @Override
    public void handle(MouseEvent event) {
        nombreDefensa.setText("Trampa Arenosa");
        creditosDefensa.setText("Créditos: 25");
        rangoDefensa.setText("Rango: 1");
        danioDefensa.setText("Ralentización: 50%");
        tiempoDespliegue.setText("Tiempo de despliegue: 0 turnos");
    }
}
