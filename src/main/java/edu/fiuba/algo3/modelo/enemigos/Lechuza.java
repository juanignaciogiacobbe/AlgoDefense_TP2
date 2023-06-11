package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.estados.EstadoVivo;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;

public class Lechuza extends Enemigo {

    private Atacante atacante;

    public Lechuza(ParcelaDePasarela pasarela) {
        this.creditos = 1;
       // this.danio = 0;
        this.estadoDeVida = new EstadoVivo(5);
        this.pasarelaActual = pasarela;
        this.atacante = new DestructorDeDefensas();
    }

    @Override
    public void atacar(Jugador jugador) {
        this.atacante.atacar(jugador);
    }
}
