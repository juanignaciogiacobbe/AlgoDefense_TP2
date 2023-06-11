package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;
import edu.fiuba.algo3.modelo.estados.EstadoVida;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;

public abstract class Enemigo {
	protected EstadoVida estadoDeVida;

	protected int creditos;

	//protected int danio;

	protected ParcelaDePasarela pasarelaActual;

	protected Trasladable trasladable;

	public void recibirDanio(int puntosARecibir) {

		this.estadoDeVida = this.estadoDeVida.recibirDanio(puntosARecibir);
	}

	public boolean puedeMoverseA(Parcela parcela) {
		return (parcela.puedeMoverseAqui());
	}

	//public int getDanio() {
	//	return this.danio;
	//}

	public ParcelaDePasarela getPasarelaActual() {
		return (trasladable.getPasarelaActual());
	}

	public void setPasarelaActual(ParcelaDePasarela pasarela) {
		//this.pasarelaActual = pasarela;
		this.trasladable.setPasarelaActual(pasarela);
	}

	public int getVida() {
		return estadoDeVida.getVida();
	}


	public void mover(Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
		//ParcelaDePasarela parcelaaMover = this.pasarelaActual.mover(this.getVelocidad(), mapa);
		//this.setPasarelaActual(parcelaaMover);
		this.trasladable = trasladable.moverse(mapa);

	}

	public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio) throws EnemigoFueraDeRango {
		if (!this.pasarelaActual.estaEnRango(parcelaDefensa, rangoAtaque)) {
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

	public abstract void atacar(Jugador jugador);
}
