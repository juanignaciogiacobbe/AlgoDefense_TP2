package clases;

public class PasarelaMeta extends ParcelaDePasarela {
    public PasarelaMeta(int abscisa, int ordenada) {
        super();
        this.coordenada = new Coordenada(abscisa, ordenada);
    }

    @Override
    boolean puedoConstruir(Defensa defensa) {
        return false;
    }
}
