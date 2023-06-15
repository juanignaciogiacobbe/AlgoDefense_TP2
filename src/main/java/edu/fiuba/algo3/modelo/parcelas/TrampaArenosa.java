package edu.fiuba.algo3.modelo.parcelas;


import edu.fiuba.algo3.modelo.defensas.Defensa;
import edu.fiuba.algo3.modelo.defensas.TorreNoDesplegada;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.EnemigosFueraDeRango;
import edu.fiuba.algo3.modelo.mapa.Mapa;

import java.util.List;

public class TrampaArenosa extends Defensa {

	public TrampaArenosa() {
		this.costoConstruccion = 25;
	}


	@Override
	public void atacar(List<Enemigo> enemigos, Parcela parcelaDefensa) throws TerrenoNoAptoParaCaminar, TorreNoDesplegada, EnemigosFueraDeRango {
		//super.atacar(enemigos, parcelaDefensa);
	}

	public ParcelaDePasarela ralentizar(int distancia, Mapa mapa, PasarelaComun pasarela) {
		return mapa.obtenerPasarelasEnRango(pasarela, (int) Math.ceil((double) distancia / 2));
	}
}
