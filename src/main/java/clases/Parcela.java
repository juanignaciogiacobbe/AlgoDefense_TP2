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

    protected Defendible defendible;



    abstract boolean puedeMoverseAqui();

    public Coordenada getCoordenada() {
        return coordenada;
    }


    public abstract void construir(Defensa defensa) throws TerrenoNoAptoParaConstruir;

    public abstract  ParcelaDePasarela mover(int distancia,Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar;

    public void defender(List<Enemigo> enemigos) throws TerrenoNoAptoParDefender {
        this.defendible.defender(enemigos, this);
    }


    public boolean estaADistancia(Coordenada coordenada, int distancia) {
        double difrenciaAbsica = this.coordenada.getAbscisa() - coordenada.getAbscisa();
        double difrenciaOrdenada = this.coordenada.getOrdenada() - coordenada.getOrdenada();
        return (difrenciaAbsica == 0 && Math.abs(difrenciaOrdenada) == distancia) || (Math.abs(difrenciaAbsica) == distancia && difrenciaOrdenada == 0);
    }

    public boolean estaEnRango(Parcela parcelaDefensa, int rango) {
        return this.getCoordenada().estaDentroDelRango(parcelaDefensa.getCoordenada(), rango);
    }


}

