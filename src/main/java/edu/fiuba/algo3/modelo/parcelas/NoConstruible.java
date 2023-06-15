package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.Defensa;

public class NoConstruible implements Construible {
	@Override
	public void construir(Defensa defensaAConsrtruir, Parcela parcela) throws TerrenoNoAptoParaConstruir {
		throw new TerrenoNoAptoParaConstruir();
	}
}
