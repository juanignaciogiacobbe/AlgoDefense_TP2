package clases;

public class TorrePlateada extends Defensa {
    int tiempoConstruccion;
    int costoConstruccion;
    int danio;
    int rangoAtaque;
    int turnosDeVida;

    public TorrePlateada() {
        this.tiempoConstruccion = 2;
        this.rangoAtaque = 5;
        this.danio = 2;
        this.costoConstruccion = 20;
        this.turnosDeVida = 0;

    }


    public int getCostoConstruccion() {
        return costoConstruccion;
    }
    public void sumarturno() {
        this.turnosDeVida += 1;
    }

    public boolean estaconstruida() {
        return turnosDeVida >= tiempoConstruccion;
    }

    public boolean enemigoDentroDeRango(int distanciaAEnemigo) {
        return (distanciaAEnemigo <= rangoAtaque);
    }

}
