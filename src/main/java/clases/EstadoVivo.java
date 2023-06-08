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
        logger.log("Vida restante: " + this.vida);
        if (this.vida <= 0) {
            throw new SinVidaRestante();

        }
    }




}
