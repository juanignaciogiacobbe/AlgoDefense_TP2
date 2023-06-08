package clases;


public class Desplazable implements Movible{

    @Override
    public ParcelaDePasarela mover(Parcela parcela, int distancia,Mapa mapa) {
        return mapa.obtenerPasarelasEnRango(parcela,distancia);
    }



}
