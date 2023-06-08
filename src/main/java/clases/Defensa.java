package clases;

import Excepciones.EnemigoFueraDeRango;
import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TorreNoDesplegada;

import java.util.List;

abstract class Defensa {

    protected int costoConstruccion;

    protected int danio;
    protected int rangoAtaque;
    protected int turnosRestantesParaDespliegue;

    protected Desplegable desplegable;

    abstract boolean enemigoDentroDeRango(int distanciaAEnemigo);

    /*
    public void restarTurnoParaDespliegue() {
        turnosRestantesParaDespliegue -= 1;
        if (desplegable.puedeDesplegarse() ) {
            desplegable = new Despelgado();
        }
    }

     */

    public boolean estaDesplegada() {
        return (turnosRestantesParaDespliegue <= 0);
    }

    public int getCostoConstruccion() {
        return costoConstruccion;
    }


    public int getRangoAtaque() {
        return rangoAtaque;
    }

    public void atacarA(Enemigo enemigo) {
        enemigo.recibirDanio(danio);
    }

    public void atacar(List<Enemigo> enemigos, Parcela parcelaDefensa) throws TerrenoNoAptoParaCaminar, TorreNoDesplegada {
        desplegable.atacar(enemigos, parcelaDefensa, rangoAtaque, danio);
        if (desplegable.puedeDesplegarse()) {
            desplegable = new Despelgado();
        }
    }
}