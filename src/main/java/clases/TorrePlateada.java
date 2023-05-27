package clases;

public class TorrePlateada extends Defensa {
    private int tiempoConstruccion;
    private int costoConstruccion;
    private int danio;
    private int rangoAtaque;

    private int turnosDeVida;

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
