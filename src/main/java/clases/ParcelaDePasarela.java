package clases;

abstract class ParcelaDePasarela extends Parcela{

    public boolean puedoConstruir(Defensa defensa) {
        return false;
    }

    public boolean puedeMoverseAqui() {
        return true;
    }

    public boolean puedeDefender(){
        return false;
    }

    public ParcelaDePasarela darSiguientePasarela() {
        return mapa.darSiguientePasarela(this);
    }
}
