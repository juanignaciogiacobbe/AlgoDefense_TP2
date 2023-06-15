package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.parcelas.Parcela;

public class NoDaniable implements Daniable {
    @Override
    public void recibirDanio(int puntosARecibir) {

    }

    @Override
    public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio) throws EnemigoFueraDeRango {

    }
}
