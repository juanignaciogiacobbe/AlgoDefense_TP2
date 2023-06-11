package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.estados.EstadoVivo;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;

import java.util.Random;

public class Arania extends Enemigo {
	public Arania(ParcelaDePasarela pasarela) {
		this.creditos = obtenerCreditosRandom();
		this.velocidad = 2;
		this.danio = 2;
		this.estadoDeVida = new EstadoVivo(2);
		this.pasarelaActual = pasarela;
	}


	private int obtenerCreditosRandom() {
		Random random = new Random();
		return random.nextInt(11);
	}

	@Override
	public void atacar(Jugador jugador) {
		jugador.recibirdanio(this.getDanio());
	}
}
