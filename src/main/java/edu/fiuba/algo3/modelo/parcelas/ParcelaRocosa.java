package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.Defensa;
import edu.fiuba.algo3.modelo.mapa.Coordenada;
import edu.fiuba.algo3.modelo.mapa.Mapa;

public class ParcelaRocosa extends Parcela {
	public ParcelaRocosa(int abscisa, int ordenada) {

		this.coordenada = new Coordenada(abscisa, ordenada);
		construible = new NoConstruible();
		movible = new NoDesplazable();
	}

	public ParcelaDePasarela mover(int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir {
		return (movible.mover(this, distancia, mapa));
	}


	@Override
	public String toString() {
		return "R";
	}
}
