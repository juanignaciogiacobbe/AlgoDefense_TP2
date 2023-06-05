package clases;
import Excepciones.SinVidaRestante;

import java.util.Random;

public class Hormiga extends Enemigo{

    public Hormiga(ParcelaDePasarela pasarela) {
        this.creditos= 1;
        this.velocidad = 1;
        this.danio = 1;
        this.energia = new Vida(1);
        this.pasarelaActual = pasarela;
        }

}
