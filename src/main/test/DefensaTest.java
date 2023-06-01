import clases.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class DefensaTest {
    @Test
    public void test01CreoUnaTorreBlancaYSusPropiedadesSonLasIndicadas() {
        TorreBlanca torreBlanca = new TorreBlanca();
        int creditosNecesariosParaConstruirLaDefensa = 10;

        assertEquals(torreBlanca.getCostoConstruccion(), creditosNecesariosParaConstruirLaDefensa);
    }

    @Test
    public void test02CreoUnaTorrePlateadaYSusPropiedadesSonLasIndicadas() {
        TorrePlateada torrePlateada = new TorrePlateada();
        int creditosNecesariosParaConstruirLaDefensa = 20;

        assertEquals(torrePlateada.getCostoConstruccion(), creditosNecesariosParaConstruirLaDefensa);
    }

    /*@Test

    public void test03PruebaFuncionamiento() {
        AlgoDefense algo = new AlgoDefense();
        Hormiga hormiga = new Hormiga(algo.getMapa().getOrigen());
        algo.agregarEnemigo(hormiga);
        algo.moverEnemigos();
        algo.moverEnemigos();
        algo.comenzarAtaqueJugador();
        assertEquals(0,hormiga.getVida());
    }*/
}
