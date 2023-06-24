package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.CustomLogger;
import edu.fiuba.algo3.modelo.convertidor.*;
import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.enemigos.*;
import edu.fiuba.algo3.modelo.juego.CreditosInsuficientes;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;
import edu.fiuba.algo3.modelo.defensas.TorreNoDesplegada;
import edu.fiuba.algo3.modelo.defensas.TorrePlateada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.NombreInvalido;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.*;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class CasosDeUsoTest {

	@Test
	public void test01JugadorComienzaConVidaYCreditosCorrespondientes() throws NombreInvalido {
		Jugador jugador = new Jugador("Mariana");
		int vidaEsperada = 20;
		int creditosEsperados = 100;
		assertEquals(vidaEsperada, jugador.getVida());
		assertEquals(creditosEsperados, jugador.getCreditos());
	}

	@Test
	public void test02DefensaPuedeSerUtilizadaLuegoDeCrearseRecienAlPasarTurnosCorrespondientes() {
		List<Enemigo> enemigos = new ArrayList<>();
		Hormiga hormiga = new Hormiga(new PasarelaLargada(0, 0));
		enemigos.add(hormiga);
		ParcelaDeTierra parcelaDeTierra = new ParcelaDeTierra(1, 1);
		TorrePlateada defensa = new TorrePlateada();
		defensa.atacar(enemigos, parcelaDeTierra);
		assertEquals(1, hormiga.getVida());
		defensa.pasarTurno();
		defensa.pasarTurno();
		defensa.atacar(enemigos, parcelaDeTierra);
		assertEquals(0, hormiga.getVida());
	}

	@Test
	public void test03VerificoQueJugadorPuedaConstruir() throws NombreInvalido {
		Jugador jugador = new Jugador("Mariana");
		TorrePlateada torre = new TorrePlateada();
		ParcelaDeTierra tierra = new ParcelaDeTierra(0, 1);
		assertDoesNotThrow(() -> jugador.construir(torre, tierra));
	}

	@Test
	public void test04VerificoQueSePuedeConstruirDefensasSoloSobreTierra() {
		TorrePlateada defensa = new TorrePlateada();
		ParcelaDeTierra tierra = new ParcelaDeTierra(1, 0);
		ParcelaRocosa rocoso = new ParcelaRocosa(0, 1);

		assertDoesNotThrow(() -> tierra.construir(defensa));
		assertThrows(TerrenoNoAptoParaConstruir.class, () -> rocoso.construir(defensa));
	}

	@Test
	public void test05VerificoQueLasDefensasAtaquenDentroDelRangoEsperado() throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar, TorreNoDesplegada, EnemigosFueraDeRango {
		TorrePlateada torre1 = new TorrePlateada();
		TorrePlateada torre2 = new TorrePlateada();
		ParcelaDeTierra tierra1 = new ParcelaDeTierra(1, 1);
		ParcelaDeTierra tierra2 = new ParcelaDeTierra(7, 1);
		tierra1.construir(torre1);
		tierra2.construir(torre2);

		for (int i = 0; i < 3; i++) {
			torre1.pasarTurno();
			torre2.pasarTurno();
		}
		PasarelaComun ubicacionEnemigo = new PasarelaComun(1, 2);

		Hormiga enemigo1 = new Hormiga(ubicacionEnemigo);
		List<Enemigo> enemigos1 = new ArrayList<>();
		enemigos1.add(enemigo1);

		Hormiga enemigo2 = new Hormiga(ubicacionEnemigo);
		List<Enemigo> enemigos2 = new ArrayList<>();
		enemigos2.add(enemigo2);


		torre1.atacar(enemigos1, tierra1);
		torre2.atacar(enemigos2, tierra2);

		assertEquals(0, enemigo1.getVida());
		assertEquals(1, enemigo2.getVida());
	}

	@Test
	public void test06VerificoQueLasUnidadesEnemigasSonDaniadasAcordeAlAtaqueRecibido() throws EnemigoFueraDeRango, EnemigoNoDaniable {
		PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
		ParcelaDeTierra tierra1 = new ParcelaDeTierra(1, 1);

		Arania arania = new Arania(pasarelaLargada);
		arania.recibirAtaque(tierra1, 10, 1);
		assertEquals(1, arania.getVida());
	}

	@Test
	public void test07EnemigosCaminanPorTerrenoValido() throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
		Mapa mapa = new Mapa();
		PasarelaMeta parcela1 = new PasarelaMeta(0, 1);
		ParcelaRocosa parcela2 = new ParcelaRocosa(1, 0);
		mapa.getParcelas().add(parcela1);
		mapa.getParcelas().add(parcela2);
		mapa.setMeta(parcela1);
		PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
		Arania arania = new Arania(pasarelaLargada);
		arania.mover(mapa);

		assertEquals(parcela1.getCoordenada(), arania.getPasarelaActual().getCoordenada());
	}

	@Test
	public void test08JugadorObtieneCreditosAlDestruirEnemigo() throws NombreInvalido, EnemigoFueraDeRango, EnemigoNoDaniable {
		Jugador jugador = new Jugador("pepito");
		PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
		Hormiga hormiga = new Hormiga(pasarelaLargada);
		hormiga.recibirAtaque(pasarelaLargada, 10, 100);
		int creditos = hormiga.recolectarCreditos();
		jugador.agregarCreditos(creditos);
		assertEquals(jugador.getCreditos(), 101);
	}

	@Test
	public void test09EnemigosSeMuevenPorMapa() throws IOException, ParseException, FormatoJSONInvalidoException, TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar, NombreInvalido, DefensasVacias {
		AlgoDefense algoDefense = new AlgoDefense();
		algoDefense.agregarJugador("Sebastian");
		Arania arania = new Arania(algoDefense.getMapa().getOrigen());

		algoDefense.agregarEnemigo(arania);
		for (int i = 0; i < 12; i++) { // en el turno 12 se llega a la meta en 10,14
			algoDefense.moverEnemigos();
		}
		assertEquals(arania.getPasarelaActual().getCoordenada(), algoDefense.getMapa().getMeta().getCoordenada());
	}

	@Test
	public void test10AlEliminarTodasLasUnidaesEnemigasGanaElJugador() throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar, FormatoJSONInvalidoException, IOException, ParseException, DefensasVacias, EnemigoFueraDeRango, EnemigoNoDaniable {
		PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
		AlgoDefense algoDefense = new AlgoDefense();
		Mapa mapa = algoDefense.getMapa();
		Hormiga hormiga = new Hormiga(mapa.getOrigen());
		algoDefense.agregarEnemigo(hormiga);
		hormiga.recibirAtaque(pasarelaLargada, 10, 100);
		algoDefense.moverEnemigos();
		algoDefense.actualizarEnemigos();
		algoDefense.pasarTurno();
		algoDefense.actualizarEnemigos();
		assertDoesNotThrow(() -> algoDefense.agregarJugador("Mariana"));
		String ganador = algoDefense.finDelJuego();
		assertEquals("Mariana", ganador);//Null cambiamos
	}

	@Test
	public void test11NoSeEliminanTodasLasUnidaesEnemigasPeroNoAlcanzaElDanioGanaElJugador() throws NombreInvalido, IOException, ParseException, FormatoJSONInvalidoException, TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar, DefensasVacias {
		AlgoDefense algoDefense = new AlgoDefense();
		algoDefense.agregarJugador("Mariana");
		Mapa mapa = algoDefense.getMapa();
		Hormiga enemigo1 = new Hormiga(mapa.getOrigen());
		Hormiga enemigo2 = new Hormiga(mapa.getOrigen());
		algoDefense.agregarEnemigo(enemigo1);
		algoDefense.agregarEnemigo(enemigo2);
		for (int i = 0; i < 23; i++) {
			algoDefense.moverEnemigos();
			algoDefense.pasarTurno();
		}
		String ganador = algoDefense.finDelJuego();
		assertEquals("Mariana", ganador);
	}

	@Test
	public void test12NoSeEliminanTodasLasUnidadesEnemigasPeroAlcanzaElDanioGanaLaComputadora() throws NombreInvalido, IOException, ParseException, FormatoJSONInvalidoException, TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar, DefensasVacias {
		AlgoDefense algoDefense = new AlgoDefense();
		algoDefense.agregarJugador("Mariana");
		Mapa mapa = algoDefense.getMapa();
		for (int i = 0; i < 23; i++) {
			Hormiga hormiga = new Hormiga(mapa.getOrigen());
			algoDefense.agregarEnemigo(hormiga);
		}
		for (int i = 0; i < 24; i++) {
			algoDefense.moverEnemigos();
		}
		String ganador = algoDefense.finDelJuego();
		assertEquals("Computadora", ganador);

	}

	@Test
	public void test13VerificoFormatoValidoJSONEnemigos() throws IOException, FormatoJSONInvalidoException, ParseException {
		AlgoDefense algoDefense = new AlgoDefense();
		FileReader fileReader = new FileReader("src/resources/enemigos.json");
		ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion(fileReader,algoDefense.getMapa().getOrigen());
		assertDoesNotThrow(convertidor::cargarEnemigos);
	}


	@Test
	public void test14VerificoFormatoValidoJSONMapa() throws FileNotFoundException {
		FileReader fileReader = new FileReader("src/resources/mapa.json");
		ConvertidorMapa convertidor = new ConvertidorMapaImplementacion(fileReader);
		assertDoesNotThrow(convertidor::cargarMapa);
	}

	@Test
	public void test15VerificoCorrectaLecturaYConversionDeJsonEnemigos() throws IOException, FormatoJSONInvalidoException, ParseException {
		AlgoDefense algoDefense = new AlgoDefense();
		FileReader fileReader = new FileReader("src/resources/enemigos.json");
		ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion(fileReader,algoDefense.getMapa().getOrigen());
		AtomicReference<Map<Integer, List<Enemigo>>> enemigosPorRonda = new AtomicReference<>();
		assertDoesNotThrow(() -> enemigosPorRonda.set(convertidor.cargarEnemigos()));
		assertNotNull(enemigosPorRonda);

		// Comprobar las rondas 4 y 5
		int rondaAComprobar = 4;
		assertTrue(enemigosPorRonda.get().containsKey(rondaAComprobar), "Falta la ronda " + rondaAComprobar);
		List<Enemigo> enemigosRonda4 = enemigosPorRonda.get().get(rondaAComprobar);
		assertNotNull(enemigosRonda4, "La lista de enemigos para la ronda " + rondaAComprobar + " es nula");
		assertEquals(1, enemigosRonda4.size(), "Número incorrecto de enemigos para la ronda " + rondaAComprobar);

		rondaAComprobar = 5;
		assertTrue(enemigosPorRonda.get().containsKey(rondaAComprobar), "Falta la ronda " + rondaAComprobar);
		List<Enemigo> enemigosRonda5 = enemigosPorRonda.get().get(rondaAComprobar);
		assertNotNull(enemigosRonda5, "La lista de enemigos para la ronda " + rondaAComprobar + " es nula");
		assertEquals(1, enemigosRonda5.size(), "Número incorrecto de enemigos para la ronda " + rondaAComprobar);

		// Comprobar la longitud de las rondas
		int rondasEsperadas = 12;
		assertEquals(rondasEsperadas, enemigosPorRonda.get().size(), "Número incorrecto de rondas");
	}

	@Test
	public void test16VerificoCorrectaLecturaYConversionDeJSONMapa() throws IOException, ParseException, FormatoJSONInvalidoException {
		Reader reader = new FileReader("src/resources/mapa.json");
		ConvertidorMapa convertidor = new ConvertidorMapaImplementacion(reader);
		Mapa mapa = convertidor.cargarMapa();
		assertNotNull(mapa);
		// Comprobar el tamaño del mapa
		int filasEsperadas = 15;
		int columnasEsperadas = 15;
		assertEquals(filasEsperadas * columnasEsperadas, mapa.getParcelas().size(), "Tamaño incorrecto del mapa");
		// Comprobar algunas parcelas individuales
		int filaAComprobar = 5;
		int columnaAComprobar = 10;
		int indiceParcela = (filaAComprobar - 1) * columnasEsperadas + (columnaAComprobar - 1);
		// Coordenadas (X, Y): (10, 5)
		List<Parcela> parcelas = mapa.getParcelas();
		assertNotNull(parcelas.get(indiceParcela), "La parcela en la fila " + filaAComprobar + " y columna " + columnaAComprobar + " es nula");
		assertEquals(ParcelaDeTierra.class, parcelas.get(indiceParcela).getClass(), "Tipo de parcela incorrecto");

		filaAComprobar = 10;
		columnaAComprobar = 8;
		indiceParcela = (filaAComprobar - 1) * columnasEsperadas + (columnaAComprobar - 1);
		// Coordenadas (X, Y): (8, 10)
		assertNotNull(parcelas.get(indiceParcela), "La parcela en la fila " + filaAComprobar + " y columna " + columnaAComprobar + " es nula");
		assertEquals(ParcelaDeTierra.class, parcelas.get(indiceParcela).getClass(), "Tipo de parcela incorrecto");
	}

	@Test
	void test17VerificarQueElJuegoSecreaAcordeAAmbosJSON() {
		assertDoesNotThrow(() -> {
					FileReader reader = new FileReader("src/resources/mapa.json");
					ConvertidorMapa convertidor = new ConvertidorMapaImplementacion(reader);
					Mapa mapa = convertidor.cargarMapa();

					FileReader readerEnemigos = new FileReader("src/resources/enemigos.json");
					ConvertidorEnemigos convertidorEnemigos = new ConvertidorEnemigosImplementacion(readerEnemigos,mapa.getOrigen());
					List<Enemigo> enemigos = convertidorEnemigos.cargarEnemigos().values().stream()
							.flatMap(List::stream)
							.peek(enemigo -> enemigo.setPasarelaActual(mapa.getOrigen()))
							.collect(Collectors.toList());

					//new AlgoDefense(mapa, enemigosTurno, enemigos);
					// podriamos tener un turno y verificar si tal estado es el esperado para crear o mockearlo y verificar que se llame
				}
		);
	}

	@Test
	void test18SimuloYVerificoQueGanaJugador() throws FormatoJSONInvalidoException, IOException, ParseException, NombreInvalido, TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, TorreNoDesplegada, DefensasVacias, CreditosInsuficientes {
		AlgoDefense algoDefense = new AlgoDefense();
		TorrePlateada torre = new TorrePlateada();
		TorrePlateada torre1 = new TorrePlateada();
		algoDefense.agregarJugador("Sebastian");
		algoDefense.cargarEnemigos(2);
		algoDefense.construir(torre, algoDefense.getMapa().obtenerParcelaConCoordenadas(0,2));
		algoDefense.construir(torre1, algoDefense.getMapa().obtenerParcelaConCoordenadas(1,0));
		algoDefense.moverEnemigos();
		algoDefense.ataqueJugador();
		for (int i = 0; i < 24; i++) {
			algoDefense.moverEnemigos();
			algoDefense.pasarTurno();
		}
		String ganador = algoDefense.finDelJuego();
		assertEquals("Sebastian", ganador);
	}

	@Test
	void test19SimuloYVerificoQueGanaComputadora() throws FormatoJSONInvalidoException, IOException, ParseException, TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, NombreInvalido, DefensasVacias {
		AlgoDefense algoDefense = new AlgoDefense();
		algoDefense.agregarJugador("Sebastian");

		for (int i = 0; i <15; i++) {
			algoDefense.cargarEnemigos();
			algoDefense.pasarTurno();
		}
		for (int i = 0; i < 30; i++) {
			algoDefense.moverEnemigos();
		}


		String ganador = algoDefense.finDelJuego();
		assertEquals("Computadora", ganador);
	}

	@Test
	void test20VerificarSistemaDeLog() {
		// Create a ByteArrayOutputStream to capture the printed output
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		PrintStream originalOut = System.out;

		try {
			// Redirect System.out to the ByteArrayOutputStream
			System.setOut(new PrintStream(outputStream));

			// Log a message using the custom logger
			CustomLogger customLogger = CustomLogger.getInstance();
			customLogger.log("Test log message");

			// Get the printed output from the ByteArrayOutputStream
			String loggedMessage = outputStream.toString().trim();

			// Verify the printed output
			assertEquals("Test log message", loggedMessage);

		} finally {
			// Restore the original System.out
			System.setOut(originalOut);
		}
	}
}
