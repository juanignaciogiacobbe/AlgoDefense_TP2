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


    public String finDelJuego() {
        if (mapa.obtenerCantidadEnemigosVivos() == 0) {
            return jugador1.getNombre();
        }

        int danio = calcularDanioTotal();
        if (jugador1.sobreviveConDanio(danio)) {
            return jugador1.getNombre();
        }

        return "Computadora";
    }

    public void moverEnemigos() {
        for (Enemigo enemigo: enemigos) {
            enemigo.mover(this.mapa);
        }
    }



    private int calcularDanioTotal() {
        return mapa.obtenerDanioMeta();
    }

    public void comenzarturno() {
        PasarelaLargada pasarelaLargada = new PasarelaLargada(0, 0);
        Enemigo enemigo = new Hormiga(pasarelaLargada);
        agregarEnemigo(enemigo);
        mapa.reiniciarEnemigosPasarelas();
        mapa.moverEnemigos(mapa.getOrigen());
    }


    public void agregarEnemigo(Enemigo enemigo) {

        mapa.getOrigen().agregarEnemigo(enemigo);
        this.enemigos.add(enemigo);
    }

    public int obtenersizeMeta() {
        return (mapa.getMeta().getEnemigos().size());
    }

}
