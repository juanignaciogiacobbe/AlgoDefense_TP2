package clases;

import Excepciones.SinVidaRestante;
import clases.CustomLogger;
import clases.EstadoVida;

public class EstadoMuerto implements EstadoVida {
    private final CustomLogger logger = CustomLogger.getInstance();

    private int vida;

    public EstadoMuerto() {
        this.vida = 0;
    }


    public int getVida() {
        return vida;
    }

    @Override
    public void recibirDanio(int danioARecibir) throws SinVidaRestante {
    }

    public int recolectarCreditos(int creditos) {
        return creditos;
    }
}