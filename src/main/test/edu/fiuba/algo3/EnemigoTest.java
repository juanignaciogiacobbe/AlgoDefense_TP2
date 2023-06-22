package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.convertidor.FormatoJSONInvalidoException;
import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.defensas.TorreBlanca;
import edu.fiuba.algo3.modelo.defensas.TorreNoDesplegada;
import edu.fiuba.algo3.modelo.defensas.TorrePlateada;
import edu.fiuba.algo3.modelo.enemigos.*;
import edu.fiuba.algo3.modelo.juego.CreditosInsuficientes;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.NombreInvalido;
import edu.fiuba.algo3.modelo.parcelas.*;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EnemigoTest {
	@Test
	public void test01CreoUnaHormigaYSusPropiedadesSonLasIndicadas() {
		PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
		Hormiga hormiga = new Hormiga(pasarelaLargada);
		int puntosDeEnergiaEsperados = 1;
		assertEquals(hormiga.getVida(),puntosDeEnergiaEsperados);
	}

	@Test
	public void test02CreoUnaAraniaYSusPropiedadesSonLasIndicadas() {
		PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
		Arania arania = new Arania(pasarelaLargada);
		int puntosDeEnergiaEsperados = 2;
		assertEquals(arania.getVida(),puntosDeEnergiaEsperados);
	}

	@Test
	public void test03AraniaNoPuedeSerAtacadoPorNoEstarEnRangoLanzaExcepcion() {
		PasarelaComun pasarela = new PasarelaComun(1, 1);
		Arania arania = new Arania(pasarela);
		ParcelaDeTierra parcelaDefensa = new ParcelaDeTierra(3, 1);
		assertThrows(EnemigoFueraDeRango.class, () -> arania.recibirAtaque(parcelaDefensa, 1, 2));
	}

	@Test
	public void test04HormigaMuereAlRecolectarCreditosDaSusCreditos() throws EnemigoFueraDeRango, EnemigoNoDaniable {
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
		assertEquals(0, hormiga.recolectarCreditos());

	}

	@Test
	public void test05AgregoLechuzaADefensaYAtaca() throws FormatoJSONInvalidoException, IOException, ParseException, NombreInvalido, TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, DefensasVacias, CreditosInsuficientes {
		AlgoDefense algoDefense = new AlgoDefense();
		algoDefense.agregarJugador("Sebastian");
		Lechuza lechuza = new Lechuza((ParcelaDePasarela) algoDefense.getMapa().obtenerParcelaConCoordenadas(10,13));
		TorreBlanca torre = new TorreBlanca();
		algoDefense.agregarEnemigo(lechuza);
		algoDefense.construir(torre, algoDefense.getMapa().obtenerParcelaConCoordenadas(0,2));
		assertEquals(1,algoDefense.obtenerCantidadDefensas());
		algoDefense.moverEnemigos();
		algoDefense.moverEnemigos();
		assertEquals(0,algoDefense.obtenerCantidadDefensas());

	}

	@Test
	public void test06CreoUnTopoYAlAtacarloNoRecibeDanio() {
		PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
		ParcelaDeTierra parcelaDefensa = new ParcelaDeTierra(1, 1);
		Topo topo = new Topo(pasarelaLargada);

		assertThrows(EnemigoNoDaniable.class, () -> topo.recibirAtaque(parcelaDefensa, 10, 2));
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
	public void test08LaLechuzaSeMueveDeFormaCorrecta() throws FormatoJSONInvalidoException, IOException, ParseException, NombreInvalido, TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, DefensasVacias, CreditosInsuficientes {
		AlgoDefense algoDefense = new AlgoDefense();
		algoDefense.agregarJugador("Sebastian");
		TorreBlanca torre = new TorreBlanca();
		Parcela parcela = algoDefense.getMapa().obtenerParcelaConCoordenadas(0,2);
		algoDefense.construir(torre, parcela);
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
	public void test09LaLechuzaSeMueveDeFormaCorrectaConLaMitadDeSuVida() throws FormatoJSONInvalidoException, IOException, ParseException, NombreInvalido, TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, DefensasVacias, TorreNoDesplegada, EnemigosFueraDeRango, EnemigoFueraDeRango, EnemigoNoDaniable {
		AlgoDefense algoDefense = new AlgoDefense();
		algoDefense.agregarJugador("Sebastian");
		TorrePlateada torre = new TorrePlateada();
		ParcelaDeTierra tierra = new ParcelaDeTierra(1,1);
		tierra.setDefensa(torre);
		torre.pasarTurno();
		torre.pasarTurno();
		Hormiga enemigo = new Hormiga(algoDefense.getMapa().getOrigen());
		enemigo.recibirAtaque(tierra,10,3);
		algoDefense.agregarEnemigo(enemigo);
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
