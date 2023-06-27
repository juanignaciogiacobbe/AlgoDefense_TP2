package edu.fiuba.algo3.modelo.defensas;

public class TorrePlateada extends Torre {

	private static final String NOMBRE_TORRE = "Torre Plateada";
	private static final int RANGO_ATAQUE = 5;
	private static final int DANIO = 2;
	private static final int COSTO_CONSTRUCCION = 20;
	private static final int TURNOS_RESTANTES_DESPLIEGUE = 2;
	private static final int DESPLEGABLE_NO_DESPLAGADO = 2;

	public TorrePlateada() {

		this.nombre = NOMBRE_TORRE;
		this.rangoAtaque = RANGO_ATAQUE;
		this.danio = DANIO;
		this.costoConstruccion = COSTO_CONSTRUCCION;
		this.turnosRestantesParaDespliegue = TURNOS_RESTANTES_DESPLIEGUE;
		this.desplegable = new NoDesplegado(DESPLEGABLE_NO_DESPLAGADO);
	}

	@Override
	public String toString() {
		return "TP" + desplegable.toString();
}
}
