import Excepciones.CreditosInsuficientes;
import Excepciones.NombreInvalido;
import Excepciones.TerrenoNoAptoParaConstruir;
import clases.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


import clases.vida.SinVidaRestanteException;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class JugadorTest {

    @Test
    public void test01JugadorSeCreaConNombreMenorA6CaracteresLanzaExcepcion() {
        assertThrows(NombreInvalido.class, () -> {
            new Jugador("Alan");
        });
    }

    @Test
    public void test02JugadorUtilizaTodosSusCreditosNoDeberiaSaltarExcepcion() throws NombreInvalido {
        Jugador jugador = new Jugador("Mariana");

        for (int i = 0;i < 10; i++) {
            ParcelaDeTierra tierra = new ParcelaDeTierra(0,1);
            TorreBlanca torre = new TorreBlanca();
            assertDoesNotThrow(() -> {jugador.construir(torre, tierra);});
        }
    }

    @Test
    public void test03JugadorUtilizaTodosSusCreditosIntentaConstruirUnaVezMasDeberiaSaltarExcepcion() throws NombreInvalido, TerrenoNoAptoParaConstruir, CreditosInsuficientes {
        Jugador jugador = new Jugador("Mariana");

        for (int i = 0;i < 10; i++) {
            ParcelaDeTierra tierra = new ParcelaDeTierra(0,1);
            TorreBlanca torre = new TorreBlanca();
            jugador.construir(torre, tierra);
        }
        ParcelaDeTierra tierra = new ParcelaDeTierra(1,1);
        TorreBlanca torre = new TorreBlanca();
        assertThrows(CreditosInsuficientes.class, () -> {jugador.construir(torre, tierra);});
    }

    @Test
    public void test04CreoUnJugadorYRecibeElDanioCorrespondienteYMuere() throws NombreInvalido {
        Jugador jugador = new Jugador("Juancito");

        jugador.recibirDanio(25);


        assertEquals(jugador.getVida(), -5);
        assertThrows(SinVidaRestanteException.class, ()-> {jugador.comenzarTurno();});
    }
}
