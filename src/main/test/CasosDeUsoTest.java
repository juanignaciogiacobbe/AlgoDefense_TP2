import Excepciones.NombreInvalido;
import Excepciones.TerrenoNoAptoParaConstruir;
import clases.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


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
        assertEquals(arania.getVida(), 1);
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
        int creditosObtenidos = defensa.atacarA(hormiga);
        jugador.agregarCreditos(creditosObtenidos);
        assertEquals(jugador.getCreditos(), 101);
    }

    @Test
    public void test09EnemigosSeMuevenPorMapa() {
        PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
        AlgoDefense algodefense = new AlgoDefense();
        Enemigo enemigo = new Hormiga(pasarelaLargada);
        Mapa mapa = algodefense.getMapa();
        algodefense.agregarEnemigo(enemigo);
        for (int i = 0; i < 4; i++) {
            algodefense.moverEnemigos();
        }
        assertEquals(1, algodefense.obtenersizeMeta());
    }

    @Test
    public void test10AlEliminarTodasLasUnidaesEnemigasGanaElJugador() {
        PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
        AlgoDefense algoDefense = new AlgoDefense();
        Mapa mapa = algoDefense.getMapa();
        Enemigo enemigo = new Hormiga((PasarelaLargada) mapa.getOrigen());
        algoDefense.agregarEnemigo(enemigo);
        TorrePlateada torre = new TorrePlateada();
        torre.atacarA(enemigo);
        /*mapa.reiniciarEnemigosPasarelas();
        mapa.moverEnemigos(mapa.getOrigen());*/
        assertDoesNotThrow(() -> algoDefense.agregarJugador("Mariana"));
        algoDefense.moverEnemigos();
        String ganador = algoDefense.finDelJuego();
        assertEquals(ganador, "Mariana");
    }

    @Test
    public void test11NoSeEliminanTodasLasUnidaesEnemigasPeroNoAlcanzaElDanioGanaElJugador() throws NombreInvalido {
        AlgoDefense algoDefense = new AlgoDefense();
        Mapa mapa = algoDefense.getMapa();
        Enemigo enemigo = new Hormiga((PasarelaLargada) mapa.getOrigen());
        Enemigo enemigo2 = new Hormiga((PasarelaLargada) mapa.getOrigen());
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
    public void test12NoSeEliminanTodasLasUnidadesEnemigasPeroAlcanzaElDanioGanaLaComputadora() throws NombreInvalido {
        PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
        AlgoDefense algoDefense = new AlgoDefense();
        Mapa mapa = algoDefense.getMapa();
        for (int i = 0; i < 30; i++) {
            Arania arania = new Arania((PasarelaLargada) mapa.getOrigen());
            algoDefense.agregarEnemigo(arania);
        }

        for (int i = 0; i < 2; i++) {
            algoDefense.moverEnemigos();
        }


        algoDefense.agregarJugador("Mariana");
        String ganador = algoDefense.finDelJuego();
        assertEquals(ganador, "Computadora");
    }
}
