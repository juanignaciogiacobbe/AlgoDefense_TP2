package edu.fiuba.algo3.modelo.estados;


import edu.fiuba.algo3.modelo.enemigos.Enemigo;

import java.util.List;

public interface EstadoVida {

	EstadoVida recibirDanio(int danioARecibir);

	int getVida();

	int recolectarCreditos(int creditos);

	public List<Enemigo> actualizarLista(List<Enemigo> enemigos, Enemigo enemigo);
}
