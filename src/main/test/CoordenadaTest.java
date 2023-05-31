import clases.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;



public class CoordenadaTest {
    @Test
    public void test01CreoUnaCoordenadaYObtengoSuVecinoIzquierdo() {
        Coordenada coordenada = new Coordenada(1, 1);

        Coordenada vecino = coordenada.obtenerVecinoIzquierdo();

        assertEquals(vecino.getAbscisa(), 0);
        assertEquals(vecino.getOrdenada(), 1);
    }

    @Test
    public void test02CreoUnaCoordenadaYObtengoSuVecinoDerecho() {
        Coordenada coordenada = new Coordenada(1, 1);

        Coordenada vecino = coordenada.obtenerVecinoDerecho();

        assertEquals(vecino.getAbscisa(), 2);
        assertEquals(vecino.getOrdenada(), 1);
    }

    @Test
    public void test03CreoUnaCoordenadaYObtengoSuVecinoSuperior() {
        Coordenada coordenada = new Coordenada(1, 1);

        Coordenada vecino = coordenada.obtenerVecinoSuperior();

        assertEquals(vecino.getAbscisa(), 1);
        assertEquals(vecino.getOrdenada(), 2);
    }

    @Test
    public void test04CreoUnaCoordenadaYObtengoSuVecinoInferior() {
        Coordenada coordenada = new Coordenada(1, 1);

        Coordenada vecino = coordenada.obtenerVecinoInferior();

        assertEquals(vecino.getAbscisa(), 1);
        assertEquals(vecino.getOrdenada(), 0);
    }
}
