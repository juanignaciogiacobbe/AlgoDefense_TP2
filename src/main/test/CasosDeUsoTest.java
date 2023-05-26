import clases.Jugador;

import clases.TorrePlateada;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CasosDeUsoTest {


    @Test
    void test01JugadorComienzaConVidaYCreditosCorrespondientes() {
        Jugador jugador = new Jugador();
        int vidaEsperada = 20;
        int creditosEsperados = 100;
        assertEquals(vidaEsperada, jugador.getVida());
        assertEquals(creditosEsperados, jugador.getCreditos());

    }

    @Test
    void test02DefensaPuedeSerUtilizadaLuegoDeCrearse() {
        TorrePlateada torre = new TorrePlateada();
        assertFalse(torre.estaconstruida());
        /* torre.sumarturno() -> Simula que pasa un turno en la partida*/
        torre.sumarturno();
        torre.sumarturno();
        assertTrue(torre.estaconstruida());


    }

    @Test
    void test03VerificoQueJugadorPuedaConstruir(){
        Jugador jugador= new Jugador();
        TorrePlateada torre=new TorrePlateada();
        assertTrue(jugador.puedeconstruir(torre.getCostoConstruccion()));
    }
}
