package clases;

import Excepciones.*;


import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private Creditos creditos;

    private EstadoVida estadoDeVida;

    private String nombre;

    private List<Parcela> defensas = new ArrayList<>();

    public Jugador(String nombre) throws NombreInvalido {
        this.creditos = new Creditos(100);
        //this.vida = new Vida(20);
        if (nombre.length() < 6) {
            throw new NombreInvalido();
        }
        this.nombre = nombre;
        this.estadoDeVida = new EstadoVivo(20);
    }

    public int getCreditos() {
        return this.creditos.getCreditos();
    }

    public int getVida() {
        return this.estadoDeVida.getVida();
    }

    public String getNombre() {
        return this.nombre;
    }

    public void agregarCreditos(int creditosRecibidos) {
        this.creditos.agregarCreditos(creditosRecibidos);
    }

    public void construir(Defensa defensa, Parcela parcela) throws CreditosInsuficientes, TerrenoNoAptoParaConstruir {
        this.creditos.consumirPuntos(defensa.getCostoConstruccion());
        try {
            parcela.construir(defensa);
            defensas.add(parcela);
        } catch (TerrenoNoAptoParaConstruir e) {
            this.creditos.agregarCreditos(defensa.getCostoConstruccion());
            throw new TerrenoNoAptoParaConstruir();
        }
    }

    /*
    public boolean sobreviveConDanio(int danio) {
        return (this.estadoDeVida.getVida() - danio > 0);
    }

    public void defender(List<Enemigo> enemigos) throws TerrenoNoAptoParDefender {
        for (Parcela defensa : this.defensas) {
            defensa.defender(enemigos);
        }
        for (Enemigo enemigo: enemigos) {
            this.agregarCreditos(enemigo.recolectarCreditos());
        }
    }



     */
    public void recibirdanio(int danio) {
        this.estadoDeVida = this.estadoDeVida.recibirDanio(danio);
    }

    public boolean estaMuerto(){
        return (estadoDeVida.getVida() <= 0 );
    }
}




