package clases;

import Excepciones.TerrenoNoAptoParaConstruir;

abstract class ParcelaDePasarela extends Parcela{

    public boolean puedoConstruir(Defensa defensa) {
        return false;
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

    public ParcelaDePasarela mover(int distancia,Mapa mapa) {
        return mapa.hallarParcelaVecinaCorrectaADistancia(this, distancia);
    }

    public void construir(Defensa defensa) throws TerrenoNoAptoParaConstruir {
        throw new TerrenoNoAptoParaConstruir();
    }



}
