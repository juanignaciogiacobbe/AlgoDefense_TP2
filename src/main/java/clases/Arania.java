package clases;

import java.util.Random;

public class Arania extends Enemigo {
    public Arania() {
        puntosEnergia = 2;
        this.creditos = obtenerCreditosRandom();
        this.velocidad = 2;
    }


    private int obtenerCreditosRandom() {
        Random random = new Random();
        return random.nextInt(11);
    }
}
