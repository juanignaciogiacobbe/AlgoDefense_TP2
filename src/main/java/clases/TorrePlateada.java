package clases;

public class TorrePlateada extends Defensa {

    public TorrePlateada() {
        this.rangoAtaque = 5;
        this.danio = 2;
        this.costoConstruccion = 20;
        this.turnosRestantesParaDespliegue = 2;
    }
    public boolean enemigoDentroDeRango(int distanciaAEnemigo) {
        return (distanciaAEnemigo <= rangoAtaque);
    }

}