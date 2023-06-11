package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;
import edu.fiuba.algo3.modelo.defensas.TorreBlanca;
import edu.fiuba.algo3.modelo.juego.CreditosInsuficientes;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.NombreInvalido;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDeTierra;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class JugadorTest {

	@Test
	public void test01JugadorSeCreaConNombreMenorA6CaracteresLanzaExcepcion() {
		assertThrows(NombreInvalido.class, () -> new Jugador("Alan"));
	}

	@Test
	public void test02JugadorUtilizaTodosSusCreditosNoDeberiaSaltarExcepcion() throws NombreInvalido {
		Jugador jugador = new Jugador("Mariana");

		for (int i = 0; i < 10; i++) {
			ParcelaDeTierra tierra = new ParcelaDeTierra(0, 1);
			TorreBlanca torre = new TorreBlanca();
			assertDoesNotThrow(() -> jugador.construir(torre, tierra));
		}
	}

	@Test
	public void test03JugadorUtilizaTodosSusCreditosIntentaConstruirUnaVezMasDeberiaSaltarExcepcion() throws NombreInvalido, TerrenoNoAptoParaConstruir, CreditosInsuficientes {
		Jugador jugador = new Jugador("Mariana");

		for (int i = 0; i < 10; i++) {
			ParcelaDeTierra tierra = new ParcelaDeTierra(0, 1);
			TorreBlanca torre = new TorreBlanca();
			jugador.construir(torre, tierra);
		}
		ParcelaDeTierra tierra = new ParcelaDeTierra(1, 1);
		TorreBlanca torre = new TorreBlanca();
		assertThrows(CreditosInsuficientes.class, () -> jugador.construir(torre, tierra));
	}
}
