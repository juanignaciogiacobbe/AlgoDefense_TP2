package clases;

import Excepciones.CreditosInsuficientes;
import Excepciones.NombreInvalido;
import Excepciones.TerrenoNoAptoParaConstruir;

public class Jugador {
    private Creditos creditos;
    private Vida vida;

    private String nombre;


    public Jugador(String nombre) throws NombreInvalido {
        this.creditos = new Creditos(100);
        this.vida = new Vida(20);
        if (nombre.length() < 6) {
            throw new NombreInvalido();
        }
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

    public void construir(Defensa defensa, Parcela parcela) throws CreditosInsuficientes, TerrenoNoAptoParaConstruir {
        this.creditos.consumirPuntos(defensa.getCostoConstruccion());
        parcela.construir(defensa);
    }
    public boolean sobreviveConDanio(int danio) {
        return (this.vida.getVida() - danio > 0);
    }
}


