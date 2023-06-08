package clases;

import Excepciones.TerrenoNoAptoParDefender;
import clases.Enemigo;

public interface Defendible {
    public void defender() throws TerrenoNoAptoParDefender;
}
