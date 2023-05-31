package clases;

import java.util.Random;

public class Arania extends Enemigo {
    public Arania(ParcelaDePasarela pasarela) {
        //super();
        this.puntosEnergia = 2;
        this.creditos = obtenerCreditosRandom();
        this.velocidad = 2;
        this.danio = 2;
        this.energia = new Vida(2);
        this.pasarelaActual = pasarela;
    }


    private int obtenerCreditosRandom() {
        Random random = new Random();
        return random.nextInt(11);
    }

    public int recibirDanio(int puntosARecibir) {
        energia.consumirPuntos(puntosARecibir);

        if (estaMuerto()) {
            return this.obtenerCreditosRandom();
        }
        return 0;
    }
}