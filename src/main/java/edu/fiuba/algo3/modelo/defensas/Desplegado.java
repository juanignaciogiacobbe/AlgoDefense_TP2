package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.CustomLogger;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.EnemigoFueraDeRango;
import edu.fiuba.algo3.modelo.enemigos.EnemigoNoDaniable;
import edu.fiuba.algo3.modelo.enemigos.EnemigosFueraDeRango;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

import java.util.List;

public class Desplegado implements Desplegable {

	private CustomLogger logger;


	public Desplegado() {
		this.logger = CustomLogger.getInstance();
	}

		public Desplegable atacar(List<Enemigo> enemigos, Parcela parcelaDefensa, int rangoAtaque, int danio) {
		for (Enemigo enemigo : enemigos) {
			try {
				enemigo.recibirAtaque(parcelaDefensa, rangoAtaque, danio);
				logger.log("La torre ataco a una " + enemigo.getNombre() + " en la posicion ("+ enemigo.getPasarelaActual().getCoordenada().getAbscisa()
				 + "," + enemigo.getPasarelaActual().getCoordenada().getAbscisa() + ")");
				return this.pasarTurno();
			} catch (EnemigoFueraDeRango e) {
			} catch (EnemigoNoDaniable e) {
				throw new RuntimeException(e);
			}
		}

		//throw new EnemigosFueraDeRango();
		return this.pasarTurno();

	}

	public Desplegable pasarTurno() {
		return this;
	}
}

