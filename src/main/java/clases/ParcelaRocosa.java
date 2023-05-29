package clases;

public class ParcelaRocosa extends Parcela {
    public ParcelaRocosa(double abscisa, double ordenada) {
        this.coordenada = new Coordenada(abscisa, ordenada);
    }
    public boolean puedoConstruir(Defensa defensa) {
        return false;
    }

    public boolean puedeMoverseAqui() {
        return true;
    }
}
