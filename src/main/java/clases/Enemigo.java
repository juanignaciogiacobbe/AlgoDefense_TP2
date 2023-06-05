package clases;

import Excepciones.SinVidaRestante;

public abstract class Enemigo {
    protected Vida energia;

    protected int creditos;

    protected int velocidad;

    protected int danio;

    protected ParcelaDePasarela pasarelaActual;


    public int recibirDanio(int puntosARecibir) {
        /*energia.consumirPuntos(puntosARecibir);

        if (estaMuerto()) {
            return 1;
        }
        return 0;
*/
        try {
            energia.consumirPuntos(puntosARecibir);
        } catch (SinVidaRestante sinVidaRestante) {
            return this.creditos;
        }
        return 0;
    }


    public void setPasarelaActual(ParcelaDePasarela pasarela) {
        this.pasarelaActual = pasarela;
    }

    public int getVida() {
        return this.energia.getVida();
    }

    public int getVelocidad() {
        return velocidad;
    }

    public boolean estaMuerto(){
        return this.getVida() <=0;
    }


    public boolean puedeMoverseA(Parcela parcela) {
        return (parcela.puedeMoverseAqui());
    }

    public int getDanio() { return this.danio; }

    public ParcelaDePasarela getPasarelaActual() {
        return pasarelaActual;
    }






    public void mover(Mapa mapa) {
        this.setPasarelaActual(this.pasarelaActual.mover(this.getVelocidad(), mapa));

    }
}
