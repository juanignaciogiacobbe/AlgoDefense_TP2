package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.estados.EstadoVida;
import edu.fiuba.algo3.modelo.estados.EstadoVivo;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;

public class Hormiga implements Enemigo {
	private Atacante atacante;
	private int creditos;
	private Trasladable trasladable;

	private Daniable daniable;

	public Hormiga(ParcelaDePasarela pasarela) {
		this.creditos = 1;
		this.trasladable = new Caminante(1, pasarela);
		this.atacante = new AtacanteDeJugador(1);
		this.daniable = new Atacable(1);
	}

	@Override
	public void atacar(Jugador jugador) throws DefensasVacias {
		atacante.atacar(jugador);
	}

	public void mover(Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
		this.trasladable = trasladable.moverse(mapa);

	}

	public ParcelaDePasarela getPasarelaActual() {
		return (ParcelaDePasarela) trasladable.getPasarelaActual();
	}

	public void setPasarelaActual(ParcelaDePasarela pasarela) {
		this.trasladable.setPasarelaActual(pasarela);
	}

	public int getVida() {
		return daniable.getVida();
	}

	public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio) throws EnemigoFueraDeRango, EnemigoNoDaniable {
		this.daniable.recibirAtaque(parcelaDefensa, rangoAtaque, danio, this.trasladable.getPasarelaActual());
	}

	public int obtenerCreditos() {
		return this.creditos;
	}

	public int recolectarCreditos() {
		return this.creditos;
	}

	@Override
	public String toString() {
		return "H";
	}
}
