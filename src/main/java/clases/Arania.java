package clases;

import java.util.Random;

public class Arania extends Enemigo {
    public Arania(ParcelaDePasarela pasarela) {
        this.creditos = obtenerCreditosRandom();
        this.velocidad = 2;
        this.danio = 2;
        this.estadoVida = new EstadoVivo(2);
        this.pasarelaActual = pasarela;
    }


    private int obtenerCreditosRandom() {
        Random random = new Random();
        return random.nextInt(11);
    }
}
