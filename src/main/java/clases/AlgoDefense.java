package clases;

import Excepciones.NombreInvalido;
import org.json.simple.parser.ParseException;
import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TerrenoNoAptoParaConstruir;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class AlgoDefense {

	private Jugador jugador1;

	private Mapa mapa;

	private List<Enemigo> enemigos;

	public AlgoDefense() throws IOException, ParseException, FormatoJSONInvalidoException {
		FileReader reader = new FileReader("src/temp/mapa.json");
        ConvertidorMapa convertidor = new ConvertidorMapaImplementacion(reader);
		this.mapa = convertidor.cargarMapa();
		this.enemigos = new ArrayList<>();
	}

	public Mapa getMapa() {
		return this.mapa;
	}

	public void agregarJugador(String nombre) throws NombreInvalido {
		if (nombre.length() < 6) throw new NombreInvalido();

		jugador1 = new Jugador(nombre);
	}


	public String finDelJuego() {
		if (this.enemigos.isEmpty()) {
			return jugador1.getNombre();
		}

		int danio = calcularDanioTotal();
		if (jugador1.sobreviveConDanio(danio)) {
			return jugador1.getNombre();
		}

		return "Computadora";
	}

	public void moverEnemigos() throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
		for (Enemigo enemigo : enemigos) {
			enemigo.mover(this.mapa);
		}
	}


	private int calcularDanioTotal() {
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
}
