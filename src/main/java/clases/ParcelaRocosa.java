package clases;

import Excepciones.TerrenoNoAptoParaConstruir;

public class ParcelaRocosa extends Parcela {
    public ParcelaRocosa(int abscisa, int ordenada) {

        this.coordenada = new Coordenada(abscisa, ordenada)
        ;
    }
    public boolean puedoConstruir(Defensa defensa) {
        return false;
    }

    public boolean puedeMoverseAqui() {
        return false;
    }

    public boolean puedeDefender(){
        return false;
    }

    public void construir(Defensa defensa) throws TerrenoNoAptoParaConstruir {
        throw new TerrenoNoAptoParaConstruir();
    }
}
