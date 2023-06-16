package edu.fiuba.algo3.modelo.parcelas;

import edu.fiuba.algo3.modelo.defensas.Torre;
import edu.fiuba.algo3.modelo.defensas.TrampaArenosa;

public class Edificable implements Construible {

	@Override
	public void construir(Torre defensaAConsrtruir, Parcela parcela) throws TerrenoNoAptoParaConstruir {
		parcela.setDefensa(defensaAConsrtruir);
	}

	@Override
	public void construir(TrampaArenosa defensaAConstruir, Parcela parcela) throws TerrenoNoAptoParaConstruir {
		throw new TerrenoNoAptoParaConstruir();

	}
}
