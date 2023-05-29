package clases;

public class ParcelaDeTierra extends Parcela {
    public ParcelaDeTierra(double abscisa, double ordenada) {
        this.coordenada = new Coordenada(abscisa, ordenada);
    }
    public boolean puedoConstruir(Defensa defensa) {
        return true;
    }

    public boolean puedeMoverseAqui() {
        return true;
    }
}
