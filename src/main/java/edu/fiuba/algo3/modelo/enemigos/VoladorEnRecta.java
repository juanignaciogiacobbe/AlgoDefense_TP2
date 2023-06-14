package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;

public class VoladorEnRecta implements  Trasladable{

    private int velocidad;

    private Parcela pasarelaActual;


    public VoladorEnRecta(int velocidad, Parcela pasarelaActual) {
        this.velocidad = velocidad;
        this.pasarelaActual = pasarelaActual;
    }

    @Override
    public Trasladable moverse(Mapa mapa) {
        Parcela parcelaAMover = this.volarEnLineaRecta(mapa);
        this.setPasarelaActual((ParcelaDePasarela) parcelaAMover);
        return this;
    }

    @Override
    public ParcelaDePasarela getPasarelaActual() {
        return (ParcelaDePasarela) this.pasarelaActual;
    }

    @Override
    public void setPasarelaActual(ParcelaDePasarela pasarela) {
        this.pasarelaActual = pasarela;
    }

    public Parcela volarEnLineaRecta(Mapa mapa) {

        int parcelaActualX = this.getPasarelaActual().getCoordenada().getAbscisa();
        int parcelaActualY = this.getPasarelaActual().getCoordenada().getOrdenada();
        int metaX = mapa.getMeta().getCoordenada().getAbscisa();
        int metaY = mapa.getMeta().getCoordenada().getOrdenada();

        int distancia = this.getPasarelaActual().getCoordenada().distanciaHacia(mapa.getMeta().getCoordenada());
        // Calcular los incrementos en X y Y por cada paso
        int incrementoX = (metaX - parcelaActualX) / distancia;
        int incrementoY = (metaY - parcelaActualY) / distancia;

        // Bucle para moverse en línea recta hacia la meta
        for (int i = 0; i < this.velocidad; i++) {
            parcelaActualX += incrementoX;
            parcelaActualY += incrementoY;
        }
        return mapa.obtenerParcelaConCoordenadas(parcelaActualX, parcelaActualY);

    }

}
