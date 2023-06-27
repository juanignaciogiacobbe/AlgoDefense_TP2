package edu.fiuba.algo3.controladores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

public class InformacionTorreBlanca implements EventHandler<ActionEvent> {

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
        nombreDefensa.setText("Torre Blanca");
    }

    private void setCreditosDefensa() {
        creditosDefensa.setText("Créditos: 10");
    }

    private void setRangoDefensa() {
        rangoDefensa.setText("Rango: 3");
    }

    private void setDanioDefensa() {
        danioDefensa.setText("Daño: 1");
    }

    private void setTiempoDespliegue() {
        tiempoDespliegue.setText("Tiempo de despliegue: 1 turno");
    }


}