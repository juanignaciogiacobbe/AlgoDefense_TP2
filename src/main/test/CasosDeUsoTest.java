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
        TorrePlateada defensa = new TorrePlateada();

        defensa.restarTurnoParaDespliegue();
        assertFalse(defensa.estaDesplegada());
        defensa.restarTurnoParaDespliegue();
        assertTrue(defensa.estaDesplegada());
    }

    @Test
    public void test03VerificoQueJugadorPuedaConstruir() {
        Jugador jugador = new Jugador();
        TorrePlateada torre = new TorrePlateada();
        int costoConstruccion = torre.getCostoConstruccion();
        boolean puedeConstruir = jugador.construir(costoConstruccion);
        assertTrue(puedeConstruir);
    }

    @Test
    public void test04VerificoQueSePuedeConstruirDefensasSoloSobreTierra() {
        TorrePlateada defensa = new TorrePlateada();
        ParcelaDeTierra tierra = new ParcelaDeTierra();
        ParcelaRocosa rocoso = new ParcelaRocosa();

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
        Arania arania = new Arania();
        Rocoso rocoso = new Rocoso();
        Pasarela pasarela = new Pasarela();
        assertTrue(arania.puedeCaminarEn(pasarela));
        assertFalse(arania.puedeCaminarEn(rocoso));
    }

    @Test
    public void test08JugadorObtieneCreditosAlDestruirEnemigo() {
        Jugador jugador = new Jugador();
        TorrePlateada defensa = new TorrePlateada();
        Hormiga hormiga = new Hormiga();
        int creditosObtenidos = defensa.atacarA(hormiga);
        jugador.agregarCreditos(creditosObtenidos);
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