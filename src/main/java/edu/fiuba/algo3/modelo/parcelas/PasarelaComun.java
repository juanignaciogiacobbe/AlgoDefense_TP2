package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.Defensa;
import edu.fiuba.algo3.modelo.mapa.Coordenada;

public class PasarelaComun extends ParcelaDePasarela {

	private TrampaArenosa defensa;

	public PasarelaComun(int abscisa, int ordenada) {
		super();
		this.construible = new ConstruibleTrampa();
		this.coordenada = new Coordenada(abscisa, ordenada);
	}


	public void setTrampaArenosa(TrampaArenosa defensaAConstruir) {
		this.defensa = defensaAConstruir;
	}

	@Override
	public void construir(Defensa defensa) throws TerrenoNoAptoParaConstruir {
		this.construible.construir(defensa, this);
	}
}
