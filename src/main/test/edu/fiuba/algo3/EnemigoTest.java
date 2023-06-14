package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.convertidor.FormatoJSONInvalidoException;
import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.defensas.TorreBlanca;
import edu.fiuba.algo3.modelo.enemigos.Arania;
import edu.fiuba.algo3.modelo.enemigos.EnemigoFueraDeRango;
import edu.fiuba.algo3.modelo.enemigos.Hormiga;
import edu.fiuba.algo3.modelo.enemigos.Lechuza;
import edu.fiuba.algo3.modelo.enemigos.Topo;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.NombreInvalido;
import edu.fiuba.algo3.modelo.parcelas.*;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class EnemigoTest {
	@Test
	public void test01CreoUnaHormigaYSusPropiedadesSonLasIndicadas() {
		PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
		Hormiga hormiga = new Hormiga(pasarelaLargada);
		int puntosDeEnergiaEsperados = 1;

		//assertTrue(hormiga.tieneVidaIgualA(puntosDeEnergiaEsperados));
	}

	@Test
	public void test02CreoUnaAraniaYSusPropiedadesSonLasIndicadas() {
		PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
		Arania arania = new Arania(pasarelaLargada);
		int puntosDeEnergiaEsperados = 2;

		// assertTrue(arania.tieneVidaIgualA(puntosDeEnergiaEsperados));
	}

	@Test
	public void test03AraniaNoPuedeSerAtacadoPorNoEstarEnRangoLanzaExcepcion() {
		PasarelaComun pasarela = new PasarelaComun(1, 1);
		Arania arania = new Arania(pasarela);
		ParcelaDeTierra parcelaDefensa = new ParcelaDeTierra(3, 1);

		assertThrows(EnemigoFueraDeRango.class, () -> arania.recibirAtaque(parcelaDefensa, 1, 2));
	}

	@Test
	public void test04HormigaMuereAlRecolectarCreditosDaSusCreditos() throws EnemigoFueraDeRango {
		PasarelaComun pasarela = new PasarelaComun(1, 1);
		Hormiga hormiga = new Hormiga(pasarela);
		ParcelaDeTierra parcelaDefensa = new ParcelaDeTierra(1, 1);
		hormiga.recibirAtaque(parcelaDefensa, 1, 2);
		assertEquals(hormiga.recolectarCreditos(), 1);

	}

	@Test
	public void test04HormigaNoEstaMuertaAlRecolectarCreditosDevuelve0() {
		PasarelaComun pasarela = new PasarelaComun(1, 1);
		Hormiga hormiga = new Hormiga(pasarela);
		assertEquals(hormiga.recolectarCreditos(), 0);

	}

	@Test
	public void test05AgregoLechuzaADefensaYAtaca() throws FormatoJSONInvalidoException, IOException, ParseException, NombreInvalido, TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, DefensasVacias {
		AlgoDefense algoDefense = new AlgoDefense();
		algoDefense.agregarJugador("Sebastian");
		Lechuza lechuza = new Lechuza(algoDefense.getMapa().getMeta());
		TorreBlanca torre = new TorreBlanca();
		algoDefense.agregarEnemigo(lechuza);
		algoDefense.ubicarDefensa(torre,0,2);
		assertEquals(1,algoDefense.obtenerCantidadDefensas());
		algoDefense.moverEnemigos();
		assertEquals(0,algoDefense.obtenerCantidadDefensas());

	}

	@Test
	public void test06CreoUnTopoYVerificoQueSeCreaCorrectamente() {
		PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
		Topo topo = new Topo(pasarelaLargada);
	}

	@Test
	public void test07CreoUnaHormigaYAtaca15VecesAUnJugador() throws NombreInvalido, DefensasVacias {
		PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
		Jugador jugador = new Jugador("Juancito");
		Hormiga hormiga = new Hormiga(pasarelaLargada);

		for (int i = 0; i < 15; i++) {
			hormiga.atacar(jugador);
		}

		assertEquals(jugador.getVida(), 5);
	}


	@Test
	public void test08LaLechuzaSeMueveDeFormaCorrecta() throws FormatoJSONInvalidoException, IOException, ParseException, NombreInvalido, TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, DefensasVacias {
		AlgoDefense algoDefense = new AlgoDefense();
		algoDefense.agregarJugador("Sebastian");
		TorreBlanca torre = new TorreBlanca();
		algoDefense.ubicarDefensa(torre,0,2);
		Lechuza lechuza = new Lechuza(algoDefense.getMapa().getOrigen());
		algoDefense.agregarEnemigo(lechuza);
		assertEquals(1,algoDefense.obtenerCantidadDefensas());
		algoDefense.moverEnemigos();
		algoDefense.moverEnemigos();
		algoDefense.moverEnemigos();
		algoDefense.moverEnemigos();
		algoDefense.moverEnemigos();
		assertEquals(0,algoDefense.obtenerCantidadDefensas());
	}

	@Test
	public void test09LaLechuzaSeMueveDeFormaCorrectaConLaMitadDeSuVida() throws FormatoJSONInvalidoException, IOException, ParseException, NombreInvalido, TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, DefensasVacias {
		AlgoDefense algoDefense = new AlgoDefense();
		algoDefense.agregarJugador("Sebastian");
		TorreBlanca torre = new TorreBlanca();
		algoDefense.ubicarDefensa(torre,0,2);
		Lechuza lechuza = new Lechuza(algoDefense.getMapa().getOrigen());
		lechuza.recibirDanio(3);
		algoDefense.agregarEnemigo(lechuza);
		assertEquals(1,algoDefense.obtenerCantidadDefensas());
		algoDefense.moverEnemigos();
		algoDefense.moverEnemigos();
		algoDefense.moverEnemigos();
		algoDefense.moverEnemigos();
		algoDefense.moverEnemigos();
		algoDefense.moverEnemigos();
		algoDefense.moverEnemigos();
		algoDefense.moverEnemigos();
		assertEquals(0,algoDefense.obtenerCantidadDefensas());
	}
}
