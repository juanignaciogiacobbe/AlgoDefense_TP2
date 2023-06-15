package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.mapa.Coordenada;

public class PasarelaLargada extends ParcelaDePasarela {
	public PasarelaLargada(int abscisa, int ordenada) {
		super();
		this.construible = new NoConstruible();
		this.coordenada = new Coordenada(abscisa, ordenada);
	}


}
