package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.Defensa;

public class ConstruibleTrampa implements Construible {

	@Override
	public void construir(Defensa defensaAConsrtruir, Parcela parcela) throws TerrenoNoAptoParaConstruir {
		if (defensaAConsrtruir instanceof TrampaArenosa) {
			((PasarelaComun) parcela).setTrampaArenosa((TrampaArenosa) defensaAConsrtruir);
		} else {
			throw new TerrenoNoAptoParaConstruir();
		}
	}
}
