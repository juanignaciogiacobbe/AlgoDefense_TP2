package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.Defensa;
import edu.fiuba.algo3.modelo.mapa.Mapa;

public abstract class ParcelaDePasarela extends Parcela {

	public ParcelaDePasarela() {
		construible = new NoConstruible();
		movible = new Desplazable();
	}

	public boolean puedeMoverseAqui() {
		return true;
	}

	public ParcelaDePasarela mover(int distancia, Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
		return mapa.obtenerPasarelasEnRango(this, distancia);
	}

	public void construir(Defensa defensaAConstuir) throws TerrenoNoAptoParaConstruir {
		construible.construir(defensaAConstuir, null);

	}

}
