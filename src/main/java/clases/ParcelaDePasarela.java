package clases;

import Excepciones.TerrenoNoAptoParDefender;
import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TerrenoNoAptoParaConstruir;

abstract class ParcelaDePasarela extends Parcela{

    public ParcelaDePasarela() {
        construible = new NoConstruible();
        movible = new Desplazable();
    }

    public boolean puedeMoverseAqui() {
        return true;
    }

    public ParcelaDePasarela mover(int distancia,Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
        return mapa.obtenerPasarelasEnRango(this,distancia);
    }

    public void construir(Defensa defensaAConstuir) throws TerrenoNoAptoParaConstruir {
        construible.construir(defensaAConstuir,null);

    }

}
