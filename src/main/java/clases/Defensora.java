package clases;

import Excepciones.TerrenoNoAptoParDefender;

public class Defensora implements Defendible {
    @Override
    public void defender() throws TerrenoNoAptoParDefender {
        throw new TerrenoNoAptoParDefender();
    }

}
