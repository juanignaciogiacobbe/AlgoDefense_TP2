package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.convertidor.*;
import edu.fiuba.algo3.modelo.defensas.*;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.EnemigosFueraDeRango;
import edu.fiuba.algo3.modelo.juego.CreditosInsuficientes;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.NombreInvalido;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.*;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class AlgoDefense implements Observable {

	private final Mapa mapa;
	private final Map<Integer, List<Enemigo>> enemigosTurno;

	private Jugador jugador1;
	private List<Enemigo> enemigos;
	private List<ParcelaDeTierra> defensas ;
	private final ArrayList<Observer> observers = new ArrayList<>();
	private CustomLogger logger;

	private int turno;

	private int turnosTotales;

	public AlgoDefense(Mapa mapa, Map<Integer, List<Enemigo>> enemigosTurno, List<Enemigo> enemigos) {
		this.mapa = mapa;
		this.enemigosTurno = enemigosTurno;
		this.enemigos = enemigos;
		this.turno = 0;
	}

	public AlgoDefense() throws IOException, ParseException, FormatoJSONInvalidoException {
		File file = new File("src/resources/mapa.json");
		FileReader reader = new FileReader(file);
		ConvertidorMapa convertidor = new ConvertidorMapaImplementacion(reader);
		this.mapa = convertidor.cargarMapa();
		this.enemigos = new ArrayList<>();
		FileReader readerEnemigos = new FileReader("src/resources/enemigos.json");
		ConvertidorEnemigos convertidorEnemigos = new ConvertidorEnemigosImplementacion(readerEnemigos,mapa.getOrigen());
		this.enemigosTurno = convertidorEnemigos.cargarEnemigos();
		this.defensas = new LinkedList<>();
		this.turno = 1;
		this.turnosTotales = 1;
		this.logger =  CustomLogger.getInstance();
	}

	public AlgoDefense(Mapa mapa) {
		this.mapa = mapa;
		this.enemigos = new ArrayList<>();
		this.defensas = new ArrayList<>();
		this.enemigosTurno = null;
	}


	// only for temporary vista
	public List<Enemigo> getEnemigos() {
		return enemigos;
	}

	public Mapa getMapa() {
		return this.mapa;
	}
	public Jugador getJugador() {
		return this.jugador1;
	}

	public List<ParcelaDeTierra> getDefensas() {
		return jugador1.getDefensas();
	}

	public void agregarJugador(String nombre) throws NombreInvalido, FormatoJSONInvalidoException, IOException, ParseException {
		if (nombre.length() < 6) throw new NombreInvalido();

		this.jugador1 = new Jugador(nombre);
	}

	public String finDelJuego() {

		if (jugador1.estaMuerto()) {
			logger.log("Computadora gana la partida");
			return "Computadora";
		} else if (enemigos.isEmpty() && this.turnosTotales!= 1) {
			logger.log(jugador1.getNombre() + " gana la partida");
			return jugador1.getNombre();
		}

		return null;
	}


	public void moverEnemigos() throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar, DefensasVacias {
		for (Enemigo enemigo : enemigos) {
			enemigo.mover(this.mapa);
		}

		this.enemigos = this.mapa.getMeta().actualizarEnemigos(this.enemigos, jugador1);
		this.mapa.pasarTurno();

	}

	public void agregarEnemigo(Enemigo enemigo) {

		//enemigo.setPasarelaActual(mapa.getOrigen());
		enemigos.add(enemigo);
	}

	public void cargarEnemigos(int cantTurnos) throws FileNotFoundException, FormatoJSONInvalidoException, ParseException {
		FileReader readerEnemigos = new FileReader("src/resources/enemigos.json");
		ConvertidorEnemigos convertidorEnemigos = new ConvertidorEnemigosImplementacion(readerEnemigos,mapa.getOrigen());
		Map<Integer, List<Enemigo>> enemigosPorRonda = convertidorEnemigos.cargarEnemigos();
		for (int i = 1; i < cantTurnos; i++) {
			for (Enemigo enemigoDefinido : enemigosPorRonda.get(i)) {
				enemigoDefinido.setPasarelaActual(mapa.getOrigen());
				enemigos.add(enemigoDefinido);
			}
		}

	}

	public void ubicarDefensa(Torre defensa, int absica, int ordenada) throws TerrenoNoAptoParaConstruir {
		Parcela parcelaSet = null;
		parcelaSet = this.mapa.obtenerParcelaConCoordenadas(absica, ordenada);
		parcelaSet.construir(defensa);
		defensas.add((ParcelaDeTierra) parcelaSet);
		logger.log("Se construyo");
	}

	public void ubicarTrampa(TrampaArenosa trampa, Parcela pasarela) throws TerrenoNoAptoParaConstruir {
		pasarela.construir(trampa);
		logger.log("Se construyo");
	}

	public void construir(Torre defensa, Parcela parcela) throws CreditosInsuficientes, TerrenoNoAptoParaConstruir {
		jugador1.construir(defensa, parcela);
	}

	public void construir(TrampaArenosa defensa, Parcela parcela) throws CreditosInsuficientes, TerrenoNoAptoParaConstruir {
		jugador1.construir(defensa, parcela);
	}

	public void activarDefensas() throws TerrenoNoAptoParaCaminar, TorreNoDesplegada {
		for (ParcelaDeTierra parcela : defensas) {
			parcela.getDefensa().atacar(enemigos, parcela);

		}

	}

	public int obtenerCantidadDefensas() {
		return (jugador1.getDefensas().size());
	}

	@Override
	public void addObserver(Observer observer) {
		observers.add(observer);
	}

	@Override
	public void notifyObservers() {
		observers.forEach(Observer::update);
	}


	public void ejecutarTurno() throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, DefensasVacias, TorreNoDesplegada {
		this.moverEnemigos();
		this.cargarEnemigos();
		this.pasarTurno();
		this.activarDefensas();
	}

	public void cargarEnemigos() {
		enemigos.addAll(enemigosTurno.get(turno));
	};

	public void pasarTurno() {
		if (this.turno < 12) {
			this.turno++;
		} else {
			this.turno = 1;
		}
		this.turnosTotales++;
	}
}
