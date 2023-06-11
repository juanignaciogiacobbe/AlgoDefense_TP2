package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.estados.EstadoVivo;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;

public class Hormiga extends Enemigo {

	private Atacante atacante;

	public Hormiga(ParcelaDePasarela pasarela) {
		this.creditos = 1;
		//this.danio = 1;
		this.estadoDeVida = new EstadoVivo(1);
		this.pasarelaActual = pasarela;
		this.trasladable = new Caminante(1, pasarela);
		this.atacante = new AtacanteDeJugador(1);
	}

	@Override
	public int obtenerCreditos() {
		return super.obtenerCreditos();
	}

	@Override
	public void atacar(Jugador jugador) {
		atacante.atacar(jugador);

	}

	public void mover(Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
		//ParcelaDePasarela parcelaaMover = this.pasarelaActual.mover(this.getVelocidad(), mapa);
		//this.setPasarelaActual(parcelaaMover);
		this.trasladable = trasladable.moverse(mapa);

	}
}
