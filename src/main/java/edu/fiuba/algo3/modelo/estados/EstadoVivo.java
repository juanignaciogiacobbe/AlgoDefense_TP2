package edu.fiuba.algo3.modelo.estados;


import edu.fiuba.algo3.modelo.CustomLogger;

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
			return new EstadoMuerto();

		}
		return this;
	}

	public int recolectarCreditos(int creditos) {
		return 0;
	}


}
