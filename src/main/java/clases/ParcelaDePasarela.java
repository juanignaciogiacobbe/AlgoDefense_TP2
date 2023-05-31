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

    public ParcelaDePasarela mover(int distancia, Enemigo enemigo, Mapa mapa) {
        ParcelaDePasarela pasarelaNueva = null;
        if (distancia == 2) {
           pasarelaNueva = mapa.hallarParcelaVecinaCorrectaADistancia2(this);
        } else {
            pasarelaNueva = mapa.hallarParcelaVecinaCorrectaADistancia1(this);
        }
        pasarelaNueva.agregarEnemigo(enemigo);
        this.eliminarEnemigo(enemigo);
        return pasarelaNueva;
    }
}
