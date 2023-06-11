package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.estados.EstadoVivo;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;

public class Hormiga extends Enemigo {

	public Hormiga(ParcelaDePasarela pasarela) {
		this.creditos = 1;
		this.velocidad = 1;
		this.danio = 1;
		this.estadoDeVida = new EstadoVivo(1);
		this.pasarelaActual = pasarela;
	}

	@Override
	public int obtenerCreditos() {
		return super.obtenerCreditos();
	}
}
