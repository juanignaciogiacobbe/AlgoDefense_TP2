package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.Defensa;
import edu.fiuba.algo3.modelo.enemigos.BajoTierra;
import edu.fiuba.algo3.modelo.enemigos.Caminante;
import edu.fiuba.algo3.modelo.mapa.Mapa;

public abstract class ParcelaDePasarela extends Parcela {

	public ParcelaDePasarela() {
		movible = new Desplazable();
	}

	public Parcela mover(BajoTierra bajoTierra, int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar {
		return this.movible.mover(this, bajoTierra, distancia, mapa);
	}

	public ParcelaDePasarela mover(Caminante caminante, int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar {
		return this.movible.mover(this, caminante, distancia, mapa);
	}

}
