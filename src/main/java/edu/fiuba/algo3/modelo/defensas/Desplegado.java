package edu.fiuba.algo3.modelo.defensas;

import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.EnemigoFueraDeRango;
import edu.fiuba.algo3.modelo.enemigos.EnemigosFueraDeRango;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

import java.util.List;

public class Desplegado implements Desplegable {
	public void atacar(List<Enemigo> enemigos, Parcela parcelaDefensa, int rangoAtaque, int danio) throws EnemigosFueraDeRango {
		for (Enemigo enemigo : enemigos) {
			try {
				enemigo.recibirAtaque(parcelaDefensa, rangoAtaque, danio);
				return;
			} catch (EnemigoFueraDeRango e) {
			}
		}
		throw new EnemigosFueraDeRango();
	}


	public Desplegable pasarTurno() {
		return this;
	}
}

