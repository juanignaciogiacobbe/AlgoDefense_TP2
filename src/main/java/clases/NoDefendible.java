package clases;

import Excepciones.TerrenoNoAptoParDefender;
import clases.Enemigo;

import java.util.List;

public class NoDefendible implements Defendible{

    @Override
    public void defender(List<Enemigo> enemigos, Parcela parcelaDefensa) throws TerrenoNoAptoParDefender {
        throw new TerrenoNoAptoParDefender();
    }
}
