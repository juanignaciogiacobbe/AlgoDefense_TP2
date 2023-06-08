package clases;

import Excepciones.TerrenoNoAptoParDefender;
import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TerrenoNoAptoParaConstruir;

import java.util.List;

public abstract class Parcela {

    protected Coordenada coordenada;

    protected Mapa mapa;
    protected Construible construible;
    protected Movible movible;



    abstract boolean puedeMoverseAqui();

    public Coordenada getCoordenada() {
        return coordenada;
    }


    public abstract void construir(Defensa defensa) throws TerrenoNoAptoParaConstruir;

    public abstract  ParcelaDePasarela mover(int distancia,Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar;



    public boolean estaEnRango(Parcela parcelaDefensa, int rango) {
        return this.getCoordenada().estaDentroDelRango(parcelaDefensa.getCoordenada(), rango);
    }


}

