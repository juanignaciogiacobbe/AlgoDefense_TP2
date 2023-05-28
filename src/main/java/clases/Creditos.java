package clases;

public class Creditos {
    private int puntosActuales;

    public Creditos(int puntosIniciales) {
        puntosActuales = puntosIniciales;
    }

    public int getCreditos() {
        return puntosActuales;
    }

    public void agregarCreditos(int creditosRecibidos){
        puntosActuales = puntosActuales + creditosRecibidos;
    }

    public boolean consumirPuntos(int puntosAConsumir) {
        puntosActuales -= puntosAConsumir;

        return (puntosActuales >= 0);
    }
}
