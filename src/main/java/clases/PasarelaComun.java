package clases;

public class PasarelaComun extends ParcelaDePasarela {

    public PasarelaComun(int abscisa, int ordenada) {
        super();
        this.coordenada = new Coordenada(abscisa, ordenada);
    }

    @Override
    boolean puedoConstruir(Defensa defensa) {
        return false;
    }
}
