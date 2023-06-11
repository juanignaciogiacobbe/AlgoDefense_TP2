package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;

public class Volador implements Trasladable {

    @Override
    public Trasladable moverse(Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
        return null;
    }

    @Override
    public ParcelaDePasarela getPasarelaActual() {
        return null;
    }

    @Override
    public void setPasarelaActual(ParcelaDePasarela pasarela) {

    }
}
