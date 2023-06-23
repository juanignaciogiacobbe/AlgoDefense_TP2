package edu.fiuba.algo3.modelo.defensas;


import edu.fiuba.algo3.modelo.enemigos.BajoTierra;
import edu.fiuba.algo3.modelo.enemigos.Caminante;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.EnemigosFueraDeRango;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.PasarelaComun;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;

import java.util.List;

public class TrampaArenosa implements Defensa, Ralentizador {


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
	public void atacar(List<Enemigo> enemigos, Parcela parcelaDefensa)  {
		//super.atacar(enemigos, parcelaDefensa);
	}

	public Ralentizador pasarTurno() {
		this.tiempoDeActividadRestante -= 1;
		if (this.tiempoDeActividadRestante == 0) {return new NoRalentizador();}
		return this;
	}

	@Override
	public String getNombre() {
		return "Trampa Arenosa";
	}

	public Parcela ralentizar(Parcela parcela, BajoTierra bajoTierra, int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar {
		return mapa.obtenerPasarelasEnRango(bajoTierra, parcela, (int) Math.ceil((double) distancia / 2));
	}

	public ParcelaDePasarela ralentizar(Parcela parcela, Caminante caminante, int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar {
		int distanciaRalentizada = (int) Math.ceil((double) distancia / 2);
		return mapa.obtenerPasarelasEnRango(caminante, parcela, distanciaRalentizada);
	}

	public String toString() {
		return "S";
	}
}
