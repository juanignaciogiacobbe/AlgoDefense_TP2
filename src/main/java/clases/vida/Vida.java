package clases.vida;

public class Vida {
    private int puntosActuales;
    private EstadoVida estadoVida;

    public Vida(int puntosIniciales) {
        puntosActuales = puntosIniciales;
        this.estadoVida = new EstadoVivo();
    }

    public int getVida() {
        return puntosActuales;
    }

    public void consumirPuntos(int puntosAConsumir) {
        puntosActuales -= puntosAConsumir;

        if(puntosActuales <= 0) {
            estadoVida = new EstadoMuerto();
        }
    }

    public void comenzarTurno() {
        estadoVida.comenzarTurno();
    }
}
