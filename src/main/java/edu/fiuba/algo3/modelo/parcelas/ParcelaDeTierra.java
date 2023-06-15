package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.Defensa;
import edu.fiuba.algo3.modelo.mapa.Coordenada;
import edu.fiuba.algo3.modelo.mapa.Mapa;

public class ParcelaDeTierra extends Parcela {

	private Defensa defensa;

	public ParcelaDeTierra(int abscisa, int ordenada) {

		this.coordenada = new Coordenada(abscisa, ordenada);
		this.construible = new Edificable();
		this.movible = new NoDesplazable();
		this.defensa = null;

	}
	public Defensa getDefensa() {
		return defensa;
	}

	public void setDefensa(Defensa defensa) {
		this.defensa = defensa;
	}

	public ParcelaDePasarela mover(int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir {
		return (movible.mover(this, distancia, mapa));
	}

	public void construir(Defensa defensaAConstruir) throws TerrenoNoAptoParaConstruir {
		construible.construir(defensaAConstruir, this);
	}


}
