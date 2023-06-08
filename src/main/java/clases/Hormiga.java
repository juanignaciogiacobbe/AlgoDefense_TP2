package clases;

public class Hormiga extends Enemigo{

    public Hormiga(ParcelaDePasarela pasarela) {
        this.creditos= 1;
        this.velocidad = 1;
        this.danio = 1;
        this.estadoDeVida = new EstadoVivo(1);
        this.pasarelaActual = pasarela;
        }

}
