package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.CustomLogger;
import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.estados.EstadoVida;
import edu.fiuba.algo3.modelo.estados.EstadoVivo;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;
import javafx.scene.control.Dialog;

import java.util.Random;

public class Arania implements Enemigo {
    private Atacante atacante;
    private int creditos;
    private Trasladable trasladable;

    private Daniable daniable;

    private CustomLogger logger;

    public Arania(ParcelaDePasarela pasarela) {
        this.creditos = obtenerCreditosRandom();
        this.trasladable = new Caminante(2, pasarela);
        this.atacante = new AtacanteDeJugador(2);
        this.daniable = new Atacable(2);
        this.logger = CustomLogger.getInstance();
    }

    private int obtenerCreditosRandom() {
        Random random = new Random();
        return random.nextInt(11);
    }

    @Override
    public void atacar(Jugador jugador) throws DefensasVacias {
        logger.log(this.getNombre() + " llego a la meta ,Produce " + this.getDanio() + " puntos de daño");
        this.atacante.atacar(jugador);
    }

    public ParcelaDePasarela getPasarelaActual() {
        return (ParcelaDePasarela) trasladable.getPasarelaActual();
    }


    public void setPasarelaActual(ParcelaDePasarela pasarela) {
        this.trasladable.setPasarelaActual(pasarela);
    }

    public int getVida() {
        return daniable.getVida();
    }

    @Override
    public int getCreditos() {
        return this.creditos;
    }

    public void mover(Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
        this.trasladable = trasladable.moverse(mapa);
        logger.log(this.getNombre() + " se movio a la coordenada = (" + trasladable.getPasarelaActual().getCoordenada().getAbscisa() + ","
                + trasladable.getPasarelaActual().getCoordenada().getAbscisa() + ")");
    }

    public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio) throws EnemigoFueraDeRango, EnemigoNoDaniable {
        this.daniable.recibirAtaque(parcelaDefensa, rangoAtaque, danio, this.trasladable.getPasarelaActual());
    }

    @Override
    public String getNombre() {
        return "Arania";
    }

    @Override
    public int getDanio() {
        return this.atacante.getDanio();
    }

    @Override
    public String toString() {
        return "A";
    }
}
