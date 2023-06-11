package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.Defensa;
import edu.fiuba.algo3.modelo.mapa.Coordenada;
import edu.fiuba.algo3.modelo.mapa.Mapa;

import java.util.ArrayList;
import java.util.List;

public abstract class Parcela {

	protected Coordenada coordenada;

	protected Mapa mapa;
	protected Construible construible;
	protected Movible movible;


	public abstract boolean puedeMoverseAqui();

	public Coordenada getCoordenada() {
		return coordenada;
	}


	public abstract void construir(Defensa defensa) throws TerrenoNoAptoParaConstruir;

	public abstract ParcelaDePasarela mover(int distancia, Mapa mapa) throws TerrenoNoAptoParaConstruir, TerrenoNoAptoParaCaminar;


	public boolean estaEnRango(Parcela parcelaDefensa, int rango) {
		return this.getCoordenada().estaDentroDelRango(parcelaDefensa.getCoordenada(), rango);
	}


	public void moverseA() throws TerrenoNoAptoParaCaminar {
		this.movible.moverseA();
	}

	public List<ParcelaDePasarela> vecinos(Mapa mapa, int rango) {
		List<ParcelaDePasarela> pasarelasEnRango = new ArrayList<>();
		for (Parcela parcela : mapa.getParcelas()) {
			int distancia = (parcela.getCoordenada()).distanciaHacia(this.getCoordenada());
			if (distancia <= rango) {
				try {
					parcela.moverseA();
					pasarelasEnRango.add((ParcelaDePasarela) parcela);
				} catch (TerrenoNoAptoParaCaminar e) {
				}
			}
		}
		return pasarelasEnRango;
	}
}

