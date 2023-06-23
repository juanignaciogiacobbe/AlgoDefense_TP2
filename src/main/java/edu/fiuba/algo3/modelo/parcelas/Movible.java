package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.enemigos.BajoTierra;
import edu.fiuba.algo3.modelo.enemigos.Caminante;
import edu.fiuba.algo3.modelo.mapa.Mapa;

public interface Movible {

	public Parcela mover(Parcela parcela, BajoTierra bajoTierra, int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar;


	public ParcelaDePasarela mover(Parcela parcela, Caminante caminante, int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar;

	void moverseA() throws TerrenoNoAptoParaCaminar;

}
