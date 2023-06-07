package clases.vida;

public class EstadoMuerto implements EstadoVida {

    @Override
    public void comenzarTurno() throws SinVidaRestanteException {
        throw new SinVidaRestanteException();
    }
}
