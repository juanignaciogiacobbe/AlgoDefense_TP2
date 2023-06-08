package clases;

import Excepciones.EnemigoFueraDeRango;
import Excepciones.EnemigosFueraDeRango;
import Excepciones.TorreDesplegada;

import java.util.List;

public class Desplegado implements Desplegable{
    public void atacar(List<Enemigo> enemigos, Parcela parcelaDefensa,int rangoAtaque,int danio) throws EnemigosFueraDeRango {
        for (Enemigo enemigo: enemigos) {
            try {
                enemigo.recibirAtaque(parcelaDefensa, rangoAtaque, danio);
                return;
            } catch (EnemigoFueraDeRango e) {}
        }
        throw new EnemigosFueraDeRango();
    }


    public void pasarTurno() throws TorreDesplegada {}
}

