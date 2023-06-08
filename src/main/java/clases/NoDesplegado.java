package clases;

import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TorreNoDesplegada;

import java.util.List;

public class NoDesplegado implements Desplegable{

    private int turnosRestantesParaDespliegue;

    public NoDesplegado(int turnosRestantesParaDespliegue) {
        this.turnosRestantesParaDespliegue = turnosRestantesParaDespliegue;
    }

    @Override
    public void atacar(List<Enemigo> enemigos, Parcela parcelaDefensa, int rangoAtaque, int danio) {
        try {
            this.turnosRestantesParaDespliegue -= 1;
            throw new TorreNoDesplegada();
        } catch (TorreNoDesplegada e) {
            System.out.println("Error: La torre no ha sido desplegada.");
        }


    }

    @Override
    public boolean puedeDesplegarse() {
        return (turnosRestantesParaDespliegue <= 0);
    }
}
