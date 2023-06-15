package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;

public class VoladorEnRecta implements Trasladable {

    private int velocidad;

    private Parcela pasarelaActual;


    public VoladorEnRecta(int velocidad, Parcela pasarelaActual) {
        this.velocidad = velocidad;
        this.pasarelaActual = pasarelaActual;
    }

    @Override
    public Trasladable moverse(Mapa mapa) {
        Parcela parcelaAMover = this.volarEnLineaRecta(mapa);
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

    public Parcela volarEnLineaRecta(Mapa mapa) {
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
            }
            if ((parcelaActualY != metaY)) {
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
