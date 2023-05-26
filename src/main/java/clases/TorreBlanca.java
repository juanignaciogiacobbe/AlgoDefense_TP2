package clases;

public class TorreBlanca extends Defensa {

    private int turnosDeVida;
    private int costoConstruccion;
    private int danio;
    private int rangoAtaque;
    private int tiempoConstruccion;

    public TorreBlanca() {
        this.tiempoConstruccion = 1;
        this.rangoAtaque = 3;
        this.danio = 1;
        this.costoConstruccion = 10;
        this.turnosDeVida = 0;

    }
    /**/


    public int getCostoConstruccion() {
        return costoConstruccion;
    }

    public void sumarturno() {
        this.turnosDeVida += 1;
    }

    public boolean estaconstruida() {
        return turnosDeVida >= tiempoConstruccion;
    }



}


