package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.estados.EstadoVida;
import edu.fiuba.algo3.modelo.estados.EstadoVivo;
import edu.fiuba.algo3.modelo.parcelas.Parcela;

public class Atacable implements Daniable {

    private EstadoVida estadoDeVida;

    public Atacable(int vidaInicial) {
        this.estadoDeVida = new EstadoVivo(vidaInicial);
    }

    private void recibirDanio(int puntosARecibir) {
        this.estadoDeVida = this.estadoDeVida.recibirDanio(puntosARecibir);
    }

    @Override
    public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio, Parcela parcelaActual) throws EnemigoFueraDeRango, EnemigoNoDaniable {
        if (!parcelaActual.estaEnRango(parcelaDefensa, rangoAtaque)) {
            throw new EnemigoFueraDeRango();
        }
        this.recibirDanio(danio);
    }
    public int getVida() {
        return this.estadoDeVida.getVida();
    }

}
