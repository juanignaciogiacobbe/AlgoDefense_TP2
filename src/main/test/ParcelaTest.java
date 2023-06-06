import Excepciones.TerrenoNoAptoParaConstruir;
import clases.*;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class  ParcelaTest {
    @Test
    public void test01CreoUnaParcelaYSeInicializaCorrectamente() {

    }

    @Test
    public void test02NoPuedoConstruirUnaDefensaSobreUnaPasarelaDeLargada() {
        PasarelaLargada pasarela = new PasarelaLargada(0,1);
        TorrePlateada defensa = new TorrePlateada();

        assertThrows(TerrenoNoAptoParaConstruir.class, ()-> {pasarela.construir(defensa);});
    }


    @Test
    public void test03NoPuedoConstruirUnaDefensaSobreUnaPasarelaComun() {
        PasarelaComun pasarela = new PasarelaComun(0,1);
        TorrePlateada defensa = new TorrePlateada();

        assertThrows(TerrenoNoAptoParaConstruir.class, ()-> {pasarela.construir(defensa);});
    }

    @Test
    public void test04NoPuedoConstruirUnaDefensaSobreUnaPasarelaMeta() {
        PasarelaMeta pasarela = new PasarelaMeta(0,1);
        TorrePlateada defensa = new TorrePlateada();

        assertThrows(TerrenoNoAptoParaConstruir.class, ()-> {pasarela.construir(defensa);});
    }
/*
    @Test


    public void test05ConstruyoUnaDefensaEnUnaUbicacionEsaUbicacionAhoraEsDefendible() throws TerrenoNoAptoParaConstruir {
        ParcelaDeTierra tierra = new ParcelaDeTierra(0,1);
        TorrePlateada defensa = new TorrePlateada();
        tierra.construir(defensa);
        assertTrue(tierra.defender());
    }
    @Test
    public void test06NoConstruyoUnaDefensaEnUnaUbicacionEsaNoEsDefendible() {
        ParcelaDeTierra tierra = new ParcelaDeTierra(0,1);
        assertFalse(tierra.defender());
    }
*/
}
