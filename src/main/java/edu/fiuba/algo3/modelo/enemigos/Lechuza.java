package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.estados.EstadoVida;
import edu.fiuba.algo3.modelo.estados.EstadoVivo;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;

public class Lechuza implements Enemigo {

    private EstadoVida estadoDeVida;
    private Atacante atacante;
    private Trasladable trasladable;
    public Lechuza(ParcelaDePasarela pasarela) {
        this.estadoDeVida = new EstadoVivo(5);
        this.atacante = new DestructorDeDefensas();
        this.trasladable = new Volador();
    }

    @Override
    public void atacar(Jugador jugador) {
        this.atacante.atacar(jugador);
    }

    public boolean puedeMoverseA(Parcela parcela) {
        return (parcela.puedeMoverseAqui());
    }

    public ParcelaDePasarela getPasarelaActual() {
        return (trasladable.getPasarelaActual());
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
    public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio) throws EnemigoFueraDeRango {
    }
}
