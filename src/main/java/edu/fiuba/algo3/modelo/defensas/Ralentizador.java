package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.PasarelaComun;

public interface Ralentizador {
    ParcelaDePasarela ralentizar(int distancia, Mapa mapa, PasarelaComun pasarela);

    Ralentizador pasarTurno();
}
