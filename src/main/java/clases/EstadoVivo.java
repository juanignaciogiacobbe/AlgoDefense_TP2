package clases;

import Excepciones.SinVidaRestante;
import clases.Enemigo;

import java.util.List;

public class EstadoVivo extends Estado {

    public EstadoVivo(int vidaInicial) {
        this.vida = vidaInicial;
    }

    @Override
    public void recibirDanio(int danioARecibir) throws SinVidaRestante {
        this.vida -= danioARecibir;
        if (this.vida <= 0) {
            throw new SinVidaRestante();
        }
    }

    @Override
    public void actualizarLista(List<Enemigo> lista) {
    }

    @Override
    public int recolectarCreditos(int sumaActual, int creditosEnemigo) {
        return sumaActual;
    }
}
