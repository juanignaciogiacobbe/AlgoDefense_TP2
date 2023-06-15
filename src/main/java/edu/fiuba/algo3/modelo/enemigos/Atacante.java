package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.juego.Jugador;

public interface Atacante {

    public void atacar(Jugador jugador) throws DefensasVacias;

    public int getDanio();
}
