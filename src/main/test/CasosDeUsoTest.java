import Excepciones.NombreInvalido;
import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TerrenoNoAptoParaConstruir;
import Excepciones.TorreNoDesplegada;
import clases.*;
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
    public void test02DefensaPuedeSerUtilizadaLuegoDeCrearse() {
        List<Enemigo> enemigos = new ArrayList<>();
        enemigos.add (new Hormiga(new PasarelaLargada(0,0)));
        ParcelaDeTierra parcelaDeTierra = new ParcelaDeTierra(1,1);
        TorrePlateada defensa = new TorrePlateada();
        //assertThrows(TorreNoDesplegada.class, () -> defensa.atacar(enemigos,parcelaDeTierra));
        assertDoesNotThrow(() -> defensa.atacar(enemigos,parcelaDeTierra));

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
    public void test05VerificoQueLasDefensasAtaquenDentroDelRangoEsperado() {
        TorrePlateada torre = new TorrePlateada();
        int distanciaEnemigoDentroDeRango = 1;
        int distanciaEnemigoFueraDeRango = 7;


        assertTrue(torre.enemigoDentroDeRango(distanciaEnemigoDentroDeRango));
        assertFalse(torre.enemigoDentroDeRango(distanciaEnemigoFueraDeRango));
    }

    @Test
    public void test06VerificoQueLasUnidadesEnemigasSonDaniadasAcordeAlAtaqueRecibido() {
        PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
        Arania arania = new Arania(pasarelaLargada);
        arania.recibirDanio(1);
        assertEquals(1,arania.getVida());
    }

    @Test
    public void test07EnemigosCaminanPorTerrenoValido() {
        PasarelaComun parcela2 = new PasarelaComun(1, 2);
        ParcelaRocosa parcela3 = new ParcelaRocosa(1, 0);
        PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
        Arania arania = new Arania(pasarelaLargada);
        assertTrue(arania.puedeMoverseA(parcela2));
        assertFalse(arania.puedeMoverseA(parcela3));


    }

    @Test
    public void test08JugadorObtieneCreditosAlDestruirEnemigo() throws NombreInvalido {
        Jugador jugador = new Jugador("pepito");
        TorrePlateada defensa = new TorrePlateada();
        PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
        Hormiga hormiga = new Hormiga(pasarelaLargada);
        defensa.atacarA(hormiga);
        int creditos = hormiga.recolectarCreditos();
        jugador.agregarCreditos(creditos);
        assertEquals(jugador.getCreditos(), 101);
    }

    @Test
    public void test09EnemigosSeMuevenPorMapa() throws IOException, ParseException, FormatoJSONInvalidoException, TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar, NombreInvalido {
            AlgoDefense algoDefense = new AlgoDefense();
            algoDefense.agregarJugador("Sebastian");
            Arania arania = new Arania(algoDefense.getMapa().getOrigen());
            algoDefense.agregarEnemigo(arania);
            for (int i = 0; i < 12; i++) { // en el turno 12 se llega a la meta en 10,14
                algoDefense.moverEnemigos();
            }
            assertTrue(arania.getPasarelaActual() instanceof PasarelaMeta);
    }

    @Test
    public void test10AlEliminarTodasLasUnidaesEnemigasGanaElJugador() throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar, FormatoJSONInvalidoException, IOException, ParseException {
        AlgoDefense algoDefense = new AlgoDefense();
        Mapa mapa = algoDefense.getMapa();
        Enemigo enemigo = new Hormiga(mapa.getOrigen());
        algoDefense.agregarEnemigo(enemigo);
        TorrePlateada torre = new TorrePlateada();
        torre.atacarA(enemigo);
        assertDoesNotThrow(() -> algoDefense.agregarJugador("Mariana"));
        algoDefense.moverEnemigos();
        String ganador = algoDefense.finDelJuego();
        assertEquals(ganador, "Mariana");
    }

    @Test
    public void test11NoSeEliminanTodasLasUnidaesEnemigasPeroNoAlcanzaElDanioGanaElJugador() throws NombreInvalido, IOException, ParseException, FormatoJSONInvalidoException, TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
        AlgoDefense algoDefense = new AlgoDefense();
        Mapa mapa = algoDefense.getMapa();
        Enemigo enemigo = new Hormiga(mapa.getOrigen());
        Enemigo enemigo2 = new Hormiga(mapa.getOrigen());
        algoDefense.agregarEnemigo(enemigo);
        algoDefense.agregarEnemigo(enemigo2);
        TorrePlateada torre = new TorrePlateada();
        torre.atacarA(enemigo);
        for (int i = 0; i < 4; i++) {
            algoDefense.moverEnemigos();
        }
        assertDoesNotThrow(() -> algoDefense.agregarJugador("Mariana"));
        algoDefense.agregarJugador("Mariana");
        String ganador = algoDefense.finDelJuego();
        assertEquals(ganador, "Mariana");
    }

    @Test
    public void test12NoSeEliminanTodasLasUnidadesEnemigasPeroAlcanzaElDanioGanaLaComputadora() throws NombreInvalido, IOException, ParseException, FormatoJSONInvalidoException, TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
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
        algoDefense.agregarJugador("Mariana");
        String ganador = algoDefense.finDelJuego();
        assertEquals(ganador, "Computadora");


    }

    @Test
    public void test13VerificoFormatoValidoJSONEnemigos() throws FileNotFoundException {
        FileReader fileReader = new FileReader("src/temp/enemigos.json");
        ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion(fileReader);
        assertDoesNotThrow(convertidor::cargarEnemigos);
    }


    @Test
    public void test14VerificoFormatoValidoJSONMapa() throws FileNotFoundException {
        FileReader fileReader = new FileReader("src/temp/mapa.json");
        ConvertidorMapa convertidor = new ConvertidorMapaImplementacion(fileReader);
        assertDoesNotThrow(convertidor::cargarMapa);
    }

    @Test
    public void test15VerificoCorrectaLecturaYConversionDeJsonEnemigos() throws IOException {
        FileReader fileReader = new FileReader("src/temp/enemigos.json");
        ConvertidorEnemigos convertidor = new ConvertidorEnemigosImplementacion(fileReader);
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
        assertEquals(2, enemigosRonda5.size(), "Número incorrecto de enemigos para la ronda " + rondaAComprobar);

        // Comprobar la longitud de las rondas
        int rondasEsperadas = 12;
        assertEquals(rondasEsperadas, enemigosPorRonda.get().size(), "Número incorrecto de rondas");
    }

    @Test
    public void test16VerificoCorrectaLecturaYConversionDeJSONMapa() throws IOException, ParseException, FormatoJSONInvalidoException {
        Reader reader = new FileReader("src/temp/mapa.json");
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
				    FileReader reader = new FileReader("src/temp/mapa.json");
				    ConvertidorMapa convertidor = new ConvertidorMapaImplementacion(reader);
				    Mapa mapa = convertidor.cargarMapa();

				    FileReader readerEnemigos = new FileReader("src/temp/enemigos.json");
				    ConvertidorEnemigos convertidorEnemigos = new ConvertidorEnemigosImplementacion(readerEnemigos);
				    List<Enemigo> enemigos = convertidorEnemigos.cargarEnemigos().values().stream()
						    .flatMap(List::stream)
						    .peek(enemigo -> enemigo.setPasarelaActual(mapa.getOrigen()))
						    .collect(Collectors.toList());

				    new AlgoDefense(mapa, enemigos);
						// podriamos tener un turno y verificar si tal estado es el esperado para crear o mockearlo y verificar que se llame
			    }
	    );
    }
    @Test
    void test18SimuloYVerificoQueGanaJugador() throws FormatoJSONInvalidoException, IOException, ParseException, NombreInvalido, TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, TorreNoDesplegada {
        TorrePlateada torre = new TorrePlateada();
        TorrePlateada torre1 = new TorrePlateada();
        AlgoDefense algoDefense = new AlgoDefense();
        algoDefense.agregarJugador("Sebastian");
        algoDefense.cargarEnemigos(2);
        algoDefense.ubicarDefensa(torre, 0, 2);
        algoDefense.ubicarDefensa(torre1, 1, 0);
        algoDefense.moverEnemigos();
        algoDefense.activarDefensas();
        String ganador = algoDefense.finDelJuego();
        assertEquals(ganador, "Sebastian");


    }
    @Test
    void test19SimuloYVerificoQueGanaComputadora() throws FormatoJSONInvalidoException, IOException, ParseException, TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, NombreInvalido {
        AlgoDefense algoDefense = new AlgoDefense();
        algoDefense.agregarJugador("Sebastian");
        algoDefense.cargarEnemigos(12);
        for (int i = 0; i < 24; i++) {
            algoDefense.moverEnemigos();
        }
        String ganador = algoDefense.finDelJuego();
        assertEquals(ganador, "Computadora");
    }

    @Test
    void test20VerificarSistemaDeLog(){
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
        }}
}
