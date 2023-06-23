package edu.fiuba.algo3.modelo.estados;


import edu.fiuba.algo3.modelo.CustomLogger;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;

import java.util.List;

public class EstadoVivo implements EstadoVida {
	private final CustomLogger logger = CustomLogger.getInstance();

	private int vida;

	public EstadoVivo(int vidaInicial) {
		this.vida = vidaInicial;
	}


	public int getVida() {
		return vida;
	}

	@Override
	public EstadoVida recibirDanio(int danioARecibir) {
		this.vida -= danioARecibir;
		if (this.vida <= 0) {
			logger.log("El enemigo ha muerto");
			return new EstadoMuerto();

		}
		return this;
	}

	public int recolectarCreditos(int creditos) {
		return 0;
	}

	@Override
	public List<Enemigo> actualizarLista(List<Enemigo> enemigos, Enemigo enemigo) {
        return enemigos;
    }


}
