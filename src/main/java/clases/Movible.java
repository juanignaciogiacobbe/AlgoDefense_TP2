package clases;

import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TerrenoNoAptoParaConstruir;

public interface Movible {
    ParcelaDePasarela mover(Parcela parcela,int distancia,Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar;

    void moverseA() throws TerrenoNoAptoParaCaminar;

}
