package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.mapa.Mapa;

public interface Movible {
	ParcelaDePasarela mover(Parcela parcela, int distancia, Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar;

	void moverseA() throws TerrenoNoAptoParaCaminar;

}
