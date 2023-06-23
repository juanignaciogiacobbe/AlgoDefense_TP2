package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.Defensa;
import edu.fiuba.algo3.modelo.enemigos.BajoTierra;
import edu.fiuba.algo3.modelo.enemigos.Caminante;
import edu.fiuba.algo3.modelo.mapa.Coordenada;
import edu.fiuba.algo3.modelo.mapa.Mapa;

public class ParcelaRocosa extends Parcela {
	public ParcelaRocosa(int abscisa, int ordenada) {

		this.coordenada = new Coordenada(abscisa, ordenada);
		construible = new NoConstruible();
		movible = new NoDesplazable();
	}

	public Parcela mover(BajoTierra bajoTierra, int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar {
		return mapa.obtenerPasarelasEnRango(bajoTierra, this, distancia);
	}

	public ParcelaDePasarela mover(Caminante caminante, int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar {
		return (this.movible.mover(this, caminante, distancia, mapa));
	}


	@Override
	public String toString() {
		return "R";
	}
}
