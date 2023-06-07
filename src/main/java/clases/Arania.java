package clases;

import clases.Enemigo;
import clases.vida.Vida;

import java.util.Random;

public class Arania extends Enemigo {
    public Arania(ParcelaDePasarela pasarela) {
        this.creditos = obtenerCreditosRandom();
        this.velocidad = 2;
        this.danio = 2;
       // this.estado = new EstadoVivo(2);
        this.pasarelaActual = pasarela;
        this.energia = new Vida(2);
    }


    private int obtenerCreditosRandom() {
        Random random = new Random();
        return random.nextInt(11);
    }

    public int obtenerCreditos() {
        return this.creditos;
    }
}
