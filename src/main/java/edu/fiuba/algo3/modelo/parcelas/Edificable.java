package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.Defensa;

public class Edificable implements Construible {

	@Override
	public void construir(Defensa defensaAConsrtruir, Parcela parcela) throws TerrenoNoAptoParaConstruir {
		if (defensaAConsrtruir instanceof TrampaArenosa){
			throw new TerrenoNoAptoParaConstruir();
		}
		((ParcelaDeTierra) parcela).setDefensa( defensaAConsrtruir);
	}
}
