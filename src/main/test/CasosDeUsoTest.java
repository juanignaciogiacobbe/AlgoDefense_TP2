import Excepciones.NombreInvalido;
import clases.*;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

public class CasosDeUsoTest {

    @Test
    public void test01JugadorComienzaConVidaYCreditosCorrespondientes() {
        Jugador jugador = new Jugador("Mariana");
        int vidaEsperada = 20;
        int creditosEsperados = 100;
        assertEquals(vidaEsperada, jugador.getVida());
        assertEquals(creditosEsperados, jugador.getCreditos());
    }

    @Test
    public void test02DefensaPuedeSerUtilizadaLuegoDeCrearse() {
        TorrePlateada defensa = new TorrePlateada();

        defensa.restarTurnoParaDespliegue();
        assertFalse(defensa.estaDesplegada());
        defensa.restarTurnoParaDespliegue();
        assertTrue(defensa.estaDesplegada());
    }

    @Test
    public void test03VerificoQueJugadorPuedaConstruir() {
        Jugador jugador = new Jugador("Mariana");
        TorrePlateada torre = new TorrePlateada();
        int costoConstruccion = torre.getCostoConstruccion();
        boolean puedeConstruir = jugador.construir(costoConstruccion);
        assertTrue(puedeConstruir);
    }

    @Test
    public void test04VerificoQueSePuedeConstruirDefensasSoloSobreTierra() {
        TorrePlateada defensa = new TorrePlateada();
        ParcelaDeTierra tierra = new ParcelaDeTierra(1, 0);
        ParcelaRocosa rocoso = new ParcelaRocosa(0, 1);

        assertTrue(tierra.puedoConstruir(defensa));
        assertFalse(rocoso.puedoConstruir(defensa));
    }

    @Test
    public void test05VerificoQueLasDefensasAtaquenDentroDelRangoEsperado() {
        TorrePlateada torre = new TorrePlateada();
        int distanciaEnemigoDentroDeRango = 1;
        int distanciaEnemigoFueraDeRango = 7;


        assertTrue(torre.enemigoDentroDeRango(distanciaEnemigoDentroDeRango));
        assertFalse(torre.enemigoDentroDeRango(distanciaEnemigoFueraDeRango));
    }

    @Test
    public void test06VerificoQueLasUnidadesEnemigasSonDaniadasAcordeAlAtaqueRecibido() {
        PasarelaLargada parcela1 = new PasarelaLargada(1, 1);
        Arania arania = new Arania(parcela1);
        arania.recibirDanio(1);
        assertEquals(arania.getVida(), 1);
    }

    @Test
    public void test07EnemigosCaminanPorTerrenoValido() {
        PasarelaLargada parcela1 = new PasarelaLargada(1, 1);
        PasarelaMeta parcela2 = new PasarelaMeta(1, 2);
        ParcelaRocosa parcela3 = new ParcelaRocosa(1, 0);
        ParcelaRocosa parcela4 = new ParcelaRocosa(0, 1);
        ParcelaRocosa parcela5 = new ParcelaRocosa(2, 1);
        Arania arania = new Arania(parcela1);

        assertTrue(arania.puedeMoverseA(parcela2));
        assertTrue(arania.puedeMoverseA(parcela3));
        assertTrue(arania.puedeMoverseA(parcela4));
        assertTrue(arania.puedeMoverseA(parcela5));

    }

    @Test
    public void test08JugadorObtieneCreditosAlDestruirEnemigo() {
        Jugador jugador = new Jugador("pepito");
        TorrePlateada defensa = new TorrePlateada();
        ParcelaRocosa parcela = new ParcelaRocosa(1, 1);
        Hormiga hormiga = new Hormiga(parcela);
        int creditosObtenidos = defensa.atacarA(hormiga);
        jugador.agregarCreditos(creditosObtenidos);
        assertEquals(jugador.getCreditos(), 101);
    }

//    @Test
//    public void test09EnemigosSeMuevenPorMapa() {
//        Hormiga hormiga = new Hormiga();
//        Arania arania = new Arania();
//        Pasarela pasarela1 = new Pasarela();
//        pasarela1.agregarEnemigos(hormiga);
//        pasarela1.agregarEnemigos(arania);
//        Pasarela pasarela2 = new Pasarela();
//        Pasarela pasarela3 = new Pasarela();
//        //Se mueve 2 lugares debido a su velocidad
//        arania.mover(pasarela1, pasarela3);
//        //Se mueve 1 lugar debido a su velocidad
//        hormiga.mover(pasarela1, pasarela2);
//        assertEquals(pasarela1.getUnidadesEnemigas().size(), 0);
//        assertEquals(pasarela2.getUnidadesEnemigas().size(), 1);
//        assertEquals(pasarela3.getUnidadesEnemigas().size(), 1);

    @Test
    public void test10AlEliminarTodasLasUnidaesEnemigasGanaElJugador() {
        // instance an ArrayList with Hormiga inside
        ParcelaRocosa parcela = new ParcelaRocosa(1, 1);
        List<Enemigo> enemigos = Arrays.asList(new Hormiga(parcela), new Hormiga(parcela));
        AlgoDefense algoDefense = new AlgoDefense(enemigos);
        assertDoesNotThrow(() -> algoDefense.agregarJugador("Mariana"));
        algoDefense.destruirUnidadEnemiga();
        String ganador = algoDefense.finDelJuego();
        assertEquals(ganador, "Mariana");
    }

    @Test
    public void test11NoSeEliminanTodasLasUnidaesEnemigasPeroNoAlcanzaElDanioGanaElJugador() throws NombreInvalido {
        PasarelaLargada parcela1 = new PasarelaLargada(1, 1);
        Arania arania = new Arania(parcela1);
        List<Enemigo> unidadesEnemigas = new ArrayList<Enemigo>();
        unidadesEnemigas.add(arania);

        AlgoDefense algoDefense = new AlgoDefense(unidadesEnemigas);
        algoDefense.agregarJugador("Mariana");
        String ganador = algoDefense.finDelJuego();
        assertEquals(ganador, "Mariana");
    }

    @Test
    public void test12NoSeEliminanTodasLasUnidaesEnemigasPeroAlcanzaElDanioGanaLaComputadora() throws NombreInvalido {

        List<Enemigo> unidadesEnemigas = new ArrayList<Enemigo>();
        for(int i=0; i<10; i++) {
            PasarelaLargada parcela1 = new PasarelaLargada(1, 1);
            Arania arania = new Arania(parcela1);
            unidadesEnemigas.add(arania);
        }

        AlgoDefense algoDefense = new AlgoDefense(unidadesEnemigas);
        algoDefense.agregarJugador("Mariana");
        String ganador = algoDefense.finDelJuego();
        assertEquals(ganador, "Computadora");
    }
}
