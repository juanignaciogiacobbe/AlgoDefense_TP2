package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.estados.EstadoVivo;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;

import java.util.Random;

public class Arania extends Enemigo {

	private Atacante atacante;
	public Arania(ParcelaDePasarela pasarela) {
		this.creditos = obtenerCreditosRandom();
		//this.danio = 2;
		this.estadoDeVida = new EstadoVivo(2);
		this.pasarelaActual = pasarela;
		this.trasladable = new Caminante(2, pasarela);
		this.atacante = new AtacanteDeJugador(2);
	}


	private int obtenerCreditosRandom() {
		Random random = new Random();
		return random.nextInt(11);
	}

	@Override
	public void atacar(Jugador jugador) {
		this.atacante.atacar(jugador);
	}
}
