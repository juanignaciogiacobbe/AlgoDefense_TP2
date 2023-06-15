package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.CustomLogger;
import edu.fiuba.algo3.modelo.defensas.Defensa;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDeTierra;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;
import edu.fiuba.algo3.modelo.estados.EstadoVida;
import edu.fiuba.algo3.modelo.estados.EstadoVivo;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.TrampaArenosa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Jugador {
	private final Creditos creditos;
	private final String nombre;
	private List<Parcela> defensas = new LinkedList<>();
	private EstadoVida estadoDeVida;

	private CustomLogger logger;



	public Jugador(String nombre) throws NombreInvalido {
		this.creditos = new Creditos(100);
		if (nombre.length() < 6) {
			throw new NombreInvalido();
		}
		this.nombre = nombre;
		this.estadoDeVida = new EstadoVivo(20);
		this.logger = CustomLogger.getInstance();
	}

	public List<Parcela> getDefensas() {
		return defensas;
	}

	public int getCreditos() {
		return this.creditos.getCreditos();
	}

	public int getVida() {
		return this.estadoDeVida.getVida();
	}

	public String getNombre() {
		return this.nombre;
	}

	public void agregarCreditos(int creditosRecibidos) {
		this.creditos.agregarCreditos(creditosRecibidos);
	}

	public void construir(Defensa defensa, Parcela parcela) throws CreditosInsuficientes, TerrenoNoAptoParaConstruir {
		this.creditos.consumirPuntos(defensa.getCostoConstruccion());
		try {
			parcela.construir(defensa);
			if (! (defensa instanceof TrampaArenosa)) {
				defensas.add(parcela);
			}
			logger.log("El jugador construyo una " + defensa.getNombre() + " en la posicion (" + parcela.getCoordenada().getAbscisa()
					+ "," + parcela.getCoordenada().getOrdenada() + ")" );
		} catch (TerrenoNoAptoParaConstruir e) {
			this.creditos.agregarCreditos(defensa.getCostoConstruccion());
			throw new TerrenoNoAptoParaConstruir();
		}
	}

	public void recibirdanio(int danio) {
		this.estadoDeVida = this.estadoDeVida.recibirDanio(danio);
	}

	public boolean estaMuerto() {
		return (estadoDeVida.getVida() <= 0);
	}

	public void agregarDefensa(ParcelaDeTierra parcelaSet) {
		defensas.add(parcelaSet);
	}

	public void setDefensa(List<Parcela> nuevaLista) {
		this.defensas = nuevaLista;
	}
}




