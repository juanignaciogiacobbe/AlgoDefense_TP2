package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.enemigos.BajoTierra;
import edu.fiuba.algo3.modelo.enemigos.Caminante;
import edu.fiuba.algo3.modelo.mapa.Mapa;

public class NoDesplazable implements Movible {

	public Parcela mover(Parcela parcela, BajoTierra bajoTierra, int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar {
		return mapa.obtenerPasarelasEnRango(bajoTierra, parcela, distancia);
	}

	@Override
	public ParcelaDePasarela mover(Parcela parcela, Caminante caminante, int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar {
		return null;
	}

	@Override
	public void moverseA() throws TerrenoNoAptoParaCaminar {
		throw new TerrenoNoAptoParaCaminar();
	}
}
