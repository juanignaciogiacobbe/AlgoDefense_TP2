package clases;

import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TerrenoNoAptoParaConstruir;

public interface Turno {

    void ejecutarTurno() throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir;
}
