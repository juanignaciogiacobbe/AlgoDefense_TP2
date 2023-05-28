package clases;

abstract class Defensa {

     protected int tiempoConstruccion;
     protected int costoConstruccion;
     protected int danio;
     protected int rangoAtaque;
    protected int turnosDeVida;



    public boolean puedoConstruirSobre(Terreno terreno) {
        return terreno.terrenoConstruible();
    }

    abstract boolean enemigoDentroDeRango(int distanciaAEnemigo);
}
