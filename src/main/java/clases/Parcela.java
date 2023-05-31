package clases;

import java.util.ArrayList;
import java.util.List;

abstract class Parcela {

    protected Coordenada coordenada;

    protected List<Enemigo> enemigos;

    protected List<Enemigo> visitantes;

    protected Mapa mapa;


    public Parcela(/*Mapa mapa*/) {
        this.enemigos = new ArrayList<>();
        this.visitantes =new ArrayList<>();
        //this.mapa = mapa;
    }


    public List<Enemigo> getEnemigos() {
        return enemigos;
    }

    public List<Enemigo> getVisitantes() {
        return visitantes;
    }

    abstract boolean puedoConstruir(Defensa defensa);

    abstract boolean puedeMoverseAqui();

    public void agregarEnemigo(Enemigo enemigo){
        enemigos.add(enemigo);
        visitantes.add(enemigo);
    }

    public void eliminarEnemigo(Enemigo enemigo){
        enemigos.remove(enemigo);
    }

    public Coordenada getCoordenada() {
        return coordenada;
    }

    public boolean esVecino(Coordenada coordenada){
        double difrenciaAbsica = this.coordenada.getAbscisa() - coordenada.getAbscisa();
        double difrenciaOrdenada = this.coordenada.getOrdenada() - coordenada.getOrdenada();
        return  (difrenciaAbsica == 0 && Math.abs(difrenciaOrdenada) == 1) || (Math.abs(difrenciaAbsica) == 1 && difrenciaOrdenada == 0);
        }


    public boolean fueVisitado(Enemigo e){
        return( visitantes.contains(e));}

    abstract boolean puedeDefender();

    public boolean estaADistancia2(Coordenada coordenada) {
        double difrenciaAbsica = this.coordenada.getAbscisa() - coordenada.getAbscisa();
        double difrenciaOrdenada = this.coordenada.getOrdenada() - coordenada.getOrdenada();
        return  (difrenciaAbsica == 0 && Math.abs(difrenciaOrdenada) == 2) || (Math.abs(difrenciaAbsica) == 2 && difrenciaOrdenada == 0);
    }

    public boolean estaADistancia1(Coordenada coordenada) {
        double difrenciaAbsica = this.coordenada.getAbscisa() - coordenada.getAbscisa();
        double difrenciaOrdenada = this.coordenada.getOrdenada() - coordenada.getOrdenada();
        return  (difrenciaAbsica == 0 && Math.abs(difrenciaOrdenada) == 1) || (Math.abs(difrenciaAbsica) == 1 && difrenciaOrdenada == 0);
    }

}

