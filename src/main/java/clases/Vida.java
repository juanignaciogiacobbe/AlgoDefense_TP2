package clases;

public class Vida {
    private int puntosActuales;
    public Vida(int puntosIniciales) {
        puntosActuales = puntosIniciales;
    }

    public int getVida() {
        return puntosActuales;
    }

    public void consumirPuntos(int puntosAConsumir) {
        puntosActuales -= puntosAConsumir;
    }
}
