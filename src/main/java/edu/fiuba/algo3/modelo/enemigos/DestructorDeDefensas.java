package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.juego.Jugador;

public class DestructorDeDefensas implements Atacante {

    @Override
    public void atacar(Jugador jugador) {
        jugador.getDefensas().remove(0);
    }
}
