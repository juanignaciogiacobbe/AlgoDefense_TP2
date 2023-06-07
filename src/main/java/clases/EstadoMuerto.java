package clases;

import Excepciones.SinVidaRestante;

import java.util.List;

public class EstadoMuerto implements EstadoVida {


    @Override
    public void recibirDanio(int danioARecibir) throws SinVidaRestante {

    }

    @Override
    public void actualizarLista(List<Enemigo> lista) {

    }

    @Override
    public int recolectarCreditos(int sumaActual, int creditosEnemigo) {
        return sumaActual + creditosEnemigo;
    }

    @Override
    public int getVida() {
        return 0;
    }
}
