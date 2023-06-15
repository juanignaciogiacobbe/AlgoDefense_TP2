package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.Defensa;
import edu.fiuba.algo3.modelo.mapa.Coordenada;
import edu.fiuba.algo3.modelo.mapa.Mapa;

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

	@Override
	public ParcelaDePasarela mover(int distancia, Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
		//TODO: llamar a pasar turno luego de ralentizar, y actualizar estado
		if (this.defensa == null) {
			return mapa.obtenerPasarelasEnRango(this, distancia);
		}
		return this.defensa.ralentizar(distancia, mapa, this);
	}
}
