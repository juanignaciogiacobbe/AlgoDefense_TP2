package clases;

import Excepciones.NombreInvalido;
import Excepciones.SinVidaRestante;
import org.json.simple.parser.ParseException;
import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TerrenoNoAptoParaConstruir;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class AlgoDefense {

	private Jugador jugador1;

	private Mapa mapa;

	private List<Enemigo> enemigos;

	public AlgoDefense() throws IOException, ParseException, FormatoJSONInvalidoException, NombreInvalido {
		FileReader reader = new FileReader("src/temp/mapa.json");
        ConvertidorMapa convertidor = new ConvertidorMapaImplementacion(reader);

		this.mapa = convertidor.cargarMapa();
		this.enemigos = new ArrayList<>();
		this.jugador1 = new Jugador("Mariana");
	}

	public Mapa getMapa() {
		return this.mapa;
	}

	public void agregarJugador(String nombre) throws NombreInvalido {
		if (nombre.length() < 6) throw new NombreInvalido();

		jugador1 = new Jugador(nombre);
	}


	public String finDelJuego() throws SinVidaRestante {

		if (jugador1.getVida() <= 0){
			return "Computadora";
		}
		return jugador1.getNombre();


		/*
		if (this.enemigos.isEmpty()) {
			return jugador1.getNombre();
		}

		int danio = calcularDanioTotal();
		/*
		if (jugador1.sobreviveConDanio(danio)) {
			return jugador1.getNombre();
		}


		try {
			if (this.enemigos.isEmpty()) {
				return jugador1.getNombre();
			}
			jugador1.recibirdanio(danio);
			return jugador1.getNombre();
		}
		catch (SinVidaRestante e) {
			return "Computadora";
		}
		*/

	}

	public void moverEnemigos() throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar, SinVidaRestante {
		for (Enemigo enemigo : enemigos) {
			enemigo.mover(this.mapa);
			}

		this.enemigos = this.mapa.getMeta().actualizarEnemigos(this.enemigos,jugador1);


	}


	public int calcularDanioTotal() {
		int danio = 0;
		for (Enemigo enemigo : enemigos) {
			if (enemigo.getPasarelaActual().getCoordenada().equals(mapa.getMeta().getCoordenada())) {
				danio += enemigo.getDanio();
			}
		}
		return danio;
	}

	public void agregarEnemigo(Enemigo enemigo) {
		enemigos.add(enemigo);
	}

	public int enemigosEnMeta() {
		int cantidadMeta = 0;
		for (Enemigo enemigo : enemigos) {
			if (enemigo.getPasarelaActual().getCoordenada().equals(mapa.getMeta().getCoordenada())) {
				cantidadMeta += 1;
			}
		}
		return cantidadMeta;
	}

	public void comenzaPartida() throws IOException, ParseException {
		//TurnoJugador turnoJugador = new TurnoJugador();
		//TurnoIA turnoIA = new TurnoIA();
		//while (partida sea jugable)
		//turnoJugador.ejecutarTurno();
		//turnoIA.ejecutarTurno();

	}


	public void cargarEnemigos () throws FileNotFoundException, FormatoJSONInvalidoException, ParseException {
		FileReader readerEnemigos = new FileReader("src/temp/enemigos.json");
		ConvertidorEnemigos convertidorEnemigos = new ConvertidorEnemigosImplementacion(readerEnemigos);
		Map<Integer, List<Enemigo>> enemigosPorRonda = convertidorEnemigos.cargarEnemigos();
		for (int i = 1 ; i < 12 ; i++) {
			for (Enemigo enemigoDefinido : enemigosPorRonda.get(i)) {
				enemigoDefinido.setPasarelaActual(mapa.getOrigen());
				enemigos.add(enemigoDefinido);
			}
		}

	}


}
