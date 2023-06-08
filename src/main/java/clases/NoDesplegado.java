package clases;

import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TorreDesplegada;
import Excepciones.TorreNoDesplegada;

import java.util.List;

public class NoDesplegado implements Desplegable{

    private int turnosRestantesParaDespliegue;

    public NoDesplegado(int turnosRestantesParaDespliegue) {
        this.turnosRestantesParaDespliegue = turnosRestantesParaDespliegue;
    }

    @Override
    public void atacar(List<Enemigo> enemigos, Parcela parcelaDefensa, int rangoAtaque, int danio) throws TorreNoDesplegada {
        throw new TorreNoDesplegada();


    }

    public void pasarTurno() throws TorreDesplegada {
        this.turnosRestantesParaDespliegue -= 1;
        if (this.turnosRestantesParaDespliegue == 0) {
            throw new TorreDesplegada();
        }
    }
}
