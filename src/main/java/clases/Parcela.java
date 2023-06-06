package clases;

import Excepciones.TerrenoNoAptoParaConstruir;

import java.util.ArrayList;
import java.util.List;

abstract class Parcela {

    protected Coordenada coordenada;

    protected Mapa mapa;
    protected Construible construible;

    protected Movible movible;

    protected Defendible defendible;


    abstract boolean puedoConstruir(Defensa defensa);

    abstract boolean puedeMoverseAqui();

    public Coordenada getCoordenada() {
        return coordenada;
    }


    abstract boolean puedeDefender();

    public abstract void construir(Defensa defensa) throws TerrenoNoAptoParaConstruir;


    public boolean estaADistancia(Coordenada coordenada,int distancia) {
        double difrenciaAbsica = this.coordenada.getAbscisa() - coordenada.getAbscisa();
        double difrenciaOrdenada = this.coordenada.getOrdenada() - coordenada.getOrdenada();
        return  (difrenciaAbsica == 0 && Math.abs(difrenciaOrdenada) == distancia) || (Math.abs(difrenciaAbsica) == distancia && difrenciaOrdenada == 0);
    }
}

