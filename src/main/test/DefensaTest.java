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
    public void test01CreoUnaTorrePlateadaYSusPropiedadesSonLasIndicadas() {
        TorrePlateada torrePlateada = new TorrePlateada();
        int creditosNecesariosParaConstruirLaDefensa = 20;

        assertEquals(torrePlateada.getCostoConstruccion(), creditosNecesariosParaConstruirLaDefensa);
    }
}
