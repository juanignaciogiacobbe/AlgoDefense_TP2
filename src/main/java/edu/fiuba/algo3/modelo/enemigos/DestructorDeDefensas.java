package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.CustomLogger;
import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.juego.Jugador;

public class DestructorDeDefensas implements Atacante {
    private CustomLogger logger;

    public DestructorDeDefensas() {
        this.logger = CustomLogger.getInstance();
    }

    @Override
    public void atacar(Jugador jugador) throws DefensasVacias {
        try {
            jugador.destruirSiguienteDefensa();
        } catch (IndexOutOfBoundsException e) {
            //throw new DefensasVacias();
            logger.log("No se detruyo ninguna torre porque no hay");

        }
    }

    @Override
    public int getDanio() {
        return 0;
    }
}
