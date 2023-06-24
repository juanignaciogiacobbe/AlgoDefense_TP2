package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.parcelas.Parcela;

import java.util.List;

public class NoDaniable implements Daniable {

    @Override
    public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio, Parcela parcelaActual) throws EnemigoFueraDeRango, EnemigoNoDaniable {
        throw new EnemigoNoDaniable();
    }

    public int recolectarCreditos(int creditos) {
        return 0;
    }

    @Override
    public int getVida() {
        return 0;
    }

    public List<Enemigo> actualizarLista(List<Enemigo> enemigos, Enemigo enemigo) {
        return enemigos;
    }
}
