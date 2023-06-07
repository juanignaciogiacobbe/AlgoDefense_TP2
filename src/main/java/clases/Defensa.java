package clases;

import Excepciones.EnemigoFueraDeRango;
import clases.Enemigo;

import java.util.List;

abstract class Defensa {

     protected int costoConstruccion;

     protected int danio;
     protected int rangoAtaque;
    protected int turnosRestantesParaDespliegue;

    abstract boolean enemigoDentroDeRango(int distanciaAEnemigo);

    public void restarTurnoParaDespliegue() {
        turnosRestantesParaDespliegue -= 1;
    }

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

    public void atacar(List<Enemigo> enemigos, Parcela parcelaDefensa) {
        for (Enemigo enemigo: enemigos) {
            try {
                enemigo.recibirAtaque(parcelaDefensa, this.rangoAtaque, this.danio);
                return;
            } catch (EnemigoFueraDeRango e) {}
        }
    }
}
