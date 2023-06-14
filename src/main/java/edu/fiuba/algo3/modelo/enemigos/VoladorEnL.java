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

    private int vida;


    public VoladorEnL(int velocidad, Parcela pasarelaActual,int vida) {
        this.velocidad = velocidad;
        this.pasarelaActual = pasarelaActual;
        this.vida = vida;
    }

    @Override
    public Trasladable moverse(Mapa mapa) {
        Parcela parcelaAMover = this.volarEnL(mapa);
        this.setPasarelaActual((ParcelaDePasarela) parcelaAMover);
        return this.actualizarEstado();
    }

    @Override
    public ParcelaDePasarela getPasarelaActual() {
        return null;
    }

    @Override
    public void setPasarelaActual(ParcelaDePasarela pasarela) {
        this.pasarelaActual = pasarela;

    }

    public void actualizar(int vida) {
        this.vida = vida;
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

    private Trasladable actualizarEstado(){
        if (vida < 3){
            return new VoladorEnRecta(this.velocidad,this.getPasarelaActual());
        }
        return this;
    }
}
