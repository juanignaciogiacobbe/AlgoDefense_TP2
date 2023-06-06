package clases;

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

    public boolean puedeDefender(){
        return false;
    }

    public ParcelaDePasarela darSiguientePasarela() {
        return mapa.darSiguientePasarela(this);
    }

    public ParcelaDePasarela mover(int distancia,Mapa mapa) throws TerrenoNoAptoParaConstruir {
        return movible.mover(this,distancia,mapa);
    }

    public void construir(Defensa defensa) throws TerrenoNoAptoParaConstruir {
        construible.construir();
    }



}
