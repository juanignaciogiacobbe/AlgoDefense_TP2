package clases;

abstract class Defensa {

    private int tiempoConstruccion;
    private int costoConstruccion;
    private int danio;
    private int rangoAtaque;

    public boolean puedoConstruirSobre(Terreno terreno) {
        if (terreno.terrenoConstruible()) {
            return true;
        }
        return false;
    }

    abstract boolean enemigoDentroDeRango(int distanciaAEnemigo);
}
