package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.estados.EstadoVivo;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;

public class Lechuza extends Enemigo {
        public Lechuza(ParcelaDePasarela pasarela) {
            this.creditos = 1;
            this.velocidad = 5;
            this.danio = 0;
            this.estadoDeVida = new EstadoVivo(5);
            this.pasarelaActual = pasarela;
        }

    @Override
    public int atacar(Jugador jugador) {
        return 1;
    }
}
