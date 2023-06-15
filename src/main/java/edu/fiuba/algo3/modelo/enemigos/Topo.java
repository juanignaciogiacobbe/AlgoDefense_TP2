package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;

public class Topo implements Enemigo {
    private Atacante atacante;
    private Trasladable trasladable;

    private Daniable daniable;

    public Topo(ParcelaDePasarela pasarela) {
        this.trasladable = new BajoTierra(1, pasarela, 0);
        this.atacante = new AtacanteDeJugador(2);
        this.daniable = new NoDaniable();
    }

    public void atacar(Jugador jugador) throws DefensasVacias {
        this.atacante.atacar(jugador);
    }

    public boolean puedeMoverseA(Parcela parcela) {
        return (parcela.puedeMoverseAqui());
    }
    public ParcelaDePasarela getPasarelaActual() {
        return (ParcelaDePasarela) trasladable.getPasarelaActual();
    }

    public void setPasarelaActual(ParcelaDePasarela pasarela) {
        this.trasladable.setPasarelaActual(pasarela);
    }

    public void mover(Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
        this.trasladable = trasladable.moverse(mapa);
    }
    @Override
    public void recibirDanio(int puntosARecibir) {

    }
    @Override
    public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio) throws EnemigoFueraDeRango, EnemigoNoDaniable {

    }
}
