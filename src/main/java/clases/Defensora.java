package clases;

import Excepciones.TerrenoNoAptoParDefender;
import clases.Enemigo;

import java.util.List;

public class Defensora implements Defendible {

    private Defensa defensa;

    public Defensora(Defensa defensa) {
        this.defensa = defensa;
    }

    @Override
    public void defender(List<Enemigo> enemigos, Parcela parcelaDefensa) throws TerrenoNoAptoParDefender {
        this.defensa.atacar(enemigos, parcelaDefensa);
    }

}
