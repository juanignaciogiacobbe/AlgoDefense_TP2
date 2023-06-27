package edu.fiuba.algo3.controladores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

public class InformacionTrampaArenosa implements EventHandler<ActionEvent> {


    private Text nombreDefensa;


    private Text creditosDefensa;


    private Text rangoDefensa;


    private Text danioDefensa;


    private Text tiempoDespliegue;


    @Override
    public void handle(ActionEvent actionEvent) {
        setNombreDefensa();
        setCreditosDefensa();
        setRangoDefensa();
        setDanioDefensa();
        setTiempoDespliegue();
    }

    private void setNombreDefensa() {
        nombreDefensa.setText("Trampa Arenosa");
    }

    private void setCreditosDefensa() {
        creditosDefensa.setText("Créditos: 25");
    }

    private void setRangoDefensa() {
        rangoDefensa.setText("Rango: 1");
    }

    private void setDanioDefensa() {
        danioDefensa.setText("Ralentización: 50%");
    }

    private void setTiempoDespliegue() {
        tiempoDespliegue.setText("Tiempo de despliegue: 0 turnos");
    }
}

