package edu.fiuba.algo3.controladores;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.text.Text;

public class InformacionTorrePlateadaHandler implements EventHandler<ActionEvent> {

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
        nombreDefensa.setText("Torre Plateada");
    }

    private void setCreditosDefensa() {
        creditosDefensa.setText("Créditos: 20");
    }

    private void setRangoDefensa() {
        rangoDefensa.setText("Rango: 5");
    }

    private void setDanioDefensa() {
        danioDefensa.setText("Daño: 2");
    }

    private void setTiempoDespliegue() {
        tiempoDespliegue.setText("Tiempo de despliegue: 2 turnos");
    }


}
