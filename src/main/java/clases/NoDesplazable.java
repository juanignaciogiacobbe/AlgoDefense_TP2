package clases;

import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TerrenoNoAptoParaConstruir;

public class NoDesplazable implements Movible{

    @Override
    public ParcelaDePasarela mover(Parcela parcela, int distancia, Mapa mapa) throws TerrenoNoAptoParaCaminar {
        throw new TerrenoNoAptoParaCaminar();
    }

    @Override
    public void moverseA() throws TerrenoNoAptoParaCaminar {
        throw new TerrenoNoAptoParaCaminar();
    }
}
