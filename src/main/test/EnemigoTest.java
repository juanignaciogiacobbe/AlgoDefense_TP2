import clases.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnemigoTest {
    @Test
    public void test01CreoUnaHormigaYSusPropiedadesSonLasIndicadas() {
        PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
        Hormiga hormiga = new Hormiga(pasarelaLargada);
        int puntosDeEnergiaEsperados = 1;

        assertEquals(hormiga.getVida(), puntosDeEnergiaEsperados);
    }

    @Test
    public void test02CreoUnaAraniaYSusPropiedadesSonLasIndicadas() {
        PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
        Arania arania = new Arania(pasarelaLargada);
        int puntosDeEnergiaEsperados = 2;

        assertEquals(arania.getVida(), puntosDeEnergiaEsperados);
    }
}
