package clases;

abstract class Enemigo {
    int puntosEnergia;


    abstract void recibirDanio(int puntosARecibir);
    public int vida() {
        return puntosEnergia;
    }

}
