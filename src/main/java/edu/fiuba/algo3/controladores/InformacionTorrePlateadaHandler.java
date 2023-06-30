package edu.fiuba.algo3.controladores;

import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
public class InformacionTorrePlateadaHandler implements EventHandler<MouseEvent> {

    private Label nombreDefensa;
    private Label creditosDefensa;
    private Label rangoDefensa;
    private Label danioDefensa;
    private Label tiempoDespliegue;



    public InformacionTorrePlateadaHandler(Label nombreDefensa, Label creditosDefensa, Label rangoDefensa, Label danioDefensa, Label tiempoDespliegue) {
        this.nombreDefensa = nombreDefensa;
        this.creditosDefensa = creditosDefensa;
        this.rangoDefensa = rangoDefensa;
        this.danioDefensa = danioDefensa;
        this.tiempoDespliegue = tiempoDespliegue;
    }


    @Override
    public void handle(MouseEvent event) {
        nombreDefensa.setText("Torre Plateada");
        creditosDefensa.setText("Créditos: 20");
        rangoDefensa.setText("Rango: 5");
        danioDefensa.setText("Daño: 2");
        tiempoDespliegue.setText("Tiempo de despliegue: 2 turnos");
    }



}
