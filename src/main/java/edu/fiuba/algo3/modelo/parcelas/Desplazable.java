package edu.fiuba.algo3.modelo.parcelas;


import edu.fiuba.algo3.modelo.mapa.Mapa;

public class Desplazable implements Movible {

	@Override
	public ParcelaDePasarela mover(Parcela parcela, int distancia, Mapa mapa) {
		return mapa.obtenerPasarelasEnRango(parcela, distancia);
	}

	@Override
	public void moverseA() throws TerrenoNoAptoParaCaminar {

	}
}
