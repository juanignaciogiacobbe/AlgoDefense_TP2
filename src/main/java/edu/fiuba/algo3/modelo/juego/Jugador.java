package edu.fiuba.algo3.modelo.juego;

import edu.fiuba.algo3.modelo.CustomLogger;
import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDeTierra;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;
import edu.fiuba.algo3.modelo.estados.EstadoVida;
import edu.fiuba.algo3.modelo.estados.EstadoVivo;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.defensas.TrampaArenosa;

import java.util.LinkedList;
import java.util.List;

public class Jugador {
	private final Creditos creditos;
	private final String nombre;
	private List<ParcelaDeTierra> defensas = new LinkedList<>();
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

	public List<ParcelaDeTierra> getDefensas() {
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

	public void construir(Torre defensa, Parcela parcela) throws CreditosInsuficientes, TerrenoNoAptoParaConstruir {
		this.creditos.consumirPuntos(defensa.getCostoConstruccion());
		try {
			parcela.construir(defensa);
			defensas.add((ParcelaDeTierra) parcela);
			logger.log("El jugador construyo una " + defensa.getNombre() + " en la posicion (" + parcela.getCoordenada().getAbscisa()
					+ "," + parcela.getCoordenada().getOrdenada() + ")" );
		} catch (TerrenoNoAptoParaConstruir e) {
			this.creditos.agregarCreditos(defensa.getCostoConstruccion());
			throw new TerrenoNoAptoParaConstruir();
		}
	}

	public void construir(TrampaArenosa defensa, Parcela parcela) throws CreditosInsuficientes, TerrenoNoAptoParaConstruir {
		this.creditos.consumirPuntos(defensa.getCostoConstruccion());
		try {
			parcela.construir(defensa);
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


	public void setDefensa(List<ParcelaDeTierra> nuevaLista) {
		this.defensas = nuevaLista;
	}

	public void destruirSiguienteDefensa() {
		(this.defensas.get(0)).destruirDefensa();
		this.defensas.remove(0);
	}

	public void atacarEnemigos(List<Enemigo> enemigos) {
		for (ParcelaDeTierra parcela: this.defensas) {
			parcela.atacar(enemigos);
		}

		for (Enemigo enemigo: enemigos) {
			this.creditos.agregarCreditos(enemigo.recolectarCreditos());
		}
	}

	public int cantidadDefensas() {
		return this.defensas.size();
	}
}




