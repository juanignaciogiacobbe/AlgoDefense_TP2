package edu.fiuba.algo3.modelo.parcelas;


import edu.fiuba.algo3.modelo.defensas.Defensa;
import edu.fiuba.algo3.modelo.defensas.TorreNoDesplegada;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.EnemigosFueraDeRango;
import edu.fiuba.algo3.modelo.mapa.Mapa;

import java.util.List;

public class TrampaArenosa implements Defensa {


	private int costoConstruccion;
	private int tiempoDeActividadRestante;
	public TrampaArenosa() {
		this.costoConstruccion = 25;
		this.tiempoDeActividadRestante = 3;
	}


	@Override
	public int getCostoConstruccion() {
		return this.costoConstruccion;
	}

	@Override
	public int getRangoAtaque() {
		return 0;
	}

	@Override
	public void atacar(List<Enemigo> enemigos, Parcela parcelaDefensa) throws TerrenoNoAptoParaCaminar, TorreNoDesplegada, EnemigosFueraDeRango {
		//super.atacar(enemigos, parcelaDefensa);
	}

	public TrampaArenosa pasarTurno() {
		this.tiempoDeActividadRestante -= 1;
		if (this.tiempoDeActividadRestante == 0) {return null;}
		return this;
	}

	public ParcelaDePasarela ralentizar(int distancia, Mapa mapa, PasarelaComun pasarela) {
		return mapa.obtenerPasarelasEnRango(pasarela, (int) Math.ceil((double) distancia / 2));
	}
}
