package clases;

import Excepciones.*;
import clases.vida.*;
import clases.*;

public abstract class Enemigo {
    //protected Estado estado;

    protected int creditos;

    protected int velocidad;

    protected int danio;

    protected ParcelaDePasarela pasarelaActual;

    protected Vida energia;


    public void recibirDanio(int puntosARecibir) {


        energia.consumirPuntos(puntosARecibir);
    }


    public void setPasarelaActual(ParcelaDePasarela pasarela) {
        this.pasarelaActual = pasarela;
    }

    public boolean tieneVidaIgualA(int vidaEsperada) {
        return (this.energia.getVida() == (vidaEsperada));
    }

    public int getVelocidad() {
        return velocidad;
    }


    public boolean puedeMoverseA(Parcela parcela) {
        return (parcela.puedeMoverseAqui());
    }

    public int getDanio() { return this.danio; }

    public ParcelaDePasarela getPasarelaActual() {
        return pasarelaActual;
    }

    //public int recolectarCreditos(int sumaActual) {
    //    return this.estado.recolectarCreditos(sumaActual, this.creditos);
    //}

    abstract int obtenerCreditos();


    public void mover(Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
        this.setPasarelaActual(this.pasarelaActual.mover(this.getVelocidad(), mapa));

    }

    public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio) throws EnemigoFueraDeRango {
        if (!this.pasarelaActual.estaEnRango(parcelaDefensa, rangoAtaque)) {
            throw new EnemigoFueraDeRango();
        }
        this.recibirDanio(danio);
    }
}
