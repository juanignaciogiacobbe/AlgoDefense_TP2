package edu.fiuba.algo3.modelo.juego;

public class Creditos {
	private int puntosActuales;

	public Creditos(int puntosIniciales) {
		puntosActuales = puntosIniciales;
	}

	public int getCreditos() {
		return puntosActuales;
	}

	public void agregarCreditos(int creditosRecibidos) {
		puntosActuales = puntosActuales + creditosRecibidos;
	}

	public void consumirPuntos(int puntosAConsumir) throws CreditosInsuficientes {
		if (puntosAConsumir > puntosActuales) {
			throw new CreditosInsuficientes();
		}
		puntosActuales -= puntosAConsumir;

	}
}
