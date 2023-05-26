package clases;

public class Jugador {
    private int creditos;
    private int vida;


    public Jugador() {
        this.creditos = 100;
        this.vida = 20;
    }

    public boolean puedeconstruir(int costoConstruccion){
        return creditos >= costoConstruccion;
    }

    public int getCreditos() {
        return creditos;
    }


    public int getVida() {
        return vida;
    }


}


