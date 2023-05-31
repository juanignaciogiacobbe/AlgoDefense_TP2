package clases;

import java.util.Random;

public class Hormiga extends Enemigo{

    public Hormiga(PasarelaLargada pasarela) {
        //super();
        this.puntosEnergia = 2;
        this.creditos= 1;
        this.velocidad = 1;
        this.danio = 1;
        this.energia = new Vida(1);
        this.pasarelaActual = pasarela;
        }

    public int recibirDanio(int puntosARecibir) {
        energia.consumirPuntos(puntosARecibir);

        if (estaMuerto()) {
            return 1;
        }
        return 0;
    }
    public void mover(Mapa mapa) {
        ParcelaDePasarela pasarelaNueva = this.pasarelaActual.mover(1, this, mapa);
        this.pasarelaActual = pasarelaNueva;

    }

    }
