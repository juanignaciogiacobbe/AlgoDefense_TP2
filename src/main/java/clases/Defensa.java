package clases;

abstract class Defensa {

     protected int costoConstruccion;
     protected int danio;
     protected int rangoAtaque;
    protected int turnosRestantesParaDespliegue;

    abstract boolean enemigoDentroDeRango(int distanciaAEnemigo);

    public void restarTurnoParaDespliegue() {
        turnosRestantesParaDespliegue -= 1;
    }

    public boolean estaDesplegada() {
        return (turnosRestantesParaDespliegue <= 0);
    }
    public int getCostoConstruccion() {
        return costoConstruccion;
    }

    public int atacarA(Enemigo enemigo) {
       return enemigo.recibirDanio(danio);
    }

}
