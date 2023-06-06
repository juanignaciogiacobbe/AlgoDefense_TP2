package clases;

import Excepciones.TerrenoNoAptoParDefender;

public interface Defendible {
    public void defender() throws TerrenoNoAptoParDefender;
}
