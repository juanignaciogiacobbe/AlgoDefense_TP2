package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.EnemigosFueraDeRango;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

import java.util.List;

public abstract class Defensa {

	protected int costoConstruccion;

	protected int danio;
	protected int rangoAtaque;
	protected int turnosRestantesParaDespliegue;

	protected Desplegable desplegable;

	public boolean estaDesplegada() {
		return (turnosRestantesParaDespliegue <= 0);
	}

	public int getCostoConstruccion() {
		return costoConstruccion;
	}


	public int getRangoAtaque() {
		return rangoAtaque;
	}

	public void atacarA(Enemigo enemigo) {
		enemigo.recibirDanio(danio);
	}

	public void atacar(List<Enemigo> enemigos, Parcela parcelaDefensa) throws TerrenoNoAptoParaCaminar, TorreNoDesplegada, EnemigosFueraDeRango {
		desplegable.atacar(enemigos, parcelaDefensa, rangoAtaque, danio);
	}

	public void pasarTurno() {
		this.desplegable = this.desplegable.pasarTurno();
	}

	public boolean enemigoDentroDeRango(int distanciaAEnemigo) {
		return (distanciaAEnemigo <= rangoAtaque);
	}
}