package clases;

import Excepciones.EnemigoFueraDeRango;
import Excepciones.SinVidaRestante;
import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TerrenoNoAptoParaConstruir;

public abstract class Enemigo {
    protected EstadoVida estadoDeVida;

    protected int creditos;

    protected int velocidad;

    protected int danio;

    protected ParcelaDePasarela pasarelaActual;


    public void recibirDanio(int puntosARecibir) {
        try {
            this.estadoDeVida.recibirDanio(puntosARecibir);
        } catch (SinVidaRestante sinVidaRestante) {
            this.estadoDeVida = new EstadoMuerto();
        }
    }


    public void setPasarelaActual(ParcelaDePasarela pasarela) {
        this.pasarelaActual = pasarela;
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



    public void mover(Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar {
        ParcelaDePasarela parcelaaMover = this.pasarelaActual.mover(this.getVelocidad(), mapa);
        this.setPasarelaActual(parcelaaMover);

    }

    public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio) throws EnemigoFueraDeRango {
        if (!this.pasarelaActual.estaEnRango(parcelaDefensa, rangoAtaque)) {
            throw new EnemigoFueraDeRango();
        }
        this.recibirDanio(danio);
    }

    public int obtenerCreditos() {
        return this.creditos;
    }

    public int recolectarCreditos() {
        return this.estadoDeVida.recolectarCreditos(this.creditos);
    }

    public EstadoVida getEstadoDeVida() {
        return estadoDeVida;
    }
}
