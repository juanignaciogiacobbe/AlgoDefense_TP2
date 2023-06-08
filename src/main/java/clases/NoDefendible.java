package clases;

import Excepciones.TerrenoNoAptoParDefender;
import clases.Enemigo;

public class NoDefendible implements Defendible{

    @Override
    public void defender() throws TerrenoNoAptoParDefender {
        throw new TerrenoNoAptoParDefender();
    }
}
