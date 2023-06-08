package clases;

import Excepciones.TerrenoNoAptoParaConstruir;

public class Desplazable implements Movible{

    @Override
    public ParcelaDePasarela mover(Parcela parcela, int distancia,Mapa mapa) {
        //return mapa.hallarParcelaVecinaCorrectaADistancia(parcela, distancia);
        return mapa.obtenerPasarelasEnRango(parcela,distancia);
    }
}
