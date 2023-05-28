package clases;



abstract class Enemigo {
    protected int puntosEnergia;
    protected Vida energia;

    protected int creditos;

    protected int velocidad;

    abstract int recibirDanio(int puntosARecibir);

    public int vida() {
        return puntosEnergia;
    }

    public int getVida() {
        return this.energia.getVida();
    }

    public boolean puedeCaminarEn(Terreno terreno){return terreno.terrenoEsDesfilable();}

    public boolean estaMuerto(){
        return this.getVida() <=0;
    }

    public void mover(Terreno terrenoActual,Terreno terrenoFuturo){
        terrenoActual.eliminarEnemigo(this);
        terrenoFuturo.agregarEnemigos(this);
    }

}
