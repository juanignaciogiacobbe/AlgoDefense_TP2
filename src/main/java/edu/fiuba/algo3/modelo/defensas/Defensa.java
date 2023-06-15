package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.EnemigosFueraDeRango;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

import java.util.List;

public abstract class Defensa {

	protected String nombre;

	protected int costoConstruccion;

	protected int danio;

	protected int rangoAtaque;
	protected int turnosRestantesParaDespliegue;

	protected Desplegable desplegable;



	public int getCostoConstruccion() {
		return costoConstruccion;
	}


	public int getRangoAtaque() {
		return rangoAtaque;
	}

	public void atacar(List<Enemigo> enemigos, Parcela parcelaDefensa) throws TerrenoNoAptoParaCaminar, TorreNoDesplegada, EnemigosFueraDeRango {
		desplegable.atacar(enemigos, parcelaDefensa, rangoAtaque, danio);
	}

	public void pasarTurno() {
		this.desplegable = this.desplegable.pasarTurno();
	}


	public abstract String getNombre();
}
