package clases;

abstract class Parcela {

    protected Coordenada coordenada;

    abstract boolean puedoConstruir(Defensa defensa);

    abstract boolean puedeMoverseAqui();
}
