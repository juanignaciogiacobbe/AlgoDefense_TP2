package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.CustomLogger;
import edu.fiuba.algo3.modelo.juego.Jugador;

public class AtacanteDeJugador implements Atacante {

    private int danio;


    public AtacanteDeJugador(int danio) {
        this.danio = danio;

    }

    @Override
    public void atacar(Jugador jugador) {
        jugador.recibirdanio(this.danio);
    }

    public int getDanio() {
        return danio;
    }
}
