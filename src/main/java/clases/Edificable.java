package clases;

import Excepciones.TerrenoNoAptoParaConstruir;

public class Edificable implements Construible{
    @Override
    public void construir(Defensa defensaAConstruir,ParcelaDeTierra parcelaActual) throws TerrenoNoAptoParaConstruir {
        parcelaActual.setDefensa(defensaAConstruir);
    }
}
