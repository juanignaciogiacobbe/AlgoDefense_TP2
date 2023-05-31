package clases;

public class Jugador {
    private Creditos creditos;
    private Vida vida;

    private String nombre;


    public Jugador(String nombre) {
        this.creditos = new Creditos(100);
        this.vida = new Vida(20);
        this.nombre = nombre;
    }

    public int getCreditos() {
        return this.creditos.getCreditos();
    }

    public int getVida() {
        return this.vida.getVida();
    }

    public String getNombre() { return this.nombre;}
    public void agregarCreditos(int creditosRecibidos){
        this.creditos.agregarCreditos(creditosRecibidos);
    }

    public boolean construir(int creditosRequeridos) {
        return (this.creditos.consumirPuntos(creditosRequeridos));
    }


    public boolean sobreviveConDanio(int danio) {
        return (this.vida.getVida() - danio > 0);
    }
}


