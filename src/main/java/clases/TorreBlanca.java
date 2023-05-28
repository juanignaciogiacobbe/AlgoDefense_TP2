package clases;

public class TorreBlanca extends Defensa {

    public TorreBlanca() {
        this.rangoAtaque = 3;
        this.danio = 1;
        this.costoConstruccion = 10;
        this.turnosRestantesParaDespliegue = 1;

    }
    public boolean enemigoDentroDeRango(int distanciaAEnemigo) {
        return (distanciaAEnemigo <= rangoAtaque);
    }
}


