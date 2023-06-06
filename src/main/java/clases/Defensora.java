package clases;

import Excepciones.TerrenoNoAptoParDefender;

public class Defensora implements Defendible {

    private Defensa defensa;

    public Defensora(Defensa defensa) {
        this.defensa = defensa;
    }

    @Override
    public void defender() throws TerrenoNoAptoParDefender {
        //defensa.atacarA();
    }

}
