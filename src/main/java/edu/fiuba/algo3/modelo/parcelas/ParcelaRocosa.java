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

	@Override
	public void construir(Defensa defensa) throws TerrenoNoAptoParaConstruir {
		construible.construir(defensa, null);
	}


	public ParcelaDePasarela mover(int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir {
		return (movible.mover(this, distancia, mapa));
	}

	public void construir() throws TerrenoNoAptoParaConstruir {
		construible.construir(null, null);
	}

	@Override
	public String toString() {
		return "R";
	}
}
