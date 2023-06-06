package clases;

import Excepciones.TerrenoNoAptoParaConstruir;

public class NoConstruible implements Construible{
    @Override
    public void construir(Defensa defensaAConstruir,ParcelaDeTierra parcela) throws TerrenoNoAptoParaConstruir {
        throw new TerrenoNoAptoParaConstruir();
    }
}
