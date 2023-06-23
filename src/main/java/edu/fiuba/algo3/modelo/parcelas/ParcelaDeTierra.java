package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.Defensa;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.mapa.Coordenada;
import edu.fiuba.algo3.modelo.mapa.Mapa;

import java.util.List;

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

	@Override
	public void setDefensa(Torre defensa) {
		this.defensa = defensa;
	}

	public ParcelaDePasarela mover(int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir {
		return (this.movible.mover(this, distancia, mapa));
	}

	@Override
	public String toString() {
		return "T";
	}

	public void destruirDefensa() {
		this.defensa = null;
	}

	public void atacar(List<Enemigo> enemigos) {
		this.defensa.atacar(enemigos, this);
	}
}
