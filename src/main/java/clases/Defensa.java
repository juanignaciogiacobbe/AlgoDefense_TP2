package clases;

import Excepciones.*;

import java.util.List;

abstract class Defensa {

    protected int costoConstruccion;

    protected int danio;
    protected int rangoAtaque;
    protected int turnosRestantesParaDespliegue;

    protected Desplegable desplegable;

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

    public void atacar(List<Enemigo> enemigos, Parcela parcelaDefensa) throws TerrenoNoAptoParaCaminar, TorreNoDesplegada, EnemigosFueraDeRango {
        desplegable.atacar(enemigos, parcelaDefensa, rangoAtaque, danio);
    }

    public void pasarTurno() {
        try {
            this.desplegable.pasarTurno();
        } catch (TorreDesplegada e) {
            this.desplegable = new Desplegado();
        }
    }

    public boolean enemigoDentroDeRango(int distanciaAEnemigo) {
        return (distanciaAEnemigo <= rangoAtaque);
    }
}