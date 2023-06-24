package edu.fiuba.algo3.modelo.enemigos;

import edu.fiuba.algo3.modelo.parcelas.Parcela;

import java.util.List;

public interface Daniable {
    public void recibirAtaque(Parcela parcelaDefensa, int rangoAtaque, int danio, Parcela parcelaActual) throws EnemigoFueraDeRango, EnemigoNoDaniable;

    public int getVida();

    List<Enemigo> actualizarLista(List<Enemigo> enemigos, Enemigo enemigo);

    int recolectarCreditos(int creditos);
}
