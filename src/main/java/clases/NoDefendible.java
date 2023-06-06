package clases;

import Excepciones.TerrenoNoAptoParDefender;
import Excepciones.TerrenoNoAptoParaConstruir;

public class NoDefendible implements Defendible{

    @Override
    public void defender() throws TerrenoNoAptoParDefender {
        throw new TerrenoNoAptoParDefender();
    }
}
