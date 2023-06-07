package clases;

import Excepciones.TerrenoNoAptoParDefender;

import java.util.List;

public interface Defendible {
    public void defender(List<Enemigo> enemigos, Parcela parcelaDefensa) throws TerrenoNoAptoParDefender;
}
