package clases;

import Excepciones.NombreInvalido;

import java.util.List;


public class AlgoDefense {

    private Jugador jugador1;

    private Mapa mapa;

    public AlgoDefense() {
        this.mapa = new Mapa();
    }

    public Mapa getMapa() {
        return mapa;
    }

    public void agregarJugador(String nombre) throws NombreInvalido {
        if (nombre.length() < 6) {
            throw new NombreInvalido();
        }
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



    private int calcularDanioTotal() {
        return mapa.obtenerDanioMeta();
    }

    public void comenzarturno() {
        Enemigo enemigo = new Hormiga();
        agregarEnemigo(enemigo);
        mapa.reiniciarEnemigosPasarelas();
        mapa.moverEnemigos(mapa.getOrigen());
    }


    public void agregarEnemigo(Enemigo enemigo) {
        mapa.getOrigen().agregarEnemigo(enemigo);
    }

    public int obtenersizeMeta() {
        return (mapa.getMeta().getEnemigos().size());
    }

}
