package clases;


import Excepciones.SinVidaRestante;


public class EstadoVivo implements EstadoVida {
    private final CustomLogger logger = CustomLogger.getInstance();

    private int vida;

    public EstadoVivo(int vidaInicial) {
        this.vida = vidaInicial;
    }


    public int getVida() {
        return vida;
    }

    @Override
    public void recibirDanio(int danioARecibir) throws SinVidaRestante {
        this.vida -= danioARecibir;
        if (this.vida <= 0) {
            throw new SinVidaRestante();

        }
    }

    public int recolectarCreditos(int creditos) {
        return 0;
    }




}
