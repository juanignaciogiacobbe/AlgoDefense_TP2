package clases;

public class PasarelaLargada extends ParcelaDePasarela {
    public PasarelaLargada(int abscisa, int ordenada) {
        super();
        this.coordenada = new Coordenada(abscisa, ordenada);
    }

    @Override
    boolean puedoConstruir(Defensa defensa) {
        return false;
    }
}
