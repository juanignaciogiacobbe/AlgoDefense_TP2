import Excepciones.NombreInvalido;
import clases.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CasosDeUsoTest {

    @Test
    public void test01JugadorComienzaConVidaYCreditosCorrespondientes() {
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
    public void test03VerificoQueJugadorPuedaConstruir() {
        Jugador jugador = new Jugador("Mariana");
        TorrePlateada torre = new TorrePlateada();
        int costoConstruccion = torre.getCostoConstruccion();
        boolean puedeConstruir = jugador.construir(costoConstruccion);
        assertTrue(puedeConstruir);
    }

    @Test
    public void test04VerificoQueSePuedeConstruirDefensasSoloSobreTierra() {
        TorrePlateada defensa = new TorrePlateada();
        ParcelaDeTierra tierra = new ParcelaDeTierra(1, 0);
        ParcelaRocosa rocoso = new ParcelaRocosa(0, 1);

        assertTrue(tierra.puedoConstruir(defensa));
        assertFalse(rocoso.puedoConstruir(defensa));
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
        Arania arania = new Arania();
        arania.recibirDanio(1);
        assertEquals(arania.getVida(), 1);
    }

    @Test
    public void test07EnemigosCaminanPorTerrenoValido() {
        PasarelaComun parcela2 = new PasarelaComun(1, 2);
        ParcelaRocosa parcela3 = new ParcelaRocosa(1, 0);
        Arania arania = new Arania();
        assertTrue(arania.puedeMoverseA(parcela2));
        assertFalse(arania.puedeMoverseA(parcela3));


    }

    @Test
    public void test08JugadorObtieneCreditosAlDestruirEnemigo() {
        Jugador jugador = new Jugador("pepito");
        TorrePlateada defensa = new TorrePlateada();
        Hormiga hormiga = new Hormiga();
        int creditosObtenidos = defensa.atacarA(hormiga);
        jugador.agregarCreditos(creditosObtenidos);
        assertEquals(jugador.getCreditos(), 101);
    }

    @Test
    public void test09EnemigosSeMuevenPorMapa() {
        AlgoDefense algodefense = new AlgoDefense();
        Enemigo enemigo = new Hormiga();
        Mapa mapa = algodefense.getMapa();
        algodefense.agregarEnemigo(enemigo);
        for (int i = 0; i < 4; i++) {
            mapa.reiniciarEnemigosPasarelas();
            mapa.moverEnemigos(mapa.getOrigen());
        }
        assertEquals(1, algodefense.obtenersizeMeta());
    }

    @Test
    public void test10AlEliminarTodasLasUnidaesEnemigasGanaElJugador() {

        AlgoDefense algoDefense = new AlgoDefense();
        Enemigo enemigo = new Hormiga();
        Mapa mapa = algoDefense.getMapa();
        algoDefense.agregarEnemigo(enemigo);
        TorrePlateada torre = new TorrePlateada();
        torre.atacarA(enemigo);
        mapa.reiniciarEnemigosPasarelas();
        mapa.moverEnemigos(mapa.getOrigen());
        assertDoesNotThrow(() -> algoDefense.agregarJugador("Mariana"));
        String ganador = algoDefense.finDelJuego();
        assertEquals(ganador, "Mariana");
    }

    @Test
    public void test11NoSeEliminanTodasLasUnidaesEnemigasPeroNoAlcanzaElDanioGanaElJugador() throws NombreInvalido {

        AlgoDefense algoDefense = new AlgoDefense();
        Enemigo enemigo = new Hormiga();
        Enemigo enemigo2 = new Hormiga();
        Mapa mapa = algoDefense.getMapa();
        algoDefense.agregarEnemigo(enemigo);
        algoDefense.agregarEnemigo(enemigo2);
        TorrePlateada torre = new TorrePlateada();
        torre.atacarA(enemigo);
        for (int i = 0; i < 4; i++) {
            mapa.reiniciarEnemigosPasarelas();
            mapa.moverEnemigos(mapa.getOrigen());
        }
        assertDoesNotThrow(() -> algoDefense.agregarJugador("Mariana"));
        algoDefense.agregarJugador("Mariana");
        String ganador = algoDefense.finDelJuego();
        assertEquals(ganador, "Mariana");
    }

    @Test
    public void test12NoSeEliminanTodasLasUnidadesEnemigasPeroAlcanzaElDanioGanaLaComputadora() throws NombreInvalido {
        AlgoDefense algoDefense = new AlgoDefense();
        Mapa mapa = algoDefense.getMapa();
        for (int i = 0; i < 30; i++) {
            Arania arania = new Arania();
            algoDefense.agregarEnemigo(arania);
        }
        for (int i = 0; i < 4; i++) {
            mapa.reiniciarEnemigosPasarelas();
            mapa.moverEnemigos(mapa.getOrigen());
        }

        algoDefense.agregarJugador("Mariana");
        String ganador = algoDefense.finDelJuego();
        assertEquals(ganador, "Computadora");
    }
}
