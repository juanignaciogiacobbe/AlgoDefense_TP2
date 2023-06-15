package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.parcelas.Parcela;

public class NoDaniable implements Daniable {

    @Override
    public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio, Parcela parcelaActual) throws EnemigoFueraDeRango, EnemigoNoDaniable {
        throw new EnemigoNoDaniable();
    }

    @Override
    public int getVida() {
        return 0;
    }
}
