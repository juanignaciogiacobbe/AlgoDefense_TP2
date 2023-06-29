package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.convertidor.*;
import edu.fiuba.algo3.modelo.defensas.*;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
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
import java.util.List;
import java.util.Map;


public class AlgoDefense implements Observable {

	private Mapa mapa;
	private Map<Integer, List<Enemigo>> enemigosTurno;

	private Jugador jugador1;
	private List<Enemigo> enemigos;

	private final ArrayList<Observer> observers = new ArrayList<>();
	private CustomLogger logger;

	private final int turnoMaximo = 15;

	private int turno;

	private int turnosTotales;

	public AlgoDefense(Mapa mapa, Map<Integer, List<Enemigo>> enemigosTurno, List<Enemigo> enemigos) {
		this.mapa = mapa;
		this.enemigosTurno = enemigosTurno;
		this.enemigos = enemigos;
		this.turno = 0;
	}

	public AlgoDefense() throws IOException, ParseException, FormatoJSONInvalidoException {
		this.reiniciarJuego();
	}

	public void reiniciarJuego() throws IOException, ParseException, FormatoJSONInvalidoException {
		this.cargarMapa();
		this.enemigos = new ArrayList<>();
		this.cargarMapaEnemigosPorTurno();
		this.turno = 1;
		this.turnosTotales = 1;
		this.logger =  CustomLogger.getInstance();
	}



	public AlgoDefense(Mapa mapa) {
		this.mapa = mapa;
		this.enemigos = new ArrayList<>();
		this.enemigosTurno = null;
	}

	public void cargarMapa() throws ParseException, IOException, FormatoJSONInvalidoException {
		String PATH_MAPA = "src/resources/mapa.json";
		File file = new File(PATH_MAPA);
		FileReader reader = new FileReader(file);
		ConvertidorMapa convertidor = new ConvertidorMapaImplementacion(reader);
		this.mapa = convertidor.cargarMapa();
	}

	public void cargarMapaEnemigosPorTurno() throws FormatoJSONInvalidoException, ParseException, FileNotFoundException {
		String PATH_ENEMIGO = "src/resources/enemigos.json";
		FileReader readerEnemigos = new FileReader(PATH_ENEMIGO);
		ConvertidorEnemigos convertidorEnemigos = new ConvertidorEnemigosImplementacion(readerEnemigos,mapa.getOrigen());
		this.enemigosTurno = convertidorEnemigos.cargarEnemigos();
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

		this.enemigos = this.mapa.actualizarMeta(this.enemigos, jugador1);
		this.mapa.pasarTurno();

	}

	public void agregarEnemigo(Enemigo enemigo) {
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

	public void construir(Torre defensa, Parcela parcela) throws CreditosInsuficientes, TerrenoNoAptoParaConstruir {
		jugador1.construir(defensa, parcela);
	}

	public void construir(TrampaArenosa defensa, Parcela parcela) throws CreditosInsuficientes, TerrenoNoAptoParaConstruir {
		jugador1.construir(defensa, parcela);
	}


	public void ataqueJugador() {
		jugador1.atacarEnemigos(this.enemigos);
	}

	public int obtenerCantidadDefensas() {
		return jugador1.cantidadDefensas();
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
		this.ataqueJugador();
		this.actualizarEnemigos();
	}

	public void actualizarEnemigos() {
		for (Enemigo enemigo: this.enemigos) {
			this.enemigos = enemigo.actualizarLista(this.enemigos);
		}
	}

	public void cargarEnemigos() {
		if (this.turnosTotales < this.turnoMaximo) {
			enemigos.addAll(enemigosTurno.get(turno));
		}
	};

	public void pasarTurno() {
		if (this.turno < 12) {
			this.turno++;
		} else {
			try {
				cargarMapaEnemigosPorTurno();
			} catch (FormatoJSONInvalidoException e) {
				throw new RuntimeException(e);
			} catch (ParseException e) {
				throw new RuntimeException(e);
			} catch (FileNotFoundException e) {
				throw new RuntimeException(e);
			}
			this.turno = 1;
		}
		this.turnosTotales++;
	}
}
