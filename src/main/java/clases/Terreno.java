package clases;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

abstract class Terreno {
    private List<Enemigo> unidadesEnemigas;


    public Terreno() {
        this.unidadesEnemigas = new ArrayList<>();
    }

    public abstract boolean terrenoConstruible();



    public abstract boolean terrenoEsDesfilable();

    public void agregarEnemigos(Enemigo enemigo){
        unidadesEnemigas.add(enemigo);
    }

    public void eliminarEnemigo(Enemigo enemigo){
        unidadesEnemigas.remove(enemigo);
    }

    public List<Enemigo> getUnidadesEnemigas() {
        return unidadesEnemigas;
    }
}

