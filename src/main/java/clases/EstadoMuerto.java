package clases;

import Excepciones.SinVidaRestante;

import java.util.List;

public class EstadoMuerto extends Estado {

    public EstadoMuerto() {
        this.vida = 0;
    }
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
}