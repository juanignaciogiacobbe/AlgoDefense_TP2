package clases;

import Excepciones.SinVidaRestante;

public class Vida {
    private int puntosActuales;
    public Vida(int puntosIniciales) {
        puntosActuales = puntosIniciales;
    }

    public int getVida() {
        return puntosActuales;
    }

    public void consumirPuntos(int puntosAConsumir) throws SinVidaRestante {
        puntosActuales -= puntosAConsumir;

        if(puntosActuales <= 0) {
            throw new SinVidaRestante();
        }
    }
}
