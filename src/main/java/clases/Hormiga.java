package clases;
import clases.Enemigo;
import clases.vida.Vida;

import java.util.Random;

public class Hormiga extends Enemigo {

    public Hormiga(ParcelaDePasarela pasarela) {
        this.creditos = 1;
        this.velocidad = 1;
        this.danio = 1;
        //this.estado = new EstadoVivo(1);
        this.pasarelaActual = pasarela;
        this.energia = new Vida(1);
        }

        public int obtenerCreditos() {
            return 1;
        }

}
