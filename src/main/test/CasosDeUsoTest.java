import clases.Arania;
import clases.Jugador;

import clases.Rocoso;
import clases.Tierra;
import clases.TorrePlateada;
import org.junit.jupiter.api.Test;

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
    public void test03VerificoQueJugadorPuedaConstruir(){
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

        arania.recibirDanio(1);
        assertEquals(arania.vida(), 1);
    }

}
