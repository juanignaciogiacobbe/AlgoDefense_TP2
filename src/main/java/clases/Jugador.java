package clases;

public class Jugador {
    private Creditos creditos;
    private Vida vida;


    public Jugador() {
        this.creditos = new Creditos(100);
        this.vida = new Vida(20);
    }

    public int getCreditos() {
        return this.creditos.getCreditos();
    }

    public int getVida() {
        return this.vida.getVida();
    }

    public void agregarCreditos(int creditosRecibidos){
        this.creditos.agregarCreditos(creditosRecibidos);
    }

    public boolean construir(int creditosRequeridos) {
        return (this.creditos.consumirPuntos(creditosRequeridos));
    }


}


