package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;

public class Caminante implements Trasladable {

    private int velocidad;

    private Parcela pasarelaActual;

    public Caminante(int velocidad, Parcela pasarela) {
        this.velocidad = velocidad;
        this.pasarelaActual = pasarela;
    }

    @Override
    public Trasladable moverse(Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
        ParcelaDePasarela parcelaAMover = this.pasarelaActual.mover(this.velocidad, mapa);
        this.setPasarelaActual(parcelaAMover);

        return this;
    }

    public void setPasarelaActual(Parcela pasarela) {
        this.pasarelaActual = pasarela;
    }

    public Parcela getPasarelaActual() {
        return pasarelaActual;
    }

}
