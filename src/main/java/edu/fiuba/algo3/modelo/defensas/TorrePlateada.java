package edu.fiuba.algo3.modelo.defensas;

public class TorrePlateada extends Defensa {

	public TorrePlateada() {
		this.nombre = "Torre Plateada";
		this.rangoAtaque = 5;
		this.danio = 2;
		this.costoConstruccion = 20;
		this.turnosRestantesParaDespliegue = 2;
		this.desplegable = new NoDesplegado(2);
	}

	@Override
	public String getNombre() {
		return "TorrePlateada";
	}
}
