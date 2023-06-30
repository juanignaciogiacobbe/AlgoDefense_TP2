package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.CustomLogger;
import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.mapa.Coordenada;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;

import java.util.List;

public class Topo implements Enemigo {
    private Atacante atacante;
    private Trasladable trasladable;

    private Daniable daniable;

    private CustomLogger logger;

    public Topo(ParcelaDePasarela pasarela) {
        this.trasladable = new BajoTierra(1, pasarela, 0);
        this.atacante = new AtacanteDeJugador(2);
        this.daniable = new NoDaniable();
        this.logger = CustomLogger.getInstance();
    }

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

    public void mover(Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
        this.trasladable = trasladable.moverse(mapa);
        logger.log(this.getNombre() + " se movio a la coordenada = (" + trasladable.getPasarelaActual().getCoordenada().getAbscisa() + ","
            + trasladable.getPasarelaActual().getCoordenada().getAbscisa() + ")");
    }

    @Override
    public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio) throws EnemigoFueraDeRango, EnemigoNoDaniable {
        this.daniable.recibirAtaque(parcelaDefensa, rangoAtaque, danio, this.trasladable.getPasarelaActual());
        logger.log(this.getNombre() + " se movio a la coordenada = (" + trasladable.getPasarelaActual().getCoordenada().getAbscisa() + ","
                + trasladable.getPasarelaActual().getCoordenada().getAbscisa() + ")");
    }

    public List<Enemigo> actualizarLista(List<Enemigo> enemigos) {
        this.daniable.actualizarLista(enemigos, this);
        return enemigos;
    }

    public int recolectarCreditos() {
        return 0;
    }

    @Override
    public boolean seEncuentraEn(Coordenada coordenada) {
        return this.getPasarelaActual().getCoordenada().equals(coordenada);
    }

    @Override
    public String getNombre() {
        return "Topo";
    }

    @Override
    public int getDanio() {
        return this.atacante.getDanio();
    }

    @Override
    public int getVida() {
        return daniable.getVida();
    }

    @Override
    public int getCreditos() {
        return 3;
    }


    @Override
    public String toString() {
        return "TO";
    }
}
