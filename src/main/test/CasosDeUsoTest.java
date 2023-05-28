import clases.*;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CasosDeUsoTest {


    @Test
    public void test01JugadorComienzaConVidaYCreditosCorrespondientes() {
        Jugador jugador = new Jugador();
        int vidaEsperada = 20;
        int creditosEsperados = 100;
        assertEquals(vidaEsperada, jugador.getVida());
        assertEquals(creditosEsperados, jugador.getCreditos());

    }

    @Test
    public void test02DefensaPuedeSerUtilizadaLuegoDeCrearse() {
        TorrePlateada torre = new TorrePlateada();
        assertFalse(torre.estaconstruida());
        /* torre.sumarturno() -> Simula que pasa un turno en la partida*/
        torre.sumarturno();
        torre.sumarturno();
        assertTrue(torre.estaconstruida());


    }

    @Test
    public void test03VerificoQueJugadorPuedaConstruir() {
        Jugador jugador = new Jugador();
        TorrePlateada torre = new TorrePlateada();
        assertTrue(jugador.puedeconstruir(torre.getCostoConstruccion()));
    }

    @Test
    public void test04VerificoQueSePuedeConstruirDefensasSoloSobreTierra() {
        TorrePlateada torre = new TorrePlateada();
        Tierra tierra = new Tierra();
        Rocoso rocoso = new Rocoso();

        assertTrue(torre.puedoConstruirSobre(tierra));
        assertFalse(torre.puedoConstruirSobre(rocoso));
    }

    @Test
    public void test05VerificoQueLasDefensasAtaquenDentroDelRangoEsperado() {
        TorrePlateada torre = new TorrePlateada();

        assertTrue(torre.enemigoDentroDeRango(1));
        assertFalse(torre.enemigoDentroDeRango(7));
    }

    @Test
    public void test06VerificoQueLasUnidadesEnemigasSonDaniadasAcordeAlAtaqueRecibido() {
        Arania arania = new Arania();
        Jugador jugador = new Jugador();
        arania.recibirDanio(1, jugador);
        assertEquals(arania.vida(), 1);
    }

    @Test
    public void test07EnemigosCaminanPorTerrenoValido() {
        Arania arania = new Arania();
        Rocoso rocoso = new Rocoso();
        Pasarela pasarela = new Pasarela();
        assertTrue(arania.puedeCaminarEn(pasarela));
        assertFalse(arania.puedeCaminarEn(rocoso));
    }

    @Test
    public void test08JugadorObtieneCreditosAlDestruirEnemigo() {
        Jugador jugador = new Jugador();
        Hormiga hormiga = new Hormiga();
        hormiga.recibirDanio(2, jugador);
        assertEquals(jugador.getCreditos(), 101);
    }

    @Test
    public void test09EnemigosSeMuevenPorMapa() {
        Hormiga hormiga = new Hormiga();
        Arania arania = new Arania();
        Pasarela pasarela1 = new Pasarela();
        pasarela1.agregarEnemigos(hormiga);
        pasarela1.agregarEnemigos(arania);
        Pasarela pasarela2 = new Pasarela();
        Pasarela pasarela3 = new Pasarela();
        //Se mueve 2 lugares debido a su velocidad
        arania.mover(pasarela1, pasarela3);
        //Se mueve 1 lugar debido a su velocidad
        hormiga.mover(pasarela1, pasarela2);
        assertEquals(pasarela1.getUnidadesEnemigas().size(), 0);
        assertEquals(pasarela2.getUnidadesEnemigas().size(), 1);
        assertEquals(pasarela3.getUnidadesEnemigas().size(), 1);
    }

    @Test
    public void test10() {
    }
}