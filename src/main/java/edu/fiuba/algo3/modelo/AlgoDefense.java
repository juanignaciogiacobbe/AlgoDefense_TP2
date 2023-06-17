package edu.fiuba.algo3.modelo;

import edu.fiuba.algo3.modelo.convertidor.*;
import edu.fiuba.algo3.modelo.defensas.*;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.enemigos.EnemigosFueraDeRango;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.NombreInvalido;
import edu.fiuba.algo3.modelo.juego.Turno;
import edu.fiuba.algo3.modelo.juego.TurnoIA;
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

	private final Mapa mapa;
	private Jugador jugador1;

	private List<Turno> turnos;

	private List<Enemigo> enemigos;
	private List<ParcelaDeTierra> defensas;
	private final ArrayList<Observer> observers = new ArrayList<>();
	private CustomLogger logger;

	private int turno;

	public AlgoDefense(Mapa mapa, List<Enemigo> enemigos) {
		this.mapa = mapa;
		this.enemigos = enemigos;
		this.turno = 0;
	}

	public AlgoDefense() throws IOException, ParseException, FormatoJSONInvalidoException {
		File file = new File("src/resources/mapa.json");
		FileReader reader = new FileReader(file);
		ConvertidorMapa convertidor = new ConvertidorMapaImplementacion(reader);
		this.mapa = convertidor.cargarMapa();
		this.enemigos = new ArrayList<>();
		this.defensas = new ArrayList<>();
		this.turnos = new ArrayList<>();
		this.logger =  CustomLogger.getInstance();
	}

	private void inicializarTurnos() throws FormatoJSONInvalidoException, IOException, ParseException {
		TurnoIA turnoIA = new TurnoIA(this.mapa,this.jugador1);
		this.turnos.add(turnoIA);
	}

	public AlgoDefense(Mapa mapa) {
		this.mapa = mapa;
		this.enemigos = new ArrayList<>();
		this.defensas = new ArrayList<>();
	}

	public void siguienteTurno() {
		this.notifyObservers();

	}

	// only for temporary vista
	public List<Enemigo> getEnemigos() {
		return enemigos;
	}

	public Mapa getMapa() {
		return this.mapa;
	}

	public List<ParcelaDeTierra> getDefensas() {
		return defensas;
	}

	public void agregarJugador(String nombre) throws NombreInvalido, FormatoJSONInvalidoException, IOException, ParseException {
		if (nombre.length() < 6) throw new NombreInvalido();

		this.jugador1 = new Jugador(nombre);
		this.inicializarTurnos();
	}

	public String finDelJuego() {

		if (jugador1.estaMuerto() || enemigos.isEmpty()) {
			logger.log("Computadora gana la partida");
			return "Computadora";
		}
		logger.log(jugador1.getNombre() + " gana la partida");
		return jugador1.getNombre();

	}


	public void ejecutarTurnos() throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, DefensasVacias {
		for (Turno turno : turnos){
			turno.ejecutarTurno();
		}
	}

	public void moverEnemigos() throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar, DefensasVacias {
		for (Enemigo enemigo : enemigos) {
			enemigo.mover(this.mapa);
		}

		this.enemigos = this.mapa.getMeta().actualizarEnemigos(this.enemigos, jugador1);
		this.mapa.pasarTurno();

	}

	public void agregarEnemigo(Enemigo enemigo) {
		enemigos.add(enemigo);
	}

	public void cargarEnemigos(int cantTurnos) throws FileNotFoundException, FormatoJSONInvalidoException, ParseException {
		FileReader readerEnemigos = new FileReader("src/resources/enemigos.json");
		ConvertidorEnemigos convertidorEnemigos = new ConvertidorEnemigosImplementacion(readerEnemigos);
		Map<Integer, List<Enemigo>> enemigosPorRonda = convertidorEnemigos.cargarEnemigos();
		for (int i = 1; i < cantTurnos; i++) {
			for (Enemigo enemigoDefinido : enemigosPorRonda.get(i)) {
				enemigoDefinido.setPasarelaActual(mapa.getOrigen());
				enemigos.add(enemigoDefinido);
			}
		}

	}

	public void ubicarDefensa(Defensa defensa, int absica, int ordenada) {
		ParcelaDeTierra parcelaSet = null;
		parcelaSet = (ParcelaDeTierra) this.mapa.obtenerParcelaConCoordenadas(absica, ordenada);
		parcelaSet.setDefensa((Torre) defensa);
		jugador1.agregarDefensa(parcelaSet);
	}

	public void ubicarTrampa(TrampaArenosa trampa, PasarelaComun pasarela) throws TerrenoNoAptoParaConstruir {
		pasarela.construir(trampa);
	}


	public void activarDefensas() throws TerrenoNoAptoParaCaminar, TorreNoDesplegada {
		for (ParcelaDeTierra parcela : defensas) {
			((Torre)parcela.getDefensa()).pasarTurno();//no desplegada
			((Torre)parcela.getDefensa()).pasarTurno();//no desplegada
			try {
				parcela.getDefensa().atacar(enemigos, parcela);// desplegada
			} catch (EnemigosFueraDeRango e) {
			}
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
}
