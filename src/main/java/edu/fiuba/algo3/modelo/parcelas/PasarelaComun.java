package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.NoRalentizador;
import edu.fiuba.algo3.modelo.defensas.Ralentizador;
import edu.fiuba.algo3.modelo.defensas.TrampaArenosa;
import edu.fiuba.algo3.modelo.mapa.Coordenada;
import edu.fiuba.algo3.modelo.mapa.Mapa;

public class PasarelaComun extends ParcelaDePasarela {

	private Ralentizador defensa;

	public PasarelaComun(int abscisa, int ordenada) {
		super();
		this.defensa = new NoRalentizador();
		this.construible = new ConstruibleTrampa();
		this.coordenada = new Coordenada(abscisa, ordenada);
	}


	public void setTrampaArenosa(TrampaArenosa defensaAConstruir) {
		this.defensa = defensaAConstruir;
	}

	public Ralentizador getDefensa() {
		return defensa;
	}

	@Override
	public ParcelaDePasarela mover(int distancia, Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
		return this.defensa.ralentizar(distancia, mapa, this);
	}

	@Override
	public void pasarTurno() {
		this.defensa = this.defensa.pasarTurno();
	}
	@Override
	public String toString() {
		return this.defensa.toString();
	}
}
