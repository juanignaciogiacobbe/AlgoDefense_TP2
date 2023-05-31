package clases;

import Excepciones.NombreInvalido;

import java.util.ArrayList;
import java.util.List;


public class AlgoDefense {

    private Jugador jugador1;

    private Mapa mapa;

    private List<Enemigo> enemigos;

    public AlgoDefense() {

        this.mapa = new Mapa();
        this.enemigos = new ArrayList<Enemigo>();
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void agregarJugador(String nombre) throws NombreInvalido {
        if (nombre.length() < 6) throw new NombreInvalido();
        ;
        jugador1 = new Jugador(nombre);
    }


    public int getEnemigosSize() {
        return enemigos.size();
    }

    public String finDelJuego() {
        if (getEnemigosSize() == 0) {
            return jugador1.getNombre();
        }

        int danio = calcularDanioTotal();
        if (jugador1.sobreviveConDanio(danio)) {
            return jugador1.getNombre();
        }

        return "Computadora";
    }

    public void moverEnemigos() {
        for (Enemigo enemigo : enemigos) {
            enemigo.mover(this.mapa);
        }
    }


    private int calcularDanioTotal() {
        int danio = 0;
        for (Enemigo enemigo : enemigos) {
            if (enemigo.getPasarelaActual() == mapa.getMeta()) {
                danio += enemigo.getDanio();
            }
        }
        return danio;
    }

    public void agregarEnemigo(Enemigo enemigo){
        enemigos.add(enemigo);
    }
    public int obtenersizeMeta() {
        int cantidadMeta = 0;
        for (Enemigo enemigo : enemigos) {
            if (enemigo.getPasarelaActual() == (ParcelaDePasarela) mapa.getMeta()) {
                cantidadMeta += 1;
            }
        }
        return cantidadMeta;
    }




}
