package clases;

import Excepciones.TerrenoNoAptoParDefender;
import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TerrenoNoAptoParaConstruir;

public class ParcelaRocosa extends Parcela {
    public ParcelaRocosa(int abscisa, int ordenada) {

        this.coordenada = new Coordenada(abscisa, ordenada);
        construible = new NoConstruible();
        movible = new NoDesplazable();
        defendible = new NoDefendible();
    }

    public boolean puedeMoverseAqui() {
        return false;
    }

    @Override
    public void construir(Defensa defensa) throws TerrenoNoAptoParaConstruir {
        construible.construir(defensa, null);
    }


    public ParcelaDePasarela mover(int distancia,Mapa mapa) throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir {
        return(movible.mover(this,distancia,mapa));
    }

    public void construir() throws TerrenoNoAptoParaConstruir {
        construible.construir(null,null);
    }
}
