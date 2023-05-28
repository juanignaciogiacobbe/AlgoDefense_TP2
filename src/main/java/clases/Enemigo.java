package clases;



abstract class Enemigo {
    protected int puntosEnergia;

    protected int creditos;

    protected int velocidad;


    public void recibirDanio(int puntosARecibir,Jugador jugador) {
        puntosEnergia = puntosEnergia - puntosARecibir;
        if (estaMuerto()){
            jugador.agregarCreditos(creditos);
        }
    }

    public int vida() {
        return puntosEnergia;
    }

    public boolean puedeCaminarEn(Terreno terreno){return terreno.terrenoEsDesfilable();}

    public boolean estaMuerto(){
        return puntosEnergia <=0;
    }

    public void mover(Terreno terrenoActual,Terreno terrenoFuturo){
        terrenoActual.eliminarEnemigo(this);
        terrenoFuturo.agregarEnemigos(this);
    }

}
