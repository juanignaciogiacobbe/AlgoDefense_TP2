package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.defensas.DefensasVacias;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.mapa.Mapa;
import edu.fiuba.algo3.modelo.parcelas.Parcela;
import edu.fiuba.algo3.modelo.parcelas.ParcelaDePasarela;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaCaminar;
import edu.fiuba.algo3.modelo.parcelas.TerrenoNoAptoParaConstruir;

public class Lechuza implements Enemigo {

    private Atacante atacante;
    private Trasladable trasladable;

    private Daniable daniable;

    public Lechuza(ParcelaDePasarela pasarela) {
        this.atacante = new DestructorDeDefensas();
        this.trasladable = new VoladorEnL(5, pasarela);
        this.daniable = new Atacable(5);
    }

    @Override
    public void atacar(Jugador jugador) throws DefensasVacias {
        this.atacante.atacar(jugador);
    }
    public Parcela getPasarelaActual() {
        return trasladable.getPasarelaActual();
    }

    public void setPasarelaActual(ParcelaDePasarela pasarela) {
        this.trasladable.setPasarelaActual(pasarela);
    }

    public void mover(Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
        this.trasladable = trasladable.moverse(mapa);

    }

    @Override
    public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio) throws EnemigoFueraDeRango, EnemigoNoDaniable {
        this.daniable.recibirAtaque(parcelaDefensa, rangoAtaque, danio, this.trasladable.getPasarelaActual());
        if (this.daniable.getVida() < 3 ){
            this.trasladable = new VoladorEnRecta(5, this.getPasarelaActual());
        }

    }

}