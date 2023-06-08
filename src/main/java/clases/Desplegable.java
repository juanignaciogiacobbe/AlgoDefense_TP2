package clases;

import Excepciones.TerrenoNoAptoParaCaminar;
import Excepciones.TorreNoDesplegada;

import java.util.List;

public interface Desplegable {

     void atacar(List<Enemigo>enemigos, Parcela parcelaDefensa,int rangoAtaque , int danio) throws TerrenoNoAptoParaCaminar, TorreNoDesplegada;

    boolean puedeDesplegarse();
}
