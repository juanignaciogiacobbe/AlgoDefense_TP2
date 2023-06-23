package edu.fiuba.algo3.modelo.parcelas;


import edu.fiuba.algo3.modelo.enemigos.BajoTierra;
import edu.fiuba.algo3.modelo.enemigos.Caminante;
import edu.fiuba.algo3.modelo.mapa.Mapa;

public class Desplazable implements Movible {


	public Parcela mover(Parcela parcela, BajoTierra bajoTierra, int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar {
		return mapa.obtenerPasarelasEnRango(bajoTierra, parcela, distancia);
	}

	public ParcelaDePasarela mover(Parcela parcela, Caminante caminante, int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar {
		return mapa.obtenerPasarelasEnRango(caminante, parcela, distancia);
	}

	@Override
	public void moverseA() throws TerrenoNoAptoParaCaminar {

	}
}
