import Excepciones.NombreInvalido;
import Excepciones.SinVidaRestante;
import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TerrenoNoAptoParaConstruir;
import clases.*;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

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
        TorrePlateada defensa = new TorrePlateada();

        defensa.restarTurnoParaDespliegue();
        assertFalse(defensa.estaDesplegada());
        defensa.restarTurnoParaDespliegue();
        assertTrue(defensa.estaDesplegada());
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

        assertDoesNotThrow(() -> {
            tierra.construir(defensa);
        });
        assertThrows(TerrenoNoAptoParaConstruir.class, () -> {
            rocoso.construir(defensa);
        });
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
        //assertTrue(//arania.tieneVidaIgualA(1));
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
        int creditos = hormiga.obtenerCreditos();
        jugador.agregarCreditos(creditos);
        assertEquals(jugador.getCreditos(), 101);
    }

    @Test
    public void test09EnemigosSeMuevenPorMapa() throws IOException, ParseException, FormatoJSONInvalidoException, TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar, NombreInvalido, SinVidaRestante {
        AlgoDefense algodefense = new AlgoDefense();
        algodefense.agregarJugador("Mariana");
        Enemigo enemigo = new Hormiga(algodefense.getMapa().getOrigen());
        algodefense.agregarEnemigo(enemigo);
        for (int i = 0; i < 23; i++) {
            algodefense.moverEnemigos();

        }
        assertEquals(1, algodefense.enemigosEnMeta());
    }

    @Test
    public void test10AlEliminarTodasLasUnidaesEnemigasGanaElJugador() throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar, FormatoJSONInvalidoException, IOException, ParseException, SinVidaRestante, NombreInvalido {
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
    public void test11NoSeEliminanTodasLasUnidaesEnemigasPeroNoAlcanzaElDanioGanaElJugador() throws NombreInvalido, IOException, ParseException, FormatoJSONInvalidoException, FileNotFoundException, TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar, SinVidaRestante {
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
    public void test12NoSeEliminanTodasLasUnidadesEnemigasPeroAlcanzaElDanioGanaLaComputadora() throws NombreInvalido, IOException, ParseException, FormatoJSONInvalidoException, TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar, SinVidaRestante {
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
    void test18SimuloYVerificoQueGanaJugador() throws FormatoJSONInvalidoException, IOException, ParseException, NombreInvalido, TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, SinVidaRestante {
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
    void test19SimuloYVerificoQueGanaComputadora() throws FormatoJSONInvalidoException, IOException, ParseException, TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, SinVidaRestante, NombreInvalido {
        AlgoDefense algoDefense = new AlgoDefense();
        algoDefense.agregarJugador("Sebastian");
        algoDefense.cargarEnemigos(12);
        for (int i = 0; i < 24; i++) {
            algoDefense.moverEnemigos();
        }
        String ganador = algoDefense.finDelJuego();
        assertEquals(ganador, "Computadora");
    }



}
