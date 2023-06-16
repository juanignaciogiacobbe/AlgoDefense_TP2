package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.Defensa;
import edu.fiuba.algo3.modelo.mapa.Mapa;

public abstract class ParcelaDePasarela extends Parcela {

	public ParcelaDePasarela() {
		movible = new Desplazable();
	}

	public ParcelaDePasarela mover(int distancia, Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
		return mapa.obtenerPasarelasEnRango(this, distancia);
	}

}
