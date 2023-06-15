package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.juego.Jugador;

public class DestructorDeDefensas implements Atacante {

    @Override
    public void atacar(Jugador jugador) throws DefensasVacias {
        try {
            jugador.getDefensas().remove(0);
        } catch (IndexOutOfBoundsException e) {
            throw new DefensasVacias();

        }
    }
}
