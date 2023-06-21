package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.PasarelaComun;

public class NoRalentizador implements Ralentizador{
    @Override
    public ParcelaDePasarela ralentizar(int distancia, Mapa mapa, PasarelaComun pasarela) {
        return mapa.obtenerPasarelasEnRango(pasarela, distancia);
    }

    @Override
    public Ralentizador pasarTurno() {
        return this;
    }

    public String toString() { return "C";}
}
