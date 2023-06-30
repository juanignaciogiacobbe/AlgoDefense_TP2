package edu.fiuba.algo3.modelo.defensas;

public class TorreBlanca extends Torre {

    private static final String NOMBRE_TORRE = "Torre Blanca";
    private static final int RANGO_ATAQUE = 3;
    private static final int DANIO = 1;
    private static final int COSTO_CONSTRUCCION = 10;
    private static final int TURNOS_RESTANTES_DESPLIEGUE = 1;
    private static final int DESPLEGABLE_NO_DESPLAGADO = 1;

    public TorreBlanca() {

        this.nombre = NOMBRE_TORRE;
        this.rangoAtaque = RANGO_ATAQUE;
        this.danio = DANIO;
        this.costoConstruccion = COSTO_CONSTRUCCION;
        this.turnosRestantesParaDespliegue = TURNOS_RESTANTES_DESPLIEGUE;
        this.desplegable = new NoDesplegado(DESPLEGABLE_NO_DESPLAGADO);

    }

    @Override
    public String toString() {
       return "TB" + desplegable.toString();
    }
}


