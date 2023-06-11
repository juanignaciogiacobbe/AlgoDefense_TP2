package edu.fiuba.algo3.modelo.parcelas;


import edu.fiuba.algo3.modelo.AlgoDefense;
import edu.fiuba.algo3.modelo.enemigos.Enemigo;
import edu.fiuba.algo3.modelo.juego.Jugador;
import edu.fiuba.algo3.modelo.mapa.Coordenada;

import java.util.ArrayList;
import java.util.List;

public class PasarelaMeta extends ParcelaDePasarela {
	public PasarelaMeta(int abscisa, int ordenada) {
		super();
		this.coordenada = new Coordenada(abscisa, ordenada);
	}

	public List<Enemigo> actualizarEnemigos(List<Enemigo> enemigos, Jugador jugador) {
		List<Enemigo> nuevaLista = new ArrayList<>();
		int contador = 0;

		for (Enemigo enemigo : enemigos) {
			if (!enemigo.getPasarelaActual().getCoordenada().equals(this.getCoordenada())) {
				nuevaLista.add(enemigo);
			} else {
				//Enemigo atacar a jugador enemigo.atacar(Jugador)
				contador += enemigo.atacar(jugador);
				jugador.recibirdanio(enemigo.getDanio());
				enemigo.setPasarelaActual(this);
			}
		}
		this.actualizarDefensas(contador,jugador);


		return nuevaLista;
	}

	private void actualizarDefensas(int contador,Jugador jugador) {
		List<Parcela> nuevaLista = jugador.getDefensas();
		for(int i = 0 ; i < contador ; i++){
			nuevaLista.remove(i);
		}
		jugador.setDefensa(nuevaLista);
	}


}




