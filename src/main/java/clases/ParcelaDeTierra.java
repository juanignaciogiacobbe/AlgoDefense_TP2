package clases;

import Excepciones.TerrenoNoAptoParaConstruir;

public class ParcelaDeTierra extends Parcela {


    private Defensa defensa;

    private boolean defendible;
    public ParcelaDeTierra(int abscisa, int ordenada ) {

        this.coordenada = new Coordenada(abscisa, ordenada);
        this.defendible= false;

    }
    public boolean puedoConstruir(Defensa defensa) {
        return true;
    }

    public boolean puedeMoverseAqui() {
        return false;
    }

    public void setDefensa(Defensa defensa) {
        this.defensa = defensa;
        defendible= true;

    }

    public boolean puedeDefender(){
        return isDefendible();
    }

    public boolean isDefendible() {
        return defendible;
    }


    public Defensa getDefensa() {
        return defensa;
    }

    public void construir(Defensa defensa) throws TerrenoNoAptoParaConstruir {
    }
}
