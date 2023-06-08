package clases;


import Excepciones.TerrenoNoAptoParaCaminar;

public class Desplazable implements Movible{

    @Override
    public ParcelaDePasarela mover(Parcela parcela, int distancia,Mapa mapa) {
        return mapa.obtenerPasarelasEnRango(parcela,distancia);
    }

    @Override
    public void moverseA() throws TerrenoNoAptoParaCaminar {

    }
}
