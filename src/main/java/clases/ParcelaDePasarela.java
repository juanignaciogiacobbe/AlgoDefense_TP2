package clases;

import Excepciones.TerrenoNoAptoParDefender;
import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TerrenoNoAptoParaConstruir;

abstract class ParcelaDePasarela extends Parcela{

    public ParcelaDePasarela() {
        construible = new NoConstruible();
        movible = new Desplazable();
        defendible = new NoDefendible();
    }

    public boolean puedeMoverseAqui() {
        return true;
    }

    public ParcelaDePasarela mover(int distancia,Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
        return movible.mover(this,distancia,mapa);
    }

    public void construir(Defensa defensaAConstuir) throws TerrenoNoAptoParaConstruir {
        construible.construir(defensaAConstuir,null);

    }

    public void defender() throws TerrenoNoAptoParDefender {
        defendible.defender();
    }



}
