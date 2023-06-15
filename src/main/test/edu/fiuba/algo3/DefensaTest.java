package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.defensas.TorreBlanca;
import edu.fiuba.algo3.modelo.defensas.TorrePlateada;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.NombreInvalido;
import edu.fiuba.algo3.modelo.parcelas.PasarelaLargada;
import edu.fiuba.algo3.modelo.parcelas.PasarelaMeta;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;
import edu.fiuba.algo3.modelo.parcelas.TrampaArenosa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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

	@Test
	public void test03UnaTrampaArenosaNoSePuedeConstruirEnUnPasarelaInvalida() throws NombreInvalido {
		Jugador jugador = new Jugador("Jugador");
		TrampaArenosa trampaArenosa = new TrampaArenosa();
		PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 1);
		PasarelaMeta pasarelaMeta = new PasarelaMeta(0, 2);
		assertThrows(TerrenoNoAptoParaConstruir.class, () -> jugador.construir(trampaArenosa, pasarelaLargada));
		assertThrows(TerrenoNoAptoParaConstruir.class, () -> jugador.construir(trampaArenosa, pasarelaMeta));
	}
}
