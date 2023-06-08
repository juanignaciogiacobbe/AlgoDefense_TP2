package clases;

import Excepciones.EnemigoFueraDeRango;

import java.util.List;

public class Despelgado implements Desplegable{
    public void atacar(List<Enemigo> enemigos, Parcela parcelaDefensa,int rangoAtaque,int danio){
        for (Enemigo enemigo: enemigos) {
            try {
                enemigo.recibirAtaque(parcelaDefensa, rangoAtaque, danio);
                return;
            } catch (EnemigoFueraDeRango e) {}
        }
    }

    @Override
    public boolean puedeDesplegarse() {
        return false;
    }
}

