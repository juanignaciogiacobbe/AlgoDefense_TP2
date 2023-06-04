package clases;

public abstract class Enemigo {
    protected int puntosEnergia;
    protected Vida energia;

    protected int creditos;

    protected int velocidad;

    protected int danio;

    protected ParcelaDePasarela pasarelaActual;


    abstract int recibirDanio(int puntosARecibir);


    public void setPasarelaActual(ParcelaDePasarela pasarela) {
        this.pasarelaActual = pasarela;
    }

    public int getVida() {
        return this.energia.getVida();
    }

    public int getVelocidad() {
        return velocidad;
    }

    public boolean estaMuerto(){
        return this.getVida() <=0;
    }


    public boolean puedeMoverseA(Parcela parcela) {
        return (parcela.puedeMoverseAqui());
    }

    public int getDanio() { return this.danio; }

    public ParcelaDePasarela getPasarelaActual() {
        return pasarelaActual;
    }






    public void mover(Mapa mapa) {
        this.setPasarelaActual(this.pasarelaActual.mover(this.getVelocidad(), mapa));

    }
}
