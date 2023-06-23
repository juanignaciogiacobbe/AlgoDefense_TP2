package edu.fiuba.algo3.modelo.estados;

import edu.fiuba.algo3.modelo.CustomLogger;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;

import java.util.ArrayList;
import java.util.List;

public class EstadoMuerto implements EstadoVida {
	private final CustomLogger logger = CustomLogger.getInstance();

	private final int vida;

	public EstadoMuerto() {
		this.vida = 0;
	}


	public int getVida() {
		return vida;
	}

	@Override
	public EstadoVida recibirDanio(int danioARecibir) {
		return this;
	}

	public int recolectarCreditos(int creditos) {
		return creditos;
	}

	public List<Enemigo> actualizarLista(List<Enemigo> enemigos, Enemigo enemigo) {
		List<Enemigo> enemigosActualizado = new ArrayList<>(enemigos);
		enemigosActualizado.remove(enemigo);
		return enemigosActualizado;
	}
}
