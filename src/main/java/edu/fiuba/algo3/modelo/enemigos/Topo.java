package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.estados.EstadoVivo;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;

public class Topo extends Enemigo {

    public Topo(ParcelaDePasarela pasarela) {
        this.creditos = 1;
        this.danio = 2;
        this.estadoDeVida = new EstadoVivo(5);
        this.pasarelaActual = pasarela;
        this.trasladable = new BajoTierra(1, pasarela, 0);

    }

    public void atacar(Jugador jugador) {
        jugador.getDefensas().remove(0);
    }
}
