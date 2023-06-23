package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.mapa.Mapa;

public class NoDesplazable implements Movible {

	@Override
	public ParcelaDePasarela mover(Parcela parcela, int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar {
		throw new TerrenoNoAptoParaCaminar();
	}

	@Override
	public void moverseA() throws TerrenoNoAptoParaCaminar {
		throw new TerrenoNoAptoParaCaminar();
	}
}
