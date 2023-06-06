package clases;

import Excepciones.TerrenoNoAptoParDefender;
import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TerrenoNoAptoParaConstruir;

public abstract class Parcela {

    protected Coordenada coordenada;

    protected Mapa mapa;

    protected Construible construible;

    protected Movible movible;

    protected Defendible defendible;



    abstract boolean puedeMoverseAqui();

    public Coordenada getCoordenada() {
        return coordenada;
    }


    public abstract void construir(Defensa defensa) throws TerrenoNoAptoParaConstruir;

    public abstract  ParcelaDePasarela mover(int distancia,Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar;

    public abstract void defender() throws TerrenoNoAptoParDefender;


    public boolean estaADistancia(Coordenada coordenada, int distancia) {
        double difrenciaAbsica = this.coordenada.getAbscisa() - coordenada.getAbscisa();
        double difrenciaOrdenada = this.coordenada.getOrdenada() - coordenada.getOrdenada();
        return (difrenciaAbsica == 0 && Math.abs(difrenciaOrdenada) == distancia) || (Math.abs(difrenciaAbsica) == distancia && difrenciaOrdenada == 0);
    }


}

