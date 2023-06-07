import Excepciones.EnemigoFueraDeRango;
import Excepciones.TerrenoNoAptoParaConstruir;
import clases.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnemigoTest {
    @Test
    public void test01CreoUnaHormigaYSusPropiedadesSonLasIndicadas() {
        PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
        Hormiga hormiga = new Hormiga(pasarelaLargada);
        int puntosDeEnergiaEsperados = 1;

        assertTrue(hormiga.tieneVidaIgualA(puntosDeEnergiaEsperados));
    }

    @Test
    public void test02CreoUnaAraniaYSusPropiedadesSonLasIndicadas() {
        PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
        Arania arania = new Arania(pasarelaLargada);
        int puntosDeEnergiaEsperados = 2;

        assertTrue(arania.tieneVidaIgualA(puntosDeEnergiaEsperados));
    }

    @Test
    public void test03HormigaNoPuedeSerAtacadoPorNoEstarEnRangoLanzaExcepcion() {
        PasarelaComun pasarela = new PasarelaComun(1,1);
        Arania arania = new Arania(pasarela);
        ParcelaDeTierra parcelaDefensa = new ParcelaDeTierra(3,1);

        assertThrows(EnemigoFueraDeRango.class, ()-> {arania.recibirAtaque(parcelaDefensa, 1, 2);});
    }
}
