package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.estados.EstadoVida;
import edu.fiuba.algo3.modelo.estados.EstadoVivo;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;

public class Hormiga implements Enemigo {
	private EstadoVida estadoDeVida;
	private Atacante atacante;
	private int creditos;
	private Trasladable trasladable;

	public Hormiga(ParcelaDePasarela pasarela) {
		this.creditos = 1;
		this.estadoDeVida = new EstadoVivo(1);
		this.trasladable = new Caminante(1, pasarela);
		this.atacante = new AtacanteDeJugador(1);
	}
	@Override
	public void atacar(Jugador jugador) {
		atacante.atacar(jugador);
	}

	public void mover(Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
		this.trasladable = trasladable.moverse(mapa);

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
