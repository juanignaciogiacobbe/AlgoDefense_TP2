package clases;

import Excepciones.EnemigosFueraDeRango;
import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TorreDesplegada;
import Excepciones.TorreNoDesplegada;

import java.util.List;

public interface Desplegable {

     void atacar(List<Enemigo>enemigos, Parcela parcelaDefensa,int rangoAtaque , int danio) throws TerrenoNoAptoParaCaminar, TorreNoDesplegada, EnemigosFueraDeRango;

    void pasarTurno() throws TorreDesplegada;
}
