package edu.fiuba.algo3.controladores;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class InformacionTorreBlancaHandler implements EventHandler<MouseEvent> {

    private Label nombreDefensa;
    private Label creditosDefensa;
    private Label rangoDefensa;
    private Label danioDefensa;
    private Label tiempoDespliegue;

    public InformacionTorreBlancaHandler(Label nombreDefensa, Label creditosDefensa, Label rangoDefensa, Label danioDefensa, Label tiempoDespliegue) {
        this.nombreDefensa = nombreDefensa;
        this.creditosDefensa = creditosDefensa;
        this.rangoDefensa = rangoDefensa;
        this.danioDefensa = danioDefensa;
        this.tiempoDespliegue = tiempoDespliegue;
    }

    @Override
    public void handle(MouseEvent event) {
        nombreDefensa.setText("Torre Blanca");
        creditosDefensa.setText("Créditos: 10");
        rangoDefensa.setText("Rango: 3");
        danioDefensa.setText("Daño: 1");
        tiempoDespliegue.setText("Tiempo de despliegue: 1 turno");
    }
}

