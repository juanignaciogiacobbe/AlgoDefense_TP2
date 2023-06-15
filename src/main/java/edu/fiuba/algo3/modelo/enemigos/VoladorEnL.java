package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;

import java.util.concurrent.TransferQueue;

public class VoladorEnL implements Trasladable {


    private int velocidad;

    private Parcela pasarelaActual;

    public VoladorEnL(int velocidad, Parcela pasarelaActual) {
        this.velocidad = velocidad;
        this.pasarelaActual = pasarelaActual;
    }

    @Override
    public Trasladable moverse(Mapa mapa) {
        Parcela parcelaAMover = this.volarEnL(mapa);
        this.setPasarelaActual(parcelaAMover);
        return this;
    }

    @Override
    public Parcela getPasarelaActual() {
        return this.pasarelaActual;
    }

    @Override
    public void setPasarelaActual(Parcela pasarela) {
        this.pasarelaActual = pasarela;

    }


    public Parcela volarEnL(Mapa mapa) {

        int parcelaActualX = this.getPasarelaActual().getCoordenada().getAbscisa();
        int parcelaActualY = this.getPasarelaActual().getCoordenada().getOrdenada();
        int metaX = mapa.getMeta().getCoordenada().getAbscisa();
        int metaY = mapa.getMeta().getCoordenada().getOrdenada();


        for (int i = 0; i < this.velocidad; i++) {
            if (parcelaActualX != metaX) {
                if (parcelaActualX < metaX) {
                    parcelaActualX++;
                } else {
                    parcelaActualX--;
                }
            } else if (parcelaActualY != metaY) {
                if (parcelaActualY < metaY) {
                    parcelaActualY++;
                } else {
                    parcelaActualY--;
                }
            }
        }

        return mapa.obtenerParcelaConCoordenadas(parcelaActualX, parcelaActualY);
    }

}
