package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.estados.EstadoVida;
import edu.fiuba.algo3.modelo.estados.EstadoVivo;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;

import java.util.Random;

public class Arania implements Enemigo {
	private EstadoVida estadoDeVida;
	private Atacante atacante;
	private int creditos;
	private Trasladable trasladable;

	public Arania(ParcelaDePasarela pasarela) {
		this.creditos = obtenerCreditosRandom();
		this.estadoDeVida = new EstadoVivo(2);
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

	public void recibirDanio(int puntosARecibir) {
		this.estadoDeVida = this.estadoDeVida.recibirDanio(puntosARecibir);
	}

	public boolean puedeMoverseA(Parcela parcela) {
		return (parcela.puedeMoverseAqui());
	}

	public ParcelaDePasarela getPasarelaActual() {
		return (trasladable.getPasarelaActual());
	}


	public void setPasarelaActual(ParcelaDePasarela pasarela) {
		this.trasladable.setPasarelaActual(pasarela);
	}

	public int getVida() {
		return estadoDeVida.getVida();
	}

	public void mover(Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
		this.trasladable = trasladable.moverse(mapa);

	}

	public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio) throws EnemigoFueraDeRango {
		if (!this.trasladable.getPasarelaActual().estaEnRango(parcelaDefensa, rangoAtaque)) {
			throw new EnemigoFueraDeRango();
		}
		this.recibirDanio(danio);
	}

	public int obtenerCreditos() {
		return this.creditos;
	}

	public int recolectarCreditos() {
		return this.estadoDeVida.recolectarCreditos(this.creditos);
	}
}
