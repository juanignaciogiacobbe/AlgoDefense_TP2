package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.enemigos.BajoTierra;
import edu.fiuba.algo3.modelo.enemigos.Caminante;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.PasarelaComun;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;

public class NoRalentizador implements Ralentizador{

    public Parcela ralentizar(Parcela parcela, BajoTierra bajoTierra, int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar {
        return mapa.obtenerPasarelasEnRango(bajoTierra, parcela, distancia);
    }

    public ParcelaDePasarela ralentizar(Parcela parcela, Caminante caminante, int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar {
        return mapa.obtenerPasarelasEnRango(caminante, parcela, distancia);
    }

    @Override
    public Ralentizador pasarTurno() {
        return this;
    }

    public String toString() { return "C";}
}
