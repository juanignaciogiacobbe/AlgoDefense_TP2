package clases;

import Excepciones.SinVidaRestante;

import java.util.List;

public abstract class Estado {

    protected int vida;

    public abstract void recibirDanio(int danioARecibir) throws SinVidaRestante;

    public abstract void actualizarLista(List<Enemigo> lista);

    public abstract int recolectarCreditos(int sumaActual, int creditosEnemigo);

    public boolean tieneVidaIgualA(int vidaEsperada) {
        return (this.vida == vidaEsperada);
    }
}