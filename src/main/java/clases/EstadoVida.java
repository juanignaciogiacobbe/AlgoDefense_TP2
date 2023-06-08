package clases;

import Excepciones.SinVidaRestante;

import java.util.List;

public interface EstadoVida {

    public void recibirDanio(int danioARecibir) throws SinVidaRestante;

    public int getVida();

    int recolectarCreditos(int creditos);

}
