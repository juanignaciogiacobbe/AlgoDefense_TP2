package edu.fiuba.algo3.modelo.defensas;

public class TorreBlanca extends Torre {

	public TorreBlanca() {
		this.nombre = "Torre Blanca";
		this.rangoAtaque = 3;
		this.danio = 1;
		this.costoConstruccion = 10;
		this.turnosRestantesParaDespliegue = 1;
		this.desplegable = new NoDesplegado(1);

	}
}


