package edu.fiuba.algo3;

import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.convertidor.FormatoJSONInvalidoException;
import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.defensas.TorreBlanca;
import edu.fiuba.algo3.modelo.defensas.TorrePlateada;
import edu.fiuba.algo3.modelo.defensas.TrampaArenosa;
import edu.fiuba.algo3.modelo.enemigos.Arania;
import edu.fiuba.algo3.modelo.enemigos.Lechuza;
import edu.fiuba.algo3.modelo.enemigos.Topo;
import edu.fiuba.algo3.modelo.juego.CreditosInsuficientes;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.juego.NombreInvalido;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.*;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

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

	@Test
	public void test03UnaTrampaArenosaNoSePuedeConstruirEnUnPasarelaInvalida() throws NombreInvalido {
		Jugador jugador = new Jugador("Jugador");
		TrampaArenosa trampaArenosa = new TrampaArenosa();
		PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 1);
		PasarelaMeta pasarelaMeta = new PasarelaMeta(0, 2);
		ParcelaDeTierra tierra = new ParcelaDeTierra(1, 1);
		ParcelaRocosa roca = new ParcelaRocosa(1, 2);
		assertThrows(TerrenoNoAptoParaConstruir.class, () -> jugador.construir(trampaArenosa, pasarelaLargada));
		assertThrows(TerrenoNoAptoParaConstruir.class, () -> jugador.construir(trampaArenosa, pasarelaMeta));
		assertThrows(TerrenoNoAptoParaConstruir.class, () -> jugador.construir(trampaArenosa, tierra));
		assertThrows(TerrenoNoAptoParaConstruir.class, () -> jugador.construir(trampaArenosa, roca));
	}

	@Test
	public void test04UnaTrampaArenosaSoloEsConstruibleEnUnaPasarelaComun() throws NombreInvalido {
		Jugador jugador = new Jugador("Jugador");
		TrampaArenosa trampaArenosa = new TrampaArenosa();
		PasarelaComun pasarelaComun = new PasarelaComun(0, 3);
		assertDoesNotThrow(() -> jugador.construir(trampaArenosa, pasarelaComun));
	}

	@Test
	public void test05TrampaArenosaDebeCostar25Creditos() throws NombreInvalido {
		Jugador jugador = new Jugador("Jugador");
		TrampaArenosa trampaArenosa = new TrampaArenosa();
		PasarelaComun pasarelaComun = new PasarelaComun(0, 3);
		assertDoesNotThrow(() -> jugador.construir(trampaArenosa, pasarelaComun));
		assertEquals(75, jugador.getCreditos());
	}

	@Test
	public void test06EnemigoPasaPorUnaTrampaArenosaEsRalentizado() throws NombreInvalido, TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir {
		Jugador jugador = new Jugador("Jugador");
		TrampaArenosa defensa = new TrampaArenosa();
		PasarelaComun pasarelaComun = new PasarelaComun(0, 1);
		PasarelaComun pasarelaComun2 = new PasarelaComun(0, 2);
		PasarelaMeta pasarelaMeta = new PasarelaMeta(0, 3);
		List<Parcela> pasarelas = List.of(pasarelaComun, pasarelaComun2, pasarelaMeta);
		Mapa mapa = new Mapa(pasarelas);
		mapa.setMeta(pasarelaMeta);
		assertDoesNotThrow(() -> jugador.construir(defensa, pasarelaComun));

		Arania arania = new Arania(pasarelaComun);
		arania.mover(mapa);
		assertEquals(pasarelaComun2.getCoordenada(), arania.getPasarelaActual().getCoordenada());

	}

	@Test
	public void test07EnemigoNoPasaPorUnaTrampaArenosaNoEsRalentizado() throws NombreInvalido, TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir {
		PasarelaComun pasarelaComun = new PasarelaComun(0, 1);
		PasarelaComun pasarelaComun2 = new PasarelaComun(0, 2);
		PasarelaMeta pasarelaMeta = new PasarelaMeta(0, 3);
		List<Parcela> pasarelas = List.of(pasarelaComun, pasarelaComun2, pasarelaMeta);
		Mapa mapa = new Mapa(pasarelas);
		mapa.setMeta(pasarelaMeta);

		Arania arania = new Arania(pasarelaComun);
		arania.mover(mapa);
		assertEquals(pasarelaMeta.getCoordenada(), arania.getPasarelaActual().getCoordenada());
	}

	@Test
	public void test08UnaLechuzaQuePasaPorUnaTrampaArenosaNoEsRealentizada() throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, NombreInvalido {
		Jugador jugador = new Jugador("Jugador");
		TrampaArenosa defensa = new TrampaArenosa();

		PasarelaComun pasarelaComun = new PasarelaComun(0, 1);
		PasarelaComun pasarelaComun2 = new PasarelaComun(0, 2);
		PasarelaMeta pasarelaMeta = new PasarelaMeta(0, 5);
		List<Parcela> pasarelas = List.of(pasarelaComun, pasarelaComun2, pasarelaMeta);
		Mapa mapa = new Mapa(pasarelas);
		mapa.setMeta(pasarelaMeta);

		assertDoesNotThrow(() -> jugador.construir(defensa, pasarelaComun));

		Lechuza lechuza = new Lechuza(pasarelaComun);
		lechuza.mover(mapa);
		assertNotEquals(pasarelaComun2.getCoordenada(), lechuza.getPasarelaActual().getCoordenada());
	}

	@Test
	public void test09UnTopoQuePasaPorUnaTrampaArenosaEsRealentizado() throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, NombreInvalido {
		Jugador jugador = new Jugador("Jugador");
		TrampaArenosa defensa = new TrampaArenosa();
		PasarelaComun pasarelaComun1 = new PasarelaComun(0, 1);
		PasarelaComun pasarelaComun2 = new PasarelaComun(0, 2);
		PasarelaComun pasarelaComun3 = new PasarelaComun(0, 3);
		PasarelaComun pasarelaComun4 = new PasarelaComun(0, 4);
		PasarelaComun pasarelaComun5 = new PasarelaComun(0, 5);
		PasarelaMeta pasarelaMeta = new PasarelaMeta(0, 6);
		List<Parcela> pasarelas = List.of(pasarelaComun1, pasarelaComun2, pasarelaComun3, pasarelaComun4, pasarelaComun5, pasarelaMeta);
		Mapa mapa = new Mapa(pasarelas);
		mapa.setMeta(pasarelaMeta);

		assertDoesNotThrow(() -> jugador.construir(defensa, pasarelaComun5));

		Topo topo = new Topo(pasarelaComun1);
		for (int i = 0; i < 5; i++) {
			topo.mover(mapa);
		}
		assertEquals(pasarelaMeta.getCoordenada(), topo.getPasarelaActual().getCoordenada());
	}

	@Test
	public void test10TrasDosTurnosLaTrampaArenosaRalentiza() throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, NombreInvalido, DefensasVacias, FormatoJSONInvalidoException, IOException, ParseException, CreditosInsuficientes {
		TrampaArenosa defensa = new TrampaArenosa();
		PasarelaComun pasarelaComun1 = new PasarelaComun(0, 1);
		PasarelaComun pasarelaComun2 = new PasarelaComun(0, 2);
		PasarelaMeta pasarelaMeta = new PasarelaMeta(0, 3);
		List<Parcela> pasarelas = List.of(pasarelaComun1, pasarelaComun2, pasarelaMeta);
		Mapa mapa = new Mapa(pasarelas);
		mapa.setMeta(pasarelaMeta);

		AlgoDefense algoDefense = new AlgoDefense(mapa);
		algoDefense.agregarJugador("Jugador");
		algoDefense.construir(defensa, pasarelaComun1);

		Arania arania1 = new Arania(pasarelaComun1);
		algoDefense.agregarEnemigo(arania1);
		algoDefense.moverEnemigos();

		Arania arania2 = new Arania(pasarelaComun1);
		algoDefense.agregarEnemigo(arania2);
		algoDefense.moverEnemigos();

		Arania arania3 = new Arania(pasarelaComun1);
		algoDefense.agregarEnemigo(arania3);
		algoDefense.moverEnemigos();

		assertEquals(pasarelaComun2.getCoordenada(), arania3.getPasarelaActual().getCoordenada());


	}

	@Test
	public void test11TrasTresTurnosLaTrampaArenosaNoRalentiza() throws TerrenoNoAptoParaCaminar, TerrenoNoAptoParaConstruir, NombreInvalido, DefensasVacias, FormatoJSONInvalidoException, IOException, ParseException, CreditosInsuficientes {
		TrampaArenosa defensa = new TrampaArenosa();
		PasarelaComun pasarelaComun1 = new PasarelaComun(0, 1);
		PasarelaComun pasarelaComun2 = new PasarelaComun(0, 2);
		PasarelaMeta pasarelaMeta = new PasarelaMeta(0, 3);
		List<Parcela> pasarelas = List.of(pasarelaComun1, pasarelaComun2, pasarelaMeta);
		Mapa mapa = new Mapa(pasarelas);
		mapa.setMeta(pasarelaMeta);
		AlgoDefense algoDefense = new AlgoDefense(mapa);
		algoDefense.agregarJugador("Jugador");
		algoDefense.construir(defensa, pasarelaComun1);


		Arania arania1 = new Arania(pasarelaComun1);
		algoDefense.agregarEnemigo(arania1);
		algoDefense.moverEnemigos();

		Arania arania2 = new Arania(pasarelaComun1);
		algoDefense.agregarEnemigo(arania2);
		algoDefense.moverEnemigos();

		Arania arania3 = new Arania(pasarelaComun1);
		algoDefense.agregarEnemigo(arania3);
		algoDefense.moverEnemigos();

		Arania arania4 = new Arania(pasarelaComun1);
		algoDefense.agregarEnemigo(arania4);
		algoDefense.moverEnemigos();

		assertEquals(pasarelaMeta.getCoordenada(), arania4.getPasarelaActual().getCoordenada());


	}
}
