import Excepciones.NombreInvalido;
import Excepciones.TerrenoNoAptoParaConstruir;
import clases.*;

import java.io.FileNotFoundException;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CasosDeUsoTest {

    @Test
    public void test01JugadorComienzaConVidaYCreditosCorrespondientes() throws NombreInvalido{
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
        ParcelaDeTierra tierra = new ParcelaDeTierra(0,1);
        assertDoesNotThrow(() -> {jugador.construir(torre, tierra);});
    }

    @Test
    public void test04VerificoQueSePuedeConstruirDefensasSoloSobreTierra()  {
        TorrePlateada defensa = new TorrePlateada();
        ParcelaDeTierra tierra = new ParcelaDeTierra(1, 0);
        ParcelaRocosa rocoso = new ParcelaRocosa(0, 1);

        assertDoesNotThrow( () -> {tierra.construir(defensa);});
        assertThrows(TerrenoNoAptoParaConstruir.class, () -> {
            rocoso.construir(defensa);});
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
        assertTrue(arania.tieneVidaIgualA(1));
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
    public void test08JugadorObtieneCreditosAlDestruirEnemigo() throws NombreInvalido{
        Jugador jugador = new Jugador("pepito");
        TorrePlateada defensa = new TorrePlateada();
        PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
        Hormiga hormiga = new Hormiga(pasarelaLargada);
        defensa.atacarA(hormiga);
        int creditosObtenidos = 0;
        creditosObtenidos = hormiga.recolectarCreditos(creditosObtenidos);
        jugador.agregarCreditos(creditosObtenidos);
        assertEquals(jugador.getCreditos(), 101);
    }

    @Test
    public void test09EnemigosSeMuevenPorMapa() throws FileNotFoundException {
        AlgoDefense algodefense = new AlgoDefense();
        Enemigo enemigo = new Hormiga(algodefense.getMapa().getOrigen());
        algodefense.agregarEnemigo(enemigo);
        for (int i = 0; i < 23; i++) {
            algodefense.moverEnemigos();

        }

        assertEquals(1, algodefense.enemigosEnMeta());
    }

    @Test
    public void test10AlEliminarTodasLasUnidaesEnemigasGanaElJugador() throws FileNotFoundException {
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
    public void test11NoSeEliminanTodasLasUnidaesEnemigasPeroNoAlcanzaElDanioGanaElJugador() throws NombreInvalido, FileNotFoundException {
        AlgoDefense algoDefense = new AlgoDefense();
        Mapa mapa = algoDefense.getMapa();
        Enemigo enemigo = new Hormiga( mapa.getOrigen());
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
    public void test12NoSeEliminanTodasLasUnidadesEnemigasPeroAlcanzaElDanioGanaLaComputadora() throws NombreInvalido, FileNotFoundException {
        AlgoDefense algoDefense = new AlgoDefense();
        Mapa mapa = algoDefense.getMapa();
        for (int i = 0; i < 23; i++) {
            Hormiga hormiga = new Hormiga(mapa.getOrigen());
            algoDefense.agregarEnemigo(hormiga);
        }
        for (int i = 0; i < 23; i++) {
            algoDefense.moverEnemigos();
        }


        algoDefense.agregarJugador("Mariana");
        String ganador = algoDefense.finDelJuego();
        assertEquals(ganador, "Computadora");
    }
}
