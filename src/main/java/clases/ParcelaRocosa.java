package clases;

public class ParcelaRocosa extends Parcela {
    public ParcelaRocosa(int abscisa, int ordenada) {

        this.coordenada = new Coordenada(abscisa, ordenada)
        ;
    }
    public boolean puedoConstruir(Defensa defensa) {
        return false;
    }

    public boolean puedeMoverseAqui() {
        return false;
    }

    public boolean puedeDefender(){
        return false;
    }
}
