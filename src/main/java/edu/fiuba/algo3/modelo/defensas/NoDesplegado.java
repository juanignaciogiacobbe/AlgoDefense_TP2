package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.CustomLogger;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

import java.util.List;

public class NoDesplegado implements Desplegable {

	private int turnosRestantesParaDespliegue;

	private CustomLogger logger;

	public NoDesplegado(int turnosRestantesParaDespliegue) {
		this.turnosRestantesParaDespliegue = turnosRestantesParaDespliegue;
		this.logger = CustomLogger.getInstance();
	}

	@Override
	public void atacar(List<Enemigo> enemigos, Parcela parcelaDefensa, int rangoAtaque, int danio) throws TorreNoDesplegada {
		logger.log("La torre aun no ha sido desplegada");
		throw new TorreNoDesplegada();
	}

	public Desplegable pasarTurno() {
		this.turnosRestantesParaDespliegue -= 1;
		if (this.turnosRestantesParaDespliegue == 0) {
			return new Desplegado();
		}
		return this;
	}
}
