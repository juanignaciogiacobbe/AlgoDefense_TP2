package clases;

public abstract class Enemigo {
    protected int puntosEnergia;
    protected Vida energia;

    protected int creditos;

    protected int velocidad;

    protected int danio;

    protected Parcela parcelaActual;

    abstract int recibirDanio(int puntosARecibir);

    public int vida() {
        return puntosEnergia;
    }

    public int getVida() {
        return this.energia.getVida();
    }

    public boolean estaMuerto(){
        return this.getVida() <=0;
    }


    public boolean puedeMoverseA(Parcela parcela) {
        return (parcela.puedeMoverseAqui());
    }

    public int getDanio() { return this.danio; }
}
