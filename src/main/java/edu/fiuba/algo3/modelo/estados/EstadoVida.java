package edu.fiuba.algo3.modelo.estados;


public interface EstadoVida {

	EstadoVida recibirDanio(int danioARecibir);

	int getVida();

	int recolectarCreditos(int creditos);

}
