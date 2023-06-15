package edu.fiuba.algo3.modelo.mapa;

import java.util.Objects;

public class Coordenada {
	final int abscisa;
	final int ordenada;

	public Coordenada(int UnaAbscisa, int unaOrdenada) {
		this.abscisa = UnaAbscisa;
		this.ordenada = unaOrdenada;
	}

	public int getAbscisa() {
		return this.abscisa;
	}

	public int getOrdenada() {
		return this.ordenada;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Coordenada that = (Coordenada) o;
		return abscisa == that.abscisa && ordenada == that.ordenada;
	}

	@Override
	public int hashCode() {
		return Objects.hash(abscisa, ordenada);
	}

	public int distanciaHacia(Coordenada coordenada) {
		int x1 = this.getAbscisa();
		int y1 = this.getOrdenada();
		int x2 = coordenada.getAbscisa();
		int y2 = coordenada.getOrdenada();

		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}

	public boolean estaDentroDelRango(Coordenada coordenada, int rango) {
		return distanciaHacia(coordenada) <= rango;
	}
	@Override
	public String toString() {
		return "(" + abscisa + ", " + ordenada + ")";
	}
}
